package com.nts.pjt3.dto;

public class DisplayInfoImage {

	private int id;
	private int displayInfoId;
	private int fileId;
	private int deleteFlag;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private String createDate;
	private String modifyDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return String.format(
			"DisplayInfoImage [id=%s, displayInfoId=%s, fileId=%s, deleteFlag=%s, fileName=%s, saveFileName=%s, contentType=%s, createDate=%s, modifyDate=%s]",
			id, displayInfoId, fileId, deleteFlag, fileName, saveFileName, contentType, createDate, modifyDate);
	}

}
