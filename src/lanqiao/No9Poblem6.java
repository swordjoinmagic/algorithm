package lanqiao;

/**
 * 蓝桥杯第九届第六题---递增三元组
 * @author SJM
 *
 */

/*
 * 策略：
 * 		对于递增三元组问题，一开始数据的时候，先使用
 * 		TreeMap记录每个数字出现的次数，举个例子，比如说对于：
 * 		A = [1,2,3,1,5]
		B = [3,2,1,1,5]
		C = [2,1,3,1,5]
		
		记录得到的TreeMap为(key-value表示数字-出现次数):
				1 2 3 4 5
		mapA = [2,1,1,0,1]
				1 2 3 4 5
		mapC = [2,1,1,0,1]
				1 2 3 4 5
		mapB = [2,1,1,0,1]
		
		从第二个数开始，得到一个新的TreeMap
				 1 2 3 4 5
		mapAA = [2,3,4,4,5]
		从倒数第二个数开始，得到一个新的TreeMap
				 1 2 3 4 5
		mapCC = [5,3,2,1,1]
			    1 2 3 4 5
		mapB = [2,1,1,0,1]
		根据这个TreeMap，找到A中比B[i]小的数的总数，找到C中比B[i]大的总数
		上述例子中，步骤一一进行：
			1.第一个比较的数是1，找到mapAA中最后一个小于1的数，没找到，结束
			2.第二个比较的数是2，找到mapAA中最后一个小于2的数，是1，总数为2
			  找到mapCC中第一个大于2的数，是3，总数为2，则三元组的数量+上2*2个，
			 这4个三元组是下面这样：
			 	1 2 3
			    1 2 3
		        1 2 5
		        1 2 5
		   	3.第三个比较的数是3,找到mapAA中最后一个小于3的数，是2，总数为3，
		   	 找到mapCC中第一个大于3的数，是4，总数是1，三元组数量+上3*1
		   	 这三个三元组如下：
		   	 	1 3 5
		   	 	1 3 5
		   	 	2 3 5
		   	4.第四个比较的数是5，找到mapAA中最后一个小于5的数，是4，总数是4，
		   	 找到mapCC中第一个大于5的数，找不到，跳过本次比较
		  总的来说，该三元组数量为7个
		  
		算法复杂度，一开始记录每个数的出现次数==3N,接下来获得countA,countB,countC==3N,
		最后来一次总的遍历==N，总的复杂度是O(7N)，简化来说就是O(N)
		
		由于对于100%的数据，1 <= N <= 100000 0 <= Ai, Bi, Ci <= 100000 ，
		所以对于O(N)来说，这个时间复杂度应该是不会超的.
		         
 */

import java.io.*;
import java.util.*;

public class No9Poblem6 {
	private int N;
	private int[] A;
	private int[] B;
	private int[] C;
	private int[] countA = new int[100001];
	private int[] countB = new int[100001];
	private int[] countC = new int[100001];
	private int[] countAA = new int[100001];
	private int[] countCC = new int[100001];
	// max_X:表示X数组中最大的那个数
	private int maxA;
	private int maxB;
	private int maxC;
	
	// 三元组数量
	private int count = 0;
	
	public static void main(String[] args) {
		new No9Poblem6().slove();
	}
	public void slove() {
		Scanner in = new Scanner(System.in);
		//=================================
		// 输入
		//===================================
		N = in.nextInt();
		A = new int[N+1];
		B = new int[N+1];
		C = new int[N+1];
		for(int i=0;i<N;i++) {
			A[i] = in.nextInt();
			maxA = Math.max(maxA, A[i]);
			countA[A[i]] += 1;
			countAA[A[i]] += 1;
		}
		for(int i=0;i<N;i++) {
			B[i] = in.nextInt();
			countB[B[i]] += 1;
			maxB = Math.max(maxB, B[i]);
		}
		for(int i=0;i<N;i++) {
			C[i] = in.nextInt();
			countC[C[i]] += 1;
			countCC[C[i]] += 1;
			maxC = Math.max(maxC, C[i]);
		}
		
		//====================================
		// 生成countAA和countCC
		//====================================
		for(int i=1;i<=maxA;i++) {
			countAA[i] += countAA[i-1];
		}
		for(int i=maxC-1;i>=0;i--) {
			countCC[i] += countCC[i+1];
		}
		
		//=====================================
		// 循环N次，获得三元组数量
		//====================================
		for(int i=0;i<N;i++) {
			if(B[i]!=0) {
				// 获得A中比B[i]小的个数的总数
				int a = countAA[B[i]-1];
				// 获得C中比B[i]大的个数的总数
				int b = countCC[B[i]+1];
				
				// 三元组数量增加
				count += a*b;
			}
		}
		
		
		System.out.println(count);
	}
}
