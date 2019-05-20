
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
		 * ���ֲ��ң�ǰ������
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
		 * ð������(O(n2))
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
		 * ��������(o(n2))����
		 * @param a
		 * @return
		 */
		public static int[] insertSort(int a[])
		{
			
			for(int i = 1;i<a.length;i++)
			{
				//�������
				int insertVal = a[i];
				//�������λ�ã�׼����ǰһ�������бȽ�
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
		 * ��������(nlogn)
		 * @param a
		 * @param low(�½�)
		 * @param high(�Ͻ�)
		 */
		public static void qSort(int a[],int low,int high)
		{
			int start =low;
			int end   = high;
			int key  =a[low];
//			System.out.println("key:"+key);
			//�Ӻ���ǰ�Ƚ�
			while(end>start&&a[end]>=key)
			
				//���û�бȹؼ�ֵС�ģ��ͱȽ���һ����ֱ���бȹؼ���С�Ľ���λ�ã�Ȼ���ִ�ǰ����Ƚ�
				end--;
				if(a[end]<=key)
				{
					int temp = a[end];
					a[end] = a[start];
					a[start] = temp;
//					System.out.println("a yes");
				
					
				}
			
			//��ǰ����Ƚ�
			while(end>start&&a[start]<=key)
			
				//���û�бȹؼ�ֵ��ģ��Ƚ���һ��ֱ���бȹؼ�ֵ��Ľ���λ��
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
		//��һ��ѭ���ȽϽ������ؼ�ֵ��λ���Ѿ�ȷ���ˣ���ߵ�ֵС�ڹؼ�ֵ���ұߵ�ֵ���ڹؼ�ֵ��
		//�����������е�˳���ǲ�һ���ģ������Ҫ���еݹ�
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
		 * ϣ������O(n^(1.3��2)) 
		 * ϣ�������ǰ��ղ�ͬ������Ԫ�ؽ��в������򣬵��տ�ʼԪ�غ������ʱ�򣬲������
		 * ���Բ��������Ԫ�ظ������٣��ٶȺܿ죻��Ԫ�ػ��������ˣ�������С����������������������Ч�ʺܸߡ�
		 * ���ԣ�ϣ�������ʱ�临�ӶȻ��o(n^2)��һЩ��
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
			//���Ʋ�������ֻ�ǲ�������������1������������dk,��1����dk�Ϳ�����
			for(int i = dk;i<a.length;i++)
			{
				if(a[i]<a[i-dk]) {
					int j ;
					int x = a[i];//������Ԫ��
					a[i]  = a[i-dk];
					for(j = i-dk;i>=0&&x<a[j];j=j-dk) {
						//ͨ��ѭ�����������һλ�ҵ�Ҫ�����λ��
						a[j+dk] = a[j];
					}
					a[j+dk] = x;//����
				}
			}
		}
		
}
