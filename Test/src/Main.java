import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		//System.out.println(number(20));
		System.out.println(S(4));
		//System.out.println(Math.pow(2, 0.5));
		
		System.out.println(S2(3));
		
		
		
	}
	public static int S(int n )
	{
		int s = 2*n*n;
		for(int i = 1;i*i<Math.PI*n*n;i++)
		{
			if(i*i==s)
				return s;
			if(i*i>s)
				return (i-1)*(i-1);
		}
		return 0;
	}
	public static int S2(int n)
	{
		int i =1;
		while(true)
		{
			if(i*Math.pow(2, 0.5)>n)
				break;
			else
				i++;
		}
		return 4*(i-1)*(i-1);
	}
	public static int number(int n)
	{
		if(n==1)
			return 2;
		if(n==2)
			return 3;
		if(n==3)
			return 4;
		return number(n-2)+number(n-3);
	}
	public static int f1(int n)
	{
		if(n==1||n==2)
			return 1;
		return f1(n-1)+f1(n-2);
	}
	public static int f2(int n)
	{
		if(n==1)
			return 1;
		if(n==2)
			return 2;
		return f2(n-1)+f2(n-2);
	}
	
}
