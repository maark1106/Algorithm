package new_.드래곤커브_15685_구현;

import java.io.*;
import java.util.*;

public class Main {

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, -1, 0, 1};
    boolean[][] board;

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        board = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            List<Integer> numbers = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            numbers.add(d);

            for (int j = 0; j < g; j++) {
                int curSize = numbers.size();
                for (int k = curSize - 1; k >= 0; k--) {
                    numbers.add((numbers.get(k) + 1) % 4);
                }
            }

            int curX = x;
            int curY = y;
            board[x][y] = true;
            for (int j = 0; j < numbers.size(); j++) {
                curX += dx[numbers.get(j)];
                curY += dy[numbers.get(j)];
                board[curX][curY] = true;
            }
        }

        int res = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(board[i][j] && board[i + 1][j] &&
                    board[i][j + 1] && board[i + 1][j + 1]){
                    res++;
                }
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
