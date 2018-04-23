package sophia.copier.manager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sophia.copier.constant.IConstants;
import sophia.copier.dto.ConfigDto;
import sophia.copier.dto.MappingDto;
import sophia.copier.util.CopierUtil;

public class ConfigManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final ConfigManager instance = new ConfigManager();

	private ConfigManager() {
	}

	private ConfigDto configDto;

	public static ConfigManager instance() {
		return instance;
	}

	public ConfigDto getConfigDto() {
		return configDto;
	}

	public Properties loadProp(String path) {
		Properties prop = new Properties();
		try {
			prop.load(new BufferedInputStream(new FileInputStream(new File(path))));
		} catch (FileNotFoundException e) {
			logger.error("[COPIER] Failed to find {}", path, e);
		} catch (IOException e) {
			logger.error("[COPIER] IOException occurred while reading {}", path, e);
		}

		prop2Dto(prop);
		return prop;
	}

	public void prop2Dto(Properties prop) {
		configDto.setLog4jPropPath(prop.getProperty(IConstants.CONFIG_PROPERTY.LOG4J_PROP_PATH));
		configDto.setMybatisConfigPath(prop.getProperty(IConstants.CONFIG_PROPERTY.MYBATIS_CONFIG_PATH));
		configDto.setSourceDbDriverClassName(prop.getProperty(IConstants.CONFIG_PROPERTY.SOURCE_DB_DRIVER_CLASS_NAME));
		configDto.setSourceDbUrl(prop.getProperty(IConstants.CONFIG_PROPERTY.SOURCE_DB_URL));
		configDto.setSourceDbUsername(prop.getProperty(IConstants.CONFIG_PROPERTY.SOURCE_DB_USERNAME));
		configDto.setSourceDbPassword(prop.getProperty(IConstants.CONFIG_PROPERTY.SOURCE_DB_PASSWORD));
		configDto.setTargetDbDriverClassName(prop.getProperty(IConstants.CONFIG_PROPERTY.TARGET_DB_DRIVER_CLASS_NAME));
		configDto.setTargetDbUrl(prop.getProperty(IConstants.CONFIG_PROPERTY.TARGET_DB_URL));
		configDto.setTargetDbUsername(prop.getProperty(IConstants.CONFIG_PROPERTY.TARGET_DB_USERNAME));
		configDto.setTargetDbPassword(prop.getProperty(IConstants.CONFIG_PROPERTY.TARGET_DB_PASSWORD));

		convertToMappingDtoList(prop);

		String minWait = prop.getProperty(IConstants.CONFIG_PROPERTY.COPIER_MIN_WAIT);
		if (CopierUtil.instance().isNumber(minWait)) {
			configDto.setCopierMinWait(Integer.parseInt(minWait));
		}

		String maxWait = prop.getProperty(IConstants.CONFIG_PROPERTY.COPIER_MIN_WAIT);
		if (CopierUtil.instance().isNumber(maxWait)) {
			configDto.setCopierMaxWait(Integer.parseInt(maxWait));
		}

	}

	private void convertToMappingDtoList(Properties prop) {

		List<MappingDto> mappingDtoList = new ArrayList<MappingDto>();

		// 1. prop에서 mappingList 읽어오기
		String prefix = IConstants.CONFIG_PROPERTY.MAPPING_LIST;
		String seq = IConstants.CONFIG_PROPERTY.FIRST_SEQ;
		List<String> mappingList = new ArrayList<String>();
		String mapping = null;
		while ((mapping = prop.getProperty(prefix + seq)) != null) {
			mappingList.add(mapping);
			seq = CopierUtil.instance().getNextNumberString(seq);
		}

		// 2. mappingList -> tempMap
		// sourceQueryId를 key로 사용하여 데이터를 분류하기 위해 tempMap을 만듦
		Map<String, MappingDto> tempMap = new HashMap<String, MappingDto>();
		String DELIMITER = IConstants.MAPPING_DELIMITER;
		for (int i = 0; i < mappingList.size(); i++) {
			String[] strArr = mappingList.get(i).split(DELIMITER);
			String sourceQueryId = strArr[0];
			String sourceColumn = strArr[1];
			String targetQueryId = strArr[2];
			String targetColumn = strArr[3];

			MappingDto tempMappingDto = null;
			if ((tempMappingDto = tempMap.get(sourceQueryId)) == null) {
				MappingDto dto = new MappingDto();

				dto.setSourceQueryId(sourceQueryId);
				dto.setTargetQueryId(targetQueryId);

				List<String> sourceColumnList = new ArrayList<String>();
				sourceColumnList.add(sourceColumn);
				List<String> targetColumnList = new ArrayList<String>();
				targetColumnList.add(targetColumn);

				tempMap.put(sourceQueryId, dto);

			} else {
				// sourceQueryId와 targetQueryId가 일대일 맵핑이 되리라는 전제 하에 아래 로직을 작성함.
				tempMappingDto.getSourceColumnList().add(sourceColumn);
				tempMappingDto.getTargetColumnList().add(targetColumn);
			}
		}

		// 3. tempMap -> mappingDtoList
		Set<String> keys = tempMap.keySet();
		for (String key : keys) {
			MappingDto mappingDto = tempMap.get(key);
			mappingDtoList.add(mappingDto);
		}

		configDto.setMappingDtoList(mappingDtoList);
	}

	public void handleConfig() {

		// log4j.properties
		PropertyConfigurator.configure(configDto.getLog4jPropPath());

		// check whether source db connection and target db connection can use
		// only one connection.
		if (configDto.getSourceDbUrl().equalsIgnoreCase(configDto.getTargetDbUrl())
				&& configDto.getSourceDbUsername().equalsIgnoreCase(configDto.getTargetDbUsername())) {
			configDto.setSourceTargetSameConn(true);
		}

	}
}
