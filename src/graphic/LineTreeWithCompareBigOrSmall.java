package graphic;

import java.io.*;
import java.util.*;

public class LineTreeWithCompareBigOrSmall {
	
	class Edge{
		int left,right;
		int max;		// 表示这条线段里面最大的数是多少
		public Edge(int left, int right) {
			this.left = left;
			this.right = right;
		}
		
	}
	
	// 使用数组建树，当前下标*2是左子树，当前下标*2+1是右子树
	private Edge[] tree = new Edge[2000000];
	private int[] array = new int[200001];
	
	private int max;		// 最大值
	
	private int N;			// 一共有多少个堡垒
	private int M;			// 一共要操作的数量
	
	// 建立线段树的方法
	// 建立一个左端点为left，右端点为right的线段树，num为数组下标，因为使用数组建立线段树
	public void makeTree(int left,int right,int num) {
		tree[num] = new Edge(left, right);
		
		// 如果到达了叶子节点，那么这条线段的最大值就是他自己
		if(left==right) {
			tree[num].max = array[left];
			return;
		}
		
		// 递归构造左子树和右子树
		int mid = (left+right)/2;
		makeTree(left, mid, num+num);		// 左子树
		makeTree(mid+1, right, num+num+1);	// 右子树
		
		// 当前线段的最大值，等于他两个子线段里面最大的那个
		tree[num].max = Math.max(tree[num+num].max, tree[num+num+1].max);
	}
	
	// 更新线段树的方法
	// 方法为更新第i个堡垒的数目为j，num为数组下标
	// 更新的策略是:
	//		先一只递归到最后一个位置，再比较大小。差不多相当于重新建立一次线段树
	public void update(int i,int j,int num) {
		Edge edge = tree[num];
		
		// 到达叶子节点
		if(i==edge.left && i==edge.right) {
			edge.max = j;
			return;
		}
		
		if(edge.left==edge.right) {
			return;
		}
		
		// 递归左子树和右子树
		update(i, j, num+num);
		update(i, j, num+num+1);
		
		// 当前节点的最大值是他左右节点的中最大的那个
		edge.max = Math.max(tree[num+num].max, tree[num+num+1].max);
	}
	
	// 查询从第i个堡垒到第j个堡垒之间的最大值,num是数组下标
	public void query(int i,int j,int num) {
		
		Edge edge = tree[num];		 // 当前线段
		
		// 如果要查询的线段包含在当前线段里面,那么对最大值进行一次更新
		if(i<=edge.left && j>=edge.right) {
			this.max = Math.max(this.max, edge.max);
			return;
		}
		
		// 递归查询左子树和右子树
		// 如果当前线段在左子树，对左子树进行查询
		int mid = (edge.left+edge.right)/2;
		
		// 如果当前线段在要查询线段
		
		// 如果要查询的线段在当前线段的左边
		if(j<=mid) {
			query(i, j, num+num);
		}else if(i>mid) {
			query(i, j, num+num+1);
		}else{
			query(i, j, num+num);
			query(i, j, num+num+1);
		}
		
	}
	
	public void slove() {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		
		for(int i=1;i<=N;i++) {
			array[i] = in.nextInt();
		}
		
		makeTree(1, N, 1);
		
		for(int k=0;k<M;k++) {
			
			max = 0;
			
			String command = in.next();
			int i = in.nextInt();
			int j = in.nextInt();
			
			if(command.equals("Q")) {
				query(i, j, 1);
				System.out.println(max);
			}else if(command.equals("U")) {
				update(i, j, 1);
			}
		}
	}
	
	public static void main(String[] args) {
		new LineTreeWithCompareBigOrSmall().slove();
	}
	
}
