package com.hncu.taoshu.bean;

import java.io.Serializable;


public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String small_IMAGE, large_IMAGE, medium_IMAGE;
	private String ISBN, TITLE, PRICE, PUBLISHER, PUBDATE, AUTHER, 
			SUMMARY;
	private int Category;
	
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Book [small_IMAGE=" + small_IMAGE + ", large_IMAGE="
				+ large_IMAGE + ", medium_IMAGE=" + medium_IMAGE + ", ISBN="
				+ ISBN + ", TITLE=" + TITLE + ", PRICE=" + PRICE
				+ ", PUBLISHER=" + PUBLISHER + ", PUBDATE=" + PUBDATE
				+ ", AUTHER=" + AUTHER + ", SUMMARY=" + SUMMARY + ", Category="
				+ Category + "]";
	}

	public Book(String small_IMAGE, String large_IMAGE,
			String medium_IMAGE, String iSBN, String tITLE, String pRICE,
			String pUBLISHER, String pUBDATE, String aUTHER, String sUMMARY,
			int category) {
		super();
		this.small_IMAGE = small_IMAGE;
		this.large_IMAGE = large_IMAGE;
		this.medium_IMAGE = medium_IMAGE;
		ISBN = iSBN;
		TITLE = tITLE;
		PRICE = pRICE;
		PUBLISHER = pUBLISHER;
		PUBDATE = pUBDATE;
		AUTHER = aUTHER;
		SUMMARY = sUMMARY;
		Category = category;
	}
	public String getSmall_IMAGE() {
		return small_IMAGE;
	}
	public void setSmall_IMAGE(String small_IMAGE) {
		this.small_IMAGE = small_IMAGE;
	}
	public String getLarge_IMAGE() {
		return large_IMAGE;
	}
	public void setLarge_IMAGE(String large_IMAGE) {
		this.large_IMAGE = large_IMAGE;
	}
	public String getMedium_IMAGE() {
		return medium_IMAGE;
	}
	public void setMedium_IMAGE(String medium_IMAGE) {
		this.medium_IMAGE = medium_IMAGE;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getPRICE() {
		return PRICE;
	}
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
	public String getPUBLISHER() {
		return PUBLISHER;
	}
	public void setPUBLISHER(String pUBLISHER) {
		PUBLISHER = pUBLISHER;
	}
	public String getPUBDATE() {
		return PUBDATE;
	}
	public void setPUBDATE(String pUBDATE) {
		PUBDATE = pUBDATE;
	}
	public String getAUTHER() {
		return AUTHER;
	}
	public void setAUTHER(String aUTHER) {
		AUTHER = aUTHER;
	}
	public String getSUMMARY() {
		return SUMMARY;
	}
	public void setSUMMARY(String sUMMARY) {
		SUMMARY = sUMMARY;
	}
	public int getCategory() {
		return Category;
	}
	public void setCategory(int category) {
		Category = category;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
