package Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import Jcom.MapItem;
import Dao.Dao;
import TableModel.book;
import TableModel.booktype;
import TableModel.borrow;
import TableModel.reader;
import TableModel.readertype;
import Frame.UserManagement;
import util.Document;
import Frame.Login;
public class BookBorrow extends JInternalFrame {
	//private Operater user = BookLoginIFrame.getUser(); 
		
	private final JTextField b_admin;
	private JTable table;
	private JTextField author;
	private JTextField press;
	private JTextField b_type;//图书类别
	private JTextField b_name;
	private JTextField b_id;//图书编码
	private JTextField r_typename;//读者类别
	private JTextField deadline;
	private JTextField canborrow_int;//可借册数
	private JTextField r_name;//读者姓名
	private JTextField r_id;//读者编号
	private JTextField price ;
	private JFormattedTextField current1;
	private Map map = MapItem.getMap();
	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");

//***********************************************

	public BookBorrow() {
		super();
//		addInternalFrameListener(new InternalFrameAdapter() {
//			public void internalFrameClosing(InternalFrameEvent e) {
//			}
//		});//关闭窗口时候引发的事件
		//System.out.println(user.getName());
			
//********************************************************************
		setTitle("图书借阅管理");
		setIconifiable(true); // 设置窗体可最小化－－－必须
		setClosable(true); // 设置窗体可关闭－－－必须
		setBounds(100, 100, 600, 400);

		final JPanel panel = new JPanel();
		getContentPane().add(panel);

			
		final JPanel panel_1 = new JPanel();//用于显示读者|图书
		panel_1.setPreferredSize(new Dimension(550, 220));
		getContentPane().add(panel_1, BorderLayout.NORTH);
		final JSplitPane splitPane = new JSplitPane();
		panel_1.add(splitPane);
//**********************************************************************读者信息栏
		final JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(265, 200));
		splitPane.setLeftComponent(panel_3);

		final JPanel panel_5 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(2);
		gridLayout.setVgap(10);
		panel_5.setLayout(gridLayout);
		panel_5.setPreferredSize(new Dimension(220, 180));
		panel_3.add(panel_5);
			
		final JLabel label = new JLabel();
		label.setText("读者编号：");
		panel_5.add(label);
		r_id = new JTextField();
		r_id.setDocument(new Document(6));
		r_id.setDocument(new Document(13));
		//r_id.addKeyListener(new ISBNListenerlostFocus());
		panel_5.add(r_id);

		final JLabel label_1 = new JLabel();
		label_1.setText("读者姓名：");
		panel_5.add(label_1);
		r_name = new JTextField();
		r_name.setEditable(false);
		panel_5.add(r_name);
			
		final JLabel label1_3 = new JLabel();
		label1_3.setText("读者类别：");
		panel_5.add(label1_3);
		r_typename = new JTextField();
		r_typename.setEditable(false);
		panel_5.add(r_typename);
			
		final JLabel label_2 = new JLabel();
		label_2.setText("可借数量：");
		panel_5.add(label_2);
		canborrow_int = new JTextField();
		canborrow_int.setEditable(false);
		panel_5.add(canborrow_int);

		final JLabel label_4 = new JLabel();
		label_4.setText("借书期限");
		panel_5.add(label_4);
		deadline = new JTextField();
		deadline.setEditable(false);
		panel_5.add(deadline);
		Icon icon = new ImageIcon("1.gif");
//**********************************************************************图书信息栏目
		final JPanel panel_0 = new JPanel();
		panel_0.setPreferredSize(new Dimension(265, 200));
		splitPane.setRightComponent(panel_0);
			
		final JPanel panel_4 = new JPanel();
		final GridLayout gridLayout_1 = new GridLayout(0, 2);
		gridLayout_1.setVgap(10);
		panel_4.setLayout(gridLayout_1);
		panel_4.setPreferredSize(new Dimension(220, 180));
		panel_0.add(panel_4);

			
		final JLabel label_5 = new JLabel();
		label_5.setText("图书编号：");
		panel_4.add(label_5);
		b_id = new JTextField();
		b_id.setDocument(new Document(8));
		b_id.addKeyListener(new bookISBNListenerlostFocus());
		panel_4.add(b_id);

		final JLabel label_6 = new JLabel();
		label_6.setText("图书名称：");
		panel_4.add(label_6);
		b_name = new JTextField();
		b_name.setEditable(false);
		panel_4.add(b_name);
		
		price = new JTextField();
		
		final JLabel label_7 = new JLabel();
		label_7.setText("图书类别：");
		panel_4.add(label_7);
		b_type = new JTextField();
		b_type.setEditable(false);
		panel_4.add(b_type);

		final JLabel label_8 = new JLabel();
		label_8.setText("图书作者：");
		panel_4.add(label_8);
		author = new JTextField();
		author.setEditable(false);
		panel_4.add(author);
			
		final JLabel label2_5 = new JLabel();
		label2_5.setText(" 出版社： ");
		panel_4.add(label2_5);
		press = new JTextField();
		press.setEditable(false);
		panel_4.add(press);
		
