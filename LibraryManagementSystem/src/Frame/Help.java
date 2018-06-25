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
	setIconifiable(true);							// 设置窗体可最小化－－－必须
	setClosable(true);
	setTitle("帮助");
	setBounds(100, 100, 600, 400);
	setVisible(true);
	
	helptext = new JTextArea(600,400);
	helptext.setTabSize(4);  
	helptext.setFont(new Font("宋体", Font.PLAIN,16));  
	helptext.setLineWrap(true);// 激活自动换行功能  
	helptext.setWrapStyleWord(true);// 激活断行不断字功能  
	helptext.setEnabled(false);
	helptext.append("帮助信息");
	jscrollpane = new JScrollPane(helptext);
	getContentPane().add(jscrollpane);
	}
}