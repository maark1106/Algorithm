import java.util.*;
import java.io.*;

class Main{

    /*
        1. 일단 왼쪽 or 위에서 먹을 수 있으면 먹는 게 최적
        2. 위 -> 아래 그대로 받기
        	단, 현재 우유(0, 1, 2)에 따라서 이전 값 + 1이 최대면 갱신
        	- 초기 조건 주의(1을 먹으려면 0을 먹고, 2를 먹으려면 0, 1를 먹은 상태여야됨)
        3. 왼쪽 -> 오른쪽 갱신
    */

	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[][] milk = new int[N + 1][N + 1];
		int[][][] dp = new int[N + 1][N + 1][3];
		for(int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++){
				milk[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= N; j++){ // 위에서 아래로 초기화
				dp[i][j][0] = dp[i - 1][j][0];
				dp[i][j][1] = dp[i - 1][j][1];
				dp[i][j][2] = dp[i - 1][j][2];

				if(milk[i][j] == 0){
					dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][j][2] + 1);
				}
				if(milk[i][j] == 1 && dp[i - 1][j][0] >= 1){
					dp[i][j][1] = Math.max(dp[i][j][1], dp[i - 1][j][0] + 1);
				}
				if(milk[i][j] == 2 && dp[i - 1][j][1] >= 2){
					dp[i][j][2] = Math.max(dp[i][j][2], dp[i - 1][j][1] + 1);
				}
			}

			for(int j = 2; j <= N; j++){
				dp[i][j][0] = Math.max(dp[i][j][0], dp[i][j - 1][0]);
				dp[i][j][1] = Math.max(dp[i][j][1], dp[i][j - 1][1]);
				dp[i][j][2] = Math.max(dp[i][j][2], dp[i][j - 1][2]);

				if(milk[i][j] == 0){
					dp[i][j][0] = Math.max(dp[i][j][0], dp[i][j - 1][2] + 1);
				}
				if(milk[i][j] == 1 && dp[i][j - 1][0] >= 1){
					dp[i][j][1] = Math.max(dp[i][j][1], dp[i][j - 1][0] + 1);
				}
				if(milk[i][j] == 2 && dp[i][j - 1][1] >= 2){
					dp[i][j][2] = Math.max(dp[i][j][2], dp[i][j - 1][1] + 1);
				}
			}
		}

		int res = Math.max(dp[N][N][0], Math.max(dp[N][N][1], dp[N][N][2]));
		System.out.println(res);
	}


}
