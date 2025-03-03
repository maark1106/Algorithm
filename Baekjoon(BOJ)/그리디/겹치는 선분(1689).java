

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
        List<Pair> pairs = new ArrayList<>();
        for(int i = 0 ; i < N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pairs.add(new Pair(start, 1));
            pairs.add(new Pair(end, -1));
        }

        Collections.sort(pairs);

        int cur = 0;
        int res = 0;
        for(int i = 0 ; i < pairs.size(); i++){
            Pair p = pairs.get(i);
            cur += p.num;
            res = Math.max(res, cur);
        }
        System.out.print(res);
    }

    class Pair implements Comparable<Pair>{
        int pos;
        int num;

        public Pair(int pos, int num){
            this.pos = pos;
            this.num = num;
        }

        @Override
        public int compareTo(Pair p){
            if(this.pos == p.pos){
                return this.num - p.num;
            }
            return this.pos - p.pos;
        }
    }
}
