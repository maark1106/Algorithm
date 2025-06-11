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
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            Arrays.fill(board[i], 1000000000);
        }

        for(int i = 1; i <= N; i++){
            board[i][i] = 0;
        }

        for(int i = 0; i <M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            board[s][e] = Math.min(dis, board[s][e]);
        }

        for(int k = 1; k <= N; k++){
            for(int j = 1; j <= N; j++){
                for(int i = 1; i <= N; i++){
                    if(board[i][k] + board[k][j] < board[i][j]){
                        board[i][j] = board[i][k] + board[k][j];
                    }
                }
            }
        }

        for(int i = 1; i<= N; i++){
            for(int j = 1; j<= N; j++){
                if (board[i][j] == 1000000000)
                    System.out.print(0 + " ");
                else
                    System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
