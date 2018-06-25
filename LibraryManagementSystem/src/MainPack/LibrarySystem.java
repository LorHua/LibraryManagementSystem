package MainPack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import Frame.Login;
import util.CreatedIcon;
import Action.MenuActions;

public class LibrarySystem extends JFrame {
private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
public final static JTextField FindText = new JTextField();

public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
			new Login();//登录窗口
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		/*LibrarySystem a = new LibrarySystem();
		a.setVisible(true);*/
	}
	public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
		DESKTOP_PANE.add(iframe);
	}
	public LibrarySystem() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setLocationByPlatform(true);//级联窗口位置
		setBounds(300,50,1200,900);
		setResizable(false);
		setTitle("图书馆管理系统");
		JMenuBar menuBar = createMenu(); // 调用创建菜单栏的方法
		setJMenuBar(menuBar);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // 窗体背景

		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();//非负整数高度宽度
				label.setSize(e.getComponent().getSize());
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/LibraryBounds.jpg")//background
						+ "'></html>");
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
	}

	
	/**
	 * 创建菜单栏
	 */
	private JMenuBar createMenu() { // 创建菜单栏的方法
		JMenuBar menuBar = new JMenuBar();

		JMenu BookManagementMenu = new JMenu(); // -----------------图书管理
		BookManagementMenu.setIcon(CreatedIcon.add("tushuguanli.jpg"));
		
		JMenu BookKeepIMenu = new JMenu("图书维护");
		BookKeepIMenu.add(MenuActions.BOOK_ADD);//图书信息添加
		BookKeepIMenu.add(MenuActions.BOOK_UPDATE_DELETE);//图书信息更新、删除
		BookKeepIMenu.add(MenuActions.BOOK_FIND);//图书查询
		
		JMenu BorrowBorrowIMenu = new JMenu("借阅管理");
		JMenuItem BorrowHistoryMItem = new JMenuItem("借阅记录查询");
		BorrowBorrowIMenu.add(MenuActions.BOOK_BORROW);
		BorrowBorrowIMenu.add(MenuActions.BOOK_RETURN);
		BorrowBorrowIMenu.add(MenuActions.BORROW_HISTORY);
		
		BookManagementMenu.add(BookKeepIMenu);
		BookManagementMenu.add(BorrowBorrowIMenu);
		
		
		

		
		JMenu ReaderManageMemu = new JMenu(); //----------------------读者管理
		ReaderManageMemu.setIcon(CreatedIcon.add("duzheguanli.jpg"));
		
		ReaderManageMemu.add(MenuActions.READER_ADD);
		ReaderManageMemu.add(MenuActions.READER_UPDATE);
		
		JMenu UserManageMenu = new JMenu(); // ---------------用户管理
		 UserManageMenu.setIcon(CreatedIcon.add("yonghuguanli.jpg"));
		 UserManageMenu.add(MenuActions.USE);

		
		
		JMenu sysManageMenu = new JMenu(); // -------------------系统数据设置
		sysManageMenu.setIcon(CreatedIcon.add("xitong.jpg"));
		JMenu ReaderTypeMItem = new JMenu("读者类别设置");
		JMenu BookTypeMItem = new JMenu("图书类型设置");
		
		sysManageMenu.add(BookTypeMItem);
		sysManageMenu.add(ReaderTypeMItem);
		sysManageMenu.add(MenuActions.FINE);
		BookTypeMItem.add(MenuActions.BOOK_TYPE_ADD);
		BookTypeMItem.add(MenuActions.BOOK_TYPE_FIT);
		ReaderTypeMItem.add(MenuActions.READER_TYPE_ADD);
		ReaderTypeMItem.add(MenuActions.READER_TYPE_FIT);
		JMenu HelpMenu = new JMenu(); // -------------------帮助
		HelpMenu.setIcon(CreatedIcon.add("help.jpg"));
		HelpMenu.add(MenuActions.HELP);
		JMenu OfMenu = new JMenu(); // -------------------关于
		OfMenu.setIcon(CreatedIcon.add("guanyu.jpg"));
		OfMenu.addMouseListener(new MouseAdapter() {  
	                public void mouseClicked(MouseEvent event) {  
	                	JOptionPane.showMessageDialog(null, "Version 1.1.0\nCopyright © 2017 bear.All Rights Reserve. 小熊科技","关于",JOptionPane.WARNING_MESSAGE); 
	                }  
		});

		 
		
		JMenu Find = new JMenu();//---------------查询
		Find.add(MenuActions.READER_MAIN_SELECT);//*****************************
		Find.setIcon(CreatedIcon.add("chaxun.jpg"));
		FindText.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {	if(FindText.getText().equals("输入读者姓名："))
                FindText.setText("");
            }
            @Override
            public void focusLost(FocusEvent e)
            {	if(FindText.getText().equals(""))
                FindText.setText("输入读者姓名：");
            }
        });
	
		Find.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent e) {  
            	JTextField temp= new JTextField();
            	if(FindText.getText().length()==0||FindText.getText().equals("输入读者姓名："))
            	JOptionPane.showMessageDialog(null, "请输入查询内容","提示",JOptionPane.WARNING_MESSAGE); 
            	else {
            	temp.setText(FindText.getText());
            	/*	if()*/
            	
       }
            }
            });
		

		
		menuBar.add(BookManagementMenu); // 添加图书管理菜单到菜单栏
		menuBar.add(ReaderManageMemu); // 添加读者管理菜单到菜单栏
		menuBar.add(UserManageMenu); // 添加用户管理菜单到菜单栏
		menuBar.add(sysManageMenu); // 添加系统数据设置菜单到菜单栏
		menuBar.add(HelpMenu);//添加帮助菜单到菜单栏
		menuBar.add(OfMenu);//添加关于菜单到菜单栏
		menuBar.add(FindText);
		menuBar.add(Find);
		return menuBar;
	}
}
