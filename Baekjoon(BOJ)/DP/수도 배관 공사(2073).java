import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[] dp = new int[D + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for(int i = 0 ; i < P;i++){
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            for(int j = D; j >=0; j--){
                if(L + j > D){
                    continue;
                }
                if(j != 0 && dp[j] == Integer.MAX_VALUE){
                    continue;
                }

                int value = Math.min(C, dp[j]);
                if(dp[L + j] == Integer.MAX_VALUE){
                    dp[L + j] = value;
                }
                else{
                    dp[L + j] = Math.max(dp[L + j], value);
                }
            }
        }

        System.out.print(dp[D]);
    }
}
