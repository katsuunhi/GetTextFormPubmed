package add;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import org.jsoup.Jsoup;

public class AddAbs implements ActionListener {
	String HGMDAddress = "C:/Users/����/Desktop/���������/HGMD/HGMD.txt";
	String writeAddress = "C:/Users/����/Desktop/���������/HGMD/����/pmid/";
	public JFrame frame = new JFrame();
	public JPanel panel = new JPanel();
	public JTextField textField = new JTextField(10);
	public JButton button = new JButton("���");
	public String pmid = null;
	public AddAbs()
	{
		frame.setTitle("���");
		frame.setBounds(300,300,300,300);
		button.addActionListener(this);
		button.setEnabled(true);
		frame.add(panel);
		panel.add(textField);
		panel.add(button);
	}
	public void judge() throws IOException
	{
		JFrame frame = new JFrame();
		JLabel label = new JLabel();
		frame.setBounds(100,100,100,100);
		label.setEnabled(false);
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.add(label);			
		if(new File(writeAddress + pmid + ".txt").exists())
		{
			label.setText(pmid + "�Ѿ����ڣ�");
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
		}
		else
		{
			BufferedWriter wr = new BufferedWriter(new FileWriter(writeAddress + pmid + ".txt"));
			try
			{
				wr.write(getText(pmid).toString());
				label.setText(pmid + "д����ϣ�");
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);
				wr.close();
			}
			catch(Exception e)
			{
				wr.write("NULL");
				label.setText(pmid + "д���쳣��");
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);
				wr.close();
			}
		}
	}
	public static org.jsoup.nodes.Element getText(String url) throws IOException{
		String Url = "https://www.ncbi.nlm.nih.gov/pubmed/?term=" + url; 
		org.jsoup.nodes.Document doc =Jsoup.connect(Url).get(); 
		org.jsoup.nodes.Element text = doc.select("abstracttext").first();
		return text;
		}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		pmid = textField.getText();
		try {
			judge();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String [] arge)
	{
		AddAbs add= new AddAbs();
		add.frame.setVisible(true);
	}

}
