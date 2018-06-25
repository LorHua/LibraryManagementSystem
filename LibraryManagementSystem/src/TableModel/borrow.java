package TableModel;
import java.sql.Date;
public class borrow {
	private String borr_record;
	private String r_id;
	private String r_name;
	private String b_id;
	private String b_name;
	private String b_type;
	private double price;
	private Date borrow_date;
	private Date return_date;
	private boolean isreturn;
	private String b_admin;
	
	public String getBorr_record() {
		return borr_record;
	}
	public String getR_id() {
		return r_id;
	}
	public String getR_name() {
		return r_name;
	}
	public String getB_id() {
		return b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public String getB_type() {
		return b_type;
	}
	public double getPrice() {
		return price;
	}
	public Date getBorrow_date() {
		return borrow_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public boolean getIsreturn() {
		return isreturn;
	}
	public String getB_admin() {
		return b_admin;
	}
	public void setBorr_record(String borr_records) {
		this.borr_record =borr_records ;
	}
	public void setR_id(String id) {
		this.r_id = id;
	}
	public void setR_name(String name) {
		this.r_name = name;
	}
	public void setB_id(String id) {
		this.b_id = id;
	}
	public void setB_name(String name) {
		this.b_name = name;
	}
	public void setB_type(String type) {
		this.b_type = type;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setBorrow_date(Date date) {
		this.borrow_date = date;
	}
	public void setReturn_date(Date date) {
		this.return_date = date;
	}
	public void setIsreturn(boolean isreturn) {
		this.isreturn = isreturn;
	}
	public void setB_admin(String admin) {
		this.b_admin = admin;
	}
}
