package Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import Jcom.Item;
import Jcom.MapItem;	
import Dao.Dao;
import TableModel.borrow;
import TableModel.back;

public class BorrowHistory  extends JInternalFrame {
	private JTextField textField_1,textField_2;
	private JComboBox comboBox_1,comboBox_2;
	private JTable table_1, table_2;
	private JComboBox choice1,choice2;
	private JScrollPane scrollPane_1, scrollPane_2;
	//private Map m=MapPz.getMap();


	/**
	 * Launch the application
	 * 
	 * @param args
	 */

	/**
	 * Create the frame
	 */
	String borrowhistory[] = { "借出记录号", "读者编号", "读者姓名", "图书编号","图书名称",  "图书类别", "图书价格","借出日期","归还日期",  "是否归还", "借出管理员"};
	String returnhistory[] = { "归还记录号", "读者编号", "读者姓名", "图书编号","图书名称",  "图书类别", "图书价格","借出日期","归还日期",  "超期罚金", "归还管理员"};
	private Object[][] getselectborrow(List list) {
		Object[][] b = new Object[list.size()][11];
		for (int i = 0; i < list.size(); i++) {
			borrow Borrow = (borrow) list.get(i);
			b[i][0] = Borrow.getBorr_record();
			b[i][1] = Borrow.getR_id();
			b[i][2] = Borrow.getR_name();
			b[i][3] = Borrow.getB_id();
			b[i][4] = Borrow.getB_name();
			b[i][5] = Borrow.getB_type();
			b[i][6] = Borrow.getPrice();
			b[i][7] = Borrow.getBorrow_date();
			b[i][8] = Borrow.getReturn_date();
			b[i][9] = Borrow.getIsreturn();
			b[i][10] = Borrow.getB_admin();
		
		}
		return b;

	}
	private Object[][] getselectreturn(List list) {
		Object[][] r = new Object[list.size()][11];
		for (int i = 0; i < list.size(); i++) {
			back Back = (back) list.get(i);
			r[i][0] = Back.getRe_record();
			r[i][1] = Back.getR_id();
			r[i][2] = Back.getR_name();
			r[i][3] = Back.getB_id();
			r[i][4] = Back.getB_name();
			r[i][5] = Back.getB_type();
			r[i][6] = Back.getPrice();
			r[i][7] = Back.getBorrow_date();
			r[i][8] = Back.getReturn_date();
			r[i][9] = Back.getPunish_fine();
			r[i][10] = Back.getR_admin();
		
		}
		return r;

	}

	/*private Object[] getselectid(List list) {
		Object[] ids = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			BookInfo book = new BookInfo();
			ids[i] = book.getISBN();
		}
		return ids;
	}*/

	public BorrowHistory() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("借阅记录查询");
		setBounds(100, 100, 800, 600);
		setVisible(true);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 70));
		getContentPane().add(tabbedPane);
//*****************************************************借出记录查询
		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		tabbedPane.addTab("借出记录查询", null, panel_1, null);

		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "请选择查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 100));
		panel_1.add(panel_1_1, BorderLayout.NORTH);
		
        choice1=new JComboBox();
		String[] array={"借出记录号","读者编号","读者姓名","图书编号","图书名称"};
		for(int i=0;i<array.length;i++){
			choice1.addItem(array[i]);
			
		}
		panel_1_1.add(choice1);
		textField_1 = new JTextField();
		textField_1.setPreferredSize(new Dimension (200,30));
		panel_1_1.add(textField_1);
        
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "查询结果显示", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(700, 310));
		panel.add(scrollPane_1);

		final JPanel panel_1_2 = new JPanel();
		panel_1_2.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_1_2, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("查询");
		panel_1_2.add(button);

		 button.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					if(textField_1.getText().length()==0){
						JOptionPane.showMessageDialog(null, "请输入搜索内容！");
						return;
					}
					String name=(String)choice1.getSelectedItem();
					if(name.equals("借出记录号")) {
						Object[][] results=getselectborrow(Dao.selectBorrowMHrecord(textField_1.getText()));
						table_1 = new JTable(results,borrowhistory);
						
						scrollPane_1.setViewportView(table_1);
					}
					else if(name.equals("读者编号")){
						
						Object[][] results=getselectborrow(Dao.selectBookBorrowr_id(textField_1.getText()));
						table_1 = new JTable(results,borrowhistory);
						
						scrollPane_1.setViewportView(table_1);
					}
					else if(name.equals("读者姓名")){
						
						Object[][] results=getselectborrow(Dao.selectBorrowMHr_name(textField_1.getText()));
						table_1 = new JTable(results,borrowhistory);
						
						scrollPane_1.setViewportView(table_1);
					}
					else if(name.equals("图书编号")){
						
						Object[][] results=getselectborrow(Dao.selectBookBorrowb_id(textField_1.getText()));
						table_1 = new JTable(results,borrowhistory);
						
						scrollPane_1.setViewportView(table_1);
					}
					else if(name.equals("图书名称")){
						
						Object[][] results=getselectborrow(Dao.selectBorrowMHb_name(textField_1.getText()));
						table_1 = new JTable(results,borrowhistory);
						
						scrollPane_1.setViewportView(table_1);
					}
				}
	        	
	        	
	        });
		
		
		final JButton button_1 = new JButton();
		button_1.setText("退出");
		panel_1_2.add(button_1);
		button_1.addActionListener(new CloseActionListener());
		setVisible(true);
