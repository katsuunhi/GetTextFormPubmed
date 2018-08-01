package ReadFile;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import add.AddAbs;

public class ReadFile implements ActionListener{
	String HGMDAddress = "C:/Users/二哈/Desktop/计算机资料/HGMD/HGMD.txt";
	String writeAddress = "C:/Users/二哈/Desktop/计算机资料/HGMD/任务/pmid/";
	public static String pmid;
	public JTextField textField = new JTextField(10);
	public static String text;
	public String getText()
	{
		return text;
	}
	boolean flag = false;
	public static void main (String [] arge)
	{
		ReadFile rf = new ReadFile();

		rf.getFrame();
	}
	public void getFrame()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton("确定");
		frame.setBounds(300,300,300,300);
		frame.add(panel);
		panel.add(textField);
		panel.add(button);
		button.addActionListener(this);
		frame.setVisible(true);
	}

	@SuppressWarnings("resource")
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			pmid = textField.getText();
			if(new File(writeAddress + pmid + ".txt").exists())
			{
				DocColorTest dct = new DocColorTest();
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(writeAddress + pmid + ".txt"));
				dct.insertDocument(new BufferedReader(new FileReader(HGMDAddress)).readLine() + "\n\n", new Color(0,0,0));
				dct.insertDocument(br.readLine() + "\n\n\n", new Color(0,0,0));
				text = br.readLine();	
				String pointText = "";
				String lastText = "";
				String sentences[] = text.split("\\. ");
				for(int length = 0; length < sentences.length; length ++)
					sentences[length] = sentences[length] + ". ";
				//Cys972(TGT) to Tyr972(TAT)
				String formal1test = "(\\(*[AGCTU]{3,}\\)*|Val\\d{3,}|Leu\\d{3,}|Trp\\d{3,}|Met\\d{3,}|Ile\\d{3,}|Thr\\d{3,}|Lys\\d{3,}|Phe\\d{3,}|Cys\\d{3,}|Tyr\\d{3,}|Gln\\d{3,})";
	//			String formal2test = "(Val|Leu|Trp|Met|Ile|Thr|Lys|Phe|Cys)";
	//			String formal3test = "([AGCTU]+[ *]to|>|/[ *][AGCTU]+)";
	//			String formal4test = "(Val|Leu|Trp|Met|Ile|Thr|Lys|Phe|Cys\\d{3,}\\([AGCTU]+ to (Val|Leu|Trp|Met|Ile|Thr|Lys|Phe|Cys)\\d{3,}\\([AGCTU]+)";
	//			String formal1 = "(.*)([A-Z]{3,})(.*)";
	//			String formal2 = "(.*)(Val|Leu|Trp|Met|Ile|Thr|Lys|Phe)(.*)";
	//			String formal3 = "(.*)((A|G|C|T|U)+)(( )*)(to|>|/)(( )*)((A|G|C|T|U)+)(.*)";
	//			String formal4 = "(.*)([A-Z]+)(\\d){3,}(.*)";
				String AGCTU = formal1test /*+ "|" + formal2test +"|" + formal3test + "|" + formal4test*/;
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
			
			else
			{
				JFrame frame = new JFrame();
				JLabel label = new JLabel();
				JPanel panel = new JPanel();
				panel.add(label);
				label.setEnabled(false);
				label.setText(pmid + "文件不存在");
				frame.add(panel);
				frame.setBounds(100,100,100,100);
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
