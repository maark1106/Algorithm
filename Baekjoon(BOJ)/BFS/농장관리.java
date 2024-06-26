
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    int N, M;
    int[][] board = new int[103][103];
    boolean[][] visited = new boolean[103][103];
    int[] dy = {0, 0, 1, 1, 1, -1, -1, -1};
    int[] dx = {1, -1, 0, 1, -1, 0, 1, -1};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int result = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (board[i][j] != 0 && !visited[i][j]) {
                    if (BFS(i, j) == true) {
                        result++;
                    }
                }
            }
        }

        System.out.println(result);
    }

    boolean BFS(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{y,x});
        visited[y][x] = true;
        boolean isPeak = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cy = current[0];
            int cx = current[1];

            for(int i = 0; i < 8;i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if(ny < 1 || ny > N || nx < 1 || nx > M){
                    continue;
                }

                if(board[cy][cx] < board[ny][nx]){
                    isPeak = false;
                }
                else if(board[cy][cx] == board[ny][nx] && !visited[ny][nx]){
                    visited[ny][nx] = true;
                    queue.offer(new int[]{ny, nx});
                }
            }
        }

        return isPeak;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
