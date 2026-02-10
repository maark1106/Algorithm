import java.util.*;
import java.io.*;

class Main{

    int N;
    int M;
    int H;
    boolean[][] ledder;
    int res;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ledder = new boolean[H + 3][N + 3]; // H + 1에 도달해야됨
        res = Integer.MAX_VALUE;

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ledder[a][b] = true;
        }

        dfs(0, 1); // y, cnt, check
        System.out.print(res == Integer.MAX_VALUE ? -1 : res);
    }

    void dfs(int cnt, int y){ // 세로선 = x좌표 = N개
        if(cnt >= res){
            return;
        }
        if(checkLedder()) {
            res = cnt;
            return;
        }
        if(cnt == 3){
            return;
        }

        for(int i = y; i <= H; i++){
            for(int j = 1; j < N; j++){
                if(!ledder[i][j] && !ledder[i][j - 1] && !ledder[i][j + 1]){
                    ledder[i][j] = true;
                    dfs(cnt + 1,i);
                    ledder[i][j] = false;
                }
            }
        }
    }

    boolean checkLedder(){
        for(int j = 1; j <= N; j++){
            int curY = 1;
            int curX = j;
            while(true){
                if(ledder[curY][curX]){
                    curX++;
                }
                else if(ledder[curY][curX - 1]){
                    curX--;
                }
                curY++;
                
                if(curY == H + 1){
                    if(curX != j){
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

}
