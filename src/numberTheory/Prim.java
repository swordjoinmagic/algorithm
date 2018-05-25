package numberTheory;

import java.util.Arrays;

/**
 * 
 * @author SJM
 *
 *	ʹ��ɸ��Ѱ����������ʹ������ͨ��ɸ����ͨ��ɸ�ӽ����з������������ı�������ʽɸ��ȥ
 */

public class Prim {
	static int[] su = new int[10000];		// ������
	static boolean[] u = new boolean[10000];			// ɸ��
	static int num = 0;
	public static void main(String[] args) {
		Arrays.fill(u,true);
		euler(100);
		for(int i=0;i<num;i++) {
			System.out.println(su[i]);
		}
	}
	
	// ���0-n������������˼·�ǽ����������ı��������ɷ�����ɸȥ��ʱ�临�Ӷ�O(logN)
	// �������ɸ��--
	public static void getsu(int n) {
		for(int i=2;i<=n;i++) {
			// ���i������
			if(u[i]) {
				su[num++] = i;
				// �������������б���ȫ��ȥ��
				for(int j=2;i*j<=n;j++) {
					u[j*i] = false;
				}
			}
		}
	}
	
	// ŷ��ɸ��������˼·�ǵ�һ��������������С������ɸȥ��ʱ�������ʱ��ģ�
	// ��Ϊ��ͨ������ɸ�и�ȱ�㣬����˵12��������������2,3,�����Ǹ��㷨���м����ʱ��
	// 2�Ѿ���12����2�ı�����6����ɸȥ�ˣ���12�ں����ֻᱻ����3�ı�����4������ɸȥ���ͻ������࣬
	// ŷ��ɸ������Ϊ�˽�������������ڵġ�
	public static void euler(int n) {
		for(int i=2;i<=n;i++) {
			if(u[i]) {
				su[num++] = i;
			}
			// ѭ��������
			for(int j=0;j<num;j++) {
				// ���������Χ����ô������һ����
				if(su[j]*i>n)	break;	
				// ɸȥ�����ı���
				u[su[j]*i] = false;	
				// �����ǰ�������ǵ�ǰ������С�����ӣ���ô������һ����
				if(i%su[j]==0) break;	
			}
		}
	}
}
