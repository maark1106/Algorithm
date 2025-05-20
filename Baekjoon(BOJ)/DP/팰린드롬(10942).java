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
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i<= N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N + 1][N + 1];

        for(int i = 1; i<= N;i++){ // 기준이 1개
            int left = i;
            int right = i;

            while(left > 0 && right <= N){
                if(arr[left] == arr[right]){
                    dp[left][right] = true;
                    left--;
                    right++;
                }
                else{
                    break;
                }
            }
        }

        for(int i = 1; i <= N - 1;i++){ // 기준이 2개
            int left = i;
            int right = i + 1;

            while(left > 0 && right <= N){
                if(arr[left] == arr[right]){
                    dp[left][right] = true;
                    left--;
                    right++;
                }
                else{
                    break;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append((dp[S][E] == true ? 1 : 0) + "\n");
        }

        System.out.println(sb);
    }

}
