package lanqiao;

import java.io.*;
import java.util.*;

/**
 * �ھŽ����ű�ʡ���ڰ���----��־ͳ��
 * @author SJM
 *	
 *	���ԣ�
 *		ʹ�÷��������¼ÿһ��������ÿ��ʱ�̵�����
 *		���ݽṹ���£�
 *		[id1]     [ts1,ts2,ts3,ts4,ts5,ts6....,ts7]
 *		[id2]     [ts1,ts2,ts3,ts4...ts5]
 *
 *		�õ������ݽṹ�󣬱���ÿһ��id���õ���ÿ��ʱ�̵ĵ���
 *		
 */

public class No9Problem8 {
	
	private int N;
	private int D;
	private int K;
	
	private Map<Integer, List<Integer>>map = new HashMap<>();
	
	public static void main(String[] args) {
		new No9Problem8().slove();
	}
	
	public void slove() {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		D = in.nextInt();
		K = in.nextInt();
		for(int i=0;i<N;i++) {
			int ts = in.nextInt();
			int id = in.nextInt();
			if(map.containsKey(id)) {
				map.get(id).add(ts);
			}else {
				map.put(id, new ArrayList<>());
				map.get(id).add(ts);
			}
		}
		
		List<Integer>result = new ArrayList<>();
		
		for(int key:map.keySet()) {
			if(isHot(map.get(key))) {
				result.add(key);
			}
		}
		
		Collections.sort(result);
		
		for(int i:result) {
			System.out.println(i);
		}
	}
	
	// �ж�һ�������Ƿ��
	public boolean isHot(List<Integer>list) {
		int[] array = new int[100001];
		int r = 0,l = 0;		// �����䣬������
		int min = 100001;		// ��Сֵ
		int max = 0;			// ���ֵ
		for(int i=0;i<list.size();i++) {
			int data = list.get(i);
			array[data] += 1;
			if(data>max) {
				max = data;
			}else if(data<min) {
				min = data;
				l = data;
			}
		}
		
		int total = 0;
		
		r = l+D > max? max+1:l+D;
		
		for(int i=l;i<r;i++) {
			total += array[i];
		}
		
		while(true) {
			if(total>=K) {
				return true;
			}
			if(r>max) {
				break;
			}
			// �������������ƶ�һ����λ
			total -= array[l];
			l += 1;
			r += 1;
			total += array[r];
		}
		return false;
	}
}
