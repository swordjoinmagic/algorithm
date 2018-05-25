package PermutationAndCombines;

import java.io.*;
import java.util.*;

// 基于字典序的生成下一个排列的方法~ 

// 对于字典序生成排列法，使用下面这样的策略，对于一个排列：
//		1.由右至左计算最后一个正序的尾下标   i=max{j|pj-1<pj,pj>=pj+1}
//		2.找到pi-1后面比pi-1大的最后一个数的下标：j=max{k|k>i,pi-1<pk,pi-1>=pk+1}
//		3.互换pi-1和pj
//		4.反排pj后面的数，使其递增
//
//	假设有如下排列：
//		2763541
//	下一排列是:
//		2764135
//	步骤如下：
// 		第一步，找到最后一个正序的尾下标
//		276 35 41		其中5就是最后一个正序的尾下标了,也就是i=4，pi=5
// 		第二步：找到pi-1后面比pi-1大的最后一个数的下标，这里可以从右往前找，直到第一个比pi-1大的数就是了
//		276 35 4 1		其中4就是最后一个比3大的数
//		第三步，互相交换
//		276 45 3 1
//		第四步，排序pi-1后慢慢的数
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
		// 第一步，找到最后一个正序的尾下标
		int lastI = len-1;		// 最后一个正序的尾下标
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
		
		// 第二步，找到比pi-1大的最后一个数的下标
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
		
//		System.out.println("尾下标是:"+lastI+" 尾下标对应的数是:"+array[lastI]+" i-1是:"+array[lastISub1]+" 比他大的最后一个数是:"+array[lastMaxI]);
		
		// 第三步，交换这两个数
		int t = array[lastISub1];
		array[lastISub1] = array[lastMaxI];
		array[lastMaxI] = t;
		
		// 第四步，对i-1后面的全部数进行一次排序
		Arrays.sort(array, lastI, len);
		
		return true;
	}
}
