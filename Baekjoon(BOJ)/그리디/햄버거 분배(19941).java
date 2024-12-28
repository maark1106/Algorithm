package new_.햄버거분배_19941_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    int N;
    int K;
    char[] eat;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        eat = st.nextToken().toCharArray();

        int res = 0;

        for (int i = 0; i < N; i++) {
            if (eat[i] != 'P') {
                continue;
            }
            for (int j = i - K; j <= i + K; j++) {
                if (j < 0 || j >= N) {
                    continue;
                }

                if (eat[j] == 'H'){
                    eat[j] = 'X';
                    res++;
                    break;
                }
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }


}
