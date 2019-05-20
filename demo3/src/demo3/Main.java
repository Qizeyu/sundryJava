package demo3;

import java.util.*;
import java.util.Map.Entry;

public class Main {
	public static Character start_terminal;//开始符号
    public static LinkedHashMap<Character, String> production = new LinkedHashMap<>();
    public static LinkedHashSet<Character> non_terminal = new LinkedHashSet<>();
    public static LinkedHashSet<Character> terminal = new LinkedHashSet<>();
    public static LinkedHashSet<Character> all_terminal = new LinkedHashSet<>();
    public static LinkedHashMap<Character,HashSet<Character>> firstVT =new LinkedHashMap<>();
    public static LinkedHashMap<Character,HashSet<Character>> lastVT =new LinkedHashMap<>();
    public static int[][] OPMatrix;
    public static char[] terminalList;
    public static Scanner in = new Scanner(System.in);
    public static String string;
    public static void main(String[] args) {
        // TODO 输入产生式
        inputProduction();
        // TODO 判断是否为算符优先算法文法
        printInformation();
        
       if(isOG())
       {
    	 
    	   getFirstVT();		//求firstvt集合
    	   getLastVT();			//求lastvt集合
    	   getOPMatrix();
    	   printOPMatrix1();
    	   
    	   if(isOPG())
    	   {
    		   output(firstVT);
    		   output(lastVT);
    		   System.out.println("是算符优先文法");
    		   printOPMatrix();
    		  
    		   System.out.println("请输入句型");
    		   Statute();
    	   }
    	   else
    	   {
    		   System.out.println("不是算符优先文法");
    	   }
       }
       else
       {
    	   System.out.println("不是算符文法");
       }
//       output(lastVT);
//       System.out.println(firstVT.get('E'));
       
 
//        // TODO 求算符优先矩阵
//        getOPMatrix();
//        // TODO 判断是否为算法优先文法
//        System.out.println(isOPG());

    }
    
//    public static boolean isOG(){
//    	Set<Map.Entry<Character, String>> entries = production.entrySet();
//    	  for (Map.Entry<Character, String> entry : entries) {
//    		char[] c = entry.getValue().toCharArray();
//    		
//    		  
//    		  for(int i=0;i<c.length-1;i++)
//    		  {
//    			 if(judge(c[i],non_terminal)==true)
//    			 {
//    				 if(judge(c[i+1],non_terminal)==true)
//    				 {
//    					 return false;
//    				 }
//    			 }
//    		  }
//              
//          }
//        return  true;
//    }
   
    public  static boolean isOPG(){
    	Set<Integer> e = new HashSet<>();
    	e.add(0);e.add(1);e.add(2);e.add(4);
    	       for(int i=0;i<OPMatrix.length;i++){
    	           for (int j=0;j<OPMatrix.length;j++){
    	               if(!e.contains(OPMatrix[i][j])){
    	            	   System.out.println(i+" "+j);
    	                   return false;
    	               }
    	           }
    	       }
    	      
    	       return true;
   }
    public static boolean isOG(){
        Set<Map.Entry<Character, String>> entries = production.entrySet();
        for (Map.Entry<Character, String> entry : entries) {
            char[] chars = entry.getValue().toCharArray();
            for (int i = 0; i < chars.length-1; i++) {
                if(non_terminal.contains(chars[i])&&non_terminal.contains(chars[i+1])){
                    return false;
                }
            }
        }
        return  true;
    }
   

