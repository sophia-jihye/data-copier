package sophia.copier.util;

public interface IConstants {

	String MAPPING_DELIMITER = "@";
	
	interface QUERY_PREFIX {
		String INSERT = "insert";
		String UPDATE = "update";
	}
	
	interface MYBATIS_CONFIG {
		String SOURCE = "source";
		String TARGET = "target";
	}

	// copier-config.properties 관련 상수
	interface CONFIG_PROPERTY {
		String LOG4J_PROP_PATH = "log4jPropPath";
		String MYBATIS_CONFIG_PATH = "mybatisConfigPath";

		String SOURCE_DB_DRIVER_CLASS_NAME = "sourceDbDriverClassName";
		String SOURCE_DB_URL = "sourceDbUrl";
		String SOURCE_DB_USERNAME = "sourceDbUsername";
		String SOURCE_DB_PASSWORD = "sourceDbPassword";

		String TARGET_DB_DRIVER_CLASS_NAME = "targetDbDriverClassName";
		String TARGET_DB_URL = "targetDbUrl";
		String TARGET_DB_USERNAME = "targetDbUsername";
		String TARGET_DB_PASSWORD = "targetDbPassword";

		String MAPPING_LIST = "mappingList";
		String FIRST_SEQ = "0";

		String COPIER_OPTION = "copier.option";
		String OPTION_ONE_TIME = "onetime";
		String OPTION_CONTINUE = "continue";
		String OPTION_OFF = "off";

		String COPIER_MIN_WAIT = "copier.minWait";
		String COPIER_MAX_WAIT = "copier.maxWait";
	}

}
