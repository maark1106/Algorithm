import java.util.*;
import java.io.*;


class Solution{
    
    /*
        풀이
        
      	1. (2 ~ 제곱근 n)까지 나눈다
        2. 만들 수 있는 수인지 체크한다.
        
    */
    
    boolean[] num;
    int[] dp;
    int M;
    
	public static void main(String args[]) throws Exception{
        new Solution().solution();
	}
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
     	   
        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T; tc++){
            num = new boolean[10];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i <= 9; i++){
                int number = Integer.parseInt(st.nextToken());
                if(number == 1){
                    num[i] = true;
                }
            }
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            M = N;
            dp = new int[N + 1];
            Arrays.fill(dp, -2);
            
            int res = solve(N);
            System.out.println("#" + tc + " " + (res == -1 ? -1 : res + 1));
        }
    }
    
    int solve(int N){
        if(dp[N] != -2){
            return dp[N];
        }
        
        int checkRes = checkNum(N);
        if(checkRes != -1){
            return dp[N] = checkRes;
        }
        
        int min = Integer.MAX_VALUE;
        
        for(int i = 2; i * i <= N; i++){
            if(N % i == 0){
                int a = solve(N / i);
                int b = solve(i);

                if(a != -1 && b != -1){
                    min = Math.min(min, a + b + 1);
                }
            }
        }
        
        dp[N] = (min == Integer.MAX_VALUE ? -1 : min);
        return dp[N];
    }
    
  	int checkNum(int N){
        int cnt = 0;
        
        while(N > 0){
            int cur = N % 10;
            if(!num[cur]){
                return -1;
            }
            N /= 10;
            cnt++;
        }
        
        return cnt;
    }
    
    
}
