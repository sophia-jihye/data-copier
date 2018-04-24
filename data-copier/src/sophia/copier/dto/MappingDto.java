package sophia.copier.dto;

import java.util.List;

public class MappingDto {

	private String sourceQueryId;
	private List<String> sourceColumnList;
	private String targetTableName;
	private List<String> targetColumnList;

	public String getSourceQueryId() {
		return sourceQueryId;
	}

	public void setSourceQueryId(String sourceQueryId) {
		this.sourceQueryId = sourceQueryId;
	}

	public List<String> getSourceColumnList() {
		return sourceColumnList;
	}

	public void setSourceColumnList(List<String> sourceColumnList) {
		this.sourceColumnList = sourceColumnList;
	}

	public String getTargetTableName() {
		return targetTableName;
	}

	public void setTargetTableName(String targetTableName) {
		this.targetTableName = targetTableName;
	}

	public List<String> getTargetColumnList() {
		return targetColumnList;
	}

	public void setTargetColumnList(List<String> targetColumnList) {
		this.targetColumnList = targetColumnList;
	}

	@Override
	public String toString() {
		return "MappingDto [sourceQueryId=" + sourceQueryId + ", sourceColumnList=" + sourceColumnList
				+ ", targetTableName=" + targetTableName + ", targetColumnList=" + targetColumnList + "]";
	}

}
