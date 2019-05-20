package demo4;


import java.util.*;

public class Main {
    public  static Character start_terminal;
    public static LinkedHashMap<Character, String> production = new LinkedHashMap<>();
    public static LinkedHashSet<Character> non_terminal = new LinkedHashSet<>();
    public static LinkedHashSet<Character> terminal = new LinkedHashSet<>();
    public static LinkedHashSet<Character> all_terminal = new LinkedHashSet<>();
    public static LinkedHashSet<String> leftRecursion = new LinkedHashSet<>();
    public static LinkedHashMap<Character,HashSet<Character>> first =new LinkedHashMap<>();
    public static LinkedHashMap<Character,HashSet<Character>> follow =new LinkedHashMap<>();
    public static String[][] predictTable;

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO 输入产生式
        inputProduction();
      // printInformation();
        // TODO 寻找左递归
//        toFindLeftRecursion();

//        System.out.println("找到左递归");
//        for (String s : leftRecursion) {
//            System.out.println(s);
//        }
        // TODO 处理左递归
//        dealLeftRecursion();
        //  TODO 求first集
        getFirst();
        ptintFirst();
        //  TODO 求follow集
        getFollow();
        printFollow();
        System.out.println();
        System.out.println();
        // TODO 判断是否为ll1
        if(isLL1()){
            //  TODO 求预测分析表集
            getPredictTable();
            printpredictTable();
            analyist();
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
    public static void toFindLeftRecursion(){
        for (Character c : non_terminal) {
            findLeftRecursion(c,"");
        }

    }
    public static void findLeftRecursion(Character c,String s){
        String value = production.get(c);
        String[] splits = value.split("\\|");
        for (String split : splits) {
            if(non_terminal.contains(split.charAt(0))){
                int a=s.indexOf(c);
                if(a!=-1){
                    s=s+c;
                    leftRecursion.add(s.substring(a));
                    return;
                }
                else {
                    findLeftRecursion(split.charAt(0),s+c);
                    return;
                }

            }
        }
    }
    public static void dealLeftRecursion(){


    }
    public static void getFirst(){
        Set<Map.Entry<Character, String>> entries = production.entrySet();
        for (Map.Entry<Character, String> entry : entries) {
            getOneFirst(entry.getKey());
        }
    }
    public static void getOneFirst(Character c){
        String s=production.get(c);
        if(!first.containsValue(c)){
            HashSet<Character> set = new HashSet<>();
            first.put(c,set);
        }
        String[] splits = s.split("\\|");
        for (String split : splits) {
            if(terminal.contains(split.charAt(0))){
                    HashSet<Character> set = first.get(c);
                    set.add(split.charAt(0));

            }else {
                getOneFirst(split.charAt(0));

                HashSet<Character> set = new HashSet<>();
                set.addAll(first.get(split.charAt(0)));
                set.remove('0');
                first.get(c).addAll(set);
            }
        }
    }
    public static void getFollow(){
        Set<Map.Entry<Character, String>> entries = production.entrySet();
        for (Map.Entry<Character, String> entry : entries) {
            start_terminal=entry.getKey();
            break;
        }
        HashSet<Character> set = new HashSet<>();
        set.add('#');
        follow.put(start_terminal,set);
        for (Character character : non_terminal) {
            getOneFollow(character );
        }
    }
    public static void getOneFollow(Character c){
        Set<Map.Entry<Character, String>> entries = production.entrySet();
        if(!follow.containsKey(c)){
            HashSet<Character> set = new HashSet<>();
            follow.put(c,set);
        }
        for (Map.Entry<Character, String> entry : entries) {
            String value = entry.getValue();
            String[] splits = value.split("\\|");
            for (String split : splits) {
                if(split.charAt(split.length()-1)==c){
                    if(follow.get(entry.getKey()).isEmpty()){
                        getOneFollow(entry.getKey());
                    }
                    HashSet<Character> set = follow.get(c);
                    set.addAll(follow.get(entry.getKey()));
                }
                if(split.length()>=2){
                    if(split.charAt(split.length()-2)==c){
                        if(terminal.contains(split.charAt(split.length()-1))){
                            follow.get(c).add(split.charAt(split.length()-1));
                        }
                        if(non_terminal.contains(split.charAt(split.length()-1))){
                            HashSet<Character> set = follow.get(c);
                            HashSet<Character> set1 = first.get(split.charAt(split.length() - 1));
                            set.addAll(set1);
                            set.remove('0');
                            if(set1.contains('0')){
                                if(follow.get(entry.getKey()).isEmpty()){
                                    getOneFollow(entry.getKey());
                                }
                                set.addAll(follow.get(entry.getKey()));
                            }

                        }

                    }
                }
            }
        }
    }
    public static boolean isLL1(){
        return true;
    }
    public static void getPredictTable(){
        terminal.add('#');
        predictTable=new String[non_terminal.size()+1][terminal.size()+1];
        for(int i=0;i<predictTable.length;i++)
        {
        	for(int j=0;j<predictTable[0].length;j++)
        	{
        		predictTable[i][j]="null";
        	}
        }
        int k=1;
        HashMap< Character,Integer> getTerIndex = new HashMap<>();
        for (Character character : terminal) {
            getTerIndex.put(character,k);
            predictTable[0][k++]=character.toString();

        }
        k=1;
        for (Character character : non_terminal) {
            predictTable[k++][0]=character.toString();
        }
        for (int i = 1; i < predictTable.length; i++) {
            HashSet<Character> firstSet = first.get(predictTable[i][0].charAt(0));
            for (Character character : firstSet) {
                if(character=='0'){
                    continue;
                }
                String s = production.get(predictTable[i][0].charAt(0));
                String[] splits = s.split("\\|");
                for (String split : splits) {
                    char c=split.charAt(0);
                    if(terminal.contains(c)){
                        if(character==c){
                            predictTable[i][getTerIndex.get(character)]=split;
                        }
                    }
                    if(non_terminal.contains(c)){
                        if(first.get(c).contains(character)){
                            predictTable[i][getTerIndex.get(character)]=split;
                        }
                    }
                }
            }
            if(firstSet.contains('0')){
                HashSet<Character> followSet = follow.get(predictTable[i][0].charAt(0));
                System.out.println(predictTable[i][0].charAt(0));
                for (Character character : followSet) {
                    System.out.println(character);
                    Integer a = getTerIndex.get(character);
                    System.out.println(i+"   "+a);
                    predictTable[i][a]="0";
                }
            }
        }


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
    public static void ptintFirst(){
        System.out.println("first:");
        Set<Map.Entry<Character, HashSet<Character>>> entries = first.entrySet();
        for (Map.Entry<Character, HashSet<Character>> entry : entries) {
            System.out.println(entry.getKey());
            HashSet<Character> value = entry.getValue();
            for (Character character : value) {
                System.out.print(character+" ");
            }
            System.out.println("");
        }
    }
    public static void printFollow(){
        System.out.println("follow:");
        Set<Map.Entry<Character, HashSet<Character>>> entries = follow.entrySet();
        for (Map.Entry<Character, HashSet<Character>> entry : entries) {
            System.out.println(entry.getKey());
            HashSet<Character> value = entry.getValue();
            for (Character character : value) {
                System.out.print(character+" ");
            }
            System.out.println("");
        }
    }
    public static void printpredictTable(){
        for (int i = 0; i < predictTable.length; i++) {
            for (int j = 0; j < predictTable[0].length; j++) {
                System.out.print(predictTable[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void analyist()
    {
    	Stack<Character> stack = new Stack<>();
    	stack.push('#');
    	stack.push(start_terminal);
    	System.out.println("请输入句型");
    	String string = in.nextLine();
    	string = string.substring(1, string.length());
    	int i=0;
    	while(i<string.length()&&(stack.peek()!='#'))
    	{
    		//System.out.println(i);
    		if(terminal.contains(stack.peek()))
    		{
    				if(stack.peek()==string.charAt(i))
    				{
    					i++;
    					stack.pop();
    					printStack(stack);
    					System.out.print(string.substring(i,string.length()));
    					for(int k1 = 0;k1<9-(string.length()-i);k1++)
    						System.out.print("-");
    					System.out.println("所用产生式："+"匹配");
    			
    				}
    		}
    		else
    		{
    			if(stack.peek()=='#')
    			{
    			System.out.println("结束");
    			return ;
    			}
    			else
    			{
    				int a1 = 0;
    				int a2 = 0;    	
    				String string2=string.substring(i,i+1);
    				for(int w=0;w<predictTable[0].length;w++)
    				{
    					if(predictTable[0][w].equals(string2))
    					{
    						a2 = w;
    					}
    				}
    				for(int v=0;v<predictTable.length;v++)
    				{
    					if((!stack.isEmpty())&&predictTable[v][0].equals(stack.peek().toString()))
    					{
    						a1 = v;
    					}
    				}
    				//System.out.println(a1+"---"+a2);
    				if(predictTable[a1][a2].equals("0"))
    				{
    					//System.out.println("asdasd");
    					stack.pop();
    					printStack(stack);
    					System.out.print(string.substring(i,string.length()));
    					for(int k1 = 0;k1<9-(string.length()-i);k1++)
    						System.out.print("-");
    					System.out.println("所用产生式:"+predictTable[a1][0]+"->"+predictTable[a1][a2]);
    					
    				}
    				else if(!predictTable[a1][a2].equals("null"))
    				{
    	
    					stack.pop();
    				
    					for(int t=predictTable[a1][a2].length()-1;t>=0;t--)
    					{
    						stack.push(predictTable[a1][a2].charAt(t));
    					
    					}
//    					Stack e2 = new Stack();
    					printStack(stack);
    					System.out.print(string.substring(i,string.length()));
    					for(int k1 = 0;k1<9-(string.length()-i);k1++)
    						System.out.print("-");
    					System.out.println("所用产生式:"+predictTable[a1][0]+"->"+predictTable[a1][a2]);
    				}
    				
    		
    			}
    		
    		}
    	}
    	System.out.println("分析完成");
    	
    }
    public static void printStack(Stack<Character> stack ){
    	int i=0;
        if (!stack.isEmpty())
        { 
                System.out.print("堆栈中的元素：");
                Enumeration items = stack.elements(); // 得到 stack 中的枚举对象
                while (items.hasMoreElements()) //显示枚举（stack ） 中的所有元素
                {
                    System.out.print(items.nextElement()+" ");
                    i++;
                }
        }
    	for(int j=8;j>i;j--)
    	{
    		System.out.print("--");
    	}
       
    }
}
