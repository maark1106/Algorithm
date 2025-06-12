import java.util.*;
import java.io.*;

class Main{

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] num = new int[N + 1];
        int[] sum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i++){
            num[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + num[i];
        }

        long res = 0;
        Map<Integer, Integer> storage = new HashMap<>();
        for(int i = 1; i <= N ; i++){
            if(sum[i] == K){
                res++;
            }
            int cnt = storage.getOrDefault(sum[i] - K, 0);
            res += cnt;
            storage.put(sum[i], storage.getOrDefault(sum[i], 0) + 1);
        }

        System.out.print(res);
    }
}
