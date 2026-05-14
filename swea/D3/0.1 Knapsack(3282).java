import java.util.*;
import java.io.*;

class Solution{
    
    /*
    	시간 복잡도
        DP : N^2
        
        풀이
        1. weight[i] ~ K까지 해당 값으로 갱신하기
        
    */
    
	public static void main(String args[]) throws Exception{
        new Solution().solution();
	}
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] V = new int[N + 1];
            int[] C = new int[N + 1];
            int[][] dp = new int[K + 1][10001];
            
            for(int i = 1; i <= N; i++){
            	st = new StringTokenizer(br.readLine());
                V[i] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());
            }
            
            for(int i = 1 ; i <= N; i++){
                int v = V[i]; // 부피
                int c = C[i]; // 가치
                for(int j = 1; j <= K; j++){
                    if(v > j){// 부피가 커서 불가능 -> 이전꺼 그대로 받아오기
                        dp[i][j] = dp[i - 1][j];
                    }
                    else{
                        dp[i][j] = Math.max(dp[i -1][j], dp[i - 1][j - v] + c);
                    }
                }
            }
            
            int res = 0;
            for(int i = 1; i <= K; i++){
                res = Math.max(res, dp[N][i]);
            }
            
            System.out.println("#" + tc + " " + res);
        }
    }
    
}
