
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        while (end <= N) {
            if(sum >= S){
                result = Math.min(result, end - start);
                sum -= arr[start++];
            }else{
                if(end == N) break;
                sum += arr[end++];
            }
        }

        System.out.println(result == Integer.MAX_VALUE ? 0 : result);
    }

}
