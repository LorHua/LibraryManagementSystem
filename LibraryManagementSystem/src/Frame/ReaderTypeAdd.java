package Frame;


	import java.awt.BorderLayout;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
	import java.text.NumberFormat;

	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JFormattedTextField;
	import javax.swing.JInternalFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JTextField;

	import Dao.Dao;
	import util.CreatedIcon;
	import util.Document;

	public class ReaderTypeAdd extends JInternalFrame {

		private JTextField order;
		private JTextField r_typename;
		private JTextField canborrow;
		private JTextField deadline;
		
		/**
		 * Create the frame
		 */
		public ReaderTypeAdd() {
			super();
			setIconifiable(true);							// 设置窗体可最小化－－－必须
			setClosable(true);
			setTitle("读者类别添加");
			setBounds(100, 100, 400, 250);

			final JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setPreferredSize(new Dimension(400, 80));
			getContentPane().add(panel, BorderLayout.NORTH);
			
			final JLabel label_4 = new JLabel();
			ImageIcon bookTypeAddIcon=CreatedIcon.add("ReaderTypeAdd.jpg");
			label_4.setIcon(bookTypeAddIcon);
			label_4.setPreferredSize(new Dimension(400, 80));
			panel.add(label_4);
			
			final JPanel panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout());
			panel_1.setPreferredSize(new Dimension(400, 200));
			getContentPane().add(panel_1);
			
			final JPanel panel_3 = new JPanel();
			final GridLayout gridLayout = new GridLayout(0, 2);
			gridLayout.setVgap(5);
			gridLayout.setHgap(5);
			panel_3.setLayout(gridLayout);
			panel_1.add(panel_3);
			
			final JLabel label_3 = new JLabel();
			label_3.setText("        类 别 编 号：");
			panel_3.add(label_3);

			order = new JTextField();
			order.setColumns(20);
			order.setEditable(false);
			panel_3.add(order);
	        
			final JLabel label_2 = new JLabel();
			label_2.setText("        类 别 名 称：");
			panel_3.add(label_2);

			r_typename = new JTextField();
			r_typename.setDocument(new Document(20));
			r_typename.setColumns(20);
			panel_3.add(r_typename);

			final JLabel label_5 = new JLabel();
			label_5.setText("        可借册数：  ");
			panel_3.add(label_5);

			canborrow = new JTextField();
			canborrow.setColumns(20);
			canborrow.setDocument(new Document(3));
			canborrow.addKeyListener(new CanborrowListener());
			panel_3.add(canborrow);
			
			final JLabel label_6 = new JLabel();
			label_6.setText("       借书期限（天）：  ");	
			panel_3.add(label_6);

			deadline = new JTextField();
			deadline.setColumns(20);
			deadline.setDocument(new Document(4));
			deadline.addKeyListener(new DealineListener());
			panel_3.add(deadline);
			
			final JPanel panel_4 = new JPanel();
			panel_1.add(panel_4,BorderLayout.SOUTH);
			
			final JButton button = new JButton();
			button.setText("保存");
			button.addActionListener(new ActionListener(){
				public void actionPerformed(final ActionEvent e) {
					if(r_typename.getText().length()==0){
						JOptionPane.showMessageDialog(null, "类别名称不可为空");
						return;
					}
					else if(canborrow.getText().length()==0){
						JOptionPane.showMessageDialog(null, "可借册数不可为空");
						return;
					}
					else if(deadline.getText().length()==0){
						JOptionPane.showMessageDialog(null, "借书期限不可为空");
						return;
					}
					else {
						int i=Dao.InsertReaderType(r_typename.getText().trim(),Integer.parseInt(canborrow.getText()),Integer.parseInt(deadline.getText()));
						if(i==1){
							JOptionPane.showMessageDialog(null, "添加成功！");
							//doDefaultCloseAction();
						}
						else {
							JOptionPane.showMessageDialog(null, "读者类别名称已存在!");
							//doDefaultCloseAction();
						}
					}
				}
			});
			panel_4.add(button);

			final JButton button1 = new JButton();
			button1.setText("关闭");
			button1.addActionListener(new ActionListener(){
				public void actionPerformed(final ActionEvent e) {
					doDefaultCloseAction();
				}
			});
			panel_4.add(button1);
			setVisible(true);
		}
		class CanborrowListener extends KeyAdapter {
			public void keyTyped(KeyEvent e) {
				String numStr="0123456789";
				if(numStr.indexOf(e.getKeyChar())<0){
					e.consume();
				}
			}
		}
		class DealineListener extends KeyAdapter {
			public void keyTyped(KeyEvent e) {
				String numStr="0123456789";
				if(numStr.indexOf(e.getKeyChar())<0){
					e.consume();
				}
			}
		}
}
