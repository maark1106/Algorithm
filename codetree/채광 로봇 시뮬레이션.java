import java.util.*;
import java.io.*;

/*

    풀이
    격자 N x N = 1000000 => 한번에 탐색해야 함.
    요구사항
    오른쪽이나 아래쪽으로만 이동할 수 있다.
    T초를 한번 되돌릴 수 있다.
    

    1. 먼저 각 칸에서 T초 동안 이동해서 갈 수 있는 최댓값을 구한다
    2. 그 후 처음부터 다시 탐색해서
        역행을 사용해서 최댓값
        역행을 사용하지 않고 최댓값을 구하기
*/

public class Main {

    static int N;
    static int T;
    static int[][] board;
    static int[][][] dp;
    static int[][] timeDp;
    static int[] dy = {0, 1};
    static int[] dx = {1, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        dp = new int[N][N][2];
        timeDp = new int[N][N];

        for(int i = 0 ; i < N; i++){
            Arrays.fill(timeDp[i], -10000000);
        }

        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < 2; k++){
                    dp[i][j][k] = -10000000;
                }
            }
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int num = dfs(i, j, 0, board[i][j]);
                timeDp[i][j] = num;
            }
        }

        // 해당 위치에서 오른쪽, 아래로 갱신해서 이동
        // 맨 윗 줄은 오른쪽으로 이동만하기
        // 항상 첫번째는 위에서만 내려오기


        //1행은 정렬
        dp[0][0][0] = board[0][0];
        dp[0][0][1] = board[0][0] + timeDp[0][0];
        for(int j = 1; j < N; j++){
            dp[0][j][0] = dp[0][j - 1][0] + board[0][j];
            dp[0][j][1] = Math.max(dp[0][j][0] + timeDp[0][j]
                            , dp[0][j - 1][1] + board[0][j]);
        }

        for(int i = 1; i < N; i++){
            dp[i][0][0] = dp[i - 1][0][0] + board[i][0]; // 맨 첫번째 열은 위에서 바로 내려와야 함
            // 이전에서 쓴거 vs 현재에서 쓴 거 
            dp[i][0][1] = Math.max(dp[i - 1][0][1] + board[i][0], dp[i][0][0] + timeDp[i][0]);
            for(int j = 1; j < N; j++){ // 나머지 행은 위, 왼쪽 중 Max
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i][j - 1][0]) + board[i][j];
                dp[i][j][1] = Math.max(dp[i][j][0] + timeDp[i][j], 
                    Math.max(dp[i - 1][j][1] + board[i][j], dp[i][j - 1][1] + board[i][j]));
            }
        }

        System.out.print(Math.max(dp[N - 1][N - 1][0], dp[N - 1][N - 1][1]));
    }

    static int dfs(int y, int x, int dis, int sum){ 
        if(dis == T){
            return sum;
        }

        int res = -10000000;
        for(int i = 0 ; i < 2; i++){
            int yy = y + dy[i];
            int xx = x + dx[i];

            if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                continue;
            }

            int total = dfs(yy, xx, dis + 1, sum + board[yy][xx]);
            res = Math.max(res, total);
        }
        return res;
    }
}
