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
	private static Map<String, JInternalFrame> frames; // �Ӵ��弯��
	
	public static BookAddAction BOOK_ADD; // ͼ����Ϣ��Ӵ��嶯��
	public static BookUpdateAction BOOK_UPDATE_DELETE;//ͼ����Ϣ���´��嶯��
	public static BookFindAction BOOK_FIND;//ͼ����Ϣ��ѯ���嶯��
	public static BookBorrowAction BOOK_BORROW;//ͼ����贰�嶯��
	public static BookReturnAction BOOK_RETURN;//ͼ��黹���嶯��
	public static BorrowHistoryAction BORROW_HISTORY;//���ļ�¼��ѯ���嶯��
	public static ReaderAddAction READER_ADD;//����ע�ᴰ�嶯��
	public static ReaderUpdateFindAction READER_UPDATE;//������Ϣ��ѯ����
	public static ReaderUpdateMainFindAction READER_MAIN_SELECT;
	public static BookTypeAddAction BOOK_TYPE_ADD;//ͼ���������
	public static BookTypeFitAction BOOK_TYPE_FIT;//ͼ������޸�
	public static ReaderTypeAddAction READER_TYPE_ADD;//����������
	public static ReaderTypeFitAction READER_TYPE_FIT;//��������޸�
	public static FineAction FINE;//����
	public static UserManagementAction USE;//�û�����
	public static HelpAction HELP;//����
	
	
	
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
	


private static class BookAddAction extends AbstractAction {				// ͼ����Ϣ���
	BookAddAction() {

		super("ͼ����Ϣ���", null);
		//super();
		putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µ�ͼ����Ϣ");
		putValue(Action.SHORT_DESCRIPTION, "ͼ����Ϣ���");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("ͼ����Ϣ���")||frames.get("ͼ����Ϣ���").isClosed()) {
			BookAdd iframe = new BookAdd();
			frames.put("ͼ����Ϣ���", iframe);
			LibrarySystem.addIFame(frames.get("ͼ����Ϣ���"));
		}
	}
}
private static class BookUpdateAction extends AbstractAction {	//ͼ����Ϣ�޸ġ�ɾ��
	
		BookUpdateAction() {
			super("ͼ�����������", null);
			putValue(Action.LONG_DESCRIPTION, "�޸�ͼ�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "ͼ�����������");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ�����������")||frames.get("ͼ�����������").isClosed()) {
				BookUpdate iframe=new BookUpdate();
				frames.put("ͼ�����������", iframe);
				LibrarySystem.addIFame(frames.get("ͼ�����������"));
			}
		}
	}

