import java.io.*;
import java.util.*;

/*
    시간 복잡도
    - dfs: 16 x 4^6

    풀이
    1. 모든 좌표에서 DFS를 시작한다.
    2. 현재 위치의 숫자를 number 배열에 저장한다.
    3. depth가 7이 되면 7자리 숫자를 문자열로 만들어 Set에 저장한다.
    4. Set을 사용해 중복 숫자를 제거한다.
*/

class Solution {

    int[][] board;
    HashSet<String> storage;

    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public static void main(String args[]) throws Exception {
        new Solution().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            storage = new HashSet<>();
            board = new int[4][4];

            for (int y = 0; y < 4; y++) {
                st = new StringTokenizer(br.readLine());

                for (int x = 0; x < 4; x++) {
                    board[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            int[] number = new int[7];

            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    number[0] = board[y][x];
                    dfs(y, x, 1, number);
                }
            }

            System.out.println("#" + tc + " " + storage.size());
        }
    }

    void dfs(int y, int x, int depth, int[] number) {
        if (depth == 7) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 7; i++) {
                sb.append(number[i]);
            }

            storage.add(sb.toString());
            return;
        }

        for (int direction = 0; direction < 4; direction++) {
            int ny = y + dy[direction];
            int nx = x + dx[direction];

            if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) {
                continue;
            }

            number[depth] = board[ny][nx];
            dfs(ny, nx, depth + 1, number);
        }
    }
}
