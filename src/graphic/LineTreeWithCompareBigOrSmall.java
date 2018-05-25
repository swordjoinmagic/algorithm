package graphic;

import java.io.*;
import java.util.*;

public class LineTreeWithCompareBigOrSmall {
	
	class Edge{
		int left,right;
		int max;		// ��ʾ�����߶������������Ƕ���
		public Edge(int left, int right) {
			this.left = left;
			this.right = right;
		}
		
	}
	
	// ʹ�����齨������ǰ�±�*2������������ǰ�±�*2+1��������
	private Edge[] tree = new Edge[2000000];
	private int[] array = new int[200001];
	
	private int max;		// ���ֵ
	
	private int N;			// һ���ж��ٸ�����
	private int M;			// һ��Ҫ����������
	
	// �����߶����ķ���
	// ����һ����˵�Ϊleft���Ҷ˵�Ϊright���߶�����numΪ�����±꣬��Ϊʹ�����齨���߶���
	public void makeTree(int left,int right,int num) {
		tree[num] = new Edge(left, right);
		
		// ���������Ҷ�ӽڵ㣬��ô�����߶ε����ֵ�������Լ�
		if(left==right) {
			tree[num].max = array[left];
			return;
		}
		
		// �ݹ鹹����������������
		int mid = (left+right)/2;
		makeTree(left, mid, num+num);		// ������
		makeTree(mid+1, right, num+num+1);	// ������
		
		// ��ǰ�߶ε����ֵ���������������߶����������Ǹ�
		tree[num].max = Math.max(tree[num+num].max, tree[num+num+1].max);
	}
	
	// �����߶����ķ���
	// ����Ϊ���µ�i�����ݵ���ĿΪj��numΪ�����±�
	// ���µĲ�����:
	//		��һֻ�ݹ鵽���һ��λ�ã��ٱȽϴ�С������൱�����½���һ���߶���
	public void update(int i,int j,int num) {
		Edge edge = tree[num];
		
		// ����Ҷ�ӽڵ�
		if(i==edge.left && i==edge.right) {
			edge.max = j;
			return;
		}
		
		if(edge.left==edge.right) {
			return;
		}
		
		// �ݹ���������������
		update(i, j, num+num);
		update(i, j, num+num+1);
		
		// ��ǰ�ڵ�����ֵ�������ҽڵ���������Ǹ�
		edge.max = Math.max(tree[num+num].max, tree[num+num+1].max);
	}
	
	// ��ѯ�ӵ�i�����ݵ���j������֮������ֵ,num�������±�
	public void query(int i,int j,int num) {
		
		Edge edge = tree[num];		 // ��ǰ�߶�
		
		// ���Ҫ��ѯ���߶ΰ����ڵ�ǰ�߶�����,��ô�����ֵ����һ�θ���
		if(i<=edge.left && j>=edge.right) {
			this.max = Math.max(this.max, edge.max);
			return;
		}
		
		// �ݹ��ѯ��������������
		// �����ǰ�߶����������������������в�ѯ
		int mid = (edge.left+edge.right)/2;
		
		// �����ǰ�߶���Ҫ��ѯ�߶�
		
		// ���Ҫ��ѯ���߶��ڵ�ǰ�߶ε����
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
