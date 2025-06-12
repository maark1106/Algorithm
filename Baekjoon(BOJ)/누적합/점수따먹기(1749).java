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
        int M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N + 1][M + 1];
        int[][] sum = new int[N + 1][M + 1];

        for(int i = 1; i <= N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=M;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1;i <= N ; i++){
            for(int j = 1; j <=M;j++){
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1]
                        - sum[i - 1][j - 1] + board[i][j];
            }
        }

        int res = Integer.MIN_VALUE;
        for(int i = 1; i<= N; i++){
            for(int j = 1; j <= M;j++){
                for(int k = i; k <= N; k++){
                    for(int m = j; m <= M; m++){
                        int sY = i;
                        int sX = j;
                        int eY = k;
                        int eX = m;

                        int sum1 = sum[eY][eX] - sum[eY][sX - 1]
                                - sum[sY - 1][eX] + sum[sY - 1][sX - 1];
                        res = Math.max(sum1, res);
                    }
                }
            }
        }

        System.out.print(res);
    }
}
