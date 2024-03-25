
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    int N;
    char[] arr;
    boolean[] visited = new boolean[10];
    List<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        DFS(0, 0, "");
        System.out.println(result.get(result.size() - 1));
        System.out.println(result.get(0));
    }

    private void DFS(int depth, int before, String cur) {
        if (depth == N + 1) {
            result.add(cur);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }
            if (depth == 0 ||
                    isCorrectNumber(before, i, arr[depth - 1])) {
                visited[i] = true;
                DFS(depth + 1, i, cur + i);
                visited[i] = false;
            }
        }
    }

    boolean isCorrectNumber(int before, int cur, char c) {
        if (c == '<') {
            if (before < cur) {
                return true;
            }
        } else {
            if (before > cur) {
                return true;
            }
        }

        return false;
    }
}
