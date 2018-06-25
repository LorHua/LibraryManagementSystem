package TableModel;
import java.sql.Date;
public class reader {
	//reader(r_id,r_name,r_sex,r_type,r_unit,r_address,r_tel,r_email,r_addtime,r_brief)
	private int r_id;
	private String r_name;
	private String r_sex;
	private String r_type;
	private String r_unit;
	private String r_address;
	private String r_tel;
	private String r_email;
	private Date r_addtime;
	private String r_brief;
	
	public int getId() {
		return r_id;
	}
	public String getName() {
		return r_name;
	}
	public String getSex() {
		return r_sex;
	}
	public String getType() {
		return r_type;
	}
	public String getUnit() {
		return r_unit;
	}
	public String getAddress() {
		return r_address;
	}
	public String getTel() {
		return r_tel;
	}
	public String getEmail() {
		return r_email;
	}
	public Date getAddtime() {
		return r_addtime;
	}
	public String getBrief() {
		return r_brief;
	}
	
	public void setId(int id) {
		this.r_id = id;
	}
	public void setName(String name) {
		this.r_name = name;
	}
	public void setSex(String sex) {
		this.r_sex = sex;
	}
	public void setType(String type) {
		this.r_type = type;
	}
	public void setUnit(String unit) {
		this.r_unit = unit;
	}
	public void setAddress(String address) {
		this.r_address = address;
	}
	public void setTel(String tel) {
		this.r_tel = tel;
	}
	public void setEmail(String email) {
		this.r_email = email;
	}
	public void setAddtime(Date addtime) {
		this.r_addtime = addtime;
	}
	public void setBrief(String brief) {
		this.r_brief = brief;
	}
}
