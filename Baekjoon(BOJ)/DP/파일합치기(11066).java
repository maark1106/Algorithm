import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] files = new int[k + 1];
            int[] sum = new int[k + 1];
            int[][] dp = new int[k + 1][k + 1];

            for (int i = 1; i <= k; i++) {
                int num = Integer.parseInt(st.nextToken());
                files[i] = num;
                sum[i] = sum[i - 1] + num;
            }

            for (int n = 1; n <= k; n++) {
                for (int from = 1; from + n <= k; from++) {
                    int to = from + n;
                    dp[from][to] = Integer.MAX_VALUE;

                    for (int divide = from; divide < to; divide++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][divide]
                                + dp[divide + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }
            System.out.println(dp[1][k]);
        }
    }
}
