
public class Main3 {
		public static void main(String[] args) {
			int a[] = {5,4,3,2,1};
			int b[] = {1,2,3,4,5};
			qSort(a,0,a.length-1);
			
			
		
			
		}
		public static void out(int a[])
		{
			for(int i = 0;i<a.length;i++)
			{
				System.out.print(a[i]+" ");
				
			}
			System.out.println();
		}
		/**
		 * 二分查找，前提有序
		 * @param array
		 * @param a
		 * @return
		 */
		public static int biSearch(int array[],int a) {
			         int io = 0;
			         int hi = array.length-1;
			         int mid;
			         while(io<=hi)
			         {
			        	 mid = (io+hi)/2;
			        	 if(array[mid]==a)
			        	 {
			        		 return mid+1;
			        	 }
			        	 else if(array[mid]<a)
			        	 {
			        		 io=mid+1;
			        		 
			        	 }else
			        	 {
			        		 hi=mid-1;
			        	 }
			         }
			         return -1;
		}
		/**
		 * 冒泡排序(O(n2))
		 * @param a
		 * @return
		 */
		public static int[] bubbleSort(int []a)
		{
			for(int i =0;i<a.length;i++)
			{
				for(int j =1;j<a.length-i;j++)
				{
					if(a[j-1]>a[j])
					{
						int temp = a[j-1];
						a[j-1] = a[j];
						a[j]  =temp;
					}
				}
			}
			return a;
			
		}
		/**
		 * 插入排序(o(n2))正序
		 * @param a
		 * @return
		 */
		public static int[] insertSort(int a[])
		{
			
			for(int i = 1;i<a.length;i++)
			{
				//插入的数
				int insertVal = a[i];
				//被插入的位置，准备与前一个数进行比较
				int index = i-1;
				while(index>=0&&insertVal<a[index])
				{
					a[index+1] = a[index];
					index--;
				}
				a[index+1] = insertVal;
			}
			return a;
		}
		/**
		 * 快速排序(nlogn)
		 * @param a
		 * @param low(下界)
		 * @param high(上界)
		 */
		public static void qSort(int a[],int low,int high)
		{
			int start =low;
			int end   = high;
			int key  =a[low];
//			System.out.println("key:"+key);
			//从后往前比较
			while(end>start&&a[end]>=key)
			
				//如果没有比关键值小的，就比较下一个，直到有比关键字小的交换位置，然后又从前往后比较
				end--;
				if(a[end]<=key)
				{
					int temp = a[end];
					a[end] = a[start];
					a[start] = temp;
//					System.out.println("a yes");
				
					
				}
			
			//从前往后比较
			while(end>start&&a[start]<=key)
			
				//如果没有比关键值大的，比较下一个直到有比关键值大的交换位置
				start++;
//				System.out.println(start);
			if(a[start]>=key)
			{
				int temp = a[start];
				a[start] = a[end];
				a[end] =	temp;
//				System.out.println("b yes");
				
				
			}
			
//			out(a);
//			System.out.println("---------------end---------------");	
		//第一次循环比较结束，关键值的位置已经确认了，左边的值小于关键值，右边的值大于关键值，
		//但两边子序列的顺序还是不一样的，因此需要进行递归
			if(start>low) 
				{
//				System.out.println("before");
				qSort(a,low,start-1);
				}	
			if(end<high) 
				{
//				System.out.println("after");
				qSort(a,end+1,high);
				}

		}
		/**
		 * 希尔排序O(n^(1.3—2)) 
		 * 希尔排序是按照不同步长对元素进行插入排序，当刚开始元素很无序的时候，步长最大，
		 * 所以插入排序的元素个数很少，速度很快；当元素基本有序了，步长很小，插入排序对于有序的序列效率很高。
		 * 所以，希尔排序的时间复杂度会比o(n^2)好一些。
		 * @param a
		 */
		public static void shellSort(int a[])
		{
			int dk = a.length/2;
			while(dk>=1)
			{
				ShellInsertSort(a,dk);
				dk=dk/2;
			}
		}
		public static void ShellInsertSort(int a[],int dk) {
			//类似插入排序，只是插入排序增量是1，这里增量是dk,把1换成dk就可以了
			for(int i = dk;i<a.length;i++)
			{
				if(a[i]<a[i-dk]) {
					int j ;
					int x = a[i];//待插入元素
					a[i]  = a[i-dk];
					for(j = i-dk;i>=0&&x<a[j];j=j-dk) {
						//通过循环，逐个后移一位找到要插入的位置
						a[j+dk] = a[j];
					}
					a[j+dk] = x;//插入
				}
			}
		}
		
}
