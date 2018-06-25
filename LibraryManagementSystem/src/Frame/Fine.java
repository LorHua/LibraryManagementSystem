package Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.Dao;
//import model.fine;
import util.Document;
import util.CreatedIcon;

public class Fine extends JInternalFrame {
	private JTextField punish;
	public Fine() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("���ڷ�������");
		setBounds(100, 100, 400,200);
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.NORTH);

		final JLabel logoLabel = new JLabel();

		ImageIcon bookTypeModiAndDelIcon=CreatedIcon.add("Fine.jpg");
		logoLabel.setIcon(bookTypeModiAndDelIcon);
		
		logoLabel.setPreferredSize(new Dimension(400, 80));
		panel.add(logoLabel);

		final JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		final JLabel label = new JLabel();
		label.setText("���ڷ�����/Ԫ����");
		panel_1.add(label);

		punish = new JTextField();
		punish.setDocument(new Document(5));
		punish.setColumns(10);
		punish.addKeyListener(new NumberListener());
		punish.setText(String.valueOf(Dao.selectFine()));
		panel_1.add(punish);

		final JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4,BorderLayout.SOUTH);
		final JButton buttonMod = new JButton();
		buttonMod.setText("����");
		buttonMod.addActionListener(new ButtonAddListener());
		panel_4.add(buttonMod);

		final JButton buttonExit = new JButton();
		buttonExit.setText("�˳�");
		buttonExit.addActionListener(new CloseActionListener());
		panel_4.add(buttonExit);
		setVisible(true);
	}
	class ButtonAddListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(punish.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���ڷ��𲻿���Ϊ��");
				return;
			}
			else {
				int i=Dao.updateFine("����",Double.parseDouble(punish.getText()));
				System.out.println(i);
				if(i==1){
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					doDefaultCloseAction();
				}
				else {
					JOptionPane.showMessageDialog(null, "�޸�ʧ��");
				}
			}
		}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
}