import java.util.*;
import java.io.*;

class Main{

    int N;
    int M;
    int L;
    int K;
    Pair[] stars;


    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // M이 가로
        N = Integer.parseInt(st.nextToken()); // N이 세로
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        stars = new Pair[K];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars[i] = new Pair(y, x);
        }

        int res = 0;

        for(int i = 0 ; i < K; i++){
            for(int j = 0; j < K; j++) {
                int startY = stars[i].y;
                int startX = stars[j].x;
                res = Math.max(getTotal(startY, startX), res);
            }
        }

        System.out.print(K - res);
    }

    int getTotal(int startY, int startX) {
        int total = 0;
        for(Pair cur: stars){
            if((cur.y >= startY && cur.y <= startY + L)
                && (cur.x >= startX && cur.x <= startX + L)){
                total++;
            }
        }

        return total;
    }

    class Pair{
        int y;
        int x;

        public Pair(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

}
