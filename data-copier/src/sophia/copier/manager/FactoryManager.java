package sophia.copier.manager;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sophia.copier.dto.MappingDto;
import sophia.copier.dto.TempOutputDto;
import sophia.copier.util.IConstants;

public class FactoryManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final FactoryManager instance = new FactoryManager();

	private FactoryManager() {
	}

	public static FactoryManager instance() {
		return instance;
	}

	private SqlSessionFactory sourceSqlSessionFactory;
	private SqlSessionFactory targetSqlSessionFactory;

	public SqlSessionFactory getSourceSessionFactory() {
		Reader reader = null;
		if (sourceSqlSessionFactory == null) {
			if (ConfigManager.instance().getConfigDto().isSourceTargetSameConn() == true
					&& targetSqlSessionFactory != null) {
				sourceSqlSessionFactory = targetSqlSessionFactory;
			} else {
				try {
					reader = Resources
							.getResourceAsReader(ConfigManager.instance().getConfigDto().getMybatisConfigPath());
				} catch (IOException e) {
					logger.error("[COPIER] IOException occurred while reading mybatis-config.xml", e);
				}

				sourceSqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, IConstants.MYBATIS_CONFIG.SOURCE,
						ConfigManager.instance().getConfigDto().getSourceDbProp());
			}
		}

		close(reader);
		return sourceSqlSessionFactory;
	}

	public SqlSessionFactory getTargetSessionFactory() {
		Reader reader = null;
		if (targetSqlSessionFactory == null) {
			if (ConfigManager.instance().getConfigDto().isSourceTargetSameConn() == true
					&& sourceSqlSessionFactory != null) {
				targetSqlSessionFactory = sourceSqlSessionFactory;
			} else {
				try {
					reader = Resources
							.getResourceAsReader(ConfigManager.instance().getConfigDto().getMybatisConfigPath());
				} catch (IOException e) {
					logger.error("[COPIER] IOException occurred while reading mybatis-config.xml", e);
				}

				targetSqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, IConstants.MYBATIS_CONFIG.TARGET,
						ConfigManager.instance().getConfigDto().getTargetDbProp());
			}
		}

		close(reader);
		return targetSqlSessionFactory;
	}

	public List<TempOutputDto> selectFromSource(MappingDto mappingDto) {
		SqlSession sourceSession = getSourceSessionFactory().openSession();

		List<TempOutputDto> sourceOutputList = null;
		try {
			sourceOutputList = sourceSession.selectList(mappingDto.getSourceQueryId(), mappingDto);
		} catch (Exception e) {
			logger.error("[COPIER] Error occurred while executing query. (Failed to select list from sourceTable)", e);
		} finally {
			sourceSession.close();
		}

		return sourceOutputList;
	}

	public Integer insertTarget(String targetQueryId, TempOutputDto sourceOutput) {
		SqlSession targetSession = getTargetSessionFactory().openSession();

		Integer insertCount = null;
		try {
			insertCount = targetSession.insert(targetQueryId, sourceOutput);
			targetSession.commit();
		} catch (Exception e) {
			targetSession.rollback();
			logger.error("[COPIER] Error occurred while executing query. (Failed to insert into targetTable)", e);
		} finally {
			targetSession.close();
		}

		return insertCount;
	}
	
	public Integer updateTarget(String targetQueryId, TempOutputDto sourceOutput) {
		SqlSession targetSession = getTargetSessionFactory().openSession();

		Integer updateCount = null;
		try {
			updateCount = targetSession.update(targetQueryId, sourceOutput);
			targetSession.commit();
		} catch (Exception e) {
			targetSession.rollback();
			logger.error("[COPIER] Error occurred while executing query. (Failed to update targetTable)", e);
		} finally {
			targetSession.close();
		}

		return updateCount;
	}

	private void close(Reader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				logger.error("[COPIER] IOException occurred while closeing reader");
				e.printStackTrace();
			}
		}
	}

}
