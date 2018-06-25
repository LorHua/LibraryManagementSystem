package Frame;

	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.GridLayout;
	import java.awt.SystemColor;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.sql.Date;
	import java.text.NumberFormat;
	import java.text.SimpleDateFormat;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

	import javax.swing.DefaultComboBoxModel;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFormattedTextField;
	import javax.swing.JInternalFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import javax.swing.border.EmptyBorder;
	import javax.swing.border.LineBorder;
	import javax.swing.table.DefaultTableModel;

import Frame.BookAdd.b_idkeyListener;
import Jcom.Item;
import Jcom.MapItem;
import Dao.Dao;
	import TableModel.book;
	import TableModel.booktype;
	import util.CreatedIcon;
	import util.Document;

	/**
	 * 图书更新与删除
	 *
	 */
	public class BookUpdate  extends JInternalFrame {
		private JTable table;
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
		private String[] columnNames;
		private Map m=MapItem.getMap();

		//取数据库中图书相关信息放入表格中
		private Object[][] getFileStates(List list){
			String[] columnNames = { "图书编号", "图书名称", "图书类别", "作者", "出版社",
					"出版日期","价格", "书籍页码","登记日期","关键词","备注" };
			Object[][]results=new Object[list.size()][columnNames.length];
			
			for(int i=0;i<list.size();i++){
				book bookinfo=(book)list.get(i);
				results[i][0]=bookinfo.getId();
				results[i][1]=bookinfo.getName();
				results[i][2]=bookinfo.getType();
				results[i][3]=bookinfo.getAuthor();
				results[i][4]=bookinfo.getPress();
				results[i][5]=bookinfo.getPress_date();
				results[i][6]=bookinfo.getPrice();
				results[i][7]=bookinfo.getPage();
				results[i][8]=bookinfo.getAddtime();
				results[i][9]=bookinfo.getKeryword();
				results[i][10]=bookinfo.getBrief();
			}
			return results;
		         		
		}
		
		public BookUpdate() {
			super();
			final BorderLayout borderLayout = new BorderLayout();
			getContentPane().setLayout(borderLayout);
			setIconifiable(true);
			setClosable(true);
			setTitle("图书更新与清理");
			setBounds(100, 100, 800, 600);
//****************************************************panel_1 button
		
//**********************************************************panel_2 booktale
			final JLabel headLogo = new JLabel();
			ImageIcon bookModiAndDelIcon=CreatedIcon.add("BookUpdate.jpg");
			headLogo.setIcon(bookModiAndDelIcon);
			headLogo.setOpaque(true);
			headLogo.setBackground(Color.CYAN);
			headLogo.setPreferredSize(new Dimension(400, 80));
			headLogo.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
			getContentPane().add(headLogo, BorderLayout.NORTH);


			final JPanel panel_2 = new JPanel();
			final BorderLayout borderLayout_1 = new BorderLayout();
			borderLayout_1.setVgap(5);
			panel_2.setLayout(borderLayout_1);
			panel_2.setBorder(new EmptyBorder(5, 10, 5, 10));
			getContentPane().add(panel_2);

			final JScrollPane scrollPane = new JScrollPane();
			panel_2.add(scrollPane);

			Object[][] results=getFileStates(Dao.selectBook());
			columnNames = new String[]{"图书编号", "图书名称", "图书类别", "作者", "出版社",
					"出版日期","价格", "书籍页码","登记日期","关键词","备注"};
			table = new JTable(results,columnNames);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			//鼠标单击表格中的内容产生事件,将表格中的内容放入文本框中
			table.addMouseListener(new TableListener());
			scrollPane.setViewportView(table);
//****************************************************panel_3 book
			final JPanel panel_3 = new JPanel();
			panel_2.add(panel_3, BorderLayout.SOUTH);
			
			final GridLayout gridLayout = new GridLayout(0, 6);
			gridLayout.setVgap(5);
			gridLayout.setHgap(5);
			panel_3.setLayout(gridLayout);

			final JLabel label_1 = new JLabel();//图书编号
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			label_1.setText("图书编号：");
			panel_3.add(label_1);
			b_id = new JTextField(/*"请输入8位书号",8*/);
			b_id.setEditable(false);
			b_id.setDocument(new Document(8)); //书号最大输入值为8
			panel_3.add(b_id);
			
			final JLabel label_2 = new JLabel(); //图书类别
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
			label_2.setText("类别：");
			panel_3.add(label_2);
			b_type = new JComboBox();
			bookTypeModel= (DefaultComboBoxModel)b_type.getModel();
			//图书类别取出
			  List list=Dao.selectBookType();
			for(int i=0;i<list.size();i++){
				booktype Booktype=(booktype)list.get(i);
				Item item = new Item();
				item.setId((String)Booktype.getTypeid());
				item.setName((String)Booktype.getTypename());
				bookTypeModel.addElement(item);
				map.put(item.getId(), item);
				
			}
			panel_3.add(b_type);
			
			final JLabel label_3 = new JLabel();//图书名称
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
			label_3.setText("书名：");
			panel_3.add(label_3);
			b_name = new JTextField();
			panel_3.add(b_name);
			
			final JLabel label_4 = new JLabel();//作者姓名
			label_4.setHorizontalAlignment(SwingConstants.CENTER);
			label_4.setText("作者：");
			panel_3.add(label_4);
			author = new JTextField();
			author.setDocument(new Document(10));
			panel_3.add(author);
		
			final JLabel label_5 = new JLabel();//出版社名称
			label_5.setHorizontalAlignment(SwingConstants.CENTER);
			label_5.setText("出版社：");
			panel_3.add(label_5);
			press = new JTextField();
			panel_3.add(press);

			final JLabel label_6 = new JLabel();//出版日期
			label_6.setHorizontalAlignment(SwingConstants.CENTER);
			label_6.setText("  出版日期：");
			panel_3.add(label_6);
			SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
			press_date= new JFormattedTextField(myfmt.getDateInstance());
			press_date.setValue(new java.util.Date());
			panel_3.add(press_date);
		
			final JLabel label_7 = new JLabel();//价格
			label_7.setHorizontalAlignment(SwingConstants.CENTER);
			label_7.setText("单价：");
			panel_3.add(label_7);
			  price = new JTextField();
			  price.setDocument(new Document(6));
			  price.addKeyListener(new PriceListener());
			panel_3.add(price);
			
			final JLabel label_8 = new JLabel(); //书籍页码
			label_8.setHorizontalAlignment(SwingConstants.CENTER);
			label_8.setText("页码：");
			panel_3.add(label_8);
			page_total = new JTextField();
			page_total.setDocument(new Document(4));
			page_total.addKeyListener(new Page_totalListener());
			panel_3.add(page_total);
			
			final JLabel label_9 = new JLabel();//登记日期
			label_9.setHorizontalAlignment(SwingConstants.CENTER);
			label_9.setText("  登记日期：");
			panel_3.add(label_9);
			//SimpleDateFormat addt=new SimpleDateFormat("yyyy-MM-dd");
			b_addtime= new JFormattedTextField(/*addt.getDateInstance()*/);
			//b_addtime.setValue(new java.util.Date());
			b_addtime.setEnabled(false);
			panel_3.add(b_addtime);
			
			final JLabel label_10 = new JLabel(); //关键词
			label_10.setHorizontalAlignment(SwingConstants.CENTER);
			label_10.setText("关键词：");
			panel_3.add(label_10);
			keyword = new JTextField();
			panel_3.add(keyword);
			
			
			final JLabel label_11 = new JLabel(); //备注
			label_11.setHorizontalAlignment(SwingConstants.CENTER);
			label_11.setText(" 备注：");
			panel_3.add(label_11);
			b_brief = new JTextField();
			panel_3.add(b_brief);
			
			final JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
			getContentPane().add(panel_1, BorderLayout.SOUTH);
			final FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(2);
			flowLayout.setHgap(30);
			flowLayout.setAlignment(FlowLayout.CENTER);
			panel_1.setLayout(flowLayout);

			final JButton button = new JButton();
			button.addActionListener(new addBookActionListener());
			button.setText("修改");
			panel_1.add(button);

			final JButton button_2 = new JButton();
			button_2.addActionListener(new ActionListener(){
				public void actionPerformed(final ActionEvent e) {
					String b_ids=b_id.getText().trim();
					int i=Dao.deleteBook(b_ids);
					if(i==1){
						JOptionPane.showMessageDialog(null, "删除成功");
						
						Object[][] results=getFileStates(Dao.selectBook());
						//注释代码为使用表格模型
						DefaultTableModel model=new DefaultTableModel();
						 model.setDataVector(results, columnNames);
						table.setModel(model);
						
					}
				}
			});
			button_2.setText("删除");
			panel_1.add(button_2);

			
			final JButton button_1 = new JButton();
			button_1.setText("关闭");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					doDefaultCloseAction();
				}
			});
			
			panel_1.add(button_1);
			setVisible(true);
		
		}
		
		
		
		
		
		
		
		class TableListener extends MouseAdapter {
			public void mouseClicked(final MouseEvent e) {
				String b_ids,b_names,b_types,authors,presss,press_dates,prices,page_totals,keywords,b_addtimes,isborrows,b_briefs;
				int talRow = table.getSelectedRow();
				b_id.setText(table.getValueAt(talRow, 0).toString().trim());
				b_name.setText( table.getValueAt(talRow, 1).toString().trim());
				bookTypeModel.setSelectedItem(table.getValueAt(talRow, 2).toString().trim());
				author.setText(table.getValueAt(talRow, 3).toString().trim());
				press.setText(table.getValueAt(talRow, 4).toString().trim());
				press_date.setText(table.getValueAt(talRow, 5).toString().trim());
				price.setText(table.getValueAt(talRow, 6).toString().trim());
				page_total.setText( table.getValueAt(talRow, 7).toString().trim());
				b_addtime.setText(table.getValueAt(talRow, 8).toString().trim());
				keyword.setText( table.getValueAt(talRow, 9).toString().trim());
				//isborrow. table.getValueAt(talRow, 10).toString().trim());
				b_brief.setText(table.getValueAt(talRow, 10).toString().trim());
			
				for(int i=0;i<bookTypeModel.getSize();i++){
					Item o=(Item)bookTypeModel.getElementAt(i);
					if(o.getName().equals(b_type.getSelectedItem())){
						bookTypeModel.setSelectedItem(o);
					}
				}
			
		
				
				
			}
		}
		class addBookActionListener implements ActionListener {
			public void actionPerformed(final ActionEvent e) { // 添加按钮的单击事件监听器
				// 
				if(b_id.getText().length()==0){
					JOptionPane.showMessageDialog(null, "书号未输入");
					return;
				}
				if(b_id.getText().length()!=8){
					JOptionPane.showMessageDialog(null, "书号输入位数为8位");
					return;
				}
				if(b_name.getText().length()==0){
					JOptionPane.showMessageDialog(null, "图书名称未输入");
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
				
				if(price.getText().length()==0){
					JOptionPane.showMessageDialog(null, "单价未输入");
					return;
				}
				
				
				
				
				
				
		/*		String b_ids=b_id.getText().trim();
			//分类
				Object selectedItem = bookTypeModel.getSelectedItem();
				if (selectedItem == null)
					return;
				Item item = (Item)selectedItem;
				String b_names=b_name.getText().trim();
				String b_types=item.getName();
				String authors=author.getText().trim();
				String presss=press.getText().trim();
				String press_dates=press_date.getText().trim();
				String prices =price.getText().trim();
				String page_totals=page_total.getText().trim();
				String b_addtimes=b_addtime.getText().trim();
				String keywords=keyword.getText().trim();
				String b_briefs=b_brief.getText().trim();
				*/
				String type=String.valueOf(b_type.getSelectedItem());
				int i=Dao.updateBook(b_id.getText().trim(),b_name.getText().trim(),type, author.getText().trim(),press.getText().trim(),Date.valueOf(press_date.getText().trim()),Double.valueOf(price.getText().trim()),Integer.valueOf(page_total.getText().trim()),keyword.getText().trim(),Date.valueOf(b_addtime.getText().trim()),b_brief.getText().trim());
				System.out.println(i);
				
				if(i==1){

					JOptionPane.showMessageDialog(null, "修改成功");
					Object[][] results=getFileStates(Dao.selectBook());
					//注释代码为使用表格模型
					DefaultTableModel model=new DefaultTableModel();
					table.setModel(model);
					model.setDataVector(results, columnNames);
					
					
				}
				else JOptionPane.showMessageDialog(null, "修改失败");
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

	}
	
		
		

	
		

	