    public static void getFirstVT(){
    	 Set<Map.Entry<Character, String>> entries = production.entrySet();
    	 for (Map.Entry<Character, String> entry : entries) {
             char[] c = entry.getValue().toCharArray();
             int flag =1;
             HashSet<Character> set = new HashSet();
             for(int i=0;i<c.length;i++)
             {
            	 if(c[i]=='|')
            	 {
            		 flag = 1;
            		 
            	 }
            	 else if(judge(c[i],terminal)==true&&flag==1)
            	 {
            		set.add(c[i]);
            		flag = 0;
            		
            	 }
            	
            	 
             }
             firstVT.put(entry.getKey(),set);
         }
    	 for(int k =0;k<production.size()-1;k++)
    	 {
    	 Set<Map.Entry<Character, String>> entries2 = production.entrySet();
    	 for (Map.Entry<Character, String> entry : entries2) {
    		 char[] c = entry.getValue().toCharArray();
    		 int flag = 1;
    		 HashSet<Character> set = firstVT.get(entry.getKey());
    		for(int i=0;i<c.length;i++)
    		{
    			if(c[i]=='|')
    			{
    				flag=1;
    			}
    			if(judge(c[i],terminal)==true)
    			{
    				flag=0;
    				
    			}
    			if(judge(c[i],non_terminal)==true&&flag==1)
    			{
    				flag=0;
    			
    				if(c[i]!=entry.getKey())
    				{
    					HashSet<Character> set2 = firstVT.get(c[i]);
    					set.addAll(set2);
    					
    				}
    						
    			}
    		}
    		 firstVT.put(entry.getKey(),set);	
    	 	}
    	 }
    }
   
    public  static void getLastVT(){
    	 Set<Map.Entry<Character, String>> entries = production.entrySet();
    	for(Entry<Character, String> entry :entries)
    	{
    		char[] c = entry.getValue().toCharArray();
            int flag =1;
            HashSet<Character> set = new HashSet();
            for(int i=c.length-1;i>0;i--)
            {
           	 if(c[i]=='|')
           	 {
           		 flag = 1;
           		 
           	 }
           	 else if(judge(c[i],terminal)==true&&flag==1)
           	 {
           		set.add(c[i]);
           		flag = 0;          		
           	 }
      	 
            }
            lastVT.put(entry.getKey(),set);
        }
    	for(int k=0;k<production.size();k++)
    	{
    		Set<Map.Entry<Character, String>> entries2 = production.entrySet();
       	 for (Map.Entry<Character, String> entry : entries2) {
       		 char[] c = entry.getValue().toCharArray();
       		 int flag = 1;
       		 HashSet<Character> set = lastVT.get(entry.getKey());
       		for(int i=c.length-1;i>0;i--)
       		{
       			if(c[i]=='|')
       			{
       				flag=1;
       			}
       			if(judge(c[i],terminal)==true)
       			{
       				flag=0;
       				
       			}
       			if(judge(c[i],non_terminal)==true&&flag==1)
       			{
       				flag=0;
       			
       				if(c[i]!=entry.getKey())
       				{
       					HashSet<Character> set2 = lastVT.get(c[i]);
       					set.addAll(set2);
       					
       				}
       						
       			}
       		}
       		 lastVT.put(entry.getKey(),set);	
       	 	}
    	}
    	
    	
    }

