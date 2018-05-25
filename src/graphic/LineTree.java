package graphic;

import java.util.Scanner;

/*
 * �߶���
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
	private int sum;		// �ɴ�����
	private int N;		// ��ʾһ����N������
	
	
	// left:��˵㣬right:�Ҷ˵㣬num:�����±�
	private void makeTree(int left,int right,int num) {
		tree[num] = new edge(left, right);
		
		// ����Ҷ�ӽڵ�
		if(right==left) {
			tree[num].sum = people[right];
			return;
		}
		
		// �ݹ鹹��������
		makeTree(left,(left+right)/2, num+num);
		// �ݹ鹹��������
		makeTree((left+right)/2+1, right, num+num+1);
		
		// ���ڵ�ķɴ��������ӽڵ�ķɴ���֮��
		tree[num].sum = tree[num+num].sum + tree[num+num+1].sum;
	}
	
	// ����j�����ݵ�����additonal���ɴ���num�������±�
	private void add(int j,int additional,int num) {
		// ���j��������߶����棬�����߶ε���ֵ+��additional
		edge e = tree[num];
		tree[num].sum += additional;
		
		if(j==e.left && j==e.right) {
			// ˵���Ѿ�����Ҷ�ӽڵ�
			return;
		}
		
		int middle = (e.left+e.right)/2;
		if(j>middle) {
			// ˵��j��������߶��ұߣ���������������
			add(j, additional, num+num+1);
		}else{
			// ˵��j��������߶���ߣ���������������
			add(j,additional,num+num);
		}
	}
	
	// ��ѯ�ӵ�i�����ݵ���j�����ݵķɴ�������num�������±�
	public void query(int i,int j,int num) {
		// ����ҵ�Ҫ����߶����䣬ֱ������ 
		if(i<=tree[num].left && j>=tree[num].right) {
			this.sum += tree[num].sum;
			return;
		}
		// ����Ļ����ж�Ҫ��ѯ���߶��ڵ�ǰ�߶ε��ұ߻��ǵ�ǰ�߶ε����
		int mid = (tree[num].left+tree[num].right)/2;
		
		// ���Ҫ��ѯ���߶��ڵ�ǰ�߶ε���ߣ���ô����߲�ѯ
		if(j<=mid) {
			query(i, j, num+num);
		}else if(i>mid) {
			query(i, j, num+num+1);
		}else {
			// Ҫ��ѯ���߶��ڸ�ѡ���м䣬�ֶβ�ѯ�����ҽڵ㶼��
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
			
			// �����߶���
			makeTree(1, N, 1);
			
			// ��ʼ��������
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
