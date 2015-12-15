package com.hanains.mysite.vo;

public class BoardInfo {

	private Long no;
	private String title;
	private Long memberNo;
	private String name;
	private Long viewCount;
	private String regDate;
	private int articleSequence;

	private Long groupNo;
	private Long orderNo;
	private Long depth;
	
	
	public Long getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Long getDepth() {
		return depth;
	}

	public void setDepth(Long depth) {
		this.depth = depth;
	}

	public int getArticleSequence() {
		return articleSequence;
	}

	public void setArticleSequence(int articleSequence) {
		this.articleSequence = articleSequence;
	}

	public BoardInfo(Long no, String title, Long memberNo, String name,
			Long viewCount, String regDate) {
		super();
		this.no = no;
		this.title = title;
		this.memberNo = memberNo;
		this.name = name;
		this.viewCount = viewCount;
		this.regDate = regDate;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "BoardInfo [no=" + no + ", title=" + title + ", memberNo="
				+ memberNo + ", name=" + name + ", viewCount=" + viewCount
				+ ", regDate=" + regDate + ", articleSequence="
				+ articleSequence + ", groupNo=" + groupNo + ", orderNo="
				+ orderNo + ", depth=" + depth + "]";
	}
	
	
}
