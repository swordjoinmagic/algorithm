# 第六题   #
## **标题：递增三元组** ##
  
给定三个整数数组  
A = [A1, A2, … AN],  
B = [B1, B2, … BN],  
C = [C1, C2, … CN]，  
请你统计有多少个三元组(i, j, k) 满足：  
1. 1 <= i, j, k <= N   
2. Ai < Bj < Ck  


    输入格式
    
    第一行包含一个整数N。  

    第二行包含N个整数A1, A2, ... AN。  
    第三行包含N个整数B1, B2, ... BN。  

    第四行包含N个整数C1, C2, ... CN。  
    对于30%的数据，1 <= N <= 100  
    对于60%的数据，1 <= N <= 1000   
    对于100%的数据，1 <= N <= 100000 0 <= Ai, Bi, Ci <= 100000
       
    输出格式  
    一个整数表示答案

    【样例输入】
    3
    1 1 1
    2 2 2
    3 3 3
    
    【样例输出】
    27 



# 第八题   #
## **标题：日志统计** ##
  
小明维护着一个程序员论坛。现在他收集了一份”点赞”日志，日志共有N行。其中每一行的格式是：

***ts id***

表示在ts时刻编号id的帖子收到一个”赞”。

现在小明想统计有哪些帖子曾经是”热帖”。如果一个帖子曾在任意一个长度为D的时间段内收到不少于K个赞，小明就认为这个帖子曾是”热帖”。

具体来说，如果存在某个时刻T满足该帖在[T, T+D)这段时间内(注意是左闭右开区间)收到不少于K个赞，该帖就曾是”热帖”。

给定日志，请你帮助小明统计出所有曾是”热帖”的帖子编号。


	输入格式
	第一行包含三个整数N、D和K。  
	以下N行每行一条日志，包含两个整数ts和id。  
	
	对于50%的数据，1 <= K <= N <= 1000  
	对于100%的数据，1 <= K <= N <= 100000 0 <= ts <= 100000 0 <= id <= 100000  
	
	【输出格式】
	按从小到大的顺序输出热帖id。每个id一行。
	
	【输入样例】
	7 10 2  
	0 1  
	0 10    
	10 10  
	10 1  
	9 1
	100 3  
	100 3  
	
	【输出样例】
	1  
	3    