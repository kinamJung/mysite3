package com.hanains.mysite.vo;

public class BoardInfo {

	private Long no;
	private String title;
	private Long memberNo;
	private String name;
	private Long viewCount;
	private String date;
	private int articleSequence;

	public int getArticleSequence() {
		return articleSequence;
	}

	public void setArticleSequence(int articleSequence) {
		this.articleSequence = articleSequence;
	}

	public BoardInfo(Long no, String title, Long memberNo, String name,
			Long viewCount, String date) {
		super();
		this.no = no;
		this.title = title;
		this.memberNo = memberNo;
		this.name = name;
		this.viewCount = viewCount;
		this.date = date;
	}

	public BoardInfo() {
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

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
