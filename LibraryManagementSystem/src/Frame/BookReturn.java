package Frame;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Jcom.MapItem;
import Dao.Dao;
import TableModel.back;
import TableModel.booktype;
import TableModel.borrow;
import util.Document;
import util.Document;
public class BookReturn  extends JInternalFrame {
		//private Operater user = Login.getUser(); 
private JTable table;
private JTextField operator;
private JTextField todaydate;
private JTextField punish;
private JTextField overdays;
private JTextField realdays;
private JTextField deadline;
private JTextField borrow_date;
private JTextField readerId;
private String[] columnNames = { "借出记录号","图书编号","图书名称","图书类别","读者姓名","读者类别","借书时间","应还时间" };
DefaultTableModel model = new DefaultTableModel();
SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
DecimalFormat    df   = new DecimalFormat("######0.00");
private String bookIds=null;
private String readerIds=null;
		
		public final void add() {
			readerIds=readerId.getText().trim();
			List list=Dao.selectBorr_Info(readerIds);
			for(int i=0;i<list.size();i++){
				List back=(List)list.get(i);
				String str[] = new String[8];
				str[0] =(String)back.get(0);
				str[1] =(String)back.get(1);
				str[2]=(String)back.get(2);
				str[3] =(String)back.get(3);
				str[4] =(String)back.get(4);
				str[5] =(String)back.get(5);
				str[6]=(String)back.get(6);
				str[7]=(String)back.get(7);
				model.addRow(str);
				System.out.println((String)back.get(7));
			}

		}
		
		/**
		 * Create the frame
		 */
		public BookReturn() {
			super();
			setIconifiable(true);							// 设置窗体可最小化－－－必须
			setClosable(true);								// 设置窗体可关闭－－－必须
			setTitle("图书归还管理");
			setBounds(100, 100, 600, 600);

			final JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "基本信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			panel.setPreferredSize(new Dimension(0, 250));
			getContentPane().add(panel, BorderLayout.NORTH);

			final JPanel panel_5 = new JPanel();
			final GridLayout gridLayout_1 = new GridLayout(0, 2);
			gridLayout_1.setVgap(5);
			panel_5.setLayout(gridLayout_1);
			panel_5.setPreferredSize(new Dimension(200, 30));
			panel.add(panel_5);

			final JLabel label_4 = new JLabel();
			label_4.setText("读者编号：");
			panel_5.add(label_4);

			readerId = new JTextField();
			readerId.setDocument(new Document(13));
			readerId.addKeyListener(new readerISBNListenerlostFocus());
			panel_5.add(readerId);

			final JPanel panel_4 = new JPanel();
			panel_4.setLayout(new BorderLayout());
			panel_4.setPreferredSize(new Dimension(550, 180));
			panel.add(panel_4);

			final JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(550, 140));
			panel_4.add(scrollPane);
			table = new JTable();
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrollPane.setViewportView(table);
			model.setColumnIdentifiers(columnNames);
			table.setModel(model);
			table.addMouseListener(new TableListener());
			
//******************************************************
			final JPanel panel_1 = new JPanel();
			getContentPane().add(panel_1);

