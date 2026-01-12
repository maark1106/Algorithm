import java.util.*;
import java.io.*;

class Main{

    /*
        1. 한번에 구해야 함.

     */

    int[][] cycle;
    List<int[]> storage;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        cycle = new int[4][8];
        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j = 0; j <= 7; j++){
                cycle[i][j] = s.charAt(j) - '0';
            }
        }
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            move(num - 1, dir);
        }

        int res = 0;
        if(cycle[0][0] == 1){
            res += 1;
        }
        if(cycle[1][0] == 1){
            res += 2;
        }
        if(cycle[2][0] == 1){
            res += 4;
        }
        if(cycle[3][0] == 1){
            res += 8;
        }

        System.out.print(res);
    }

    void move(int num, int dir){
        storage = new ArrayList<>();
        storage.add(new int[]{num, dir});
        leftMove(num, -dir);
        rightMove(num, -dir);
        for(int i = 0 ; i < storage.size(); i++){
            int[] cur = storage.get(i);
            turn(cur[0], cur[1]);
        }
    }

    void leftMove(int num, int dir){
        if(num - 1 < 0){
            return;
        }

        if(cycle[num - 1][2] == cycle[num][6]){
            return;
        }
        storage.add(new int[]{num - 1, dir});
        leftMove(num - 1, -dir);
    }

    void rightMove(int num, int dir){
        if(num + 1 > 3){
            return;
        }

        if(cycle[num][2] == cycle[num + 1][6]){
            return;
        }

        storage.add(new int[]{num + 1, dir});
        rightMove(num + 1, -dir);
    }

    void turn(int num, int dir){
        if(dir == 1){//시계방향
            int temp = cycle[num][7];
            for(int i = 7; i >= 1; i--){
                cycle[num][i] = cycle[num][i - 1];
            }
            cycle[num][0] = temp;
        }
        if(dir == -1){//시계방향
            int temp = cycle[num][0];
            for(int i = 0; i <= 6; i++){
                cycle[num][i] = cycle[num][i + 1];
            }
            cycle[num][7] = temp;
        }
    }

}
