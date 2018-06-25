package Frame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import JComPz.Item;
import Dao.Dao;
import Jcom.Item;
import TableModel.booktype;
//import model.BookType;
import util.CreatedIcon;

public class BookTypeFit extends JInternalFrame {

	//private JComboBox comboBox;
	private JTextField code;
	private JTextField typename;
	private JTextField keyword;
	private JTable table;
	private String[] array;
	private String[] columnNames={ "�����", "�������", "�ؼ���"};
	//DefaultComboBoxModel bookTypeModel;
	DefaultTableModel model;
	Map map;
	
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			booktype booktype=(booktype)list.get(i);
			results[i][0]=booktype.getTypeid();
			results[i][1]=booktype.getTypename();
			results[i][2]=booktype.getKeyword();
		}
		return results;        		
	}
	
	public BookTypeFit() {
		super();
		setTitle("ͼ������޸�");
		setBounds(100, 100, 500, 380);
		setIconifiable(true);
		setClosable(true);
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.NORTH);

		final JLabel logoLabel = new JLabel();
		
		ImageIcon bookTypeModiAndDelIcon=CreatedIcon.add("booktypemodify.jpg");
		logoLabel.setIcon(bookTypeModiAndDelIcon);
		
		logoLabel.setPreferredSize(new Dimension(400, 80));
		panel.add(logoLabel);

		final JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 150));
		panel_1.add(scrollPane);

		model=new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		Object[][] results=getFileStates(Dao.selectBookType());
		model.setDataVector(results,columnNames);
		table = new JTable();
		table.setModel(model);
		table.addMouseListener(new TableListener());
		scrollPane.setViewportView(table);
		
		final JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel_2.setLayout(gridLayout);
		panel_2.setPreferredSize(new Dimension(400, 60));

		final JLabel label = new JLabel();
		label.setText("����ţ�");
		panel_2.add(label);

		code = new JTextField();
		code.setFocusable(false);
		code.setEditable(false);
		panel_2.add(code);

		final JLabel label_1 = new JLabel();
		label_1.setText("������ƣ�");
		panel_2.add(label_1);
		
		typename = new JTextField();
		panel_2.add(typename);
		//comboBox = new JComboBox();
		//bookTypeModel= (DefaultComboBoxModel)comboBox.getModel();
		
		//�����ݿ���ȡ��ͼ�����
		/*List list=Dao.selectBookType();
		for(int i=0;i<list.size();i++){
			booktype booktype=(booktype)list.get(i);
			Item item=new Item();
			item.setId(booktype.getTypeid());
			item.setName(booktype.getTypename());
			//bookTypeModel.addElement(item);
		}*/
		//panel_2.add(comboBox);
		
		final JLabel label_2 = new JLabel();
		label_2.setText("�ؼ��ʣ�");
		panel_2.add(label_2);
		
		keyword = new JTextField();
		panel_2.add(keyword);
		
		final JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);

		final JButton buttonMod = new JButton();
		buttonMod.setText("�޸�");
		buttonMod.addActionListener(new ButtonAddListener());
		panel_4.add(buttonMod);

		final JButton buttonExit = new JButton();
		buttonExit.setText("�˳�");
		buttonExit.addActionListener(new CloseActionListener());
		panel_4.add(buttonExit);
		setVisible(true);
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = table.getSelectedRow();
			code.setText(table.getValueAt(selRow, 0).toString().trim());
			//bookTypeModel.setSelectedItem(table.getValueAt(selRow, 1).toString().trim());
			typename.setText(table.getValueAt(selRow, 1).toString().trim());
			keyword.setText(table.getValueAt(selRow, 2).toString().trim());
		}
	}
	class ButtonAddListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Object selectedItem = bookTypeModel.getSelectedItem();
			if(typename.getText().length()==0){
				JOptionPane.showMessageDialog(null, "������Ʋ���Ϊ��");
				return;
			}
			else if(keyword.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�ؼ��ʲ���Ϊ��");
				return;
			}
			else {
				int i=Dao.updateBookType(typename.getText().trim(),code.getText().trim(),keyword.getText().trim());
				System.out.println(i);
				if(i==1){
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					Object[][] results=getFileStates(Dao.selectBookType());
					model.setDataVector(results,columnNames);
					table.setModel(model);
				}
				else {
					JOptionPane.showMessageDialog(null, "ͼ����������Ѵ���!");
					//doDefaultCloseAction();
				}
			}
		}
	}
	class ButtonDelListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Object selectedItem = bookTypeModel.getSelectedItem();
			int i=Dao.deleteBookType(typename.toString());
			if(i==1){
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				Object[][] results=getFileStates(Dao.selectBookType());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}