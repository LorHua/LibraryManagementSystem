package TableModel;

public class readertype {
	private String r_typename;
	private int r_type_id;
	private int canborrow_int;
	private int deadline;
	
	public String getTypename() {
		return r_typename;
	}
	public int getTypeid() {
		return r_type_id;
	}
	public int getCanborrowint() {
		return canborrow_int;
	}
	public int getDeadline() {
		return deadline;
	}
	public void setTypeid(int typeid) {
		this.r_type_id = typeid;
	}
	public void setTypename(String typename) {
		this.r_typename = typename;
	}
	
	public void setCanborrowint(int borrowint) {
		this.canborrow_int = borrowint;
	}
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
}
