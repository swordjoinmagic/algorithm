package graphic;

import java.util.Scanner;

/*
 * 线段树
 * 
 */

class edge{
	int left,right;
	int sum;
	@Override
	public String toString() {
		return "edge [left=" + left + ", right=" + right + ", sum=" + sum + "]";
	}
	public edge(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
}

public class LineTree {
	private edge[] tree = new edge[140000];
	private int[] people = new int[50010];
	private int T;
	private int sum;		// 飞船总数
	private int N;		// 表示一共有N个堡垒
	
	
	// left:左端点，right:右端点，num:数组下标
	private void makeTree(int left,int right,int num) {
		tree[num] = new edge(left, right);
		
		// 到达叶子节点
		if(right==left) {
			tree[num].sum = people[right];
			return;
		}
		
		// 递归构造左子树
		makeTree(left,(left+right)/2, num+num);
		// 递归构造右子树
		makeTree((left+right)/2+1, right, num+num+1);
		
		// 父节点的飞船数等于子节点的飞船数之和
		tree[num].sum = tree[num+num].sum + tree[num+num+1].sum;
	}
	
	// 给第j个堡垒的增加additonal个飞船，num是数组下标
	private void add(int j,int additional,int num) {
		// 如果j这个点在线段里面，整条线段的总值+上additional
		edge e = tree[num];
		tree[num].sum += additional;
		
		if(j==e.left && j==e.right) {
			// 说明已经到达叶子节点
			return;
		}
		
		int middle = (e.left+e.right)/2;
		if(j>middle) {
			// 说明j这个点在线段右边，继续构造右子树
			add(j, additional, num+num+1);
		}else{
			// 说明j这个点在线段左边，继续构造左子树
			add(j,additional,num+num);
		}
	}
	
	// 查询从第i个堡垒到第j个堡垒的飞船总数，num是数组下标
	public void query(int i,int j,int num) {
		// 如果找到要求的线段区间，直接增加 
		if(i<=tree[num].left && j>=tree[num].right) {
			this.sum += tree[num].sum;
			return;
		}
		// 否则的话，判断要查询的线段在当前线段的右边还是当前线段的左边
		int mid = (tree[num].left+tree[num].right)/2;
		
		// 如果要查询的线段在当前线段的左边，那么往左边查询
		if(j<=mid) {
			query(i, j, num+num);
		}else if(i>mid) {
			query(i, j, num+num+1);
		}else {
			// 要查询的线段在该选段中间，分段查询，左右节点都查
			query(i, j, num+num);
			query(i, j, num+num+1);
		}
	}
	
	public void slove() {
		Scanner in = new Scanner(System.in);
		T = in.nextInt();
		for(int k=1;k<=T;k++) {
			
//			System.out.println("Case "+k+":");
			
			N = in.nextInt();
			for(int i=1;i<=N;i++) {
				people[i] = in.nextInt();
			}
			
			// 生成线段树
			makeTree(1, N, 1);
			
			// 开始接收命令
			String command = "";
			while(true) {
				command = in.next();
				if(command.equals("End")) {
					break;
				}
				
				sum = 0;
				
				int i = in.nextInt();
				int j = in.nextInt();
				
				if(command.equals("Add")) {
					add(i, j, 1);
				}else if(command.equals("Sub")) {
					add(i,-j,1);
				}else if(command.equals("Query")) {
					query(i, j, 1);
					System.out.println(sum);
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		new LineTree().slove();
	}
}
