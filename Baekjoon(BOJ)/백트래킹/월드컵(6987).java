import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    int[][] score = new int[7][5];
    int[][] match = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 2},
            {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {3, 4}, {3, 5}, {4, 5}};
    boolean possible;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int[] row : score) {
                Arrays.fill(row, 0);
            }
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    score[j][k] = Integer.parseInt(st.nextToken());
                    score[6][3] += score[j][k];
                }
            }

            possible = false;
            if (score[6][3] == 30) {
                dfs(0);
            }

            System.out.print(possible ? 1 : 0);
            System.out.print(" ");
        }
    }

    void dfs(int idx) {
        if (possible) {
            return;
        }
        if (idx == 15) {
            possible = true;
            return;
        }

        int A = match[idx][0];
        int B = match[idx][1];

        if (score[A][0] > 0 && score[B][2] > 0) {
            score[A][0]--;
            score[B][2]--;
            dfs(idx + 1);
            score[A][0]++;
            score[B][2]++;
        }

        if (score[A][1] > 0 && score[B][1] > 0) {
            score[A][1]--;
            score[B][1]--;
            dfs(idx + 1);
            score[A][1]++;
            score[B][1]++;
        }

        if (score[A][2] > 0 && score[B][0] > 0) {
            score[A][2]--;
            score[B][0]--;
            dfs(idx + 1);
            score[A][2]++;
            score[B][0]++;
        }
    }
}
