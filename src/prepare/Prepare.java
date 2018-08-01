package prepare;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.InputMismatchException;
import javax.lang.model.element.Element;
import javax.swing.*;

import org.jsoup.*;

import org.jsoup.Jsoup;

public class Prepare implements ActionListener {
	String HGMDAddress = "C:/Users/二哈/Desktop/计算机资料/HGMD/HGMD.txt";
	String writeAddress = "C:/Users/二哈/Desktop/计算机资料/HGMD/任务/pmid/";
	String pmid;
	JFrame frame = new JFrame();
	public JTextField textField = new JTextField(10);
	JPanel panel = new JPanel();
	public Prepare()
	{
		JButton okButton = new JButton("手动写入");
		JButton writeButton = new JButton("自动写入20条");
		frame.setTitle("预处理");
		frame.setBounds(300,300,300,300);
		okButton.addActionListener(this);
		writeButton.addActionListener(this);
		okButton.setEnabled(true);
		writeButton.setEnabled(true);
		frame.add(panel);
		frame.setBounds(400,400,400,400);
		frame.add(panel);
		panel.add(textField);
		panel.add(okButton);
		panel.add(writeButton);
		frame.setVisible(true);
	}
	@SuppressWarnings({ "unused", "null" })
	public static void main(String [] arge) throws IOException
	{
		Prepare a= new Prepare();
	}

	public static org.jsoup.nodes.Element getText(String url) throws IOException{
		String Url = "https://www.ncbi.nlm.nih.gov/pubmed/?term=" + url; 
		org.jsoup.nodes.Document doc =Jsoup.connect(Url).get(); 
		org.jsoup.nodes.Element text = doc.select("abstracttext").first();
		return text;		
		} 
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent arg0){
		// TODO Auto-generated method stub
		try
		{		
			if(arg0.getActionCommand() == "手动写入")
			{
				pmid = textField.getText();
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(HGMDAddress));
				BufferedWriter wr = null;
				String[] arrList = (br.readLine()).split("\\t");
				for(int n = 0 ; arrList != null; n ++)
				{
					arrList = (br.readLine()).split("\\t");
					if(arrList[25] == pmid)
					{
						System.out.println(pmid + "准备写入！");
						if(new File(writeAddress + pmid + ".txt").exists())
						{
							System.out.println(pmid + "已经存在！");
							break;
						}
						else
						{
							wr = new BufferedWriter(new FileWriter(writeAddress + pmid + ".txt"));
						}
						for(int i = 0; i < arrList.length; i++)
						{
							wr.write(arrList[i] + "	");	
						}
						try
						{
							String pmidText = getText(pmid).toString();
							wr.write(pmidText);
							System.out.println(pmid + "已经写入完毕！ ");
							wr.close();
						}
						catch(Exception e)
						{
							wr.write("NULL");
							System.out.println(pmid + "异常！ ");
							wr.close();
							break;
						}
					}
				}
				
			}
			if(arg0.getActionCommand() == "自动写入20条")
			{
				BufferedReader br = new BufferedReader(new FileReader(HGMDAddress));
				BufferedWriter wr = null;
				String[] arrList = (br.readLine()).split("\\t");
				String pmid;
				int numOfPmid = 0;
				for(int num = 0; arrList != null; num ++)
				{
					arrList = (br.readLine()).split("\\t");
					pmid = arrList[25];
					System.out.println(pmid + "准备写入！");
					if(new File(writeAddress + pmid + ".txt").exists())
					{
						System.out.println(pmid + "已经存在！");
						continue;
					}
					else
					{
						wr = new BufferedWriter(new FileWriter(writeAddress + pmid + ".txt"));
					}
					for(int i = 0; i < arrList.length; i++)
					{
						wr.write(arrList[i] + "	");	
					}
					try
					{
						String pmidText = getText(pmid).toString();
						wr.write(pmidText);
						numOfPmid ++;
						System.out.println(pmid + "已经写入完毕！ 第" + numOfPmid + "个！");
						wr.close();
					}
					catch(Exception e)
					{
						wr.write("NULL");
						numOfPmid ++;
						System.out.println(pmid + "异常！ 第" + numOfPmid + "个！");
						wr.close();
						continue;
					}
					if(numOfPmid == 20)
						break;
				}
				br.close();
				System.out.println("over");
			}
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
