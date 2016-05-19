package com.book;

public class BookDAO {

	int bookid,no_of_pages;
	String name,author,review;

	public BookDAO() {
	
	}

	public BookDAO(int bookid, int no_of_pages, String name, String author, String review) {
		this.bookid = bookid;
		this.no_of_pages = no_of_pages;
		this.name = name;
		this.author = author;
		this.review = review;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public int getNo_of_pages() {
		return no_of_pages;
	}

	public void setNo_of_pages(int no_of_pages) {
		this.no_of_pages = no_of_pages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Book Id=" + bookid + ", No. of Pages=" + no_of_pages + ", Book Name=" + name + ", Author Name=" + author
				+ ", Book Review=" + review + "";
	}
	
	
	
	
}
