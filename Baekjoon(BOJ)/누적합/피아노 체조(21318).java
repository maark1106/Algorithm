import java.util.*;
import java.io.*;

class Main{

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] number = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        for(int i = 1; i < N ; i++){
            dp[i] = dp[i - 1];
            if(number[i] > number[i + 1]){
                dp[i]++;
            }
        }
        dp[N] = dp[N - 1];

        st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken());


        for(int i = 0 ; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            System.out.println(dp[e - 1] - dp[s - 1]);
        }
    }
}
