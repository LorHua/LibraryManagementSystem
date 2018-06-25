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
import Dao.Dao;
import TableModel.book;
import util.Document;



	public class BookFind  extends JInternalFrame {

		private JTextField textField_1;

		private JComboBox comboBox_1;

		private JTable table_1, table_2;

		private JComboBox choice;

		private JTextField textField_2;

		private JScrollPane scrollPane, scrollPane_1;
		


		
		String booksearch[] = { "ͼ����", "ͼ������", "ͼ�����", "��������","������",  "��������", "�۸�","�鼮ҳ��","�ؼ���",  "�Ǽ�����", "������" ,"��ע"};

		private Object[][] getselect(List list) {
			Object[][] s = new Object[list.size()][12];
			for (int i = 0; i < list.size(); i++) {
				book Book = (book) list.get(i);
				s[i][0] = Book.getId();
				s[i][1] = Book.getName();
				s[i][2] = Book.getType();
				s[i][3] = Book.getAuthor();
				s[i][4] = Book.getPress();
				s[i][5] = Book.getPress_date();
				s[i][6] = Book.getPrice();
				s[i][7] = Book.getPage();
				s[i][8] = Book.getKeryword();
				s[i][9] = Book.getAddtime();
				s[i][10] = Book.getIsborrow();
				s[i][11] = Book.getBrief();
				

			}
			return s;

		}

		/*private Object[] getselectid(List list) {
			Object[] ids = new Object[list.size()];
			for (int i = 0; i < list.size(); i++) {
				book Book = new book();
				ids[i] =Book.getId();
			}
			return ids;
		}*/

		public BookFind() {
			super();
			setIconifiable(true);
			setClosable(true);
			setTitle("ͼ���ѯ");
			setBounds(100, 100, 800, 600);
			setVisible(true);

			final JTabbedPane tabbedPane = new JTabbedPane();
			tabbedPane.setPreferredSize(new Dimension(0, 70));
			getContentPane().add(tabbedPane);
//*****************************************************������ѯ
			final JPanel panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout());
			tabbedPane.addTab("������ѯ", null, panel_1, null);

			final JPanel panel_1_1 = new JPanel();
			panel_1_1.setBorder(new TitledBorder(null, "��ѡ���ѯ��Ŀ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			panel_1_1.setPreferredSize(new Dimension(0, 100));
			panel_1.add(panel_1_1, BorderLayout.NORTH);
			
	        choice=new JComboBox();
			String[] array={"ͼ����","ͼ������","ͼ������"};
			for(int i=0;i<array.length;i++){
				choice.addItem(array[i]);
				
			}
			panel_1_1.add(choice);
			textField_1 = new JTextField();
			textField_1.setPreferredSize(new Dimension (200,30));
			panel_1_1.add(textField_1);
	        
			final JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			panel_1.add(panel);

			
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setPreferredSize(new Dimension(700, 310));
			panel.add(scrollPane_1);

			final JPanel panel_2_1 = new JPanel();
			panel_2_1.setPreferredSize(new Dimension(0, 50));
			panel_1.add(panel_2_1, BorderLayout.SOUTH);

			final JButton button = new JButton();
			button.setText("��ѯ");
			panel_2_1.add(button);

			 button.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent arg0) {
						if(textField_1.getText().length()==0){
							JOptionPane.showMessageDialog(null, "�������ѯ���ݣ�");
							return;
						}
						String name=(String)choice.getSelectedItem();
						
						if(name.equals("ͼ������")){
							
							Object[][] results=getselect(Dao.selectBookByBookname(textField_1.getText()));
							table_2 = new JTable(results,booksearch);
							table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
							scrollPane_1.setViewportView(table_2);
						}
						else if(name.equals("ͼ����")) {
							Object[][] results=getselect(Dao.selectBookById(textField_1.getText()));
							table_2 = new JTable(results,booksearch);
							table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
							scrollPane_1.setViewportView(table_2);
							
						}
						else if(name.equals("ͼ������")){
							
							Object[][] results=getselect(Dao.selectBookByAuthor(textField_1.getText()));
							table_2 = new JTable(results,booksearch);
							table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
							scrollPane_1.setViewportView(table_2);
						}
					}
		        	
		        	
		        });
			
			
			final JButton button_1 = new JButton();
			button_1.setText("�˳�");
			panel_2_1.add(button_1);
			button_1.addActionListener(new CloseActionListener());
			setVisible(true);
//*****************************************************��ʾ����ͼ��ȫ����Ϣ
			final JPanel panel_2 = new JPanel();
			tabbedPane.addTab("��ʾͼ��ȫ����Ϣ", null, panel_2, null);

			final JPanel panel11 = new JPanel();
			panel11.setBorder(new TitledBorder(null, "����ͼ����Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			panel11.setPreferredSize(new Dimension(760, 520));
			panel_2.add(panel11, BorderLayout.NORTH);
			
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(700, 450));
			panel11.add(scrollPane);
			
			Object[][] results=getselect(Dao.selectBookseach());
			String [] booksearch = { "ͼ����", "ͼ������", "ͼ�����", "��������","������",  "��������", "�۸�","�鼮ҳ��","�ؼ���",  "�Ǽ�����", "������" ,"��ע" };
			table_1 = new JTable(results,booksearch);
			table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrollPane.setViewportView(table_1);
			
			}
		class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		}
	

}
