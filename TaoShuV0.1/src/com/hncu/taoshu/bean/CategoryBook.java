package com.hncu.taoshu.bean;


public class CategoryBook {
	private int category;
	private String categoryName;
	private String booksname;
	private String pictureUrl;
	private int count = 0;
	
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getBooksname() {
		return booksname;
	}
	public void setBooksname(String booksname) {
		this.booksname = booksname;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public CategoryBook(){
		
	}

	public CategoryBook(int category, String categoryName, String booksname,
			String pictureUrl, int count) {
		super();
		this.category = category;
		this.categoryName = categoryName;
		this.booksname = booksname;
		this.pictureUrl = pictureUrl;
		this.count = count;
	}
	@Override
	public String toString() {
		return "CategoryBook [category=" + category + ", booksname="
				+ booksname + ", pictureUrl=" + pictureUrl + ", count=" + count
				+ "]";
	}
	

	
}
