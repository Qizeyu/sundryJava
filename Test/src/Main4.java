import java.util.ArrayList;
import java.util.Collections;

public class Main4 {
		public static void main(String[] args) {
			
		}
		/**
		 * Ͱ����O(N+C)������C=N*(logN-logM) 
		 * @param a
		 */
		public static void bucketSort(int a[])
		{
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for(int i =0;i<a.length;i++)
			{
				max = Math.max(max, a[i]);
				min = Math.min(min,a[i]);
				
			}
			//����Ͱ
			int bucketNum = (max - min)/a.length-1;
			ArrayList<ArrayList<Integer>> bucketArr  = new ArrayList<>(bucketNum);
			for(int i = 0;i<bucketNum;i++)
			{
				bucketArr.add(new ArrayList<Integer>());
				
			}
			//��ÿ��Ԫ�ط���Ͱ��
			for(int i=0;i<a.length;i++)
			{
				int num = (a[i]-min)/(a.length);
				bucketArr.get(num).add(a[i]);
				
			}
			//��ÿ��Ͱ��������
			for(int i =0;i<bucketArr.size();i++)
			{
				Collections.sort(bucketArr.get(i));
			}
		}
}
