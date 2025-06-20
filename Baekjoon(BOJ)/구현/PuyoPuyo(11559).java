package 구현.PuyoPuyo_11559;

import java.util.*;
import java.io.*;

public class Main {

    char[][] board;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        int res = 0;
        while (true) {
            boolean flag = bfs1();
            if (flag) {
                res++;
            } else {
                break;
            }
        }

        System.out.print(res);
    }

    boolean bfs1() {
        boolean[][] visited = new boolean[12][6];

        boolean flag = false;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] != '.' && !visited[i][j]) {
                    List<Pos> storage = new ArrayList<>();
                    int sameWord = getSameWord(i, j, visited, storage);
                    if (sameWord >= 4) {
                        removePuyo(storage);
                        flag = true;
                    }
                }
            }
        }

        fall();
        return flag;
    }

    int getSameWord(int y, int x, boolean[][] visited, List<Pos> storage) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(y, x));
        storage.add(new Pos(y, x));
        visited[y][x] = true;
        int cnt = 1;

        char target = board[y][x];
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];

                if (yy < 0 || yy >= 12 || xx < 0 || xx >= 6) {
                    continue;
                }
                if (board[yy][xx] != target || visited[yy][xx]) {
                    continue;
                }

                visited[yy][xx] = true;
                cnt++;
                q.add(new Pos(yy, xx));
                storage.add(new Pos(yy, xx));
            }
        }

        return cnt;
    }

    void removePuyo(List<Pos> storage) {
        for (int i = 0; i < storage.size(); i++) {
            int y = storage.get(i).y;
            int x = storage.get(i).x;
            board[y][x] = '.';
        }
    }

    void fall() {
        for (int i = 0; i < 6; i++) {
            for (int j = 10; j >= 0; j--) {
                if (board[j][i] != '.') {
                    int cur = j;
                    while (cur + 1 < 12 && board[cur + 1][i] == '.') {
                        cur++;
                    }
                    if (cur != j) {
                        board[cur][i] = board[j][i];
                        board[j][i] = '.';
                    }
                }
            }
        }
    }

    class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
