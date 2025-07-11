import java.util.*;
import java.io.*;

public class Main{

    int[][] board;
    int[] question;
    int[] dice = {0, 0, 0, 0, 0, 0, 0};
    int N;
    int M;
    int K;
    int curY;
    int curX;
    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        curY = Integer.parseInt(st.nextToken());
        curX = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for(int i = 0 ; i <N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<M;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        question = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K;i++){
            question[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        play();
    }

    void play(){

        for(int i = 0 ; i < K; i++){
            int curDir = question[i];
            int yy = curY + dy[curDir];
            int xx = curX + dx[curDir];

            if(yy < 0 || yy >= N || xx < 0 || xx >= M){
                continue;
            }

            curY = yy;
            curX = xx;
            int temp = dice[6];

            if(curDir == 0){
                dice[6] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = temp;
            }
            else if(curDir == 1){
                dice[6] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = temp;
            }
            else if(curDir == 2){
                dice[6] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[2];
                dice[2] = temp;
            }
            else if(curDir == 3){
                dice[6] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = temp;
            }

            if(board[yy][xx] == 0)    {
                board[yy][xx] = dice[6];
            }
            else{
                dice[6] = board[yy][xx];
                board[yy][xx] = 0;
            }

            System.out.println(dice[1]);
        }
    }
}
