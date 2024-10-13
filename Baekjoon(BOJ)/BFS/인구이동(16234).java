import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    int N, L, R;
    int[][] map;

    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 0;
        while (true) {
            if (!move()) {
                break;
            }
            res++;
        }

        System.out.println(res);
    }

    boolean move() {
        boolean[][] visited = new boolean[N][N];
        boolean isMove = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    if (BFS(visited, i, j)) {
                        isMove = true;
                    }
                }
            }
        }

        return isMove;
    }

    boolean BFS(boolean[][] visited, int y, int x) {
        visited[y][x] = true;
        List<int[]> storage = new ArrayList<>();
        storage.add(new int[]{y, x});
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});

        int sum = map[y][x];
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int yy = cur[0] + dy[i];
                int xx = cur[1] + dx[i];

                if (yy < 0 || yy >= N || xx < 0 || xx >= N) {
                    continue;
                }

                if (visited[yy][xx]) {
                    continue;
                }

                int dif = Math.abs(map[cur[0]][cur[1]] - map[yy][xx]);
                if (dif < L || dif > R) {
                    continue;
                }

                visited[yy][xx] = true;
                q.add(new int[]{yy, xx});
                storage.add(new int[]{yy, xx});
                sum += map[yy][xx];
            }
        }

        if(storage.size() == 1){
            return false;
        }

        int avg = sum / storage.size();
        for(int i = 0 ; i < storage.size();i++){
            map[storage.get(i)[0]][storage.get(i)[1]] = avg;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
