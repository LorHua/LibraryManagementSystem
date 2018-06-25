package Action;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;



import MainPack.LibrarySystem;

import Frame.*;
import util.*;
public class MenuActions {
	private static Map<String, JInternalFrame> frames; // 子窗体集合
	
	public static BookAddAction BOOK_ADD; // 图书信息添加窗体动作
	public static BookUpdateAction BOOK_UPDATE_DELETE;//图书信息更新窗体动作
	public static BookFindAction BOOK_FIND;//图书信息查询窗体动作
	public static BookBorrowAction BOOK_BORROW;//图书出借窗体动作
	public static BookReturnAction BOOK_RETURN;//图书归还窗体动作
	public static BorrowHistoryAction BORROW_HISTORY;//借阅记录查询窗体动作
	public static ReaderAddAction READER_ADD;//读者注册窗体动作
	public static ReaderUpdateFindAction READER_UPDATE;//读者信息查询更新
	public static ReaderUpdateMainFindAction READER_MAIN_SELECT;
	public static BookTypeAddAction BOOK_TYPE_ADD;//图书类别增添
	public static BookTypeFitAction BOOK_TYPE_FIT;//图书类别修改
	public static ReaderTypeAddAction READER_TYPE_ADD;//读者类别添加
	public static ReaderTypeFitAction READER_TYPE_FIT;//读者类别修改
	public static FineAction FINE;//罚金
	public static UserManagementAction USE;//用户管理
	public static HelpAction HELP;//帮助
	
	
	
	static {
		frames = new HashMap<String, JInternalFrame>();
		
		BOOK_ADD = new BookAddAction();
		BOOK_UPDATE_DELETE = new BookUpdateAction();
		BOOK_FIND = new BookFindAction();
		BOOK_BORROW = new BookBorrowAction();
		BOOK_RETURN =new BookReturnAction();
		BORROW_HISTORY =new BorrowHistoryAction();
		READER_ADD = new ReaderAddAction();
		READER_UPDATE = new ReaderUpdateFindAction();
		READER_MAIN_SELECT = new ReaderUpdateMainFindAction();
		BOOK_TYPE_ADD =new BookTypeAddAction();
		BOOK_TYPE_FIT =new BookTypeFitAction();
		READER_TYPE_ADD =new ReaderTypeAddAction();
		READER_TYPE_FIT = new ReaderTypeFitAction();
		FINE = new FineAction();
		USE = new UserManagementAction();
		HELP =new HelpAction();
		
	}
	


private static class BookAddAction extends AbstractAction {				// 图书信息添加
	BookAddAction() {

		super("图书信息添加", null);
		//super();
		putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的图书信息");
		putValue(Action.SHORT_DESCRIPTION, "图书信息添加");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("图书信息添加")||frames.get("图书信息添加").isClosed()) {
			BookAdd iframe = new BookAdd();
			frames.put("图书信息添加", iframe);
			LibrarySystem.addIFame(frames.get("图书信息添加"));
		}
	}
}
private static class BookUpdateAction extends AbstractAction {	//图书信息修改、删除
	
		BookUpdateAction() {
			super("图书更新与清理", null);
			putValue(Action.LONG_DESCRIPTION, "修改图书的信息");
			putValue(Action.SHORT_DESCRIPTION, "图书更新与清理");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书更新与清理")||frames.get("图书更新与清理").isClosed()) {
				BookUpdate iframe=new BookUpdate();
				frames.put("图书更新与清理", iframe);
				LibrarySystem.addIFame(frames.get("图书更新与清理"));
			}
		}
	}

