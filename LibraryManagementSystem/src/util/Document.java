package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Document extends PlainDocument{ 
	   int maxLength =10; 
	   public Document(int newMaxLength){ 
	      super(); 
	      maxLength = newMaxLength; 
	   } 
	   public Document(){ 
	      this(10); 
	   } 

	   //���ظ����insertString���� 
	    public void insertString(int offset, String str, AttributeSet a)
			throws BadLocationException {
		if (getLength() + str.length() > maxLength) {//����ٶ�������Ƴ���Ϊ10 
			return;
		} else {
			super.insertString(offset, str, a);

		}
	} 

	} 
