package lexical_analyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 
 * @author qzy
 *
 */
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
				while ((str = reader.readLine()) != null)					//读入并进行预处理
				{
					str = str.trim().replace(" +", " ").replaceAll("\\t+",  " ");
					mainstring +=str;	
//					System.out.println(mainstring);
				}
				System.out.println(mainstring);
				judgement();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			finally { // 关闭资源
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
					//判断是否为运算符使用超前搜索
					if(isOperator(ch))
					{
						str="";
						str+=ch;
						if(ch=='+'||ch=='-'||ch=='>'||ch=='<'||ch=='!'||ch=='|'||ch=='&')
						{
							//如果是运算符,因为运算符存在双目运算符，所以接着判断下一个
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
					System.out.println("(4 " + "“ " + str + " ”)");
					String word = ("(4 " + "“ " + str + " ”)");
					output.write(word+'\n');
					m= 0 ;
					break;
					
				case 5:
					i--;
					System.out.println(("(B"+isBoundary(ch) + ",“ " + ch + " ”)"));
					word = ("(B"+isBoundary(ch) + ",“ " + ch + " ”)");
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
						{	//是关键字，每个关键字各归为一类
							System.out.println("(k"+isKeyword(str) + ",“ " + str + " ”)");
							word = ("(k"+isKeyword(str)+ ",“ " + str + " ”)");
							output.write(word+'\n');
						}else
						{	//不是关键字，属于变量名，函数名，数组名等等，归为一类
							  System.out.println(("(标识符 " + ",“ " + str + " ”)"));
							  word = ("(标识符" + ",“ " + str + " ”)");
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
					{	//数字归为一类
						System.out.println("(数字" + ",“ " + str + " ”)");
						word = ("(数字" + ",“ " + str + " ”)");
						output.write(word+'\n');
						i--;
						m=0;
					}
					break;
				}
			}

		}
		/**
		 * str是否是关键字
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
		 * ch是否是运算符
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
		 * 判断双目运算符的情况
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
		 * 判断ch是否是界符
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
		 * 判断ch是否是数字
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
		 * 判断ch是否是字母
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
