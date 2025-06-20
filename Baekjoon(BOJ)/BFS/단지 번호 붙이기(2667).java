package bfs.단지번호붙이기_2667;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    boolean[][] visited;
    int N;
    int[][] house;
    List<Integer> res;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        house = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                house[i][j] = line.charAt(j) - '0';
            }
        }

        Collections.sort(res);
        System.out.println(res.size());
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    int bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        q.add(new int[]{y, x});

        int cnt = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int yy = cur[0] + dy[i];
                int xx = cur[1] + dx[i];

                if (xx < 0 || xx >= N || yy < 0 || yy >= N) {
                    continue;
                }
                if (visited[yy][xx] || house[yy][xx] == 0) {
                    continue;
                }

                visited[yy][xx] = true;
                cnt++;
                q.add(new int[]{yy, xx});
            }
        }
        return cnt;
    }
}