		final JTextField price = new JTextField();
//*****************************************************当前时间，操作员，按钮操作栏目
		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(550, 120));
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JPanel panel_7 = new JPanel();
		final GridLayout gridLayout_2 = new GridLayout(0, 2);
		gridLayout_2.setVgap(10);
		panel_7.setLayout(gridLayout_2);
		panel_7.setPreferredSize(new Dimension(280, 70));
		panel_2.add(panel_7);

			final JLabel label_9 = new JLabel();
			label_9.setText("当前时间：");
			panel_7.add(label_9);
			SimpleDateFormat addt=new SimpleDateFormat("yyyy-MM-dd");
			current1 = new JFormattedTextField(addt.getDateInstance());
			current1.setValue(new java.util.Date());
			current1.setEditable(false);
			current1.setPreferredSize(new Dimension(0, 0));
			current1.setFocusable(false);
			panel_7.add(current1);
			
			
			final JLabel label_11 = new JLabel();
			label_11.setText("借出管理员：");
			panel_7.add(label_11);
			b_admin  =new JTextField(Login.getUser().getId());
			b_admin.setEditable(false);
			panel_7.add(b_admin);

			final JPanel panel_8 = new JPanel();
			panel_8.setLayout(new FlowLayout());
			panel_8.setPreferredSize(new Dimension(200, 70));
			panel_2.add(panel_8);

			final JButton buttonBorrow = new JButton();
			buttonBorrow.setText(" 借出当前图书 ");
			buttonBorrow.addActionListener(new BorrowActionListener());
			panel_8.add(buttonBorrow);
			
			final JButton buttonClear = new JButton();
			buttonClear.setText(" 清空输入编号 ");
			//buttonClear.addActionListener(new ClearActionListener(model));
			panel_8.add(buttonClear);
			
			
			setVisible(true);
			
		}
		
class bookISBNListenerlostFocus extends KeyAdapter {
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
			if (r_id.getText().trim().length()!=0
					&& b_id.getText().trim().length()!=0) {
				String b_ids = b_id.getText().trim();
				List list = Dao.selectBookById(b_ids);
				
				for (int i = 0; i < list.size(); i++) {
					book Book = (book) list.get(i);
					b_name.setText(Book.getName());
					b_type.setText(Book.getType());
					author.setText(Book.getAuthor());
					press.setText(Book.getPress());
					//price.setText(Book.getPrice());
					price.setText(String.valueOf(Book.getPrice()));
					
				}
				String r_ids = r_id.getText().trim();
				
				List list1 = Dao.selectReaderById(r_ids);
				for (int i = 0; i < list1.size(); i++) {
					reader Reader = (reader) list1.get(i);
					r_name.setText(Reader.getName());
					r_typename.setText(Reader.getType());
					}
				List list2 = Dao.selectReaderType(r_typename.getText());
				for(int i = 0; i < list1.size(); i++) {
					readertype Readertype = (readertype) list2.get(i);
					canborrow_int.setText(String.valueOf( Readertype.getCanborrowint()));
					deadline.setText(String.valueOf(Readertype.getDeadline()));
				}
				
		
				List list5 = Dao.selectReaderById(r_ids);// 此读者是否在reader表中
				List list4 = Dao.selectBookById(b_ids);// 此书是否在book表中
				if (!r_ids.isEmpty() && list5.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"此读者编号没有注册，查询输入读者编号是否有误！");
					return;
				}
				if (list4.isEmpty() && !b_ids.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"本图书馆没有此书，查询输入图书编号是否有误！");
					return;
				}
				/*List list6 = Dao.selectBookNumber(r_ids);
				if (list6.get(0)>) {
					JOptionPane.showMessageDialog(null, "借书量已经超过最大借书量！");
					return;
				}*/
			
			
		}
	}
}
}
		class BorrowActionListener implements ActionListener { 
			public void actionPerformed(final ActionEvent e) {

				
				String r_ids=r_id.getText().trim();
				String r_names=r_name.getText().trim();
				String b_ids=b_id.getText().trim();
				String b_names=b_name.getText().trim();
				String b_types=b_type.getText().trim();
				
				double prices=Double.parseDouble(price.getText().trim());
				//String borrow_dates=current1.getText().trim();
				//String b_admins 
				
				//System.out.println(borrowDate);
				//System.out.println(java.sql.Timestamp.valueOf(backDate));
				java.sql.Date borrow_dates = new java.sql.Date(new java.util.Date().getTime());
				int d = Integer.valueOf(deadline.getText().trim());
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, d);
				java.util.Date es = (java.util.Date)cal.getTime();
				java.sql.Date  return_dates= new java.sql.Date(es.getTime());
				
				List list_0= Dao.selectBook(b_ids);
				book Book=(book) list_0.get(0);
				Boolean is = Book.getIsborrow();
				int n = Dao.selectBookNumber(r_id.getText().trim(),b_type.getText().trim());
				if(is == false&&n==0) {
					int i=Dao.InsertBorrow(r_ids,r_names,b_ids,b_names,b_types,prices,borrow_dates,return_dates,"ssd");
				if(i==1){
					JOptionPane.showMessageDialog(null, "图书借阅完成！");
					
					Dao.updateBook(b_ids,1);
					b_type.setText("");
					b_id.setText("");
					press.setText("");
					author.setText("");
					b_name.setText("");
					
				}
				else {JOptionPane.showMessageDialog(null, "图书借阅失败！");}
			}
			else if(is==true&&n==0){
				JOptionPane.showMessageDialog(null, "失败，图书已被借出！");
			}
			else if(n==1){
				JOptionPane.showMessageDialog(null, "失败，读者已借书量超额！");
			}
		}

		}
}