private static class BookFindAction extends AbstractAction {
	BookFindAction() {
		super("ͼ���ѯ", null);
		putValue(Action.LONG_DESCRIPTION, "�����Ѵ洢��ͼ����Ϣ");
		putValue(Action.SHORT_DESCRIPTION, "ͼ���ѯ");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("ͼ���ѯ")||frames.get("ͼ���ѯ").isClosed()) {
			BookFind iframe=new BookFind();
			frames.put("ͼ���ѯ", iframe);
			LibrarySystem.addIFame(frames.get("ͼ���ѯ"));
		}
	}
}
private static class BookBorrowAction extends AbstractAction{
	BookBorrowAction(){
		super("ͼ�����",null);
		putValue(Action.LONG_DESCRIPTION, "ͼ�������Ϣ");
		putValue(Action.SHORT_DESCRIPTION, "ͼ�����");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("ͼ�����")||frames.get("ͼ�����").isClosed()) {
			BookBorrow iframe=new BookBorrow();
			frames.put("ͼ�����", iframe);
			LibrarySystem.addIFame(frames.get("ͼ�����"));
		}
	}
}
private static class BookReturnAction extends AbstractAction {
	BookReturnAction() {
		super("ͼ��黹", null);
		putValue(Action.LONG_DESCRIPTION, "�黹�����ͼ��");
		putValue(Action.SHORT_DESCRIPTION, "ͼ��黹");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("ͼ��黹")||frames.get("ͼ��黹").isClosed()) {
			BookReturn iframe=new BookReturn();
			frames.put("ͼ��黹", iframe);
			LibrarySystem.addIFame(frames.get("ͼ��黹"));
		}
	}
}
private static class BorrowHistoryAction extends AbstractAction {
	BorrowHistoryAction() {
		super("���ļ�¼��ѯ", null);
		putValue(Action.LONG_DESCRIPTION, "��ѯ���ļ�¼��Ϣ");
		putValue(Action.SHORT_DESCRIPTION, "���ļ�¼��ѯ");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("���ļ�¼��ѯ")||frames.get("���ļ�¼��ѯ").isClosed()) {
			BorrowHistory iframe=new BorrowHistory();
			frames.put("���ļ�¼��ѯ", iframe);
			LibrarySystem.addIFame(frames.get("���ļ�¼��ѯ"));
		}
	}
}
private static class ReaderAddAction extends AbstractAction {
	ReaderAddAction() {
		super("������Ϣ���", null);
		putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µĶ��߻�Ա��Ϣ");
		putValue(Action.SHORT_DESCRIPTION, "������Ϣ���");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("���������Ϣ���")||frames.get("���������Ϣ���").isClosed()) {
			ReaderAdd iframe=new ReaderAdd();
			frames.put("���������Ϣ���", iframe);
			LibrarySystem.addIFame(frames.get("���������Ϣ���"));
		}
	}
}
private static class ReaderUpdateFindAction extends AbstractAction {
	ReaderUpdateFindAction() {
		super("������Ϣ����", null);
		putValue(Action.LONG_DESCRIPTION, "���¶�����Ϣ");
		putValue(Action.SHORT_DESCRIPTION, "������Ϣ����");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("������Ϣ����")||frames.get("������Ϣ����").isClosed()) {
			ReaderUpdateFind iframe=new ReaderUpdateFind();
			frames.put("������Ϣ����", iframe);
			LibrarySystem.addIFame(frames.get("������Ϣ����"));
		}
	}
}
private static class ReaderUpdateMainFindAction extends AbstractAction {
	ReaderUpdateMainFindAction() {
		super("��ѯ����", null);
		putValue(Action.LONG_DESCRIPTION, "���ºͲ�ѯ����");
		putValue(Action.SHORT_DESCRIPTION, "��ѯ����");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("������Ϣ����")||frames.get("������Ϣ����").isClosed()) {
			ReaderUpdateFind iframe=new ReaderUpdateFind();
			frames.put("������Ϣ����", iframe);
			LibrarySystem.addIFame(frames.get("������Ϣ����"));
		}
	}
}
private static class BookTypeAddAction extends AbstractAction {
	BookTypeAddAction() {
		super("ͼ���������", null);
		putValue(Action.LONG_DESCRIPTION, "����ͼ������");
		putValue(Action.SHORT_DESCRIPTION, "ͼ���������");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("ͼ���������")||frames.get("ͼ���������").isClosed()) {
			BookTypeAdd iframe=new BookTypeAdd();
			frames.put("ͼ���������", iframe);
			LibrarySystem.addIFame(frames.get("ͼ���������"));
		}
	}
}
private static class BookTypeFitAction extends AbstractAction {
	BookTypeFitAction() {
		super("ͼ������޸�", null);
		putValue(Action.LONG_DESCRIPTION, "�޸�ͼ������");
		putValue(Action.SHORT_DESCRIPTION, "ͼ������޸�");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("ͼ������޸�")||frames.get("ͼ������޸�").isClosed()) {
			BookTypeFit iframe=new BookTypeFit();
			frames.put("ͼ������޸�", iframe);
			LibrarySystem.addIFame(frames.get("ͼ������޸�"));
		}
	}
}
private static class ReaderTypeAddAction extends AbstractAction {
	ReaderTypeAddAction() {
		super("����������", null);
		putValue(Action.LONG_DESCRIPTION, "��Ӷ�������");
		putValue(Action.SHORT_DESCRIPTION, "����������");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("����������")||frames.get("����������").isClosed()) {
			ReaderTypeAdd iframe=new ReaderTypeAdd();
			frames.put("����������", iframe);
			LibrarySystem.addIFame(frames.get("����������"));
		}
	}
}
private static class ReaderTypeFitAction extends AbstractAction {
	ReaderTypeFitAction() {
		super("��������޸�", null);
		putValue(Action.LONG_DESCRIPTION, "�޸Ķ�������");
		putValue(Action.SHORT_DESCRIPTION, "��������޸�");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("��������޸�")||frames.get("��������޸�").isClosed()) {
			ReaderTypeFit iframe=new ReaderTypeFit();
			frames.put("��������޸�", iframe);
			LibrarySystem.addIFame(frames.get("��������޸�"));
		}
	}
}
private static class UserManagementAction extends AbstractAction {
	 UserManagementAction() {
		super("�û�����", null);
		putValue(Action.LONG_DESCRIPTION, "�û��������");
		putValue(Action.SHORT_DESCRIPTION, "�û�����");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("�û�����")||frames.get("�û�����").isClosed()) {
			UserManagement iframe=new UserManagement();
			frames.put("�û�����", iframe);
			LibrarySystem.addIFame(frames.get("�û�����"));
		}
	}
}
private static class FineAction extends AbstractAction {
	 FineAction() {
		super("���ڷ�������", null);
		putValue(Action.LONG_DESCRIPTION, "�޸ķ����");
		putValue(Action.SHORT_DESCRIPTION, "���ڷ�������");
	}
	public void actionPerformed(ActionEvent e) {
		if (!frames.containsKey("���ڷ�������")||frames.get("���ڷ�������").isClosed()) {
			Fine iframe=new Fine();
			frames.put("���ڷ�������", iframe);
			LibrarySystem.addIFame(frames.get("���ڷ�������"));
		}
	}
}

private static class HelpAction extends AbstractAction {
	 HelpAction() {
		super("����", null);
		putValue(Action.LONG_DESCRIPTION, "������Ϣ");
		putValue(Action.SHORT_DESCRIPTION, "����");
	}
	public void actionPerformed(ActionEvent e) {
			Help iframe=new Help();
			frames.put("����", iframe);
			LibrarySystem.addIFame(frames.get("����"));
		
	}
}

}

