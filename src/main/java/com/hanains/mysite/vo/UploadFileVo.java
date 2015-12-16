package com.hanains.mysite.vo;

public class UploadFileVo {

	private long no;
	private long boardNo;
	private String fileName;
	private String originFileName;
	private String mineType;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(long boardNo) {
		this.boardNo = boardNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public String getMineType() {
		return mineType;
	}
	public void setMineType(String mineType) {
		this.mineType = mineType;
	}
	
	
}
