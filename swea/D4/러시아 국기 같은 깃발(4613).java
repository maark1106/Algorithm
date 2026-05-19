import java.io.*;
import java.util.*;


public class Solution {
	
	/*
	  
	 	시간 복잡도
	 	N x nC2 -> N^3
	 	
	 	풀이 
	 	1. 각 줄에 해당하는 W, B, R 개수 구해놓기
	 	2. 각 구간 나누고 해당 구간에 얼만큼 칠해야 하는지 개수 세기(M - 구해놓은 개수)
	 	
	 */

	int N;
	int M;
	char[][] board;
	int[][] cnt;
	
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
			M = Integer.parseInt(st.nextToken());
			board = new char[N][M];
			cnt = new int[N][3];
			
			for(int i = 0 ; i < N; i++) {
				String s = br.readLine();
				for(int j = 0;j < M; j++) {
					board[i][j] = s.charAt(j);
					if(board[i][j] == 'W') {
						cnt[i][0]++;
					}
					else if(board[i][j] == 'B') {
						cnt[i][1]++;
					}
					else if(board[i][j] == 'R') {
						cnt[i][2]++;
					}
				}
			}
			
			int res = Integer.MAX_VALUE;
			for(int a = 1; a <= N - 2; a++) {
				for(int b = a + 1; b <= N - 1; b++) {
					res = Math.min(res, paint(a, b));
				}
			}
			
			System.out.println("#" + tc + " " + res);
		}
	}
	
	int paint(int a, int b) {
		int total = 0;
		for(int i = 0 ; i <= a - 1; i++) {// M개에서 하얀색 뺀 만큼 칠하 
			total += M - cnt[i][0];
		}
		
		for(int i = a; i <= b - 1; i++) {// M개에서 파란색 뺀 만큼 칠하 
			total += M - cnt[i][1];
		}
		
		for(int i = b ; i <= N - 1; i++) {// M개에서 빨간색 뺀 만큼 칠하 
			total += M - cnt[i][2];
		}
		
		return total;
	}
}
