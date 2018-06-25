package Frame;
import Action.MenuActions;
import MainPack.LibrarySystem;
import TableModel.user_;
import util.Document;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import Frame.Login;
import TableModel.user_;

//import model.user;

import util.Document;
import Dao.Dao;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class UserManagement extends JInternalFrame{
	private user_ user = Login.getUser(); 
	
	private String[] str;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	DefaultTableModel model;
	//user_ user=new user_();
	private Object[][] getFileStates(List list){
	String[] str = { "用户编号", "密码","管理员" };
	Object[][]users=new Object[list.size()][str.length];
	for(int i=0;i<list.size();i++){
		user_ user=(user_)list.get(i);
		users[i][0]=user.getId();
		//users[i][1]=user.getPsd();//u_password.setEchoChar('*')
		users[i][1]=user.getAdmin();
	}
	return users;
}

	
	public UserManagement() {
		setIconifiable(true);
		setClosable(true);
		setTitle("用户管理");
		setBounds(500, 120, 400, 400);
		
		final JTabbedPane tabbedPane = new JTabbedPane( JTabbedPane.LEFT);
		tabbedPane.setPreferredSize(new Dimension(500, 200));
		getContentPane().add(tabbedPane);
		
//***************************************当前用户
		
		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("当前用户", null, panel_1, null);
		
		final JLabel label_1 = new JLabel();
		label_1.setText("当前用户ID：");
		label_1.setBounds(40,60,100,30);
		panel_1.add(label_1);
		
		final JTextField TextField_1 = new JTextField(user.getId());
		TextField_1.setBounds(140,60,100,30);
		TextField_1.setEditable(false);
		panel_1.add(TextField_1);
		
		final JLabel label_2 = new JLabel();
		label_2.setText("用户类型：");
		label_2.setBounds(40,110,110,30);
		panel_1.add(label_2);
		
		final JTextField TextField_2 = new JTextField();
		TextField_2.setBounds(140,110,100,30);
		if(user.getAdmin())
			TextField_2.setText("管理员");
		else TextField_2.setText("普通用户");
		TextField_2.setEditable(false);
		panel_1.add(TextField_2);
		
		final JButton button_1 = new JButton("更改密码");
		button_1.setBounds(40, 220, 100, 30);
		button_1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
            	FitPassword FP = new FitPassword();
    			LibrarySystem.addIFame(FP);
    			}
            }
		);
		panel_1.add(button_1);
		
		final JButton button_3 = new JButton("退出系统");
		button_3.setBounds(140, 220, 100, 30);
		button_3.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent event) {  
        	int i = JOptionPane.showConfirmDialog(null, "确定退出吗？","提示",JOptionPane.YES_NO_OPTION);
        	if(i==0)
        		System.exit(0);
        }  });
		panel_1.add(button_3);
		
//*************************************用户创建与删除
		Object[][] results=getFileStates(Dao.selectUser());
		str = new String[]{"用户编号","管理员" };
		table = new JTable(results,str);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String id,isadmin,password;
				int selRow = table.getSelectedRow();
				id 		 = table.getValueAt(selRow, 0).toString().trim();
				//password = table.getValueAt(selRow, 1).toString().trim();
				//password.setEchoChar('*');
				isadmin  = table.getValueAt(selRow, 1).toString().trim();
				textField_1.setText(id);
				textField_2.setText("******");
				textField_3.setText(isadmin);
			}
		});
		
		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		tabbedPane.addTab("用户创建与删除", null, panel_2, null);
		
		final JScrollPane scrollpane = new  JScrollPane();
		scrollpane.setBounds(10, 10, 250, 150);
		scrollpane.setViewportView(table);
		panel_2.add(scrollpane);
			
		final JLabel label_3 = new JLabel();
		label_3.setText("用户ID：");
		label_3.setBounds(40,170,100,30);
		panel_2.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140,170,100,30);
		textField_1.setDocument(new Document(30)); 
		textField_1.addKeyListener(new NumberListener());
		panel_2.add(textField_1);
		
		final JLabel label_4 = new JLabel();
		label_4.setText("登录密码：");
		label_4.setBounds(40,210,100,30);
		panel_2.add(label_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(140,210,100,30);
		textField_2.setDocument(new Document(50)); 
		textField_2.addKeyListener(new NumberListener());
		panel_2.add(textField_2);
		
		final JLabel label_5 = new JLabel();
		label_5.setText("是否管理员：");
		label_5.setBounds(40,250,100,30);
		panel_2.add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setText("false");
		textField_3.setBounds(140,250,100,30);
		//textField_3.setDocument(new Document(1)); 
		textField_3.addKeyListener(new NumberListener());
		panel_2.add(textField_3);
		
		final JButton button_4 = new JButton("创建");
		button_4.setBounds(40, 290, 90, 30);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(textField_1.getText().length()<6||textField_1.getText().length()>30){
						JOptionPane.showMessageDialog(null, "用户ID应保持在6~30位！");
						return;
					}
					
					if(textField_2.getText().length()<8||textField_2.getText().length()>50){
						JOptionPane.showMessageDialog(null, "用户密码应保持在8~50位！");
						return;
					}
					/*if(!textField_2.getText().equals(textField_3.getText())){
						JOptionPane.showMessageDialog(null, "两次密码输入不一致！");
						return;
					}*/
					String u_id=textField_1.getText();
					String psd=textField_2.getText();
					Boolean isadmin=Boolean.parseBoolean(textField_3.getText());
					int i=Dao.InsertUser_(u_id,psd,isadmin);
					if(i==1){
						JOptionPane.showMessageDialog(null, "添加成功！");
						Object[][] results=getFileStates(Dao.selectUser());
						model.setDataVector(results,str);
						table.setModel(model);
						//doDefaultCloseAction();
					}
					else
					JOptionPane.showMessageDialog(null, "用户ID已存在");
				}
			}
		);
		panel_2.add(button_4);
		final JButton button_5 = new JButton("删除");
		button_5.setBounds(140, 290, 90, 30);
		button_5.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				String id=textField_1.getText();
				if(user.getId().equals("ljh")&(!id.equals("ljh"))) {
					int i=Dao.deleteUser(id);
					if(i==1){
						JOptionPane.showMessageDialog(null, "删除成功");
						Object[][] results=getFileStates(Dao.selectUser());
						DefaultTableModel model=new DefaultTableModel();					
						table.setModel(model);
						model.setDataVector(results, str);
					}
				}
			}
		});
		panel_2.add(button_5);
		
		
//**************************************判断为管理员才可以创建、删除用户
		final JTextField TextField_6 = new JTextField("未授权");
		TextField_6.setBounds(40,330,90,25);
		TextField_6.setVisible(false);
		TextField_6.setEnabled(false);;
		panel_2.add(TextField_6);
		if(!TextField_2.getText().equals("管理员"))
		{
			textField_1.setEditable(false);
			textField_2.setEditable(false);
			textField_3.setEditable(false);
			button_4.setEnabled(false);
			button_5.setEnabled(false);
			TextField_6.setVisible(true);
		}
		setVisible(true);		
	}
	
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789qwertyuiopasdfghjklzxcvbnm";
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
}