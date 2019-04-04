package lexical_analyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Analyzer {
	
		 String mainstring;
		
		 BufferedWriter output;
		
		public Analyzer() {
			mainstring = "";
			try {
				
				output = new BufferedWriter(new FileWriter("output.txt"));
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		public  void getText()
		{
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader("input.txt"));
				String str;
				while ((str = reader.readLine()) != null)					//���벢����Ԥ����
				{
					str = str.trim().replace(" +", " ").replaceAll("\\t+",  " ");
					mainstring +=str;			
				}
				judgement();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			finally { // �ر���Դ
				if (reader != null)
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (output != null)
					try {
						output.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
			}
		}
		public  void judgement() throws Exception
		{
			//System.out.println(11111111);
			char ch = 0;
			int m =0;
			String str = null;
			for (int i = 0; i < mainstring.length(); i++)
			{
				//System.out.println(mainstring.length());
				
				//System.out.println();
				switch(m)
				{
				case 0:
					ch = mainstring.charAt(i);
					//System.out.println(ch);
					//�ж��Ƿ�Ϊ�����ʹ�ó�ǰ����
					if(isOperator(ch))
					{
						str="";
						str+=ch;
						if(ch=='+'||ch=='-'||ch=='>'||ch=='<'||ch=='!'||ch=='|'||ch=='&')
						{
							//����������,��Ϊ���������˫Ŀ����������Խ����ж���һ��
							ch = mainstring.charAt(i+1);
						
							if(isSpilt(ch))			//+ - & | = 
							{
								str += ch;
								m = 4;
							}
							else
							{
								ch =mainstring.charAt(i-1);
								m = 4;
							}
						}
					}
					else if(isBoundary(ch)!=0)
					{
						m = 5;
					}
					else if (isDigit(ch = mainstring.charAt(i)))
					{
						str = "";
						str+=ch;
						m = 3;
					}
					else if(isLetter(ch=mainstring.charAt(i)))
					{
						//System.out.println(ch);
						str = "";
						str+=ch;
						 m =2;
					}
					else if(ch=='_'||ch=='$')
					{
						str = "";
						str+=ch;
						m = 2;
					}
					else
					{
						
					}
					break;
					
				case 4:
					i--;
					System.out.println("(4 " + "�� " + str + " ��)");
					String word = ("(4 " + "�� " + str + " ��)");
					output.write(word+'\n');
					m= 0 ;
					break;
					
				case 5:
					i--;
					System.out.println(("(B"+isBoundary(ch) + ",�� " + ch + " ��)"));
					word = ("(B"+isBoundary(ch) + ",�� " + ch + " ��)");
					output.write(word+'\n');
					m= 0;
					break;
				case 2:
					if(isLetter(ch = mainstring.charAt(i)))
					{
						//System.out.println(str);
						str += ch;
						
					}
					else
					{
						if(isKeyword(str)!=0)
						{	//�ǹؼ��֣�ÿ���ؼ��ָ���Ϊһ��
							System.out.println("(k"+isKeyword(str) + ",�� " + str + " ��)");
							word = ("(k"+isKeyword(str)+ ",�� " + str + " ��)");
							output.write(word+'\n');
						}else
						{	//���ǹؼ��֣����ڱ����������������������ȵȣ���Ϊһ��
							  System.out.println(("(��ʶ�� " + ",�� " + str + " ��)"));
							  word = ("(��ʶ��" + ",�� " + str + " ��)");
							  output.write(word+'\n');
							  
						}
						i--;
						m=0;
						
					}
					break;
					
				case 3:
					if(isDigit(ch=mainstring.charAt(i)))
					{
						str+=ch;
						
					}
					else
					{	//���ֹ�Ϊһ��
						System.out.println("(����" + ",�� " + str + " ��)");
						word = ("(����" + ",�� " + str + " ��)");
						output.write(word+'\n');
						i--;
						m=0;
					}
					break;
				}
			}

		}
		/**
		 * str�Ƿ��ǹؼ���
		 * @param str
		 * @return
		 */
		public  int isKeyword(String str)					
		{
			for(int i =0;i<Table.Keyword.length;i++)
			{
				if(Table.Keyword[i].equals(str))
					return (i+1);
			}
			return 0;
		}
		/**
		 * ch�Ƿ��������
		 * @param ch
		 * @return
		 */
		public  boolean isOperator(char ch)
		{
			for (int i = 0; i < Table.operator.length; i++) {
				if(ch==Table.operator[i])
					return true;
			}
			return false;
			
		}
		/**
		 * �ж�˫Ŀ����������
		 * @param ch
		 * @return
		 */
		public  boolean isSpilt(char ch)
		{
			if(ch=='+'||ch=='-'||ch=='|'||ch=='='||ch=='&')
				return true;
			else
					return false;
		}
		/**
		 * �ж�ch�Ƿ��ǽ��
		 * @param ch
		 * @return
		 */
		public  int  isBoundary(char ch)
		{
			for (int i = 0; i < Table.Boundary.length; i++) {
				if(ch==Table.Boundary[i])
					return (i+1);
				
			}
			return 0;
			
		}
		/**
		 * �ж�ch�Ƿ�������
		 * @param ch
		 * @return
		 */
		public  boolean isDigit(char ch)
		{
			if(ch>='0'&&ch<='9')
			{
				return true;
			}
			return false;
		}
		/**
		 * �ж�ch�Ƿ�����ĸ
		 * @param ch
		 * @return
		 */
		public  boolean isLetter(char ch)
		{
			if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
			{
				return true;
				
			}
			return false;
		}
			
}