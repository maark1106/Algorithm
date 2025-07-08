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
        Pair[] pairs = new Pair[N];
        long sum = 0;
        for(int i = 0 ; i< N; i++){
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            pairs[i] = new Pair(pos, cnt);
            sum += cnt;
        }

        Arrays.sort(pairs);
        int res = 0;
        long total = 0;
        for(int i = 0; i < N; i++){
            total += pairs[i].cnt;
            if(total >= (sum - total)){
                res = pairs[i].pos;
                break;
            }
        }

        System.out.print(res);
    }

    class Pair implements Comparable<Pair>{
        int pos;
        int cnt;

        public Pair(int pos, int cnt){
            this.pos = pos;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair p){
            return this.pos - p.pos;
        }
    }
}
