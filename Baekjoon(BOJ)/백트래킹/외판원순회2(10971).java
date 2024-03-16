import java.io.*;
import java.util.*;

public class Main {

    int N;
    int[][] map = new int[11][11];
    boolean[] check = new boolean[11];
    int result = Integer.MAX_VALUE;


    void travel(int before, int sum, int level, int start){
        if(level == N - 1){
            if(map[before][start] != 0){
                result = Math.min(result, sum + map[before][start]);
            }
            return;
        }

        for(int i = 0 ; i < N;i++){
            if(!check[i] && map[before][i] != 0){
                check[i] = true;
                travel(i, sum + map[before][i], level + 1, start);
                check[i] = false;
            }
        }
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < N ; i++){
            check[i] = true;
            travel(i, 0, 0, i);
            check[i] = false;
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
