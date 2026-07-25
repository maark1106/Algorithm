import java.util.*;
import java.io.*;

/*
    풀이
    N x N 격자판에 연속된 K칸의 차이의 최소값
    근데 갈 수록 높이가 높아지는 경로
    
    각 칸에서 K만큼 오름차순으로 갈 수 있는 경로 구하기 -> 계속 이어가기
    한번이라도 방문하면 그 지점에서 갈 수 있는 경로를 구했으므로 방문 x
    -> 바로 return 해서 값 업데이트 해주기 
    

*/

public class Main {

    static int N;
    static int K;
    static int[][] board;
    static int[][][] dp;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static final int MAX = 1000000000;
    

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        visited = new boolean[N][N];
        dp = new int[N][N][K + 1]; // 해당 좌표에서 k번 이동했을 떄 최댓값

        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < N; j++){
                for(int k = 0; k <= K; k++){
                    dp[i][j][k] = MAX; // 도달 못함
                }
            }
        }

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(!visited[i][j]){
                    dfs(i, j);
                }
            }
        }

        int res = MAX;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(dp[i][j][K - 1] != MAX){
                    res = Math.min(res, dp[i][j][K - 1]);
                }
            }
        }

        System.out.print(res == MAX ? -1 : res);
    }

    static int[] dfs(int y, int x){
        if(visited[y][x]){
            return dp[y][x];
        }
        
        visited[y][x] = true;
        for(int i = 0 ; i < 4; i++){
            int yy = y + dy[i];
            int xx = x + dx[i];

            if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                continue;
            }

            if(board[y][x] >= board[yy][xx]){
                continue;
            }

            int[] prevList = dfs(yy, xx);
            dp[y][x][1] = Math.min(dp[y][x][1], board[yy][xx] - board[y][x]);
            int max = board[yy][xx] - board[y][x];
            for(int j = 1; j < K - 1; j++){ // 이전 거 1칸은 현재에서는 2칸
                if(prevList[j] == MAX){
                    break;
                }                
                max = Math.max(max, prevList[j]); // 현재 i + 1번 이동 == 다음 칸의 i번 이동
                dp[y][x][j + 1] = Math.min(dp[y][x][j + 1], max);
            }
        }

        return dp[y][x];
    }
}
