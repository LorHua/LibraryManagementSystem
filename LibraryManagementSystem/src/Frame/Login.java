package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import MainPack.LibrarySystem;
import TableModel.user_;
import Dao.Dao;
import util.CreatedIcon;
import util.Document;


public class Login extends JFrame {

	private class BookResetAction implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			u_id.setText("");
			u_password.setText("");
		}
	}
	class BookLoginAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			user = Dao.check(u_id.getText(), u_password.getPassword());
			if (user.getId() != null) {
				try {
					LibrarySystem frame = new LibrarySystem();
					frame.setVisible(true);
					//closeLib(frame2);
					Login.this.setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "登录失败，请检查输入情况！");
				u_id.setText("");
				u_password.setText("");
			}
		}
	}
	
	private JPasswordField u_password;
	private JTextField u_id;
	private JButton login;
	private JButton reset;
	private static user_ user;
	

	public Login() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(10);
		getContentPane().setLayout(borderLayout);
		setTitle("图书馆管理系统登录");
	
		Toolkit kit = Toolkit.getDefaultToolkit(); 
		Dimension screenSize = kit.getScreenSize(); 
		int screenWidth = screenSize.width; 
		int screenHeight = screenSize.height; 
		setBounds(screenWidth/2-200, screenHeight/2-150, 300, 200);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(panel);

		final JPanel panel_2 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(5);
		gridLayout.setVgap(20);
		panel_2.setLayout(gridLayout);
		panel.add(panel_2);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(0, 0));
		label.setMinimumSize(new Dimension(0, 0));
		panel_2.add(label);
		label.setText("用  户  名：");

		u_id = new JTextField(20);
		u_id.setPreferredSize(new Dimension(0, 0));
		panel_2.add(u_id);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1);
		label_1.setText("密      码：");

		u_password = new JPasswordField(20);
		//u_password.setDocument(new Document(6));
		u_password.setEchoChar('*');//设置密码框的回显字符
		u_password.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		panel_2.add(u_password);

		final JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);

		login=new JButton();
		login.addActionListener(new BookLoginAction());
		login.setText("登录");
		panel_1.add(login);
		
		reset=new JButton();
		reset.addActionListener(new BookResetAction());
		reset.setText("重置");
		panel_1.add(reset);

		final JLabel tupianLabel = new JLabel();
		ImageIcon loginIcon=CreatedIcon.add("login.jpg");
		tupianLabel.setIcon(loginIcon);
		tupianLabel.setOpaque(true);
		tupianLabel.setBackground(Color.GREEN);
		tupianLabel.setPreferredSize(new Dimension(260, 60));
		panel.add(tupianLabel, BorderLayout.NORTH);
		
		setVisible(true);
		setResizable(false);
		//setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

	}
	public static user_ getUser() {
		return user;
	}
	public static void setUser(user_ user) {
		Login.user = user;
	}
	/*public static void closeLib(JFrame frame2) {
		if(frame2==null) {
		LibrarySystem frame = new LibrarySystem();
		frame.setVisible(true);
		//LibrarySystem.setVisible(false);
		frame2 = frame;
		}
	}*/
}