    public static void  getOPMatrix(){
    	 Set<Map.Entry<Character, String>> entries = production.entrySet();
    	 for (Map.Entry<Character, String> entry : entries) {
             start_terminal=entry.getKey();
             break;
         }
    	OPMatrix = new int[terminal.size()+1][terminal.size()+1];
    	terminalList = new char[terminal.size()+1];
    	int k=0;
    	for(Character character:terminal)
    	{
    		terminalList[k++]=character;
    	}
    	terminalList[k]='#';
    	HashMap<Character,Integer> indexMap = new HashMap<>();
    	for(int i=0;i<terminalList.length;i++)
    	{
    		indexMap.put(terminalList[i], i);
    		
    	}
    	for(Map.Entry<Character,String> entry:entries)
    	{
    		String s = entry.getValue();
    		String[] splits = s.split("\\|");
    		for(String split:splits)
    		{
    			char[] chars = split.toCharArray();
    			for(int w=0;w<chars.length-1;w++)
    			{
    				if(non_terminal.contains(chars[w])&&terminal.contains(chars[w+1]))
    				{//xi为非终结符而xi+1为终结符
    					int a1 = indexMap.get(chars[w+1]);
    					HashSet<Character> characters = lastVT.get(chars[w]);
    					for(Character character: characters)
    					{
    						int a2 = indexMap.get(character);
    						OPMatrix[a2][a1]+=4;
    						
    					}
    				}
    				if(terminal.contains(chars[w])&&non_terminal.contains(chars[w+1]))
    				{
    					int a1 = indexMap.get(chars[w]);
    					HashSet<Character> characters = firstVT.get(chars[w+1]);
    					for(Character character:characters)
    					{
    						int a2 = indexMap.get(character);
    						OPMatrix[a1][a2]+=1;
    						
    					}
    				}
    				if(w>0)
    				{
    					if(terminal.contains(chars[w-1])&&non_terminal.contains(chars[w])&&terminal.contains(chars[w+1]))
    					{
    						int a1 = indexMap.get(chars[w-1]);
    						int a2 = indexMap.get(chars[w+1]);
    						OPMatrix[a1][a2]  +=2;
    						
    					}
    				}
    				
    			}
    		}
    	}
    	int a1=indexMap.get('#');
        HashSet<Character> characters = firstVT.get(start_terminal);
        for (Character character : characters) {
            Integer a2 = indexMap.get(character);
            OPMatrix[a1][a2]+=1;
        }
        characters = lastVT.get(start_terminal);
        for (Character character : characters) {
            Integer a2 = indexMap.get(character);
            OPMatrix[a2][a1]+=4;
        }
        OPMatrix[a1][a1]+=2;
        
    	
    }
 
