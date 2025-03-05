import java.util.*;
import java.io.*;

public class Main {

    int N;
    int[][] board;
    int res = Integer.MAX_VALUE;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] checked = new boolean[N][N];
        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 && !checked[i][j]) {
                    checkedGround(i, j, idx, checked);
                    idx++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    bfs(i, j);
                }
            }
        }

        System.out.print(res);
    }

    void checkedGround(int y, int x, int idx, boolean[][] checked) {
        Queue<Pair> q = new LinkedList<>();
        checked[y][x] = true;
        q.add(new Pair(y, x, 0));
        board[y][x] = idx;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];

                if (yy < 0 || yy >= N || xx < 0 || xx >= N) {
                    continue;
                }
                if (board[yy][xx] == 0 || checked[yy][xx]) {
                    continue;
                }

                checked[yy][xx] = true;
                board[yy][xx] = idx;
                q.add(new Pair(yy, xx, 0));
            }
        }
    }

    void bfs(int y, int x) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        visited[y][x] = true;
        q.add(new Pair(y, x, 0));
        int curGround = board[y][x];

        int cnt = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];

                if (yy < 0 || yy >= N || xx < 0 || xx >= N) {
                    continue;
                }
                if (board[yy][xx] == curGround || visited[yy][xx]) {
                    continue;
                }

                if (board[yy][xx] == 0) {
                    visited[yy][xx] = true;
                    q.add(new Pair(yy, xx, cur.dis + 1));
                } else {
                    cnt = cur.dis;
                    break;
                }
            }
            if (cnt != Integer.MAX_VALUE) {
                break;
            }
        }

        res = Math.min(res, cnt);
    }

    class Pair {
        int y;
        int x;
        int dis;

        public Pair(int y, int x, int dis) {
            this.y = y;
            this.x = x;
            this.dis = dis;
        }
    }
}
