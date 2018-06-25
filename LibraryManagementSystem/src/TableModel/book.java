package TableModel;

import java.sql.Date;

public class book {
	//book(b_id,b_name,b_type,author,press,press_date,price,page_total
	//keyword,b_addtime,b_brief,isborrow)
	private String b_id;
	private String b_name;
	private String b_type;
	private String author;
	private String press;
	private Date press_date;
	private double price;
	private int page_total;
	private String keyword;
	private Date b_addtime;
	private String b_brief;
	private Boolean isborrow;
	
	public String getId() {
		return b_id;
	}
	public String getName() {
		return b_name;
	}
	public String getAuthor() {
		return author;
	}
	public String getType() {
		return b_type;
	}
	public String getPress() {
		return press;
	}
	public Date getPress_date() {
		return press_date;
	}
	public double getPrice() {
		return price;
	}
	public int getPage() {
		return page_total;
	}
	public String getKeryword() {
		return keyword;
	}
	public Date getAddtime() {
		return b_addtime;
	}
	public String getBrief() {
		return b_brief;
	}
	public boolean getIsborrow() {
		return isborrow;
	}
	
	public void setId(String id) {
		this.b_id = id;
	}
	public void setName(String name) {
		this.b_name = name;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setType(String type) {
		this.b_type = type;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public void setPress_date(Date pressdate) {
		this.press_date = pressdate;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setPagetotal(int page) {
		this.page_total = page;
	}
	public void setAddtime(Date addtime) {
		this.b_addtime = addtime;
	}
	public void setBrief(String brief) {
		this.b_brief = brief;
	}
	public void setKeryword(String keyword) {
		this.keyword = keyword;
	}
	public void setIsborrow(boolean borrow) {
		this.isborrow = borrow;
	}
}
