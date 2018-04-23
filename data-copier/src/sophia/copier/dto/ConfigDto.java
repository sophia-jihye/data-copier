package sophia.copier.dto;

import java.util.List;

public class ConfigDto {

	private String log4jPropPath;
	private String mybatisConfigPath;
	private String sourceDbDriverClassName;
	private String sourceDbUrl;
	private String sourceDbUsername;
	private String sourceDbPassword;
	private String targetDbDriverClassName;
	private String targetDbUrl;
	private String targetDbUsername;
	private String targetDbPassword;
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

	public String getSourceDbDriverClassName() {
		return sourceDbDriverClassName;
	}

	public void setSourceDbDriverClassName(String sourceDbDriverClassName) {
		this.sourceDbDriverClassName = sourceDbDriverClassName;
	}

	public String getSourceDbUrl() {
		return sourceDbUrl;
	}

	public void setSourceDbUrl(String sourceDbUrl) {
		this.sourceDbUrl = sourceDbUrl;
	}

	public String getSourceDbUsername() {
		return sourceDbUsername;
	}

	public void setSourceDbUsername(String sourceDbUsername) {
		this.sourceDbUsername = sourceDbUsername;
	}

	public String getSourceDbPassword() {
		return sourceDbPassword;
	}

	public void setSourceDbPassword(String sourceDbPassword) {
		this.sourceDbPassword = sourceDbPassword;
	}

	public String getTargetDbDriverClassName() {
		return targetDbDriverClassName;
	}

	public void setTargetDbDriverClassName(String targetDbDriverClassName) {
		this.targetDbDriverClassName = targetDbDriverClassName;
	}

	public String getTargetDbUrl() {
		return targetDbUrl;
	}

	public void setTargetDbUrl(String targetDbUrl) {
		this.targetDbUrl = targetDbUrl;
	}

	public String getTargetDbUsername() {
		return targetDbUsername;
	}

	public void setTargetDbUsername(String targetDbUsername) {
		this.targetDbUsername = targetDbUsername;
	}

	public String getTargetDbPassword() {
		return targetDbPassword;
	}

	public void setTargetDbPassword(String targetDbPassword) {
		this.targetDbPassword = targetDbPassword;
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
