import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Obj> objs = new ArrayList<>();
        for(int i = 0 ;i < N; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int worth = Integer.parseInt(st.nextToken());
            objs.add(new Obj(weight, worth));
        }

        int[] dp = new int[100001];

        for(int i = 0 ; i < N ; i++){
            Obj cur = objs.get(i);
            for(int j = K; j >= 0; j--){
                if(j + cur.weight > K){
                    continue;
                }
                dp[j + cur.weight] = Math.max(dp[j + cur.weight], dp[j] + cur.worth);
            }
        }

        int res = 0;
        for(int i = 0; i <= K; i++){
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
    }

    class Obj{
        int weight;
        int worth;

        public Obj(int weight, int worth){
            this.weight = weight;
            this.worth = worth;
        }
    }
}
