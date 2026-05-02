import java.io.*;
import java.util.*;

class Solution {

    /*
        1. 모든 배열을 max
        2. bfs를 수행하기
        3. dp를 갱신해서 작으면 queue에 다시 넣기
    */

    int[][] dp;
    int[][] time;
    int N;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        new Solution().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            dp = new int[N][N];
            time = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();

                for (int j = 0; j < N; j++) {
                    time[i][j] = s.charAt(j) - '0';
                    dp[i][j] = 1_000_000_000;
                }
            }

            bfs();

            System.out.println("#" + testCase + " " + dp[N - 1][N - 1]);
        }
    }

    void bfs() {
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(0, 0));
        dp[0][0] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];

                if (yy < 0 || yy >= N || xx < 0 || xx >= N) {
                    continue;
                }

                int nextCost = dp[cur.y][cur.x] + time[yy][xx];

                if (nextCost >= dp[yy][xx]) {
                    continue;
                }

                dp[yy][xx] = nextCost;
                q.add(new Node(yy, xx));
            }
        }
    }

    class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
