

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    int N, M;
    int[][] map;
    int[][] dp;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    int res = 0;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ;j < M;j++){
                dp[i][j] = -1;
            }
        }

        DFS(0, 0);

        System.out.println(dp[0][0]);
    }

    private int DFS(int y, int x) {
        if(y == N - 1 && x == M - 1){
            return 1;
        }

        if(dp[y][x] != -1){
            return dp[y][x];
        }

        dp[y][x] = 0;
        for(int i = 0 ; i < 4 ;i++){
            int yy = y + dy[i];
            int xx = x + dx[i];

            if(yy < 0 || yy >= N || xx < 0 || xx >= M){
                continue;
            }

            if(map[y][x] > map[yy][xx]) {
                dp[y][x] += DFS(yy, xx);
            }
        }

        return dp[y][x];
    }
}
