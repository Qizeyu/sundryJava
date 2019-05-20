import java.util.ArrayList;
import java.util.Collections;

public class Main4 {
		public static void main(String[] args) {
			
		}
		/**
		 * 桶排序O(N+C)，其中C=N*(logN-logM) 
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
			//创建桶
			int bucketNum = (max - min)/a.length-1;
			ArrayList<ArrayList<Integer>> bucketArr  = new ArrayList<>(bucketNum);
			for(int i = 0;i<bucketNum;i++)
			{
				bucketArr.add(new ArrayList<Integer>());
				
			}
			//将每个元素放入桶中
			for(int i=0;i<a.length;i++)
			{
				int num = (a[i]-min)/(a.length);
				bucketArr.get(num).add(a[i]);
				
			}
			//对每个桶进行排序
			for(int i =0;i<bucketArr.size();i++)
			{
				Collections.sort(bucketArr.get(i));
			}
		}
}
