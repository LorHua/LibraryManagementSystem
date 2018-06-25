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

	public class BookTypeAdd extends JInternalFrame {

		private JTextField code;
		private JTextField b_typename;
		private JTextField keyword;
		
		
		/**
		 * Create the frame
		 */
		public BookTypeAdd() {
			super();
			setIconifiable(true);							// 设置窗体可最小化－－－必须
			setClosable(true);
			setTitle("图书类别添加");
			setBounds(100, 100, 400, 250);

			final JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setPreferredSize(new Dimension(400, 80));
			getContentPane().add(panel, BorderLayout.NORTH);
			
			final JLabel label_4 = new JLabel();
			ImageIcon bookTypeAddIcon=CreatedIcon.add("bookTypeAdd.jpg");
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

			code = new JTextField();
			code.setColumns(20);
			code.setEditable(false);
			panel_3.add(code);
	        
			final JLabel label_2 = new JLabel();
			label_2.setText("        类 别 名 称：");
			panel_3.add(label_2);

			b_typename = new JTextField();
			b_typename.setDocument(new Document(20));
			b_typename.setColumns(20);
			panel_3.add(b_typename);
		
			final JLabel label_5 = new JLabel();
			label_5.setText("        关 键 词：  ");
			panel_3.add(label_5);

			keyword = new JTextField();
			keyword.setDocument(new Document(20));
			keyword.setColumns(20);
			panel_3.add(keyword);

			final JPanel panel_4 = new JPanel();
			panel_1.add(panel_4,BorderLayout.SOUTH);
			
			final JButton button = new JButton();
			button.setText("保存");
			button.addActionListener(new ActionListener(){
				public void actionPerformed(final ActionEvent e) {
					if(b_typename.getText().length()==0){
						JOptionPane.showMessageDialog(null, "类别名称不可为空");
						return;
					}
					if(keyword.getText().length()==0){
						JOptionPane.showMessageDialog(null, "关键词不可为空");
						return;
					}
					int i=Dao.InsertBookType(b_typename.getText().trim(),code.getText().trim(),keyword.getText().trim());
					if(i==1){
						JOptionPane.showMessageDialog(null, "添加成功！");
						//doDefaultCloseAction();
					}
					else {
						JOptionPane.showMessageDialog(null, "图书类别名称已存在!");
						//doDefaultCloseAction();
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
}
