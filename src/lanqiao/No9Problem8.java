package lanqiao;

import java.io.*;
import java.util.*;

/**
 * 第九届蓝桥杯省赛第八题----日志统计
 * @author SJM
 *	
 *	策略：
 *		使用泛型数组记录每一个帖子在每个时刻的赞数
 *		数据结构如下：
 *		[id1]     [ts1,ts2,ts3,ts4,ts5,ts6....,ts7]
 *		[id2]     [ts1,ts2,ts3,ts4...ts5]
 *
 *		得到该数据结构后，遍历每一个id，得到他每个时刻的点赞
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
	
	// 判断一个帖子是否火
	public boolean isHot(List<Integer>list) {
		int[] array = new int[100001];
		int r = 0,l = 0;		// 右区间，左区间
		int min = 100001;		// 最小值
		int max = 0;			// 最大值
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
			// 整个区间向右移动一个单位
			total -= array[l];
			l += 1;
			r += 1;
			total += array[r];
		}
		return false;
	}
}