//*****************************************************归还记录查询
		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		tabbedPane.addTab("归还记录查询", null, panel_2, null);

		final JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new TitledBorder(null, "请选择查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_2_1.setPreferredSize(new Dimension(0, 100));
		panel_2.add(panel_2_1, BorderLayout.NORTH);
		
        choice2=new JComboBox();
		String[] array2={"归还记录号","读者编号","读者姓名","图书编号","图书名称"};
		for(int i=0;i<array2.length;i++){
			choice2.addItem(array2[i]);
			
		}
		panel_2_1.add(choice2);
		textField_2 = new JTextField();
		textField_2.setPreferredSize(new Dimension (200,30));
		panel_2_1.add(textField_2);
        
		final JPanel panel1 = new JPanel();
		panel1.setBorder(new TitledBorder(null, "查询结果显示", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_2.add(panel1);

		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setPreferredSize(new Dimension(700, 310));
		panel1.add(scrollPane_2);

		final JPanel panel_2_2 = new JPanel();
		panel_2_2.setPreferredSize(new Dimension(0, 50));
		panel_2.add(panel_2_2, BorderLayout.SOUTH);

		final JButton button2 = new JButton();
		button2.setText("查询");
		panel_2_2.add(button2);

		 button2.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					if(textField_2.getText().length()==0){
						JOptionPane.showMessageDialog(null, "请输入搜索内容！");
						return;
					}
					String name2=(String)choice2.getSelectedItem();
					if(name2.equals("归还记录号")) {
						Object[][] results1=getselectreturn(Dao.selectBookBackrecord(textField_2.getText()));
						table_2 = new JTable(results1,returnhistory);
						
						scrollPane_2.setViewportView(table_2);
					}
					else if(name2.equals("读者编号")){
						
						Object[][] results1=getselectreturn(Dao.selectBookBackr_id(textField_2.getText()));
						table_2 = new JTable(results1,returnhistory);
						
						scrollPane_2.setViewportView(table_2);
					}
					else if(name2.equals("读者姓名")){
						
						Object[][] results1=getselectreturn(Dao.selectBookBackMHr_name(textField_2.getText()));
						table_2 = new JTable(results1,returnhistory);
						
						scrollPane_2.setViewportView(table_2);
					}
					else if(name2.equals("图书编号")){
						
						Object[][] results1=getselectreturn(Dao.selectBookBackb_id(textField_2.getText()));
						table_2 = new JTable(results1,returnhistory);
						
						scrollPane_2.setViewportView(table_2);
					}
					else if(name2.equals("图书名称")){
						
						Object[][] results1=getselectreturn(Dao.selectBookBackb_name(textField_2.getText()));
						table_2 = new JTable(results1,returnhistory);
						
						scrollPane_2.setViewportView(table_2);
					}
				}
	        	
	        	
	        });
		
		
		final JButton button_2 = new JButton();
		button_2.setText("退出");
		panel_2_2.add(button_2);
		button_2.addActionListener(new CloseActionListener());
		setVisible(true);
		
		}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}

	
}




