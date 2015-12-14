package com.hanains.mysite.vo;

public class BoardVo {

	private Long no;
	private String title;
	private String content;
	private Long memberNo;
	private Long viewCount;
	private String regDate;
	
	
	
	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public BoardVo(String title, String content, Long memberNo) {
		super();
		this.title = title;
		this.content = content;
		this.memberNo = memberNo;
		
	}

	public BoardVo(Long no, String title, String content, Long memberNo,
			Long viewCount, String regDate) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.memberNo = memberNo;
		this.viewCount = viewCount;
		this.regDate = regDate;
	}
	
	public BoardVo(Long no, String title, String content, Long memberNo) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.memberNo = memberNo;
	}


	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public Long getViewCount() {
		return viewCount;
	}
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
