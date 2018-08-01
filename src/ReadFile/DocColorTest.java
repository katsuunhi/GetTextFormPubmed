package ReadFile;


import java.awt.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


@SuppressWarnings("serial")
public class DocColorTest extends JFrame{
	JTextPane textPane = new JTextPane();
	JPanel contPane = new JPanel();
	public DocColorTest()
	{
		 super("Test");
		 Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	//	 setBounds((d.width)/2,(d.height)/2,1000,1000);
		 setBounds((d.width-1000)/2,(d.height-600)/2,1000,600);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 contPane.setLayout(new BorderLayout());
		 contPane.add(new JScrollPane(textPane),"Center");
		 setContentPane(contPane);
		 setVisible(true);
	}
	public void insertDocument(String text , Color textColor)//���ݴ������ɫ�����֣������ֲ����ı���
	{
		 SimpleAttributeSet set = new SimpleAttributeSet();
		 StyleConstants.setForeground(set, textColor);//����������ɫ
		 StyleConstants.setFontSize(set, 12);//���������С
		 Document doc = textPane.getStyledDocument();
		 try
		 {
			 doc.insertString(doc.getLength(), text, set);//��������
		 }
		 catch (BadLocationException e)
		 {
		 }
	}

}
