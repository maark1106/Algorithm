package new_.컨베이어벨트위의로봇_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    int N;
    int K;
    int[][] belt;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // [0] : 벨트 내구도, [1] : 로봇 존재 여부
        belt = new int[2 * N][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i][0] = Integer.parseInt(st.nextToken());
        }

        int res = 0;
        while (true) {
            res++;
            int temp = belt[2 * N - 1][0];
            for (int i = 2 * N - 1; i > 0; i--) {
                belt[i][0] = belt[i - 1][0];
                belt[i][1] = belt[i - 1][1];
            }

            belt[0][0] = temp;
            belt[0][1] = 0;
            belt[N - 1][1] = 0;

            // N - 1가면 하차
            for (int i = N - 2; i >= 0; i--) {
                if (belt[i][1] == 1) {
                    if (belt[i + 1][0] > 0 && belt[i + 1][1] != 1) {
                        belt[i][1] = 0;
                        belt[i + 1][1] = 1;
                        belt[i + 1][0]--;
                    }
                }
            }
            belt[N - 1][1] = 0;

            if (belt[0][0] > 0) {
                belt[0][1] = 1;
                belt[0][0]--;
            }

            int cnt = 0;
            for (int i = 0; i < 2 * N; i++) {
                if (belt[i][0] == 0) {
                    cnt++;
                }
            }
            if (cnt >= K) {
                System.out.println(res);
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
