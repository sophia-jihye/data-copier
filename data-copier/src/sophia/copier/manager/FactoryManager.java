package sophia.copier.manager;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
