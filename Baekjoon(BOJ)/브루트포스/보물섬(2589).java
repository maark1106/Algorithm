import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    char[][] board = new char[51][51];
    int N;
    int M;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'L') {
                    int distance = BFS(i, j);
                    result = Math.max(result, distance);
                }
            }
        }

        System.out.println(result);
    }

    int BFS(int y, int x) {
        int maxResult = 0;
        boolean[][] visited = new boolean[51][51];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x, 0});
        visited[y][x] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            maxResult = Math.max(maxResult, cur[2]);

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }
                if (board[ny][nx] == 'W' || visited[ny][nx]) {
                    continue;
                }

                visited[ny][nx] = true;
                q.add(new int[]{ny, nx, cur[2] + 1});
            }
        }
        return maxResult;
    }
}