private static class BookFindAction extends AbstractAction {
	BookFindAction() {
		super("图书查询", null);
		putValue(Action.LONG_DESCRIPTION, "搜索已存储的图书信息");
		putValue(Action.SHORT_DESCRIPTION, "图书查询");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("图书查询")||frames.get("图书查询").isClosed()) {
			BookFind iframe=new BookFind();
			frames.put("图书查询", iframe);
			LibrarySystem.addIFame(frames.get("图书查询"));
		}
	}
}
private static class BookBorrowAction extends AbstractAction{
	BookBorrowAction(){
		super("图书出借",null);
		putValue(Action.LONG_DESCRIPTION, "图书出借信息");
		putValue(Action.SHORT_DESCRIPTION, "图书出借");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("图书出借")||frames.get("图书出借").isClosed()) {
			BookBorrow iframe=new BookBorrow();
			frames.put("图书出借", iframe);
			LibrarySystem.addIFame(frames.get("图书出借"));
		}
	}
}
private static class BookReturnAction extends AbstractAction {
	BookReturnAction() {
		super("图书归还", null);
		putValue(Action.LONG_DESCRIPTION, "归还借出的图书");
		putValue(Action.SHORT_DESCRIPTION, "图书归还");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("图书归还")||frames.get("图书归还").isClosed()) {
			BookReturn iframe=new BookReturn();
			frames.put("图书归还", iframe);
			LibrarySystem.addIFame(frames.get("图书归还"));
		}
	}
}
private static class BorrowHistoryAction extends AbstractAction {
	BorrowHistoryAction() {
		super("借阅记录查询", null);
		putValue(Action.LONG_DESCRIPTION, "查询借阅记录信息");
		putValue(Action.SHORT_DESCRIPTION, "借阅记录查询");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("借阅记录查询")||frames.get("借阅记录查询").isClosed()) {
			BorrowHistory iframe=new BorrowHistory();
			frames.put("借阅记录查询", iframe);
			LibrarySystem.addIFame(frames.get("借阅记录查询"));
		}
	}
}
private static class ReaderAddAction extends AbstractAction {
	ReaderAddAction() {
		super("读者信息添加", null);
		putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的读者会员信息");
		putValue(Action.SHORT_DESCRIPTION, "读者信息添加");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("读者相关信息添加")||frames.get("读者相关信息添加").isClosed()) {
			ReaderAdd iframe=new ReaderAdd();
			frames.put("读者相关信息添加", iframe);
			LibrarySystem.addIFame(frames.get("读者相关信息添加"));
		}
	}
}
private static class ReaderUpdateFindAction extends AbstractAction {
	ReaderUpdateFindAction() {
		super("读者信息更新", null);
		putValue(Action.LONG_DESCRIPTION, "更新读者信息");
		putValue(Action.SHORT_DESCRIPTION, "读者信息更新");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("读者信息更新")||frames.get("读者信息更新").isClosed()) {
			ReaderUpdateFind iframe=new ReaderUpdateFind();
			frames.put("读者信息更新", iframe);
			LibrarySystem.addIFame(frames.get("读者信息更新"));
		}
	}
}
private static class ReaderUpdateMainFindAction extends AbstractAction {
	ReaderUpdateMainFindAction() {
		super("查询读者", null);
		putValue(Action.LONG_DESCRIPTION, "更新和查询读者");
		putValue(Action.SHORT_DESCRIPTION, "查询读者");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("读者信息更新")||frames.get("读者信息更新").isClosed()) {
			ReaderUpdateFind iframe=new ReaderUpdateFind();
			frames.put("读者信息更新", iframe);
			LibrarySystem.addIFame(frames.get("读者信息更新"));
		}
	}
}
private static class BookTypeAddAction extends AbstractAction {
	BookTypeAddAction() {
		super("图书类别增添", null);
		putValue(Action.LONG_DESCRIPTION, "增添图书类别表");
		putValue(Action.SHORT_DESCRIPTION, "图书类别增添");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("图书类别增添")||frames.get("图书类别增添").isClosed()) {
			BookTypeAdd iframe=new BookTypeAdd();
			frames.put("图书类别增添", iframe);
			LibrarySystem.addIFame(frames.get("图书类别增添"));
		}
	}
}
private static class BookTypeFitAction extends AbstractAction {
	BookTypeFitAction() {
		super("图书类别修改", null);
		putValue(Action.LONG_DESCRIPTION, "修改图书类别表");
		putValue(Action.SHORT_DESCRIPTION, "图书类别修改");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("图书类别修改")||frames.get("图书类别修改").isClosed()) {
			BookTypeFit iframe=new BookTypeFit();
			frames.put("图书类别修改", iframe);
			LibrarySystem.addIFame(frames.get("图书类别修改"));
		}
	}
}
private static class ReaderTypeAddAction extends AbstractAction {
	ReaderTypeAddAction() {
		super("读者类别添加", null);
		putValue(Action.LONG_DESCRIPTION, "添加读者类别表");
		putValue(Action.SHORT_DESCRIPTION, "读者类别添加");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("读者类别添加")||frames.get("读者类别添加").isClosed()) {
			ReaderTypeAdd iframe=new ReaderTypeAdd();
			frames.put("读者类别添加", iframe);
			LibrarySystem.addIFame(frames.get("读者类别添加"));
		}
	}
}
private static class ReaderTypeFitAction extends AbstractAction {
	ReaderTypeFitAction() {
		super("读者类别修改", null);
		putValue(Action.LONG_DESCRIPTION, "修改读者类别表");
		putValue(Action.SHORT_DESCRIPTION, "读者类别修改");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("读者类别修改")||frames.get("读者类别修改").isClosed()) {
			ReaderTypeFit iframe=new ReaderTypeFit();
			frames.put("读者类别修改", iframe);
			LibrarySystem.addIFame(frames.get("读者类别修改"));
		}
	}
}
private static class UserManagementAction extends AbstractAction {
	 UserManagementAction() {
		super("用户管理", null);
		putValue(Action.LONG_DESCRIPTION, "用户事项管理");
		putValue(Action.SHORT_DESCRIPTION, "用户管理");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("用户管理")||frames.get("用户管理").isClosed()) {
			UserManagement iframe=new UserManagement();
			frames.put("用户管理", iframe);
			LibrarySystem.addIFame(frames.get("用户管理"));
		}
	}
}
private static class FineAction extends AbstractAction {
	 FineAction() {
		super("超期罚金设置", null);
		putValue(Action.LONG_DESCRIPTION, "修改罚金表");
		putValue(Action.SHORT_DESCRIPTION, "超期罚金设置");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("超期罚金设置")||frames.get("超期罚金设置").isClosed()) {
			Fine iframe=new Fine();
			frames.put("超期罚金设置", iframe);
			LibrarySystem.addIFame(frames.get("超期罚金设置"));
		}
	}
}

private static class HelpAction extends AbstractAction {
	 HelpAction() {
		super("帮助", null);
		putValue(Action.LONG_DESCRIPTION, "帮助信息");
		putValue(Action.SHORT_DESCRIPTION, "帮助");
	}
	public void actionPerformed(ActionEvent e) {
			Help iframe=new Help();
			frames.put("帮助", iframe);
			LibrarySystem.addIFame(frames.get("帮助"));
		
	}
}

}

