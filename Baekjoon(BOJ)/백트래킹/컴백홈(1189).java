
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    char[][] board;
    int N;
    int M;
    int K;
    boolean[][] visited;
    int result = 0;
    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[N - i - 1][j] = str.charAt(j);
            }
        }

        visited[0][0] = true;
        DFS(0, 0, 1);
        System.out.println(result);
    }

    void DFS(int y, int x, int dis) {
        if (y == N - 1 && x == M - 1) {
            if (K == dis) {
                result++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                continue;
            }

            if(!visited[ny][nx] && board[ny][nx] != 'T'){
                visited[ny][nx] = true;
                DFS(ny, nx, dis + 1);
                visited[ny][nx] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
