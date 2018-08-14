package com.nts.pjt3.dto;

public class ReservationUserCommentImage {
	private int id;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private int fileId;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private int deleteFlag;
	private String createDate;
	private String modifyDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(int reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
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

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
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
			"ReservationUserCommentImage [id=%s, reservationInfoId=%s, reservationUserCommentId=%s, fileId=%s, fileName=%s, saveFileName=%s, contentType=%s, deleteFlag=%s, createDate=%s, modifyDate=%s]",
			id, reservationInfoId, reservationUserCommentId, fileId, fileName, saveFileName, contentType, deleteFlag,
			createDate, modifyDate);
	}
}
