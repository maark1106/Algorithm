import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    int N;
    int L;
    int[][] road;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        road = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                road[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            if (calCol(i)) {
                res++;
            }
            if (calRow(i)) {
                res++;
            }
        }

        System.out.println(res);
    }

    boolean calRow(int row) {
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = road[row][i] - road[row][i + 1];
            if (diff > 1 || diff < -1) {
                return false;
            }
            if (diff == -1) { //오르막
                for (int j = 0; j < L; j++) {
                    if (i - j < 0 || visited[i - j]) {
                        return false;
                    }
                    if(road[row][i]!= road[row][i - j]){
                        return false;
                    }
                    visited[i - j] = true;
                }
            } else if (diff == 1) { //내리막
                for (int j = 1; j <= L; j++) {
                    if (i + j >= N || visited[i + j]) {
                        return false;
                    }
                    if(road[row][i] - 1 != road[row][i + j]){
                        return false;
                    }
                    visited[i + j] = true;
                }
            }
        }

        return true;
    }

    boolean calCol(int col) {
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = road[i][col] - road[i + 1][col];
            if (diff > 1 || diff < -1) {
                return false;
            }
            if (diff == -1) { //오르막
                for (int j = 0; j < L; j++) {
                    if (i - j < 0 || visited[i - j]) {
                        return false;
                    }
                    if(road[i][col] != road[i - j][col]){
                        return false;
                    }
                    visited[i - j] = true;
                }
            } else if (diff == 1) { //내리막
                for (int j = 1; j <= L; j++) {
                    if (i + j >= N || visited[i + j]) {
                        return false;
                    }
                    if(road[i][col] - 1 != road[i + j][col]){
                        return false;
                    }
                    visited[i + j] = true;
                }
            }
        }

        return true;
    }
}
