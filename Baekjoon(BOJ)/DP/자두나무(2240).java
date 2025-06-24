import java.util.*;
import java.io.*;

class Main{


    int T;
    int W;
    int[][][] dp;
    int[] tree;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        dp = new int[T + 2][W + 2][3];
        tree = new int[T + 2];

        for(int i = 0 ; i < T; i++){
            st = new StringTokenizer(br.readLine());
            tree[i + 1] = Integer.parseInt(st.nextToken());
        }

        if(tree[1] == 1){
            dp[1][0][1] = 1;
        }
        else{
            dp[1][1][2] = 1;
        }

        for(int i = 2; i <= T; i++){
            for(int j = 0; j <= W; j++){
                if(tree[i] == 1){ // 1이면
                    dp[i][j][1] = Math.max(dp[i][j][1],dp[i - 1][j][1] + 1); // 안바꾸고 먹기
                    dp[i][j + 1][1] = Math.max(dp[i][j + 1][1], dp[i - 1][j][2] + 1);// 바꿔서 먹기
                    dp[i][j][2] = Math.max(dp[i][j][2], dp[i - 1][j][2]); // 안바꾸고 안먹기
                }
                else{
                    dp[i][j][2] = Math.max(dp[i][j][2],dp[i - 1][j][2] + 1); // 안바꾸고 먹기
                    dp[i][j + 1][2] = Math.max(dp[i][j + 1][2], dp[i - 1][j][1] + 1);// 바꿔서 먹기
                    dp[i][j][1] = Math.max(dp[i][j][1], dp[i - 1][j][1]); // 안바꾸고 안먹기
                }
            }
        }

        int res = 0;
        for(int i = 0; i <= W; i++){
            res = Math.max(res, Math.max(dp[T][i][1], dp[T][i][2]));
        }

        System.out.print(res);
    }
}
