package com.example.lance.news;

public class News {
	
	private Integer timelength;
	private Integer id;
	private String title;
	
	
	public News(Integer timelength, String title, Integer id) {
		super();
		this.timelength = timelength;
		this.id = id;
		this.title = title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getTimelength() {
		return timelength;
	}
	public void setTimelength(Integer timelength) {
		this.timelength = timelength;
	}
	
	public News() {
		super();
	}


	
	

}
