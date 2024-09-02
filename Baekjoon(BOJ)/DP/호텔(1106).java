package new_.호텔_1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    int C, N;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        ArrayList<Pair> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            list.add(new Pair(c1, c2));
        }

        int[] dp = new int[C + 102];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 0 ; i < C;i++){
            if(dp[i] == Integer.MAX_VALUE){
                continue;
            }

            for(int j = 0 ; j < list.size();j++){
                if(dp[i] + list.get(j).coin < dp[i + list.get(j).city]){
                    dp[i + list.get(j).city] = dp[i] + list.get(j).coin;
                }
            }
        }

        int res = Integer.MAX_VALUE;

        for(int i = C; i < C + 101; i++){
            if(res > dp[i]){
                res = dp[i];
            }
        }

        System.out.println(res);
    }

    class Pair {
        int coin;
        int city;

        public Pair(int coin, int city) {
            this.coin = coin;
            this.city = city;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
