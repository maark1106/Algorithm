import java.util.*;
import java.io.*;

class Main{

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] money = new int[N + M];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++){
                money[i] = Integer.parseInt(st.nextToken());
            }
            int idx = 1;
            for(int i = N + 1; i < N + M; i++){
                money[i] = money[idx++];
            }
            int[] sum = new int[N + M];
            for(int i = 1; i < N + M; i++){
                sum[i] = sum[i - 1] + money[i];
            }

            if(M == N){
                System.out.println((sum[N] < K) ? 1 : 0);
                continue;
            }

            int cnt = 0;
            for(int i = M; i < N + M; i++){
                int totalMoney = sum[i] - sum[i - M];
                if(totalMoney < K){
                    cnt++;
                }
            }

            System.out.println(cnt);
        }
    }
}
