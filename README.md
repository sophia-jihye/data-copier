# data-copier
copies data from db to db

* Stpes
1. edit copier-config.properties file
		1) edit log4j-copier.properties file path
			* log4jPropPath 
		2) edit sourceDb information
			* sourceDbDriverClassName, sourceDbUrl, sourceDbUsername, sourceDbPassword
		3) edit targetDb information
			* targetDbDriverClassName, targetDbUrl, targetDbUsername, targetDbPassword	
		4) edit mappingList 
			* mappingList has a relationship with sophia/copier/dao/xml/custom*.xml and sophia/copier/dto/TempOutputDto
			* @ is a delimiter to distinguish source query id, source column name, target table name, and customized target column Name.
				: sourceQueryId @ columnName @ targetTableName @ columnName(customized)
			* As to customized target column name, refer to sophia/copier/dao/xml/custom*.xml
			* Be careful to type mappingList${SEQUENCE} correctly
		5) edit options
			* copier.option
				(1) onetime : 1회 실행 후 자동 종료 
				(2) continue  : 최소 대기 시간 (copier.minWait) 및 최대 대기 시간 (copier.maxWait)을 두고 계속 실행
				(3) off : bxt-copier.jar 실행 종료
					Ex. copier.minWait가 10분(=600000millisec), copier.maxWait가 30분(=1800000millisec)일 때, 
					Case1) DB 2 DB 작업이 총 7분 걸렸다면 -> 23분(maxWait - elapsedTime) 후 재실행 [주기: 30분 (maxWait)]
					Case2) DB 2 DB 작업이 총 17분 걸렸다면 -> 10분(minWait) 후 재실행 [주기: 10분 (minWait)]
			* copier.minWait
			* copier.maxWait	
	
2. edit log4j-copier.properties file
		1) edit log file path
			* log4j.appender.logfile.File
		2) edit other options if you need.
				
3. edit sophia/copier/dao/xml/custom*.xml	
		1) edit query
			* if you edited queryId, also edit the mappingList in copier-config.properties
			* if you edited columnName in customTargetDao.xml, also edit the mappingList in copier-config.properties and sophia/copier/dto/TempOutputDto			
									