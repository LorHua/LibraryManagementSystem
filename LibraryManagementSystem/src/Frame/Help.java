package Frame;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Help extends JInternalFrame {
	
	private JTextArea helptext;
	private JScrollPane jscrollpane;
	
	public Help(){
	super();
	setIconifiable(true);							// ���ô������С������������
	setClosable(true);
	setTitle("����");
	setBounds(100, 100, 600, 400);
	setVisible(true);
	
	helptext = new JTextArea(600,400);
	helptext.setTabSize(4);  
	helptext.setFont(new Font("����", Font.PLAIN,16));  
	helptext.setLineWrap(true);// �����Զ����й���  
	helptext.setWrapStyleWord(true);// ������в����ֹ���  
	helptext.setEnabled(false);
	helptext.append("������Ϣ");
	jscrollpane = new JScrollPane(helptext);
	getContentPane().add(jscrollpane);
	}
}