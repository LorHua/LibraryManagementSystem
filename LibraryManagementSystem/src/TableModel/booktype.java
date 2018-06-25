package TableModel;

public class booktype {
	private String b_typename;
	private String code;
	private String keyword;
	
	public String getTypename() {
		return b_typename;
	}
	public String getTypeid() {
		return code;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setTypename(String typename) {
		this.b_typename = typename;
	}
	public void setTypeid(String code) {
		this.code = code;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
