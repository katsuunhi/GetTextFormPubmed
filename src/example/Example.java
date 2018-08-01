package example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import add.AddAbs;

public class Example implements ActionListener{
	public static DocColorTest dct = new DocColorTest();
	public static int fileNum = 0;
	public static File[] t = new File("C:/Users/二哈/Desktop/计算机资料/HGMD/任务/pmid").listFiles();
	public static String pmid;
	public JTextField textField = new JTextField(10);
	public static String text;
	public static String path;
	public String getText()
	{
		return text;
	}
	boolean flag = false;
	public static void main (String [] arge)
	{
		Example rf = new Example();

		rf.getFrame();
	}
	@SuppressWarnings("resource")
	public void getFrame()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton nextbutton = new JButton("下一个");
		JButton lastbutton = new JButton("上一个");
		frame.setBounds(300,300,300,300);
		frame.add(panel);
//		panel.add(textField);
		panel.add(nextbutton);
		panel.add(lastbutton);
		nextbutton.addActionListener(this);
		lastbutton.addActionListener(this);
		frame.setVisible(true);
		
		try
		{
			pmid = textField.getText();
			path = "C:/Users/二哈/Desktop/计算机资料/HGMD/任务/pmid/" + pmid + t[fileNum].getName();
			if(new File(path).exists())
			{
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(path));
				dct.insertDocument(new BufferedReader(new FileReader("C:/Users/二哈/Desktop/计算机资料/HGMD/HGMD.txt")).readLine() + "\n\n", new Color(0,0,0));
				dct.insertDocument(br.readLine() + "\n\n\n", new Color(0,0,0));
				text = br.readLine();	
				String pointText = "";
				String lastText = "";
				String sentences[] = text.split("\\. ");
				for(int length = 0; length < sentences.length; length ++)
					sentences[length] = sentences[length] + ". ";
				String formal1test = "(\\(*[AGCTU]{3,}\\)*|Val\\d{3,}|Leu\\d{3,}|Trp\\d{3,}|Met\\d{3,}|Ile\\d{3,}|Thr\\d{3,}|Lys\\d{3,}|Phe\\d{3,}|Cys\\d{3,}|Tyr\\d{3,}|Gln\\d{3,})";
				String AGCTU = formal1test ;
				Pattern AGCTUPattern = Pattern.compile(AGCTU);
				boolean wordsFlag = true;
				for(int i = 0; i < sentences.length; i ++)
				{
					lastText = "";
					int m = 0;
					wordsFlag = true;
					Matcher AGCTUMatcher = AGCTUPattern.matcher(sentences[i]);
					while(AGCTUMatcher.find())
					{
						wordsFlag = false;
						System.out.println(i + "|||" + AGCTUMatcher.group(1)+"|||"+AGCTUMatcher.start()+"|||"+AGCTUMatcher.end());
					    int start = AGCTUMatcher.start();
						int end = AGCTUMatcher.end();
						String pointWord;
						int length = sentences[i].length();
						for(; m < length; m ++)
						{
							flag = false;
							if(start == m)
							{
								pointWord = sentences[i].substring(start, end);
								dct.insertDocument(pointWord, new Color(222,0,0));
								flag = true;
								m = end;
								lastText = sentences[i].substring(end,length);
								break;
							}
							if(!flag)
							{
								dct.insertDocument(String.valueOf(sentences[i].charAt(m)),  new Color(0,0,0));
							}
						}
						
					}
					dct.insertDocument(lastText,  new Color(0,0,0));
					if(wordsFlag)
					{
						dct.insertDocument(sentences[i],  new Color(0,0,0));
					}
					else
						pointText = pointText + sentences[i] + "\n";
				}
				dct.insertDocument("\n\n\n" + pointText, new Color(222,0,0));
				br.close();
		
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@SuppressWarnings("resource")
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			dct.clear();
			if(arg0.getActionCommand() == "下一个")
				fileNum ++;
			if(arg0.getActionCommand() == "上一个")
				fileNum --;
			pmid = textField.getText();
			path = "C:/Users/二哈/Desktop/计算机资料/HGMD/任务/pmid/" + pmid + t[fileNum].getName();
			if(new File(path).exists())
			{
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(path));
				dct.insertDocument(new BufferedReader(new FileReader("C:/Users/二哈/Desktop/计算机资料/HGMD/HGMD.txt")).readLine() + "\n\n", new Color(0,0,0));
				dct.insertDocument(br.readLine() + "\n\n\n", new Color(0,0,0));
				text = br.readLine();	
				String pointText = "";
				String lastText = "";
				String sentences[] = text.split("\\. ");
				for(int length = 0; length < sentences.length; length ++)
					sentences[length] = sentences[length] + ". ";
				//Cys972(TGT) to Tyr972(TAT)
				String formal1test = "(\\(*[AGCTU]{3,}\\)*|Val\\d{3,}|Leu\\d{3,}|Trp\\d{3,}|Met\\d{3,}|Ile\\d{3,}|Thr\\d{3,}|Lys\\d{3,}|Phe\\d{3,}|Cys\\d{3,}|Tyr\\d{3,}|Gln\\d{3,})";
				String AGCTU = formal1test ;
				Pattern AGCTUPattern = Pattern.compile(AGCTU);
				//1370808
				boolean wordsFlag = true;
				for(int i = 0; i < sentences.length; i ++)
				{
					lastText = "";
					int m = 0;
					wordsFlag = true;
					Matcher AGCTUMatcher = AGCTUPattern.matcher(sentences[i]);
					while(AGCTUMatcher.find())
					{
						wordsFlag = false;
						System.out.println(i + "|||" + AGCTUMatcher.group(1)+"|||"+AGCTUMatcher.start()+"|||"+AGCTUMatcher.end());
					    int start = AGCTUMatcher.start();
						int end = AGCTUMatcher.end();
						String pointWord;
						int length = sentences[i].length();
						for(; m < length; m ++)
						{
							flag = false;
							if(start == m)
							{
								pointWord = sentences[i].substring(start, end);
								dct.insertDocument(pointWord, new Color(222,0,0));
								flag = true;
								m = end;
								lastText = sentences[i].substring(end,length);
								break;
							}
							if(!flag)
							{
								dct.insertDocument(String.valueOf(sentences[i].charAt(m)),  new Color(0,0,0));
							}
						}
						
					}
					dct.insertDocument(lastText,  new Color(0,0,0));
					if(wordsFlag)
					{
						dct.insertDocument(sentences[i],  new Color(0,0,0));
					}
					else
						pointText = pointText + sentences[i] + "\n";
				}
				dct.insertDocument("\n\n\n" + pointText, new Color(222,0,0));
				br.close();
		
			}
			
		}
	

	
			
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
	}
}

