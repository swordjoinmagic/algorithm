package PermutationAndCombines;

import java.io.*;
import java.util.*;

// �����ֵ����������һ�����еķ���~ 

// �����ֵ����������з���ʹ�����������Ĳ��ԣ�����һ�����У�
//		1.��������������һ�������β�±�   i=max{j|pj-1<pj,pj>=pj+1}
//		2.�ҵ�pi-1�����pi-1������һ�������±꣺j=max{k|k>i,pi-1<pk,pi-1>=pk+1}
//		3.����pi-1��pj
//		4.����pj���������ʹ�����
//
//	�������������У�
//		2763541
//	��һ������:
//		2764135
//	�������£�
// 		��һ�����ҵ����һ�������β�±�
//		276 35 41		����5�������һ�������β�±���,Ҳ����i=4��pi=5
// 		�ڶ������ҵ�pi-1�����pi-1������һ�������±꣬������Դ�����ǰ�ң�ֱ����һ����pi-1�����������
//		276 35 4 1		����4�������һ����3�����
//		�����������ཻ��
//		276 45 3 1
//		���Ĳ�������pi-1����������
//		2764135


public class PermutationWithDictonary {
	public static void main(String[] args) {
		int[] array = new int[] {1,1,2,2};
		while(nextPermutation(array, array.length)) {
			for(int i:array) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}
	
	public static boolean nextPermutation(int[] array,int len) {
		// ��һ�����ҵ����һ�������β�±�
		int lastI = len-1;		// ���һ�������β�±�
//		for(int i=len-1;i>=1;i--) {
//			if(array[i-1]<=array[i]) {
//				lastI = i;
//				break;
//			}
//		}
		while(lastI>0 && array[lastI-1]>=array[lastI]) {
			lastI--;
		}
		
		if(lastI==0) {
			return false;
		}
		
		int lastISub1 = lastI-1;
		
		// �ڶ������ҵ���pi-1������һ�������±�
		int lastMaxI = lastI;
//		for(int i=len-1;i>=0;i--) {
//			if(array[i]>=array[lastISub1] && i>lastISub1) {
//				lastMaxI = i;
//				break;
//			}
//		}
		
		for(int j=lastI+1;j<len;j++) {
//			if(array[j]<=array[lastI-1]) {
//				continue;
//			}
//			if(array[j]<array[lastMaxI]) {
//				lastMaxI = j;
//			}
			if(array[j]>array[lastI-1] && array[j]<array[lastMaxI]) {
				lastMaxI = j;
			}
		}
		
//		System.out.println("β�±���:"+lastI+" β�±��Ӧ������:"+array[lastI]+" i-1��:"+array[lastISub1]+" ����������һ������:"+array[lastMaxI]);
		
		// ��������������������
		int t = array[lastISub1];
		array[lastISub1] = array[lastMaxI];
		array[lastMaxI] = t;
		
		// ���Ĳ�����i-1�����ȫ��������һ������
		Arrays.sort(array, lastI, len);
		
		return true;
	}
}
