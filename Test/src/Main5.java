import java.util.Scanner;

public class Main5 {
			public static void main(String[] args) {
				Scanner in = new Scanner(System.in);
				int a = in.nextInt();
				int b = in.nextInt();
				int c[][]  = new int[a][b];
				for(int i=0;i<a;i++)
				{
					for(int j=0;j<b;j++)
					{
						int val = in.nextInt();
						c[i][j] = val;
					}
				}
//				for(int i=0;i<a;i++)
//				{
//					for(int j=0;j<b;j++)
//					{
//						System.out.print(c[i][j]+" ");
//					}
//					System.out.println();
//				}
				int count=0;
				for(int i=0;i<a;i++)
				{
					for(int j=0;j<b;j++)
					{
						if(i-1>=0&&c[i-1][j]==c[i][j])
						{
							count++;
							c[i-1][j] =0;
						}
						if(i+1<a&&c[i+1][j]==c[i][j])
						{
							count++;
							c[i+1][j] = 0;
						}
						if(j-1>=0&&c[i][j-1]==c[i][j])
						{
							count++;
							c[i][j-1] = 0;
						}
						if(j+1<b&&c[i][j+1]==c[i][j])
						{
							count++;
							c[i][j+1] = 0;
						}
					}
				}
				System.out.println(count);
			}	
}
