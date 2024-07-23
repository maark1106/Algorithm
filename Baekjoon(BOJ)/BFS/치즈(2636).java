package new_.치즈_2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    int N;
    int M;
    int cheese = 0;
    int lastCheese = 0;
    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};
    boolean[][] board;


    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new boolean[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    board[i][j] = true;
                    cheese++;
                } else {
                    board[i][j] = false;
                }
            }
        }

        int time = 0;

        while (cheese > 0) {
            lastCheese = cheese;
            meltingCheese();
            time++;
        }

        System.out.println(time + "\n" + lastCheese);
    }

    void meltingCheese() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        boolean[][] visited = new boolean[N + 2][M + 2];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int y = cur[0] + dy[i];
                int x = cur[1] + dx[i];

                if (y < 0 || y > N + 1 || x < 0 || x > M + 1 || visited[y][x] == true) {
                    continue;
                }

                if (board[y][x] == false) {
                    q.add(new int[]{y, x});
                } else {
                    board[y][x] = false;
                    cheese--;
                }
                visited[y][x] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
