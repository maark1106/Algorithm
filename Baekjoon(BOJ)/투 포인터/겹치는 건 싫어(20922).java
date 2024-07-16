
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;

        int maxLength = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        while (end < N) {

            map.put(arr[end], map.getOrDefault(arr[end], 0) + 1);

            while(K < map.get(arr[end])){
                map.put(arr[start], map.get(arr[start]) - 1);
                start++;
            }

            if(maxLength < end - start + 1){
                maxLength = end - start + 1;
            }
            end++;
        }

        System.out.println(maxLength);
    }
}
