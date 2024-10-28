import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] cnt = new int[N + 1];
        int[] line = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            int cntPerson = 0;
            int j;
            for (j = 1; j <= N; j++) {
                if (cntPerson == cnt[i]) {
                    break;
                }
                if (line[j] == 0) {
                    cntPerson++;
                }
            }

            while (line[j] != 0) {
                j++;
            }
            line[j] = i;
        }

        for (int i = 1; i <= N; i++){
            System.out.print(line[i] + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
