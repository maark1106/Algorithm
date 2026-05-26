import java.io.*;
import java.util.*;

public class Solution {
	
	/*
	 * 
	 	
	 	시간복잡도
	 	N^2
	 	
		풀이
		dfs로 모든 지점을 방문하면서 탐색하되 이미 구해져 있는 부분은 바로 return하여 최적
		
	 */
	
	int N;
	int[][] board;
	int[][] dp;
	int[] dy = {1, -1, 0, 0};
	int[] dx = {0, 0, 1, -1};

	
	public static void main(String[] args) throws Exception{
		new Solution().solution();
	}

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());

		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			board = new int[N][N];
			dp = new int[N][N];
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < N; j++) {
					if(dp[i][j] == 0) {
						dp[i][j] = dfs(i, j);
					}
				}
			}
			
			int res = -1;
			int resNumber = 0;
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < N; j++) {
					if(res < dp[i][j]) {
						res = dp[i][j];
						resNumber = board[i][j];
					}
					else if(res == dp[i][j] && resNumber > board[i][j]) {
						resNumber = board[i][j];
					}
				}
			}
            
			System.out.println("#" + tc + " " + resNumber + " " + res);
		}
	}
	
	int dfs(int y, int x) {
		if(dp[y][x] != 0) {
			return dp[y][x];
		}
		
		int cnt = 1;
		for(int i = 0 ; i < 4; i++) {
			int yy = y + dy[i];
			int xx = x + dx[i];
			if(yy < 0 || yy >= N || xx < 0 || xx >= N) {
				continue;
			}
			if(board[y][x] + 1 == board[yy][xx]) {
				cnt += dfs(yy, xx); 
			}
		}
        
		return dp[y][x] = cnt;
	}
	
	
}

