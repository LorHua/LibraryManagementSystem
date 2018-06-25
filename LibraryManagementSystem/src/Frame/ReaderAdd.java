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
	import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Dao.Dao;
import Jcom.Item;
import TableModel.readertype;
//import com.wsy.dao.Dao;
	import util.CreatedIcon;
	import util.Document;

	public class ReaderAdd  extends JInternalFrame {

		private JTextField ISBN;
		private ButtonGroup buttonGroup = new ButtonGroup();
		private JTextField tel;
		private JFormattedTextField date;
		private JTextField r_id;
		private JComboBox r_type;
		private JTextField email;
		private JFormattedTextField r_addtime;
	

		private JTextField address;
		private JTextField unit;
		private JTextField r_name;
		DefaultComboBoxModel ReaderTypeModel;
		String [] array;
		
		

		/**
		 * Create the frame
		 */
		public ReaderAdd() {
			super();
			setTitle("读者相关信息添加");
			setIconifiable(true);							// 设置窗体可最小化－－－必须
			setClosable(true);								// 设置窗体可关闭－－－必须
															// 设置窗体标题－－－必须
			setBounds(100, 100, 600, 350);

			final JLabel logoLabel = new JLabel();
			ImageIcon readerAddIcon=CreatedIcon.add("ReaderAdd.jpg");
			logoLabel.setIcon(readerAddIcon);
			logoLabel.setOpaque(true);
			logoLabel.setBackground(Color.CYAN);
			logoLabel.setPreferredSize(new Dimension(400, 80));
			getContentPane().add(logoLabel, BorderLayout.NORTH);

			final JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			getContentPane().add(panel);

			final JPanel panel_1 = new JPanel();
			final GridLayout gridLayout = new GridLayout(0, 4);
			gridLayout.setVgap(15);
			gridLayout.setHgap(10);
			panel_1.setLayout(gridLayout);
			panel_1.setPreferredSize(new Dimension(450, 180));
			panel.add(panel_1);

			final JLabel label_15 = new JLabel();
			label_15.setText("  编    号：");
			panel_1.add(label_15);
			r_id = new JTextField();
			r_id.setEditable(false);
			panel_1.add(r_id); 
			
			final JLabel label_2 = new JLabel();
			label_2.setText("  姓    名：");
			panel_1.add(label_2);
			r_name = new JTextField();
			r_name.setDocument(new Document(10));
			panel_1.add(r_name);

			final JLabel label_3 = new JLabel();
			label_3.setText("  性    别：");
			panel_1.add(label_3);

			final JPanel label_13 = new JPanel();
			final FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(0);
			flowLayout.setVgap(0);
			label_13.setLayout(flowLayout);
			panel_1.add(label_13);

			final JRadioButton radioButton1 = new JRadioButton();
			label_13.add(radioButton1);
			radioButton1.setSelected(true);
			buttonGroup.add(radioButton1);
			radioButton1.setText("男");

			final JRadioButton radioButton2 = new JRadioButton();
			label_13.add(radioButton2);
			buttonGroup.add(radioButton2);
			radioButton2.setText("女");
			

			final JLabel label_21 = new JLabel(); //读者类别
			
			label_21.setText("  类    别：");
			panel_1.add(label_21);
			r_type = new JComboBox();
			ReaderTypeModel= (DefaultComboBoxModel)r_type.getModel();
			//***********************************************************************
			//从数据库中取出读者类别
			List list=Dao.selectReaderType();
			for(int i=0;i<list.size();i++){
				readertype readertype=(readertype)list.get(i);
				Item item=new Item();
				item.setId(String.valueOf(readertype.getTypeid()));
				item.setName((String)readertype.getTypename());
				ReaderTypeModel.addElement(item);
			}//*********************************************************8
			panel_1.add(r_type);
			

			final JLabel label_4 = new JLabel();
			label_4.setText("工作单位：");
			panel_1.add(label_4);

			unit = new JTextField();
			panel_1.add(unit);

			final JLabel label_5 = new JLabel();
			label_5.setText("家庭地址：");
			panel_1.add(label_5);

			address = new JTextField();
			address.setDocument(new Document(30));
			panel_1.add(address);
			
			
			final JLabel label_11 = new JLabel();
			label_11.setText("  电    话：");
			panel_1.add(label_11);
			

			tel = new JTextField();
			tel.addKeyListener(new TelListener());
			tel.setDocument(new Document(13));
			panel_1.add(tel);
			
			final JLabel label_9 = new JLabel();
			label_9.setText("电邮地址：");
			panel_1.add(label_9);
			email = new JTextField();
			email.setDocument(new Document(50));
			email.addKeyListener(new EmailListener());
			panel_1.add(email);

			SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");

			final JLabel label = new JLabel();
			label.setText("登记日期：");
			panel_1.add(label);

			
			r_addtime = new JFormattedTextField(myfmt.getDateInstance());
			r_addtime.setValue(new java.util.Date());
			r_addtime.addKeyListener(new DateListener());
			r_addtime.setEnabled(false);
			panel_1.add(r_addtime);


			final JPanel panel_2 = new JPanel();
			panel_2.setPreferredSize(new Dimension(450, 100));
			panel.add(panel_2);

			final JButton save = new JButton();
			panel_2.add(save);
			save.setText("保存");
			save.addActionListener(new ButtonAddListener(radioButton1));
			
			
			final JButton back = new JButton();
			panel_2.add(back);
			back.setText("退出");
			back.addActionListener(new CloseActionListener());
			setVisible(true);
			//
		}
		class DateListener extends KeyAdapter {
			public void keyTyped(KeyEvent e) {
				if(r_addtime.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
				}
			}
		}
		class ButtonAddListener implements ActionListener {
			private final JRadioButton button1;

			ButtonAddListener(JRadioButton button1) {
				this.button1 = button1;
			}

			public void actionPerformed(final ActionEvent e) {
				
				/*if(r_name.getText().length()==0){
					JOptionPane.showMessageDialog(null, "读者姓名文本框不可为空");
					return;
				}
				if(r_name.getText().length()>11||r_name.getText().length()<0){
					JOptionPane.showMessageDialog(null, "读者姓名应少于10位");
					return;
				}*/
				if(tel.getText().length()<13&&tel.getText().length()>0){
					JOptionPane.showMessageDialog(null, "电话号码必须为13位");
					return;
				}

				if(r_addtime.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
					return;
				}
				String sex="男";
				if(!button1.isSelected()){
					sex="女";}
				
				//*******************************************************************
				// *********************赋值插入表中↓
				String r_ids=r_id.getText().trim();
				//分类
				Object selectedItem = r_type.getSelectedItem();
				if (selectedItem == null)
					return;
				Item item = (Item) selectedItem;
				String r_types=item.getName();
				String r_names=r_name.getText().trim();
				//String ISBNs=ISBN.getText().trim();
				String tels=tel.getText().trim();
				String emails=email.getText().trim();
				String addresss =address.getText().trim();
				String units=unit.getText().trim();
				java.sql.Date dates=new java.sql.Date(new java.util.Date().getTime());
				
				//*******************************************************************
				int i=Dao.InsertReader(r_names, sex, r_types,units,addresss,tels,emails,dates);
				System.out.println(i);
				if(i==1){
					r_name.setText("");
					tel.setText("");
					address.setText("");
					unit.setText("");
					email.setText("");
					JOptionPane.showMessageDialog(null, "添加成功！");
					//doDefaultCloseAction();
				}
				else {
					JOptionPane.showMessageDialog(null, "添加失败！");
					//doDefaultCloseAction();
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
		class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		}



}
