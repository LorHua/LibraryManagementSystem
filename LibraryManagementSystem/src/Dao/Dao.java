package Dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import TableModel.fine;
import TableModel.borrow;
import TableModel.booktype;
import TableModel.book;
import TableModel.back;
import TableModel.user_;
import TableModel.reader;
import TableModel.readertype;
/*import com.user;
import com.reader;
import com.book;
import com.reader_type;
import com.book_type;
import com.borrow;
import com.back;
import com.fine;*/


public class Dao {
	private static Connection dbConn=null;
	private Dao(){
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=LibraryManagementSystem";
		  String userName="ljh";
		  String userPwd="0748";
		  try
		  {
			  if(dbConn == null) {
				  Class.forName(driverName);
				   dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
				   System.out.println("连接数据库成功");
			  }
			  else {
				  return;
			  }
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.print("连接失败");
		  }
	}
	private static ResultSet executeQuery(String sql) {
		try {
			if(dbConn==null)
			new Dao();
			return dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}
	private static int executeUpdate(String sql) {	
		try {
			if(dbConn==null)
				new Dao();
			return dbConn.createStatement().executeUpdate(sql);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
		}
	}
	public static void close() {
		try {
			dbConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbConn = null;
		}
	}
	/*
	*	管理员登录方法***********************************************
	*	Operator table = user table
	*/
	
	/*
	 * insert  information of reader
	 * 读者（读者编号、读者姓名、读者性别、读者种类、工作单位、家庭住址、电话号码、电邮地址、登记日期、备注）
	 * reader(r_id,r_name,r_sex,r_type,r_unit,r_address,r_tel,r_email,r_addtime,r_brief)
	 */
	public static user_ check(String id, char[] password) {
		int i = 0;
		user_ user=new user_();
		String psd = String.valueOf(password);
		String sql = "select *  from user_ where u_id='" + id
				+ "' and u_password='" + psd + "'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				String names = rs.getString(1);
				user.setId(rs.getString("u_id"));
				user.setAdmin(rs.getBoolean("isadmin"));
				user.setPsd(rs.getString("u_password"));
				if (names != null) {
					i = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return user;
		
	}
	
	public static int InsertReader(String name,String sex,String type,String unit,String address,String tel,String email,Date date){
		int i=0;
		try{
			String sql="insert into reader(r_name,r_sex,r_type,r_unit,r_address,r_tel,r_email,r_addtime,r_brief) values('"
		+name+"','"+sex+"','"+type+"','"+unit+"','"+address+"','"+tel+"','"+email+"','"+date+"',null)";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//**********************************无条件查询读者信息
	public static List selectReader() {
		List list=new ArrayList();
		String sql = "select *  from reader";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				//reader(r_id,r_name,r_sex,r_type,r_unit,r_address,r_tel,r_email,r_addtime,r_brief)
				reader reader=new reader();
				reader.setId(rs.getInt("r_id"));
				reader.setName(rs.getString("r_name"));
				reader.setSex(rs.getString("r_sex"));
				reader.setType(rs.getString("r_type"));
				reader.setUnit(rs.getString("r_unit"));
				reader.setAddress(rs.getString("r_address"));
				reader.setTel(rs.getString("r_tel"));
				reader.setEmail(rs.getString("r_email"));
				reader.setAddtime(rs.getDate("r_addtime"));
				reader.setBrief(rs.getString("r_brief"));
				
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//*******************************************根据读者ID查询读者信息
	public static List selectReaderById(String id) {
		List list=new ArrayList();
		String sql = "select *  from reader where r_id = '"+id+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				//reader(r_id,r_name,r_sex,r_type,r_unit,r_address,r_tel,r_email,r_addtime,r_brief)
				reader reader=new reader();
				reader.setId(rs.getInt("r_id"));
				reader.setName(rs.getString("r_name"));
				reader.setSex(rs.getString("r_sex"));
				reader.setType(rs.getString("r_type"));
				reader.setUnit(rs.getString("r_unit"));
				reader.setAddress(rs.getString("r_address"));
				reader.setTel(rs.getString("r_tel"));
				reader.setEmail(rs.getString("r_email"));
				reader.setAddtime(rs.getDate("r_addtime"));
				reader.setBrief(rs.getString("r_brief"));
				
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static List selectReaderByName(String name) {
		List list=new ArrayList();
		String sql = "select *  from reader where r_name = '"+name+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				//reader(r_id,r_name,r_sex,r_type,r_unit,r_address,r_tel,r_email,r_addtime,r_brief)
				reader reader=new reader();
				reader.setId(rs.getInt("r_id"));
				reader.setName(rs.getString("r_name"));
				reader.setSex(rs.getString("r_sex"));
				reader.setType(rs.getString("r_type"));
				reader.setUnit(rs.getString("r_unit"));
				reader.setAddress(rs.getString("r_address"));
				reader.setTel(rs.getString("r_tel"));
				reader.setEmail(rs.getString("r_email"));
				reader.setAddtime(rs.getDate("r_addtime"));
				reader.setBrief(rs.getString("r_brief"));
				
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static List selectReaderByType(String type) {
		List list=new ArrayList();
		String sql = "select *  from reader where r_type = '"+type+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				//reader(r_id,r_name,r_sex,r_type,r_unit,r_address,r_tel,r_email,r_addtime,r_brief)
				reader reader=new reader();
				reader.setId(rs.getInt("r_id"));
				reader.setName(rs.getString("r_name"));
				reader.setSex(rs.getString("r_sex"));
				reader.setType(rs.getString("r_type"));
				reader.setUnit(rs.getString("r_unit"));
				reader.setAddress(rs.getString("r_address"));
				reader.setTel(rs.getString("r_tel"));
				reader.setEmail(rs.getString("r_email"));
				reader.setAddtime(rs.getDate("r_addtime"));
				reader.setBrief(rs.getString("r_brief"));
				
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//无条件查询书籍
	
	public static List selectBook() {
		List list=new ArrayList();
		String sql = "select *  from book";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				book bookinfo=new book();
				bookinfo.setId(rs.getString("b_id"));
				bookinfo.setName(rs.getString("b_name"));
				bookinfo.setAuthor(rs.getString("author"));
				bookinfo.setType(rs.getString("b_type"));
				bookinfo.setPress(rs.getString("press"));
				bookinfo.setPress_date(rs.getDate("press_date"));
				bookinfo.setPrice(rs.getDouble("price"));
				bookinfo.setPagetotal(rs.getInt("page_total"));
				bookinfo.setAddtime(rs.getDate("b_addtime"));
				bookinfo.setKeryword(rs.getString("keyword"));
				bookinfo.setBrief(rs.getString("b_brief"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//书价格
	public static Double selectBookprice(String b_id) {
		double p = 0;
		String sql = "select price from book where b_id = '"+b_id+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				p=rs.getDouble("price");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return p;
	}
	public static List selectBookseach() {
		List list=new ArrayList();
		String sql = "select *  from book";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				book bookinfo=new book();
				bookinfo.setId(rs.getString("b_id"));
				bookinfo.setName(rs.getString("b_name"));
				bookinfo.setType(rs.getString("b_type"));
				bookinfo.setAuthor(rs.getString("author"));
				bookinfo.setPress(rs.getString("press"));
				bookinfo.setPress_date(rs.getDate("press_date"));
				bookinfo.setPrice(rs.getDouble("price"));
				bookinfo.setPagetotal(rs.getInt("page_total"));
				bookinfo.setKeryword(rs.getString("keyword"));
				bookinfo.setAddtime(rs.getDate("b_addtime"));
				bookinfo.setBrief(rs.getString("b_brief"));
				bookinfo.setIsborrow(rs.getBoolean("isborrow"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static List selectBook(String b_ids) {
		List list=new ArrayList();
		String sql = "select *  from book where b_id = '"+b_ids+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				book bookinfo=new book();
				bookinfo.setId(rs.getString("b_id"));
				bookinfo.setName(rs.getString("b_name"));
				bookinfo.setAuthor(rs.getString("author"));
				bookinfo.setType(rs.getString("b_type"));
				bookinfo.setPress(rs.getString("press"));
				bookinfo.setPress_date(rs.getDate("press_date"));
				bookinfo.setPrice(rs.getDouble("price"));
				bookinfo.setPagetotal(rs.getInt("page_total"));
				bookinfo.setAddtime(rs.getDate("b_addtime"));
				bookinfo.setBrief(rs.getString("b_brief"));
				bookinfo.setIsborrow(rs.getBoolean("isborrow"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static int selectBookNumber(String r_id ,String typename ) {
		int i = 0;
String sql = "select canborrow_int,count(r_id) as num from reader_type, borrow group by r_id ,isreturn,reader_type.r_typename,reader_type.canborrow_int having( r_id='"+r_id+"' and isreturn = 0 and r_typename = '"+typename+"') " ;
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				int m=0,n=0;
				m=rs.getInt("num");
				n=rs.getInt("canborrow_int");
				if(m>=n)i=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Dao.close();
		return i;
	}
	//根据书籍ID查询
	public static List selectBookById(String id) {
		List list=new ArrayList();
		String sql = "select *  from book where b_id = '"+id+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				book bookinfo=new book();
				bookinfo.setId(rs.getString("b_id"));
				bookinfo.setName(rs.getString("b_name"));
				bookinfo.setAuthor(rs.getString("author"));
				bookinfo.setType(rs.getString("b_type"));
				bookinfo.setPress(rs.getString("press"));
				bookinfo.setPress_date(rs.getDate("press_date"));
				bookinfo.setPrice(rs.getDouble("price"));
				bookinfo.setPagetotal(rs.getInt("page_total"));
				bookinfo.setKeryword(rs.getString("keyword"));
				bookinfo.setAddtime(rs.getDate("b_addtime"));
				bookinfo.setBrief(rs.getString("b_brief"));
				bookinfo.setIsborrow(rs.getBoolean("isborrow"));
				
				
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//根据*****************书名**************模糊搜索
	public static List selectBookByBookname(String bookname){
		List list=new ArrayList();
		String sql="select * from book where b_name like '%"+bookname+"%'";
		System.out.print(sql);
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				book bookinfo=new book();
				bookinfo.setId(s.getString("b_id"));
				bookinfo.setName(s.getString("b_name"));
				bookinfo.setAuthor(s.getString("author"));
				bookinfo.setType(s.getString("b_type"));
				bookinfo.setPress(s.getString("press"));
				bookinfo.setPress_date(s.getDate("press_date"));
				bookinfo.setPrice(s.getDouble("price"));
				bookinfo.setPagetotal(s.getInt("page_total"));
				bookinfo.setKeryword(s.getString("keyword"));
				bookinfo.setAddtime(s.getDate("b_addtime"));
				bookinfo.setBrief(s.getString("b_brief"));
				bookinfo.setIsborrow(s.getBoolean("isborrow"));
				list.add(bookinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//根据*********************作者名字*****************模糊搜索
	public static List selectBookByAuthor(String author){
		List list=new ArrayList();
		String sql="select * from book where author like '%"+author+"%'";
		System.out.print(sql);
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				book bookinfo=new book();
				bookinfo.setId(s.getString("b_id"));
				bookinfo.setName(s.getString("b_name"));
				bookinfo.setAuthor(s.getString("author"));
				bookinfo.setType(s.getString("b_type"));
				bookinfo.setPress(s.getString("press"));
				bookinfo.setPress_date(s.getDate("press_date"));
				bookinfo.setPrice(s.getDouble("price"));
				bookinfo.setPagetotal(s.getInt("page_total"));
				bookinfo.setKeryword(s.getString("keyword"));
				bookinfo.setAddtime(s.getDate("b_addtime"));
				bookinfo.setBrief(s.getString("b_brief"));
				bookinfo.setIsborrow(s.getBoolean("isborrow"));
				list.add(bookinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
//*********************借出记录查询****
	//*************借出记录号******
	public static List selectBorrowMHrecord(String borr_record){
		List list=new ArrayList();
		String sql="select * from borrow where borr_record ='"+borr_record+"'";
		System.out.print(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				borrow borrow=new borrow();
				borrow.setBorr_record(rs.getString("borr_record"));
				borrow.setR_id(rs.getString("r_id"));
				borrow.setR_name(rs.getString("r_name"));
				borrow.setB_id(rs.getString("b_id"));
				borrow.setB_name(rs.getString("b_name"));
				borrow.setB_type(rs.getString("b_type"));
				borrow.setPrice(rs.getDouble("price"));
				borrow.setBorrow_date(rs.getDate("borrow_date"));
				borrow.setReturn_date(rs.getDate("return_date"));
				borrow.setIsreturn(rs.getBoolean("isreturn"));
				borrow.setB_admin(rs.getString("b_admin"));
				list.add(borrow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//*************读者姓名******
	public static List selectBorrowMHr_name(String r_name){
		List list=new ArrayList();
		String sql="select * from borrow where r_name like '%"+r_name+"%'";
		System.out.print(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				borrow borrow=new borrow();
				borrow.setBorr_record(rs.getString("borr_record"));
				borrow.setR_id(rs.getString("r_id"));
				borrow.setR_name(rs.getString("r_name"));
				borrow.setB_id(rs.getString("b_id"));
				borrow.setB_name(rs.getString("b_name"));
				borrow.setB_type(rs.getString("b_type"));
				borrow.setPrice(rs.getDouble("price"));
				borrow.setBorrow_date(rs.getDate("borrow_date"));
				borrow.setReturn_date(rs.getDate("return_date"));
				borrow.setIsreturn(rs.getBoolean("isreturn"));
				borrow.setB_admin(rs.getString("b_admin"));
				list.add(borrow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//*************图书名称******
		public static List selectBorrowMHb_name(String b_name){
			List list=new ArrayList();
			String sql="select * from borrow where b_name like '%"+b_name+"%'";
			System.out.print(sql);
			ResultSet rs = Dao.executeQuery(sql);
			try {
				while (rs.next()) {
					borrow borrow=new borrow();
					borrow.setBorr_record(rs.getString("borr_record"));
					borrow.setR_id(rs.getString("r_id"));
					borrow.setR_name(rs.getString("r_name"));
					borrow.setB_id(rs.getString("b_id"));
					borrow.setB_name(rs.getString("b_name"));
					borrow.setB_type(rs.getString("b_type"));
					borrow.setPrice(rs.getDouble("price"));
					borrow.setBorrow_date(rs.getDate("borrow_date"));
					borrow.setReturn_date(rs.getDate("return_date"));
					borrow.setIsreturn(rs.getBoolean("isreturn"));
					borrow.setB_admin(rs.getString("b_admin"));
					list.add(borrow);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	
	//查询用户
	public static List selectUser() {
		List list = new ArrayList();
		String sql = "select u_id,u_password,isadmin from user_";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()) {
				user_ user = new user_();
				user.setId(rs.getString("u_id"));
				user.setPsd(rs.getString("u_password"));
				user.setAdmin(rs.getBoolean("isadmin"));
				list.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//归还表格操作
	public static List selectBorr_Info(String readerId) {
		List list=new ArrayList();
		
		String sql = "select borr_record,b_id,b_name,b_type,borrow.r_name,borrow_date,return_date,r_type from reader,borrow where (borrow.r_id='"+readerId+"'and reader.r_id='"+readerId+"' and borrow.isreturn=0)";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				List list1=new ArrayList();
				list1.add(rs.getString("borr_record"));
				list1.add(rs.getString("b_id"));
				list1.add(rs.getString("b_name"));
				list1.add(rs.getString("b_type"));
				list1.add(rs.getString("r_name"));
				list1.add(rs.getString("r_type"));
				list1.add(rs.getDate("borrow_date").toString());
				list1.add(rs.getDate("return_date").toString());
				
				list.add(list1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	/*
	 * 查询还书内容，book 、reader 、borrow之间的查询
	 */
	public static List selectBookBack(String readerId) {
		List list=new ArrayList();
		String sql = "SELECT a.b_id AS bookId, a.b_name, a.b_type ,b.borr_record,b.b_admin, b.borrow_date, b.return_date, c.r_name AS readerName, c.r_id AS readerId from book a INNER JOIN borrow b ON a.b_id = b.b_id INNER JOIN reader c ON b.r_id = c.r_id WHERE (c.r_id = '"+readerId+"' and b.isreturn = 1)";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				back back=new back();
				back.setR_id(rs.getString("r_id"));
				back.setR_name(rs.getString("r_name"));
				back.setB_id(rs.getString("b_id"));
				back.setB_name(rs.getString("b_name"));
				back.setB_type(rs.getString("b_type"));
				back.setPrice(rs.getDouble("price"));
				back.setBorrow_date(rs.getDate("borrow_date"));
				back.setReturn_date(rs.getDate("return_date"));
				back.setPunish_fine(rs.getDouble("punish_fine"));
				back.setR_admin(rs.getString("r_admin"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
//******************归还查询
	//*****************归还记录号
	public static List selectBookBackrecord(String backId) {
		List list=new ArrayList();
		String sql = "select * from back where re_record = '"+backId+"'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				back back=new back();
				back.setRe_record(rs.getString("re_record"));
				back.setR_id(rs.getString("r_id"));
				back.setR_name(rs.getString("r_name"));
				back.setB_id(rs.getString("b_id"));
				back.setB_name(rs.getString("b_name"));
				back.setB_type(rs.getString("b_type"));
				back.setPrice(rs.getDouble("price"));
				back.setBorrow_date(rs.getDate("borrow_date"));
				back.setReturn_date(rs.getDate("return_date"));
				back.setPunish_fine(rs.getDouble("punish_fine"));
				back.setR_admin(rs.getString("r_admin"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//*****************归还读者编号
	public static List selectBookBackr_id(String r_Id) {
		List list=new ArrayList();
		String sql = "select * from back where r_id ='"+r_Id+"'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				back back=new back();
				back.setRe_record(rs.getString("re_record"));
				back.setR_id(rs.getString("r_id"));
				back.setR_name(rs.getString("r_name"));
				back.setB_id(rs.getString("b_id"));
				back.setB_name(rs.getString("b_name"));
				back.setB_type(rs.getString("b_type"));
				back.setPrice(rs.getDouble("price"));
				back.setBorrow_date(rs.getDate("borrow_date"));
				back.setReturn_date(rs.getDate("return_date"));
				back.setPunish_fine(rs.getDouble("punish_fine"));
				back.setR_admin(rs.getString("r_admin"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//*****************归还图书编号
	public static List selectBookBackb_id(String b_Id) {
		List list=new ArrayList();
		String sql = "select * from back where b_id ='"+b_Id+"'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				back back=new back();
				back.setRe_record(rs.getString("re_record"));
				back.setR_id(rs.getString("r_id"));
				back.setR_name(rs.getString("r_name"));
				back.setB_id(rs.getString("b_id"));
				back.setB_name(rs.getString("b_name"));
				back.setB_type(rs.getString("b_type"));
				back.setPrice(rs.getDouble("price"));
				back.setBorrow_date(rs.getDate("borrow_date"));
				back.setReturn_date(rs.getDate("return_date"));
				back.setPunish_fine(rs.getDouble("punish_fine"));
				back.setR_admin(rs.getString("r_admin"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//*****************归还读者姓名
	public static List selectBookBackMHr_name(String r_name) {
		List list=new ArrayList();
		String sql = "select * from back where r_name like '%"+r_name+"%'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				back back=new back();
				back.setRe_record(rs.getString("re_record"));
				back.setR_id(rs.getString("r_id"));
				back.setR_name(rs.getString("r_name"));
				back.setB_id(rs.getString("b_id"));
				back.setB_name(rs.getString("b_name"));
				back.setB_type(rs.getString("b_type"));
				back.setPrice(rs.getDouble("price"));
				back.setBorrow_date(rs.getDate("borrow_date"));
				back.setReturn_date(rs.getDate("return_date"));
				back.setPunish_fine(rs.getDouble("punish_fine"));
				back.setR_admin(rs.getString("r_admin"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//*****************归还图书名称
	public static List selectBookBackb_name(String b_name) {
		List list=new ArrayList();
		String sql = "select * from back where b_name like '%"+b_name+"%'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				back back=new back();
				back.setRe_record(rs.getString("re_record"));
				back.setR_id(rs.getString("r_id"));
				back.setR_name(rs.getString("r_name"));
				back.setB_id(rs.getString("b_id"));
				back.setB_name(rs.getString("b_name"));
				back.setB_type(rs.getString("b_type"));
				back.setPrice(rs.getDouble("price"));
				back.setBorrow_date(rs.getDate("borrow_date"));
				back.setReturn_date(rs.getDate("return_date"));
				back.setPunish_fine(rs.getDouble("punish_fine"));
				back.setR_admin(rs.getString("r_admin"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static List selectBookBorrowr_id(String readerId) {
		List list=new ArrayList();
		String sql = "select * from borrow where r_id = '"+readerId+"'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				borrow borrow=new borrow();
				borrow.setBorr_record(rs.getString("borr_record"));
				borrow.setR_id(rs.getString("r_id"));
				borrow.setR_name(rs.getString("r_name"));
				borrow.setB_id(rs.getString("b_id"));
				borrow.setB_name(rs.getString("b_name"));
				borrow.setB_type(rs.getString("b_type"));
				borrow.setPrice(rs.getDouble("price"));
				borrow.setBorrow_date(rs.getDate("borrow_date"));
				borrow.setReturn_date(rs.getDate("return_date"));
				borrow.setIsreturn(rs.getBoolean("isreturn"));
				borrow.setB_admin(rs.getString("b_admin"));
				list.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static List selectBookBorrowb_id(String bookId) {
		List list=new ArrayList();
		String sql = "select * from borrow where b_id = '"+bookId+"'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				borrow borrow=new borrow();
				borrow.setBorr_record(rs.getString("borr_record"));
				borrow.setR_id(rs.getString("r_id"));
				borrow.setR_name(rs.getString("r_name"));
				borrow.setB_id(rs.getString("b_id"));
				borrow.setB_name(rs.getString("b_name"));
				borrow.setB_type(rs.getString("b_type"));
				borrow.setPrice(rs.getDouble("price"));
				borrow.setBorrow_date(rs.getDate("borrow_date"));
				borrow.setReturn_date(rs.getDate("return_date"));
				borrow.setIsreturn(rs.getBoolean("isreturn"));
				borrow.setB_admin(rs.getString("b_admin"));
				list.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static Date selectborrow_time(String borr_record) {
		Date borr = null ;
		String sql = "select borrow_date from borrow where borr_record = '"+borr_record+"'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				borr = rs.getDate("borrow_date");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return borr;
	}
	public static List selectBookBorrow(String readerId,String b_ids) {
		List list=new ArrayList();
		String sql = "select * from borrow where r_id = '"+readerId+"' and b_id ='"+b_ids+"'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				borrow borrow=new borrow();
				borrow.setR_id(rs.getString("r_id"));
				borrow.setR_name(rs.getString("r_name"));
				borrow.setB_id(rs.getString("b_id"));
				borrow.setB_name(rs.getString("b_name"));
				borrow.setB_type(rs.getString("b_type"));
				borrow.setPrice(rs.getDouble("price"));
				borrow.setBorrow_date(rs.getDate("borrow_date"));
				borrow.setReturn_date(rs.getDate("return_date"));
				borrow.setIsreturn(rs.getBoolean("isreturn"));
				borrow.setB_admin(rs.getString("b_admin"));
				list.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	/*
	 * 查询类别方法
	 */
	public static List selectBookType() {
		List list=new ArrayList();
		String sql = "select *  from book_type";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				booktype bookType=new booktype();
				bookType.setTypename(rs.getString("b_typename"));
				bookType.setTypeid(rs.getString("code"));
				bookType.setKeyword(rs.getString("keyword"));
				list.add(bookType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;	
	}
	
	public static List selectReaderType(String typename) {
		List list=new ArrayList();
		String sql = "select *  from reader_type where r_typename='"+typename+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				readertype ReaderType=new readertype();
				ReaderType.setTypeid(rs.getInt("r_type_id"));
				ReaderType.setTypename(rs.getString("r_typename"));
				ReaderType.setCanborrowint(rs.getInt("canborrow_int"));
				ReaderType.setDeadline(rs.getInt("deadline"));
				list.add(ReaderType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;	
	}
	public static  int selectReaderTypeByTypename(String typename) {
		int p = 0;
		String sql = "select canborrow_int  from reader_type where r_typename='"+typename+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				p = rs.getInt("canborrow_int");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return p;	
	}
	
	public static List selectReaderType() {
		List list=new ArrayList();
		String sql = "select *  from reader_type ";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				readertype ReaderType=new readertype();
				ReaderType.setTypeid(rs.getInt("r_type_id"));
				ReaderType.setTypename(rs.getString("r_typename"));
				ReaderType.setCanborrowint(rs.getInt("canborrow_int"));
				ReaderType.setDeadline(rs.getInt("deadline"));
				list.add(ReaderType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;	
	}
/*	public static List selectFine() {
		List list = new ArrayList();
		String sql = "select f_type,fine from fine";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()) {
				fine fine = new fine();
				fine.setType(rs.getString("f_type"));
				fine.setFine(rs.getDouble("fine"));
				list.add(fine);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}*/
	public static Double selectFine() {
		double fines = 0;
		String sql = "select fine from fine";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()) {
				
				
				fines = rs.getDouble("fine");
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return fines;
	}
	public static int InsertUser_(String id,String psd,boolean admin) {
		int i = 0;
		try {
			String sql = "insert into user_(u_id,u_password,isadmin) values ('"+id+"','"+psd+"','"+admin+"')";
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	
	public static int InsertBook(String b_id,String b_name,String b_type,String author,String press,Date press_date,Double price,int page,String keyword,Date b_addtime,boolean isborrow,String b_brief){
		int i=0;
		try{
			String sql="insert into book(b_id,b_name,b_type,author,press,press_date,price,page_total,keyword,b_addtime,isborrow,b_brief) "
					+ "values('"+b_id+"','"+b_name+"','"+b_type+"','"+author+"','"+press+"','"+press_date+"','"+price+"','"+page+"','"+keyword+"','"+b_addtime+"','"+isborrow+"','"+b_brief+"')";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Dao.close();
		return i;
	}
	/*public static int InsertBookType(String bookTypeName,String code,String keyword){
		int i=0;
		try{
			String sql="insert into book_type(b_typename,keyword) "
					+ "values('"+bookTypeName+"',"+keyword+")";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}*/
	public static int InsertBookType(String bookTypeName,String code,String keyword){
		int i=0;
		try{
			String sql="insert into book_type(b_typename,keyword) "
					+ "values('"+bookTypeName+"','"+keyword+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	/*public static int InsertReaderType(String readerTypeName,String borrow_int,int deadline){
		int i=0;
		try{
			String sql="insert into reader_type(r_typename,canborrow_int,deadline) "
					+ "values('"+readerTypeName+"','"+borrow_int+"',"+deadline+")";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}*/
	public static int InsertReaderType(String readerTypeName,int borrow_int,int deadline){
		int i=0;
		try{
			String sql="insert into reader_type(r_typename,canborrow_int,deadline) "
					+ "values('"+readerTypeName+"','"+borrow_int+"','"+deadline+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	public static int InsertBorrow(String r_id,String r_name,String b_id ,String b_name,String b_type,double price,Date borrow_date,Date return_dates,String b_admin) {
		int i = 0;
		try {
			String sql = "insert into borrow (r_id,r_name,b_id,b_name,b_type,price,borrow_date,return_date,isreturn,b_admin) "
					+ "values('"+Integer.valueOf(r_id)+"','"+r_name+"','"+b_id+"','"+b_name+"','"+b_type+"','"+price+"','"+borrow_date+"','"+return_dates+"','0','"+b_admin+"')";
			i=Dao.executeUpdate(sql);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	public static int InsertBack(String r_id,String r_name,String b_id,String b_name,String b_type,double price,Date borrow_date,Date return_date,double punish_fine,String r_admin) {
		int i = 0;
		try {
			String sql = "insert into back (r_id,r_name,b_id,b_name,b_type,price,borrow_date,return_date,punish_fine,r_admin) "
					+ "values('"+r_id+"','"+r_name+"','"+b_id+"','"+b_name+"','"+b_type+"','"+price+"','"+borrow_date+"','"+return_date+"','"+punish_fine+"','"+r_admin+"')"
							+ "update borrow set isreturn = '1' where b_id='"+b_id+"'";
			i=Dao.executeUpdate(sql);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	public static int InsertFine(String f_type,double fine) {
		int i = 0;
		try {
			String sql = "insert into fine (f_type,fine) values ('"
					+f_type+"','"+fine+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	
	//*****************************************更*新******************
	public static int updateBook(String b_id,String b_name,String b_type,String author,String press,Date press_date,Double price,int page,String keyword,Date b_addtime,String b_brief){
		int i=0;
		try{
			String sql="update book set b_name = '"+b_name+"',b_type = '"+b_type+"',author = '"+author+"',press = '"+press+"',press_date = '"+press_date+"',price = '"+price+"',page_total = '"+page+"',keyword = '"+keyword+"',b_addtime = '"+b_addtime+"',b_brief = '"+b_brief+"' where b_id = '"+b_id+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Dao.close();
		return i;
	}
	public static void updateBook(String b_ids,int isborrow){
		int i=0;
		try{
			String sql="update book set  isborrow = '"+isborrow+"' where b_id = '"+b_ids+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Dao.close();
		
	}
	

	public static int updateReader(int r_id,String name,String sex,String type,String unit,String address,String tel,String email,Date date,String brief){
		int i=0;
		try{
			String sql="update reader set r_name='"+name+"',"+"r_sex='"+sex+"',"+"r_type='"+type+"',"+"r_unit='"+unit+"',"+"r_address='"+address+"',"+"r_tel='"+tel+"',"+"r_email='"+email+"',"+"r_addtime='"+date+"',"+"r_brief='"+brief+"'"
					+ " where r_id ='"+r_id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	/*public static int updateReaderType(String readerTypeName,String borrow_int,int deadline){
		int i=0;
		try{
			String sql="update reader_type set r_typename='"+readerTypeName+"',"+"canborrow_int='"+borrow_int+"',"+"deadline='"+deadline+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}*/
	
	public static int updateReaderType(int r_type_id,String readerTypeName,int borrow_int,int deadline){
		int i=0;
		try{
			String sql="update reader_type set r_typename='"+readerTypeName+"',"+"canborrow_int='"+borrow_int+"',"+"deadline='"+deadline+"' "
					+ "where r_type_id ='"+r_type_id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	/*public static int updateBookType(String bookTypeName,String code,String keyword){
		int i=0;
		try{
			String sql="update book_type set b_typename='"+bookTypeName+"',"+"keyword='"+keyword+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}*/
	public static int updateBookType(String bookTypeName,String code,String keyword){
		int i=0;
		try{
			String sql="update book_type set b_typename='"+bookTypeName+"',"+"keyword='"+keyword
					+ "' where code ='"+code+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateBorrow(String r_name,String b_name,String b_type,double price,Date borrow_date,boolean isreturn,String b_admin) {
		int i = 0;
		try {
			String sql = "update borrow set r_name ='"+r_name+"',"+"b_name='"+b_name+"',"+"b_type='"+b_type+"',"+"price='"+price+"',"+"borrow_date='"+borrow_date+"',"+"isreturn='"+isreturn+"',"+"b_admin='"+b_admin+"'";
			i=Dao.executeUpdate(sql);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateBack(String r_name,String b_name,String b_type,double price,Date borrow_date,Date return_date,double punish_fine,String r_admin) {
		int i = 0;
		try {
			String sql = "update back set r_name ='"+r_name+"',"+"b_name='"+b_name+"',"+"b_type='"+b_type+"',"+"price='"+price+"',"+"borrow_date='"+borrow_date+"',"+"return_date='"+return_date+"',"+"punish_fine='"+punish_fine+"',"+"b_admin='"+r_admin+"'";
			i=Dao.executeUpdate(sql);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	public static void updateBackisreturn(String b_id,int isborrow) {
		int i= 0;
		try {
			String sql = "update book set isborrow = '"+isborrow+"' where b_id = '"+b_id+"'";
			i=Dao.executeUpdate(sql);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		
	}

	public static int updateUser_(String id,String psd,boolean admin) {
		int i = 0;
		try {
			String sql = "update user_ set u_id='"+id+"',"+"u_password='"+psd+"',"+"isadmin='"+admin+"'";
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int updateFine(String f_type,double fine) {
		int i = 0;
		try {
			String sql = "update fine set f_type='"+f_type+"',"+"fine='"+fine+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updatePassword(String id,String psd){
		int i=0;
		try{
			String sql="update user_ set u_password='"+psd+"' where u_id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int deleteReader(int id){
		int i=0;
		try{
			String sql_delete = "delete from borrow,back where b_id='"+id+"'";
			String sql="delete from reader where r_id='"+id+"'";
			Dao.executeUpdate(sql_delete);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int deleteUser(String id){
		int i=0;
		try{
			String sql="delete from user_ where u_id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static int deleteBook(String id){
		int i=0;
		try{
			String sql1 = "delete from borrow where b_id='"+id+"'";
			Dao.executeUpdate(sql1);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		try{
			String sql2 = "delete from back where b_id='"+id+"'";
			Dao.executeUpdate(sql2);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		try{
			String sql3="delete from book where b_id='"+id+"'";
			i=Dao.executeUpdate(sql3);
		}catch(Exception e){
			e.printStackTrace();
		}Dao.close();
		return i;
	}
	public static int deleteBookType(String typename){
		int i=0;
		try{
			String sql="delete from book where b_typename='"+typename+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int deleteReaderType(String typename){
		int i=0;
		try{
			String sql="delete from reader where r_typename='"+typename+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	/*public static int deleteBorrow(String r_id){
		int i=0;
		try{
			String sql="delete from borrow where r_id='"+r_id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static int deleteBack(String r_id){
		int i=0;
		try{
			String sql="delete from borrow where r_id='"+r_id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}*/

	
}