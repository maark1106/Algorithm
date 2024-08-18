import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    int N, M;
    int[][] board;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            for (int j = 1; j <= M; j++) {
                board[i][j] = s.charAt(j - 1) - '0';
            }
        }

        BFS();
    }

    void BFS() {
        int[][] distance = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, 1, 1});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int yy = cur[1] + dy[i];
                int xx = cur[2] + dx[i];

                if (yy <= 0 || yy > N || xx <= 0 || xx > M) {
                    continue;
                }

                int dis = cur[0] + board[yy][xx];
                if (distance[yy][xx] > dis) {
                    distance[yy][xx] = dis;
                    q.add(new int[]{dis, yy, xx});
                }
            }
        }

        System.out.println(distance[N][M] == Integer.MAX_VALUE ? 0 : distance[N][M]);
    }
}
