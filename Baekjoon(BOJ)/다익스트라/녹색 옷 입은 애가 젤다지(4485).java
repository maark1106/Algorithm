import java.io.*;
import java.util.*;

public class Main {

    int N;
    int[][] board;
    int[][] distance;
    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) {
                break;
            }
            board = new int[N][N];
            distance = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    distance[i][j] = INF;
                }
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    distance[i][j] = INF;
                }
            }

            BFS();
            System.out.println("Problem " + cnt + ": " + distance[N - 1][N - 1]);
            cnt++;
        }
    }

    void BFS() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p1[2] - p2[2]);
        pq.add(new int[]{0, 0, board[0][0]});
        distance[0][0] = board[0][0];

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int y = cur[0];
            int x = cur[1];
            int cost = cur[2];

            if(cost > distance[y][x]){
                continue;
            }

            for(int i = 0 ; i < 4 ; i ++){
                int yy = y + dy[i];
                int xx = x + dx[i];

                if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                    continue;
                }

                int newCost = cost + board[yy][xx];
                if(newCost < distance[yy][xx]){
                    distance[yy][xx] = newCost;
                    pq.add(new int[]{yy, xx, newCost});
                }
            }
        }
    }
}
