package Frame;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import Jcom.Item;
import Dao.Dao;
import TableModel.booktype;
import TableModel.book;
import util.Document;
import util.CreatedIcon;
/**
 * 名称：图书添加窗体
 * 
 */
public class BookAdd extends JInternalFrame {
	private JTextField b_id;//图书编号
	private JComboBox b_type;//图书类别
	private JTextField b_name;//图书名称
	private JTextField author;//作者
	private JTextField press;//出版社
	private JFormattedTextField press_date;//出版日期
	private JTextField   price;//价格
	private JTextField page_total;//书籍页码
	private JFormattedTextField b_addtime;//登记日期
	private JTextField keyword;//关键词
	private JTextField b_brief;//备注
	private JButton buttonadd;
	private JButton buttonclose;
	DefaultComboBoxModel bookTypeModel;
	
	Map map=new HashMap();
	
	public BookAdd() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);							// 设置窗体可最小化－－－必须
		setClosable(true);								// 设置窗体可关闭－－－必须
		setTitle("图书信息添加");						// 设置窗体标题－－－必须
		setBounds(100, 100, 600, 350);					// 设置窗体位置和大小－－－必须

		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		final JLabel label_1 = new JLabel();//图书编号
		label_1.setText("图书编号：");
		panel.add(label_1);
		b_id = new JTextField("请输入8位书号",8);
		b_id.setDocument(new Document(8)); //设置书号文本框最大输入值为8
		b_id.setColumns(8);
		b_id.addKeyListener(new b_idkeyListener());
		b_id.addKeyListener(new Book_idListener());
		//b_id.addFocusListener(new ISBNFocusListener());
		panel.add(b_id);

		
		final JLabel label_2 = new JLabel(); //图书类别
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setText("类别：");
		panel.add(label_2);
		b_type = new JComboBox();
		bookTypeModel= (DefaultComboBoxModel)b_type.getModel();
		
		//从数据库中取出图书类别
		List list=Dao.selectBookType();
		for(int i=0;i<list.size();i++){
			booktype Booktype=(booktype)list.get(i);
			Item item=new Item();
			item.setId(Booktype.getTypeid());
			item.setName((String)Booktype.getTypename());
			bookTypeModel.addElement(item);
		}
		panel.add(b_type);

		final JLabel label_3 = new JLabel();//图书名称
		label_3.setText("书名：");
		panel.add(label_3);
		b_name = new JTextField();
		b_name.setDocument(new Document(50));
		panel.add(b_name);

		final JLabel label_4 = new JLabel();//作者姓名
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("作者：");
		panel.add(label_4);
		author = new JTextField();
		author.setDocument(new Document(10));
		panel.add(author);

		final JLabel label_5 = new JLabel();//出版社名称
		label_5.setText("出版社：");
		panel.add(label_5);
		press = new JTextField();
		panel.add(press);

		final JLabel label_6 = new JLabel();//出版日期
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setText("出版日期：");
		panel.add(label_6);
		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		press_date= new JFormattedTextField(myfmt.getDateInstance());
		press_date.setValue(new java.util.Date());
		panel.add(press_date);
		
		final JLabel label_7 = new JLabel();//价格
		label_7.setText("单价：");
		panel.add(label_7);
		  price = new JTextField();
		  price.setDocument(new Document(6));
		  price.addKeyListener(new PriceListener());
		panel.add(price);
		
		final JLabel label_8 = new JLabel(); //书籍页码
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setText("页码：");
		panel.add(label_8);
		page_total = new JTextField();
		page_total.setDocument(new Document(4));
		page_total.addKeyListener(new Page_totalListener());
		panel.add(page_total);
		
		final JLabel label_9 = new JLabel();//登记日期
		label_9.setText("登记日期：");
		panel.add(label_9);
		SimpleDateFormat addt=new SimpleDateFormat("yyyy-MM-dd");
		b_addtime= new JFormattedTextField(addt.getDateInstance());
		b_addtime.setValue(new java.util.Date());
		b_addtime.setEnabled(false);
		panel.add(b_addtime);
		
		final JLabel label_10 = new JLabel(); //关键词
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setText("关键词：");
		panel.add(label_10);
		keyword = new JTextField();
		panel.add(keyword);
		
		
		final JLabel label_11 = new JLabel(); //备注
		label_11.setText("备注：");
		panel.add(label_11);
		b_brief = new JTextField();
		panel.add(b_brief);
    //*********************************************************************
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
	
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.CENTER);
		panel_1.setLayout(flowLayout);

		buttonadd= new JButton();
		buttonadd.addActionListener(new addBookActionListener());
		buttonadd.setText("添加");
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.addActionListener(new CloseActionListener());
		buttonclose.setText("关闭");
		panel_1.add(buttonclose);
    //***************************************************************
		final JLabel label_0 = new JLabel();
		ImageIcon bookAddIcon=CreatedIcon.add("BookAdd.jpg");
		label_0.setIcon(bookAddIcon);
		label_0.setPreferredSize(new Dimension(80, 100));
		label_0.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(label_0, BorderLayout.NORTH);
		setVisible(true);											// 显示窗体可关闭－－－必须在添加所有控件之后执行该语句
	}
	private KeyListener Page_totalListener() {
		// TODO Auto-generated method stub
		return null;
	}
	/*class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(!Dao.selectBookInfo(ISBN.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "添加书号重复！");
				return;
			}
		}
	}*/
	class b_idkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 13){
				buttonadd.doClick();
			}
		
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	
	class addBookActionListener implements ActionListener {		// 添加按钮的单击事件监听器
		
		public void actionPerformed(final ActionEvent e) {	

			if(b_id.getText().length()==0){
				JOptionPane.showMessageDialog(null, "书号未输入");
				return;
			}
			if(b_id.getText().length()<8){
				JOptionPane.showMessageDialog(null, "书号为8位！");
				return;
			}
			if(b_name.getText().length()==0){
				JOptionPane.showMessageDialog(null, "书名未输入");
				return;
			}
		
				if(author.getText().length()==0){
				JOptionPane.showMessageDialog(null, "作者未输入");
				return;
			}
			if(press.getText().length()==0){
				JOptionPane.showMessageDialog(null, "出版社未输入");
				return;
			}
			
			if(press_date.getText().length()==0){
				JOptionPane.showMessageDialog(null, "出版日期未输入");
				return;
			}
			if(page_total.getText().length()==0){
				JOptionPane.showMessageDialog(null, "页码未输入");
				return;
			}
			if(price.getText().length()==0){
				JOptionPane.showMessageDialog(null, "单价未输入");
				return;
			}
			if(b_addtime.getText().length()==0){
				JOptionPane.showMessageDialog(null, "登记日期未输入");
				return;
			}
			// *********************赋值插入表中↓
			String b_ids=b_id.getText().trim();
			
			//分类
			Object selectedItem = b_type.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item) selectedItem;
			String b_types=item.getName();
			String b_names=b_name.getText().trim();
			String authors=author.getText().trim();
			String presss=press.getText().trim();
			String press_dates=press_date.getText().trim();
			String prices =price.getText().trim();
			String page_totals=page_total.getText().trim();
			String b_addtimes=b_addtime.getText().trim();
			String keywords=keyword.getText().trim();
			String b_briefs=b_brief.getText().trim();
			Boolean isborrows = false;
			int i=Dao.InsertBook(b_ids,b_names,b_types, authors,presss,java.sql.Date.valueOf(press_dates),Double.parseDouble(prices),Integer.parseInt(page_totals),keywords,java.sql.Date.valueOf(b_addtimes),isborrows,b_briefs);
			if(i==1){
			
				JOptionPane.showMessageDialog(null, "添加成功");
				doDefaultCloseAction();
			}
			else
				JOptionPane.showMessageDialog(null, "添加未成功！");
			doDefaultCloseAction();
		}
	}
	class PriceListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	class Page_totalListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789";
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	class Book_idListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789ASDFGHJKLQWERTYUIOPMNBVCXZ";
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	

	
}