package Frame;


	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.sql.Date;
	import java.text.SimpleDateFormat;
	import java.util.List;

	import javax.swing.ButtonGroup;
	import javax.swing.DefaultComboBoxModel;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFormattedTextField;
	import javax.swing.JInternalFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JRadioButton;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import javax.swing.table.DefaultTableModel;

import Dao.Dao;
import Frame.ReaderAdd.DateListener;
import Frame.ReaderAdd.TelListener;
import Jcom.Item;
import MainPack.LibrarySystem;
import TableModel.reader;
import TableModel.readertype;
/*import com.wsy.dao.Dao;
	import com.wsy.model.Reader;*/
	import util.CreatedIcon;
	import util.Document;

	public class ReaderUpdateFind  extends JInternalFrame {
		private ButtonGroup buttonGroup = new ButtonGroup();
		private JTable table;
		
		private JTextField r_id;
		private JComboBox r_type;
		private JTextField email;
		private JFormattedTextField r_addtime;
		private JTextField address;
		private JTextField unit;
		private JTextField readername;
		private JTextField r_tel;
		private String str="";
		private JRadioButton JRadioButton1;
		private JRadioButton JRadioButton2;
		DefaultComboBoxModel ReaderTypeModel;
		private String[] columnNames={ "读者编号","读者姓名","性别","读者类别","工作单位","家庭地址","电话号码","电邮地址","登记日期" };
		//String id;
		

		/**
		 * Create the frame
		 */
		private Object[][] getFileStates(List list){
			Object[][]results=new Object[list.size()][columnNames.length];
			for(int i=0;i<list.size();i++){
				reader reader=(reader)list.get(i);
				//results[i][0]=reader.getId();
				String sex;
				if(reader.getSex().equals("男")){
					sex="男";
				}
				if(reader.getSex().equals("女")) {
					sex="女";
				}
				results[i][0]=reader.getId();
				results[i][1]=reader.getName();
				results[i][2]=reader.getSex();
				results[i][3]=reader.getType();
				results[i][4]=reader.getUnit();
				results[i][5]=reader.getAddress();
				results[i][6]=reader.getTel();
				results[i][7]=reader.getEmail();
				results[i][8]=reader.getAddtime();
				/*if(LibrarySystem.FindText.getText().equals("")){
					results[i][0]=reader.getId();
					results[i][1]=reader.getName();
					results[i][2]=reader.getSex();
					results[i][3]=reader.getType();
					results[i][4]=reader.getUnit();
					results[i][5]=reader.getAddress();
					results[i][6]=reader.getTel();
					results[i][7]=reader.getEmail();
					results[i][8]=reader.getAddtime();
				}
				else if(reader.getName().equals(LibrarySystem.FindText.getText())){
					results[i][0]=reader.getId();
					results[i][1]=reader.getName();
					results[i][2]=reader.getSex();
					results[i][3]=reader.getType();
					results[i][4]=reader.getUnit();
					results[i][5]=reader.getAddress();
					results[i][6]=reader.getTel();
					results[i][7]=reader.getEmail();
					results[i][8]=reader.getAddtime();
				}*/
			}
			return results;
		}
		public ReaderUpdateFind() {
			super();
			setIconifiable(true);
			setClosable(true);
			setTitle("读者信息更新");
			setBounds(100, 100, 600, 600);
			final JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setPreferredSize(new Dimension(600,80 ));
			getContentPane().add(panel, BorderLayout.NORTH);

			final JLabel logoLabel = new JLabel();
			ImageIcon readerModiAndDelIcon=CreatedIcon.add("ReaderUpdateFind.jpg");
			logoLabel.setIcon(readerModiAndDelIcon);
			logoLabel.setBackground(Color.CYAN);
			logoLabel.setOpaque(true);
			logoLabel.setPreferredSize(new Dimension(600, 80));
			panel.add(logoLabel);

			final JPanel panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout());
			getContentPane().add(panel_1);

			final JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(0, 260));
			panel_1.add(scrollPane, BorderLayout.NORTH);

			final DefaultTableModel model=new DefaultTableModel();
			//Object[][] results=getFileStates(Dao.selectReader());
			//final String str;
			if(LibrarySystem.FindText.getText() != null&(!LibrarySystem.FindText.getText().equals("输入读者姓名："))) {
				Object[][] results=getFileStates(Dao.selectReaderByName(LibrarySystem.FindText.getText()));
				model.setDataVector(results,columnNames);
				str=LibrarySystem.FindText.getText();
				LibrarySystem.FindText.setText("");
			}
			else if(LibrarySystem.FindText.getText() == null||LibrarySystem.FindText.getText().equals("输入读者姓名：")){
				Object[][] results=getFileStates(Dao.selectReader());
				model.setDataVector(results,columnNames);
			}
			
			table = new JTable();
			table.setModel(model);
			scrollPane.setViewportView(table);
			model.setColumnIdentifiers(columnNames);
			table.setModel(model);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.addMouseListener(new TableListener());

			final JPanel panel_2 = new JPanel();
			final GridLayout gridLayout = new GridLayout(0, 4);
			gridLayout.setVgap(9);
			panel_2.setLayout(gridLayout);
			panel_2.setPreferredSize(new Dimension(450, 180));
			panel_1.add(panel_2);
			
			final JLabel label_1 = new JLabel();
			label_1.setText("      编    号：");
			panel_2.add(label_1);
			r_id = new JTextField();
			r_id.setEditable(false);
			panel_2.add(r_id); 
			
			final JLabel label_2 = new JLabel();
			label_2.setText("      姓    名：");
			panel_2.add(label_2);

			readername = new JTextField();
			readername.setDocument(new Document(10));
			panel_2.add(readername);

			final JLabel label_3 = new JLabel();
			label_3.setText("      性    别：");
			panel_2.add(label_3);

			final JPanel panel_3 = new JPanel();
			final FlowLayout flowLayout_1 = new FlowLayout();
			flowLayout_1.setVgap(0);
			panel_3.setLayout(flowLayout_1);
			panel_2.add(panel_3);

			JRadioButton1 = new JRadioButton();
			JRadioButton1.setSelected(true);
			buttonGroup.add(JRadioButton1);
			panel_3.add(JRadioButton1);
			JRadioButton1.setText("男");

			JRadioButton2 = new JRadioButton();
			buttonGroup.add(JRadioButton2);
			panel_3.add(JRadioButton2);
			JRadioButton2.setText("女");

			final JLabel label_4 = new JLabel(); //读者类别			
			label_4.setText("      类    别：");
			panel_2.add(label_4);
			r_type = new JComboBox();
			ReaderTypeModel= (DefaultComboBoxModel)r_type.getModel();
			
			//从数据库中取出读者类别
			List list=Dao.selectReaderType();
			for(int i=0;i<list.size();i++){
				readertype readertype=(readertype)list.get(i);
				Item item=new Item();
				item.setId(String.valueOf(readertype.getTypeid()));
				item.setName((String)readertype.getTypename());
				ReaderTypeModel.addElement(item);
			}//*********************************************************
			panel_2.add(r_type);
			
			final JLabel label_5 = new JLabel();
			label_5.setText("    工作单位：");
			panel_2.add(label_5);
			unit = new JTextField();
			panel_2.add(unit);
			
			final JLabel label_6 = new JLabel();
			label_6.setText("    家庭地址：");
			panel_2.add(label_6);
			address = new JTextField();
			address.setDocument(new Document(30));
			panel_2.add(address);
		
			final JLabel label_7 = new JLabel();
			label_7.setText("      电    话：");
			panel_2.add(label_7);
			r_tel = new JTextField();
			r_tel.addKeyListener(new TelListener());
			r_tel.setDocument(new Document(11));
			panel_2.add(r_tel);
			
			final JLabel label_8 = new JLabel();
			label_8.setText("    电邮地址：");
			panel_2.add(label_8);
			
			email = new JTextField();
			email.setDocument(new Document(50));
			email.addKeyListener(new EmailListener());
			panel_2.add(email);
			
			SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");

			final JLabel label_9 = new JLabel();
			label_9.setText("    登记日期：");
			panel_2.add(label_9);

			
			r_addtime = new JFormattedTextField(myfmt.getDateInstance());
			r_addtime.setValue(new java.util.Date());
			r_addtime.addKeyListener(new DateListener());
			panel_2.add(r_addtime);


			final JPanel panel_4 = new JPanel();
			panel_4.setPreferredSize(new Dimension(450, 50));
			panel_1.add(panel_4,BorderLayout.SOUTH);
			
			final JButton button = new JButton();
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			panel_4.add(button);
			button.setText("修改");
			button.addActionListener(new ModiButtonListener(model));
			final JButton buttonDel = new JButton();
			panel_4.add(buttonDel);
			buttonDel.setText("删除");
			buttonDel.addActionListener(new DelButtonListener(model));
			setVisible(true);
			//
		}
		class TableListener extends MouseAdapter {
			public void mouseClicked(final MouseEvent e) {
				
				int selRow = table.getSelectedRow();
				r_id.setText(table.getValueAt(selRow, 0).toString().trim());
				readername.setText(table.getValueAt(selRow, 1).toString().trim());
				if(table.getValueAt(selRow, 2).toString().trim().equals("男"))
					JRadioButton1.setSelected(true);
				else
					JRadioButton2.setSelected(true);
				ReaderTypeModel.setSelectedItem(table.getValueAt(selRow, 3).toString().trim());
				unit.setText(table.getValueAt(selRow, 4).toString().trim());
				address.setText(table.getValueAt(selRow, 5).toString().trim());
				r_tel.setText(table.getValueAt(selRow, 6).toString().trim());
				email.setText(table.getValueAt(selRow, 7).toString().trim());
				r_addtime.setText(table.getValueAt(selRow, 8).toString().trim());
				
			}
		}
		final class NumberListener extends KeyAdapter {
			public void keyTyped(KeyEvent e) {
				String numStr="0123456789@qq.com"+(char)8;
				if(numStr.indexOf(e.getKeyChar())<0){
					e.consume();
				}
			}
		}
		private final class DelButtonListener implements ActionListener {
			private final DefaultTableModel model;

			private DelButtonListener(DefaultTableModel model) {
				this.model = model;
			}

			public void actionPerformed(final ActionEvent e) {
				int i=Dao.deleteReader(Integer.parseInt(r_id.getText().trim()));
				if(i==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					/*Object[][] results=getFileStates(Dao.selectReader());
					model.setDataVector(results,columnNames);
					table.setModel(model);*/
					//if(LibrarySystem.FindText.getText() != null&(!LibrarySystem.FindText.getText().equals("输入读者姓名/图书名称查询相关信息！"))) {
					if(!str.equals("")) {
						Object[][] results=getFileStates(Dao.selectReaderByName(str));
						model.setDataVector(results,columnNames);
					}
					else{
						Object[][] results=getFileStates(Dao.selectReader());
						model.setDataVector(results,columnNames);
					}
				}
			}
		}
		class ModiButtonListener implements ActionListener {
			private final DefaultTableModel model;

			ModiButtonListener(DefaultTableModel model) {
				this.model = model;
			}

			public void actionPerformed(final ActionEvent e) {
				if(readername.getText().length()==0){
					JOptionPane.showMessageDialog(null, "读者姓名文本框不可为空");
					return;
				}
				if(r_tel.getText().length()<11&&r_tel.getText().length()>0){
					JOptionPane.showMessageDialog(null, "电话号码必须为11位");
					return;
				}
				String sex="男";
				if(!JRadioButton1.isSelected()){
					sex="女";}
				String type=String.valueOf(r_type.getSelectedItem());
				int i=Dao.updateReader(Integer.parseInt(r_id.getText().trim()),readername.getText().trim(), sex,type,unit.getText().trim(),address.getText().trim(),r_tel.getText().trim(),email.getText().trim(),Date.valueOf(r_addtime.getText().trim()),"");
				System.out.println(i);
				if(i==1){
					JOptionPane.showMessageDialog(null, "修改成功");

					if(!str.equals("")) {
						Object[][] results=getFileStates(Dao.selectReaderByName(str));
						model.setDataVector(results,columnNames);
						//LibrarySystem.FindText.setText("");
						
					}
					else{
						Object[][] results=getFileStates(Dao.selectReader());
						model.setDataVector(results,columnNames);
					}
				}
			}
		}
		class TelListener extends KeyAdapter {
			public void keyTyped(KeyEvent e) {
				String numStr="0123456789-"+(char)8;
				if(numStr.indexOf(e.getKeyChar())<0){
					e.consume();
				}
			}
		}
		class EmailListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789@qq.com"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
		class DateListener extends KeyAdapter {
			public void keyTyped(KeyEvent e) {
				if(r_addtime.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
				}
			}
		}

}


