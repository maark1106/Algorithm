package new_.감시피하기_18428_백트래킹;

import java.io.*;
import java.util.*;

public class Main {

    int N;
    char[][] board;
    List<int[]> teacher;
    boolean res;
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new char[N][N];
        teacher = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = st.nextToken().charAt(0);
                if (board[i][j] == 'T') {
                    teacher.add(new int[]{i, j});
                }
            }
        }

        chooseThree(0, 0);
        System.out.println(res ? "YES" : "NO");
    }

    void chooseThree(int idx, int depth) {
        if (depth == 3) {
            if (isAvoid()) {
                res = true;
            }
            return;
        }
        if (idx == N * N) {
            return;
        }

        for (int i = idx; i < N * N; i++) {
            if (board[i / N][i % N] == 'X') {
                board[i / N][i % N] = 'O';
                chooseThree(i + 1, depth + 1);
                board[i / N][i % N] = 'X';
            }
        }
    }

    boolean isAvoid() {
        for (int i = 0; i < teacher.size(); i++) {
            int curY = teacher.get(i)[0];
            int curX = teacher.get(i)[1];
            for (int j = 0; j < 4; j++) {
                int y = curY;
                int x = curX;
                while (y >= 0 && y < N && x >= 0 && x < N && board[y][x] != 'O') {
                    if (board[y][x] == 'S'){
                        return false;
                    }
                    y += dy[j];
                    x += dx[j];
                }
            }
        }
        return true;
    }
}
