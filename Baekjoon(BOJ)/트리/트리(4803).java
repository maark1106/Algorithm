
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    ArrayList<Integer>[] graph;
    int node, edge;
    int N;
    int M;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }

            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                graph[v1].add(v2);
                graph[v2].add(v1);
            }
            int cnt = getTrees();
            System.out.print("Case " + number + ": ");
            if (cnt == 0) {
                System.out.println("No trees.");
            } else if (cnt == 1) {
                System.out.println("There is one tree.");
            } else {
                System.out.println("A forest of " + cnt + " trees.");
            }
            number++;
        }
    }

    int getTrees() {
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                node = 0;
                edge = 0;
                DFS(i, visited);
                if ((node - 1) * 2 == edge) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    void DFS(int p, boolean[] visited) {
        visited[p] = true;
        node++;
        for (int v : graph[p]) {
            edge++;
            if (!visited[v]) {
                DFS(v, visited);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

}