			final JPanel panel_2 = new JPanel();
			final GridLayout gridLayout_2 = new GridLayout(0, 2);
			gridLayout_2.setVgap(20);
			panel_2.setLayout(gridLayout_2);
			panel_2.setBorder(new TitledBorder(null, "罚款信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			panel_2.setPreferredSize(new Dimension(265, 260));
			panel_1.add(panel_2);

			final JLabel label_11 = new JLabel();
			label_11.setText("借书时间：");
			panel_2.add(label_11);

			borrow_date = new JTextField();
			borrow_date.setEditable(false);

			panel_2.add(borrow_date);

			final JLabel label_12 = new JLabel();
			label_12.setText("借书期限：");
			panel_2.add(label_12);

			deadline = new JTextField();
			deadline.setEditable(false);
			panel_2.add(deadline);

			final JLabel label_13 = new JLabel();
			label_13.setText("实际天数：");
			panel_2.add(label_13);

			realdays = new JTextField();
			realdays.setEditable(false);
			panel_2.add(realdays);

			final JLabel label_14 = new JLabel();
			label_14.setText("超出天数：");
			panel_2.add(label_14);

			overdays = new JTextField();
			overdays.setEditable(false);
			panel_2.add(overdays);

			final JLabel label_15 = new JLabel();
			label_15.setText("罚款金额：");
			panel_2.add(label_15);

			punish = new JTextField();
			punish.setDocument(new Document(10));
			punish.setEditable(false);
			panel_2.add(punish);

			final JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "系统信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			panel_3.setPreferredSize(new Dimension(265, 260));
			panel_1.add(panel_3);

			final JPanel panel_7 = new JPanel();
			final GridLayout gridLayout_3 = new GridLayout(0, 2);
			gridLayout_3.setVgap(35);
			panel_7.setLayout(gridLayout_3);
			panel_7.setPreferredSize(new Dimension(255, 100));
			panel_3.add(panel_7);

			final JLabel label_10_1 = new JLabel();
			label_10_1.setText("当前时间：");
			panel_7.add(label_10_1);

			todaydate = new JTextField();
			todaydate.setEditable(false);
			todaydate.setPreferredSize(new Dimension(0, 0));
			todaydate.addActionListener(new TimeActionListener());
			todaydate.setFocusable(false);
			panel_7.add(todaydate);

			final JLabel label_11_1 = new JLabel();
			label_11_1.setText("操作员：");
			panel_7.add(label_11_1);

			operator  =new JTextField(Login.getUser().getId());
			operator.setEditable(false);
			panel_7.add(operator);
			
			final JButton buttonback = new JButton();
			buttonback.setText("图书归还");
			buttonback.addActionListener(new BookBackActionListener(model));
			panel_3.add(buttonback);

			final JButton buttonExit= new JButton();
			buttonExit.setText("退出");
			buttonExit.addActionListener(new CloseActionListener());
			panel_3.add(buttonExit);
			setVisible(true);
			//
		}
		
		
		class readerISBNListenerlostFocus extends KeyAdapter{
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
					add();
				}
			}
		}
		class TimeActionListener implements ActionListener{
			public TimeActionListener(){
				Timer t=new Timer(1000,this);
				t.start();
			}
			public void actionPerformed(ActionEvent ae){
				todaydate.setText(myfmt.format(new java.util.Date()).toString());
			}
		}
		class TableListener extends MouseAdapter {
			public void mouseClicked(final MouseEvent e) {
				java.util.Date date=new java.util.Date();
				String fk="";
				String days1="";
				int selRow=table.getSelectedRow();
				
				borrow_date.setText(table.getValueAt(selRow, 6).toString().trim());
				
				int day = Dao.selectReaderTypeByTypename(table.getValueAt(selRow, 5).toString().trim());
				deadline.setText(String.valueOf(day));
				Date a = Dao.selectborrow_time(table.getValueAt(selRow, 0).toString().trim());
				
				long day1 = 0;
				try {
					day1 = (myfmt.parse(todaydate.getText()).getTime()-a.getTime())/(24*60*60*1000);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
				realdays.setText(String.valueOf(day1));
				
				if(day1-day<=0) {
					overdays.setText(String.valueOf(0));
					punish.setText(String.valueOf(0));
				}
				else {
					overdays.setText(String.valueOf(day1-day));
					punish.setText(String.valueOf(df.format(Dao.selectFine()*(day1-day))));
				}
			}
		}
		class BookBackActionListener implements ActionListener{
			private final DefaultTableModel model;

			BookBackActionListener(DefaultTableModel model) {
				this.model = model;
			}
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成方法存根
				if(readerIds==null){
					JOptionPane.showMessageDialog(null, "请输入读者编号！");
					return;
				}
				System.out.println(bookIds==null);

				if(table.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "请选择所要归还的图书！");
					return;	
				}

				int selRow=table.getSelectedRow();
				int i=Dao.InsertBack(readerId.getText(), table.getValueAt(selRow, 4).toString().trim(),table.getValueAt(selRow, 1).toString().trim()
						,table.getValueAt(selRow, 2).toString().trim(),table.getValueAt(selRow, 5).toString().trim(),Dao.selectBookprice(table.getValueAt(selRow, 1).toString().trim())
						,java.sql.Date.valueOf(table.getValueAt(selRow, 6).toString().trim()),java.sql.Date.valueOf(todaydate.getText().trim()),Double.valueOf(punish.getText().trim()),operator.getText().trim());
				System.out.print(i);
				 if(i==1){	
					Dao.updateBackisreturn(table.getValueAt(selRow, 1).toString().trim(),0);
					model.removeRow(selRow);
					JOptionPane.showMessageDialog(null, "还书操作完成！");
					borrow_date.setText("");
					deadline.setText("");
					realdays.setText("");
					overdays.setText("");
					punish.setText("");
				}
				 else JOptionPane.showMessageDialog(null, "还书操作失败！");
			}
		}
		class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		}
}


