package Frame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import Frame.ReaderTypeAdd.CanborrowListener;
import Frame.ReaderTypeAdd.DealineListener;
import TableModel.readertype;
//import model.ReaderType;
import util.CreatedIcon;
import util.Document;

public class ReaderTypeFit extends JInternalFrame {

	//private JComboBox comboBox;
	
	private JTextField typename;
	private JTextField id;
	private JTextField canborrow;
	private JTextField deadline;
	private JTable table;
	private String[] array;
	private String[] columnNames={ "类别编号", "类别名称", "可借册数","借书期限"};
	//DefaultComboBoxModel bookTypeModel;
	DefaultTableModel model;
	Map map;
	
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			readertype readertype=(readertype)list.get(i);
			results[i][0]=readertype.getTypeid();
			results[i][1]=readertype.getTypename();
			results[i][2]=readertype.getCanborrowint();
            results[i][3]=readertype.getDeadline();
            System.out.println(results[i][0]);
		}
		return results;
	}
	
	public ReaderTypeFit() {
		super();
		setTitle("读者类别修改");
		setBounds(100, 100, 500, 380);
		setIconifiable(true);
		setClosable(true);
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.NORTH);

		final JLabel logoLabel = new JLabel();
		
		ImageIcon bookTypeModiAndDelIcon=CreatedIcon.add("ReaderTypeFit.jpg");
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
		Object[][] results=getFileStates(Dao.selectReaderType());
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
		label.setText("类别编号:");
		panel_2.add(label);
		id = new JTextField();
		id.setFocusable(false);
		id.setEditable(false);
		panel_2.add(id);
		
		final JLabel label_1 = new JLabel();
		label_1.setText("类别名称：");
		panel_2.add(label_1);
		typename = new JTextField();
		panel_2.add(typename);
		
		final JLabel label_2 = new JLabel();
		label_2.setText("可借册数：");
		panel_2.add(label_2);
		canborrow = new JTextField();
		canborrow.setColumns(20);
		canborrow.setDocument(new Document(3));
		canborrow.addKeyListener(new CanborrowListener());
		panel_2.add(canborrow);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("借书期限（天）：");
		panel_2.add(label_3);

		deadline = new JTextField();
		deadline.setColumns(20);
		deadline.setDocument(new Document(4));
		deadline.addKeyListener(new DealineListener());
		panel_2.add(deadline);
		
		final JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);

		final JButton buttonMod = new JButton();
		buttonMod.setText("修改");
		buttonMod.addActionListener(new ButtonAddListener());
		panel_4.add(buttonMod);

		final JButton buttonExit = new JButton();
		buttonExit.setText("退出");
		buttonExit.addActionListener(new CloseActionListener());
		panel_4.add(buttonExit);
		setVisible(true);
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = table.getSelectedRow();
			id.setText(table.getValueAt(selRow, 0).toString().trim());
			typename.setText(table.getValueAt(selRow, 1).toString().trim());
			canborrow.setText(table.getValueAt(selRow, 2).toString().trim());
			deadline.setText(table.getValueAt(selRow, 3).toString().trim());
		}
	}
	class ButtonAddListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(typename.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者类别不可为空");
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
			//Object selectedItem = bookTypeModel.getSelectedItem();
			else {
				int i=Dao.updateReaderType(Integer.parseInt(id.getText()),typename.getText().trim(),Integer.parseInt(canborrow.getText()),Integer.parseInt(deadline.getText()));
				System.out.println(i);
				if(i==1){
					JOptionPane.showMessageDialog(null, "修改成功");
					Object[][] results=getFileStates(Dao.selectReaderType());
					model.setDataVector(results,columnNames);
					table.setModel(model);
				}
				else JOptionPane.showMessageDialog(null, "读者类别名称已存在!");
			}
		}
	}
	class ButtonDelListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int i=Dao.deleteReaderType(typename.getText().trim());
			if(i==1){
				JOptionPane.showMessageDialog(null, "删除成功");
				Object[][] results=getFileStates(Dao.selectReaderType());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
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