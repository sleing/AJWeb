package org.ddd.serialize.example15;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Book {
	private String title;
	private Integer pageCount;
	private boolean published;
	private Double price;
	private String publisher;
	private Date publishDate;
	private List<String> authors;
	private List<Chapter> chapters;
	
	public Book() {
		super();
	}
	public Book(String title, Integer pageCount, boolean published, Double price, String publisher,
			Date publishDate,List<String> authors, List<Chapter> chapters) {
		super();
		this.title = title;
		this.pageCount = pageCount;
		this.published = published;
		this.price = price;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.authors = authors;
		this.chapters = chapters;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
}
