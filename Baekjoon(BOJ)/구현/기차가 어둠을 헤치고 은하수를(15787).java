import java.util.*;
import java.io.*;

/*
    1. 1 : i번째 기차, x번째 태우기
       2 : i번째 기차, x번째 하차
       3 : i번째 기차 모두 오른쪽으로
       4 : i번째 기차 모두 왼쪽으로
    2.


*/

class Main{

    int N;
    int M;
    boolean[][] trains;


    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trains = new boolean[N + 1][22];

        for(int i = 0; i < M ;i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int trainNumber = Integer.parseInt(st.nextToken());
            if(command == 1){
                int seat = Integer.parseInt(st.nextToken());
                trains[trainNumber][seat] = true;
            }
            else if(command == 2){
                int seat = Integer.parseInt(st.nextToken());
                trains[trainNumber][seat] = false;
            }
            else if(command == 3){
                for(int j = 20; j > 1; j--){
                    trains[trainNumber][j] = trains[trainNumber][j - 1];
                }
                trains[trainNumber][1] = false;
            }
            else if(command == 4){
                for(int j = 1; j <= 19; j++){
                    trains[trainNumber][j] = trains[trainNumber][j + 1];
                }
                trains[trainNumber][20] = false;
            }
        }

        HashSet<String> list = new HashSet<>();

        for(int i = 1; i<= N;i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 1; j<=20;j++){
                if(trains[i][j]){
                    sb.append("1");
                }
                else{
                    sb.append("0");
                }
            }
            list.add(sb.toString());
        }

        System.out.print(list.size());
    }
}
