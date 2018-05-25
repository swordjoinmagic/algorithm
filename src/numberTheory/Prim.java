package numberTheory;

import java.util.Arrays;

/**
 * 
 * @author SJM
 *
 *	使用筛法寻找素数，先使用最普通的筛法，通过筛子将所有非素数以素数的倍数的形式筛出去
 */

public class Prim {
	static int[] su = new int[10000];		// 素数表
	static boolean[] u = new boolean[10000];			// 筛子
	static int num = 0;
	public static void main(String[] args) {
		Arrays.fill(u,true);
		euler(100);
		for(int i=0;i<num;i++) {
			System.out.println(su[i]);
		}
	}
	
	// 获得0-n的所有素数，思路是将所有素数的倍数都当成非素数筛去，时间复杂度O(logN)
	// 最基本的筛法--
	public static void getsu(int n) {
		for(int i=2;i<=n;i++) {
			// 如果i是素数
			if(u[i]) {
				su[num++] = i;
				// 将该素数的所有倍数全部去除
				for(int j=2;i*j<=n;j++) {
					u[j*i] = false;
				}
			}
		}
	}
	
	// 欧拉筛法，基本思路是当一个合数被它的最小素因子筛去的时候，是最不耗时间的，
	// 因为普通的素数筛有个缺点，比如说12，他有两个因子2,3,上面那个算法进行计算的时候
	// 2已经把12当做2的倍数（6倍）筛去了，而12在后面又会被当做3的倍数（4倍）被筛去，就会有冗余，
	// 欧拉筛法就是为了解决这个问题而存在的。
	public static void euler(int n) {
		for(int i=2;i<=n;i++) {
			if(u[i]) {
				su[num++] = i;
			}
			// 循环素数表
			for(int j=0;j<num;j++) {
				// 如果超出范围，那么分析下一个数
				if(su[j]*i>n)	break;	
				// 筛去素数的倍数
				u[su[j]*i] = false;	
				// 如果当前素数数是当前数的最小素因子，那么分下下一个数
				if(i%su[j]==0) break;	
			}
		}
	}
}
