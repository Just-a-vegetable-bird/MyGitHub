package com.hncu.taoshu.bean;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.hncu.taoshu.http.Response;


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
	
	public static Book constructBook(Response response) throws JSONException{
		Book book = new Book();
		System.out.println("response-->"+response.asString());
		
		JSONObject jsonObject = response.asJSONObject();
		
		book.setPUBDATE(jsonObject.getString("pubdate"));
		
		book.setPUBLISHER(jsonObject.getString("publisher"));
		book.setTITLE(jsonObject.getString("title"));
		JSONArray jsonArray = jsonObject.getJSONArray("author");
		String author = "";
		for (int i = 0; i < jsonArray.length(); i++) {
			author += jsonArray.getString(i);
		}
		book.setAUTHER(author);
		book.setISBN(jsonObject.getString("isbn13"));
		book.setSUMMARY(jsonObject.getString("summary"));
		final JSONObject image = jsonObject.getJSONObject("images");
		book.setMedium_IMAGE(image.getString("medium"));
		book.setSmall_IMAGE(image.getString("small"));
		book.setLarge_IMAGE(image.getString("large"));
		System.out.println(book.toString());
		return book;
		
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
