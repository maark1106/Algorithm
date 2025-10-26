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
        int M = Integer.parseInt(st.nextToken());

        long[] guitar = new long[N];

        for(int i = 0 ; i <N; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String check = st.nextToken();

            for(int j = 0; j < M; j++){
                if(check.charAt(j) == 'Y'){
                    guitar[i] |= (1L << j);
                }
            }
        }

        int cnt = 0;
        int minGuitar = Integer.MAX_VALUE;
        for(int i = 0 ; i < (1 << N); i++){ //N개의 조합의 수를 구하기
            long total = 0L;

            for(int j = 0; j < N; j++){
                if((i & (1 << j) ) != 0){
                    total |= guitar[j];
                }
            }

            if(Long.bitCount(total) > cnt){
                cnt = Long.bitCount(total); //칠 수 있는 곡의 수
                minGuitar = Integer.bitCount(i); // 칠 수 있는 곡이 더 크면 무조건 갱신
            }
            else if(Long.bitCount(total) == cnt
                    && minGuitar > Integer.bitCount(i)){
                minGuitar = Integer.bitCount(i);
            }
        }

        System.out.print(cnt == 0 ? -1 : minGuitar);
    }
}
