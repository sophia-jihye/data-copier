package sophia.copier.dto;

import java.util.List;
import java.util.Properties;

public class ConfigDto {

	private String log4jPropPath;
	private String mybatisConfigPath;
	private Properties sourceDbProp;
	private Properties targetDbProp;
	private List<MappingDto> mappingDtoList;
	private Integer copierMinWait;
	private Integer copierMaxWait;

	private boolean sourceTargetSameConn = false;

	public ConfigDto() {
	}

	public String getLog4jPropPath() {
		return log4jPropPath;
	}

	public void setLog4jPropPath(String log4jPropPath) {
		this.log4jPropPath = log4jPropPath;
	}

	public String getMybatisConfigPath() {
		return mybatisConfigPath;
	}

	public void setMybatisConfigPath(String mybatisConfigPath) {
		this.mybatisConfigPath = mybatisConfigPath;
	}

	public Properties getSourceDbProp() {
		return sourceDbProp;
	}

	public void setSourceDbProp(Properties sourceDbProp) {
		this.sourceDbProp = sourceDbProp;
	}

	public Properties getTargetDbProp() {
		return targetDbProp;
	}

	public void setTargetDbProp(Properties targetDbProp) {
		this.targetDbProp = targetDbProp;
	}

	public List<MappingDto> getMappingDtoList() {
		return mappingDtoList;
	}

	public void setMappingDtoList(List<MappingDto> mappingDtoList) {
		this.mappingDtoList = mappingDtoList;
	}

	public Integer getCopierMinWait() {
		return copierMinWait;
	}

	public void setCopierMinWait(Integer copierMinWait) {
		this.copierMinWait = copierMinWait;
	}

	public Integer getCopierMaxWait() {
		return copierMaxWait;
	}

	public void setCopierMaxWait(Integer copierMaxWait) {
		this.copierMaxWait = copierMaxWait;
	}

	public boolean isSourceTargetSameConn() {
		return sourceTargetSameConn;
	}

	public void setSourceTargetSameConn(boolean sourceTargetSameConn) {
		this.sourceTargetSameConn = sourceTargetSameConn;
	}

}