    public static void output(LinkedHashMap<Character,HashSet<Character>> s)
    {
    	Set<Entry<Character, HashSet<Character>>> entries = s.entrySet();
    	 for (Entry<Character, HashSet<Character>> entry : entries) {
    		 System.out.println(entry.getKey()+" -> "+entry.getValue());	 
    	 }	
    }
    public static void inputProduction() {
        String s = null;
        while (!(s = in.nextLine()).equals("#")) {
            String[] splits = s.split("->");
            production.put(splits[0].toCharArray()[0],splits[1]);
            non_terminal.add(splits[0].toCharArray()[0]);
            all_terminal.add(splits[0].toCharArray()[0]);
            terminal.add(splits[0].toCharArray()[0]);
            char[] chars = splits[1].toCharArray();
            for (char c : chars) {
                all_terminal.add(c);
                terminal.add(c);
            }
        }
        all_terminal.remove('|');
        terminal.remove('|');
        terminal.removeAll(non_terminal);
    }
    public static void printInformation(){
        Set<Map.Entry<Character, String>> entries = production.entrySet();
        System.out.println("产生式：");
        for (Map.Entry<Character, String> entry : entries) {
            System.out.println(entry.getKey()+" -> "+entry.getValue());
        }
        System.out.println("非终结符");
        for (Character character : non_terminal) {
            System.out.print( character+" ");
        }
        System.out.println();
        System.out.println("终结符");
        for (Character character : terminal) {
            System.out.print( character+" ");
        }
        System.out.println();
        System.out.println("所有符号");
        for (Character character : all_terminal) {
            System.out.print( character+" ");
        }
        System.out.println();
    }
    public static boolean judge(Character a,LinkedHashSet<Character> s)
    {
    	for(Character character :s)
    	{
    		if(character==a)
    		{
    			return true;
    		}
    	}
		return false;
    	
    }
    public static void printOPMatrix(){
        System.out.print("  ");
        for (char c : terminalList) {
            System.out.print(c+" ");
        }
        System.out.println();
        for(int i=0;i<terminalList.length;i++){
            System.out.print(terminalList[i]+" ");
            for(int j=0;j<terminalList.length;j++){
            	if(OPMatrix[i][j]==4)
            	{
            		System.out.print(">"+" ");
            	}
            	else if(OPMatrix[i][j]==2)
            	{
            		System.out.print("="+" ");
            	}
            	else if(OPMatrix[i][j]==1)
            	{
            		System.out.print("<"+" ");
            	}
            	else
            		System.out.print(" "+" ");
            }
            System.out.println();
        }

    }
    public static void printOPMatrix1(){
        System.out.print("  ");
        for (char c : terminalList) {
            System.out.print(c+" ");
        }
        System.out.println();
        for(int i=0;i<terminalList.length;i++){
            System.out.print(terminalList[i]+" ");
            for(int j=0;j<terminalList.length;j++){
                System.out.print(OPMatrix[i][j]+" ");
            }
            System.out.println();
        }

    }
    public static void Statute()
    {
    	string = in.nextLine();
    	System.out.println(string);
    	char[] p  =string.toCharArray();
    	
    	Stack<Character> stack  = new Stack<>();
    	int k=1;
    	stack.push('#');
    	
    	while(!string.equals("#"+start_terminal+"#")&&k<p.length)
    	{
    		
    		if((p[k]=='#')||terminal.contains(p[k]))
    		{
    			
    			if((!stack.empty())&&(compareto(stack.peek(),p[k])==1||compareto(stack.peek(), p[k])==2))
    			{
    				//System.out.println(stack.peek());
    				
    				stack.push(p[k]);
    				
    			}
    			else if((!stack.empty())&&compareto(stack.peek(), p[k])==4)
    			{
    				int j;
    				char c = stack.pop();
    				if(non_terminal.contains(p[k-1]))
    				{
    					j=k-2;
    				}
    				else
    				{
    					j=k-1;
    				}
    				while((!stack.empty())&&compareto(stack.peek(), p[j])==2)
    				{
    					if(j-1>0&&non_terminal.contains(p[j-1]))
        				{
        					j=k-2;
        				}
        				else
        				{
        					j=k-1;
        				}
    					stack.pop();
    				}
    				if(j-1>0&&non_terminal.contains(p[j-1]))
    				{
    					j=j-1;
    				}
    				guiyue(j,k-1);
    				System.out.println(string);	
    				
    				k=0;
    				while(!stack.empty())
    				{
    					stack.pop();
    				}
    				stack.push('#');
    				p = string.toCharArray();
    				
    			}			
    		}		
    	k++;
    	}
    	System.out.println("规约成功");
    }
    public static void guiyue(int j,int k)
    {
    	
    	System.out.println("进行规约");
    	String str = string.substring(j,k+1);
    	System.out.print("str:"+str+"------------");
    	ArrayList<Character> arrayList = new ArrayList<>();
    	for(int i=0;i<str.length();i++)
    	{
    		if(terminal.contains(str.charAt(i)))
    		{
    			arrayList.add(str.charAt(i));
    			
    		}
    	}
    	
    	Set<Map.Entry<Character, String>> entries = production.entrySet();
    	 for (Map.Entry<Character, String> entry : entries) {
    		 String[] strings = entry.getValue().split("\\|");
    		for(int i=0;i<strings.length;i++)
    		{
    			//System.out.println(strings[i]);
    			for(int w = 0;w < arrayList.size(); w ++){
    				String character  = arrayList.get(w).toString();
    	            if(strings[i].indexOf(character)!=-1)
    	            {
    	            	string = string.replace(str, entry.getKey().toString());
    	            }
    	        }
    			
    			
//    				System.out.println("替换");
//    				System.out.println("替换字符"+entry.getKey().toString());
    			
    			
    		}
         }
    
    
    }
    public static int compareto(char c1,char c2)
    {			//1< 2= 4> 0不存在
    	HashMap<Character,Integer> indexMap = new HashMap<>();
    	for(int i=0;i<terminalList.length;i++)
    	{
    		indexMap.put(terminalList[i], i);
    		
    	}
    	int a1 = indexMap.get(c1);
    	int a2 = indexMap.get(c2);
    	//System.out.println(c1+c2+OPMatrix[a1][a2]);
    	return OPMatrix[a1][a2];
    	

    }
   
}
