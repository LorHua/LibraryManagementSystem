package TableModel;
import java.sql.Date;
public class back {
	private String re_record;
	private String r_id;
	private String r_name;
	private String b_id;
	private String b_name;
	private String b_type;
	private double price;
	private Date borrow_date;
	private Date return_date;
	private double punish_fine;
	private String r_admin;
	
	public String getRe_record() {
		return re_record;
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
	public double getPunish_fine() {
		return punish_fine;
	}
	public String getR_admin() {
		return r_admin;
	}
	public void setRe_record(String re_record) {
		this.re_record = re_record;
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
	public void setPunish_fine(double fine) {
		this.punish_fine = fine;
	}
	public void setR_admin(String admin) {
		this.r_admin = admin;
	}
}
