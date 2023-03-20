package com.dep.bean;

public class ArticleBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String artid;
	private String title;
	private String maincontent;
	private String authorid;
	private String categoryid;
	private String categoryname;
	private String createtime;
	private String updatetime;
	private String state;
	private String img;
	public String getArtid() {
		return artid;
	}
	public void setArtid(String artid) {
		this.artid = artid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMaincontent() {
		return maincontent;
	}
	public void setMaincontent(String maincontent) {
		this.maincontent = maincontent;
	}
	public String getAuthorid() {
		return authorid;
	}
	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
