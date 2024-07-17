import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int[] count = new int[3001];
        int uniqueSushi = 0;

        for (int i = 0; i < k; i++) {
            if(count[sushi[i]] == 0){
                uniqueSushi++;
            }
            count[sushi[i]]++;
        }

        int result = count[c] == 0 ? uniqueSushi + 1 : uniqueSushi;

        int start = 0;
        int end = k - 1;
        for (int i = 0; i < N; i++) {
            count[sushi[start]]--;
            if(count[sushi[start]] == 0){
                uniqueSushi--;
            }
            start++;

            end = (end + 1) % N;
            if(count[sushi[end]] == 0){
                uniqueSushi++;
            }
            count[sushi[end]]++;

            int cnt = count[c] == 0 ? uniqueSushi + 1: uniqueSushi;
            if(cnt > result){
                result = cnt;
            }
        }

        System.out.println(result);
    }
}
