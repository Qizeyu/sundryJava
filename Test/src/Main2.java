import java.util.Scanner;
public class Main2 {
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Scanner sc=new Scanner(System.in);		
		while(sc.hasNext())
		{
		long n=sc.nextLong();
		if(n==-1)break;
		if(n==0) {System.out.println(0);}
		else {
		n-=1;//
		long a[][]= {{1,1},{1,0}};
		long b[][]={{1,0},{0,1}};
	    int time=0;
		while(n>0)
		{
		    if(n%2==1)
			{
		    	b=q(a, b);
			}	
		    a=q(a, a);n/=2;
		}
		
		System.out.println((b[0][0]));
		}
		}
	}
	static long [][] q(long a[][],long b[][]){//
		  long x=a.length;//a[0].length=b.length Ϊ��������
		  long y=b[0].length;//ȷ��ÿһ���м���
		  long c[][]=new long [2][2];
		  for(int i=0;i<x;i++)
			  for(int j=0;j<y;j++)
			  {
				  //��Ҫȷ��ÿһ��Ԫ��
				  //c[i][j];
				  for(int t=0;t<b.length;t++)
				  {
					  c[i][j]+=(a[i][t]%1000000007)*(b[t][j]%1000000007);
					  c[i][j]%=1000000007;
				  }
			  }
		  
		   return c;  
	  }

}
