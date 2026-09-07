import java.util.*;
import java.io.*;
 
 
class Solution{
 
	static Burger[] burgers;
	static int res;
	
	public static void main(String args[]) throws Exception{
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++){
			st = new StringTokenizer(br.readLine()); 
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			burgers = new Burger[N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				burgers[i] = new Burger(k, score);
			}
			
			res = Integer.MIN_VALUE;
			dfs(0, N, L, 0, 0);
			sb.append("#" + tc + " " + res + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int depth, int N, int L, int sumScore, int sumK) {
		if(L < sumK) { // 칼로리 넘었다면 종료
			return;
		}
		
		if(res < sumScore) {
			res = sumScore;
		}
		
		if(depth == N) {
			return;
		}
		
		
		//현재 식재료 사용
		dfs(depth + 1, N, L, sumScore + burgers[depth].score, sumK + burgers[depth].k);
		//현재 식재료 미사용
		dfs(depth + 1, N, L, sumScore, sumK);
		
	}
	
	static class Burger{
		int k;
		int score;
		
		public Burger(int k, int score) {
			this.k = k;
			this.score = score;
		}
	}
}
