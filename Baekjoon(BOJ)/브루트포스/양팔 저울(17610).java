import java.util.*;
import java.io.*;

public class Main {

    int N;
    int[] buckets;
    HashSet<Integer> res = new HashSet<>();

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buckets = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buckets[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        res.remove(0); 
        System.out.print(sum() - res.size());
    }

    void dfs(int idx, int weight) {
        if (idx == N) {
            res.add(weight);
            return;
        }

        dfs(idx + 1, weight);
        dfs(idx + 1, weight + buckets[idx]);
        dfs(idx + 1, Math.abs(weight - buckets[idx]));
    }

    int sum() {
        int total = 0;
        for (int b : buckets) {
            total += b;
        }
        return total;
    }
}
