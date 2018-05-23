package lanqiao;

/**
 * ���ű��ھŽ������---������Ԫ��
 * @author SJM
 *
 */

/*
 * ���ԣ�
 * 		���ڵ�����Ԫ�����⣬һ��ʼ���ݵ�ʱ����ʹ��
 * 		TreeMap��¼ÿ�����ֳ��ֵĴ������ٸ����ӣ�����˵���ڣ�
 * 		A = [1,2,3,1,5]
		B = [3,2,1,1,5]
		C = [2,1,3,1,5]
		
		��¼�õ���TreeMapΪ(key-value��ʾ����-���ִ���):
				1 2 3 4 5
		mapA = [2,1,1,0,1]
				1 2 3 4 5
		mapC = [2,1,1,0,1]
				1 2 3 4 5
		mapB = [2,1,1,0,1]
		
		�ӵڶ�������ʼ���õ�һ���µ�TreeMap
				 1 2 3 4 5
		mapAA = [2,3,4,4,5]
		�ӵ����ڶ�������ʼ���õ�һ���µ�TreeMap
				 1 2 3 4 5
		mapCC = [5,3,2,1,1]
			    1 2 3 4 5
		mapB = [2,1,1,0,1]
		�������TreeMap���ҵ�A�б�B[i]С�������������ҵ�C�б�B[i]�������
		���������У�����һһ���У�
			1.��һ���Ƚϵ�����1���ҵ�mapAA�����һ��С��1������û�ҵ�������
			2.�ڶ����Ƚϵ�����2���ҵ�mapAA�����һ��С��2��������1������Ϊ2
			  �ҵ�mapCC�е�һ������2��������3������Ϊ2������Ԫ�������+��2*2����
			 ��4����Ԫ��������������
			 	1 2 3
			    1 2 3
		        1 2 5
		        1 2 5
		   	3.�������Ƚϵ�����3,�ҵ�mapAA�����һ��С��3��������2������Ϊ3��
		   	 �ҵ�mapCC�е�һ������3��������4��������1����Ԫ������+��3*1
		   	 ��������Ԫ�����£�
		   	 	1 3 5
		   	 	1 3 5
		   	 	2 3 5
		   	4.���ĸ��Ƚϵ�����5���ҵ�mapAA�����һ��С��5��������4��������4��
		   	 �ҵ�mapCC�е�һ������5�������Ҳ������������αȽ�
		  �ܵ���˵������Ԫ������Ϊ7��
		  
		�㷨���Ӷȣ�һ��ʼ��¼ÿ�����ĳ��ִ���==3N,���������countA,countB,countC==3N,
		�����һ���ܵı���==N���ܵĸ��Ӷ���O(7N)������˵����O(N)
		
		���ڶ���100%�����ݣ�1 <= N <= 100000 0 <= Ai, Bi, Ci <= 100000 ��
		���Զ���O(N)��˵�����ʱ�临�Ӷ�Ӧ���ǲ��ᳬ��.
		         
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
	// max_X:��ʾX�����������Ǹ���
	private int maxA;
	private int maxB;
	private int maxC;
	
	// ��Ԫ������
	private int count = 0;
	
	public static void main(String[] args) {
		new No9Poblem6().slove();
	}
	public void slove() {
		Scanner in = new Scanner(System.in);
		//=================================
		// ����
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
		// ����countAA��countCC
		//====================================
		for(int i=1;i<=maxA;i++) {
			countAA[i] += countAA[i-1];
		}
		for(int i=maxC-1;i>=0;i--) {
			countCC[i] += countCC[i+1];
		}
		
		//=====================================
		// ѭ��N�Σ������Ԫ������
		//====================================
		for(int i=0;i<N;i++) {
			if(B[i]!=0) {
				// ���A�б�B[i]С�ĸ���������
				int a = countAA[B[i]-1];
				// ���C�б�B[i]��ĸ���������
				int b = countCC[B[i]+1];
				
				// ��Ԫ����������
				count += a*b;
			}
		}
		
		
		System.out.println(count);
	}
}
