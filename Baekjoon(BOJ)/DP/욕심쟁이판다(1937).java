import java.util.*;
import java.io.*;

class Main {

    int N;
    int[][] board;
    int[][] dp;
    boolean[][] visited;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        visited = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dp[i][j] == -1) {
                    DFS(i, j);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (res < dp[i][j]) {
                    res = dp[i][j];
                }
            }
        }

        System.out.println(res + 1);
    }

    int DFS(int y, int x) {
        if (dp[y][x] != -1) {
            return dp[y][x] + 1;
        }

        dp[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int yy = y + dy[i];
            int xx = x + dx[i];

            if (yy <= 0 || yy > N || xx <= 0 || xx > N) {
                continue;
            }
            if (visited[yy][xx] || board[y][x] >= board[yy][xx]) {
                continue;
            }

            visited[yy][xx] = true;
            int cnt = DFS(yy, xx);
            dp[y][x] = Math.max(dp[y][x], cnt);
            visited[yy][xx] = false;
        }

        return dp[y][x] + 1;
    }
}
