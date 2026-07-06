import java.util.*;
import java.io.*;

class Solution{
    
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
	int[] dyC = {1, -1, 1, -1};
    int[] dxC = {1, 1, -1, -1};
    
	public static void main(String args[]) throws Exception{
        new Solution().solution();
	}
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] board = new int[N][N];
            
            for(int i = 0 ; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int res = 0;
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    int cnt = getKillCnt(i, j, board, N, M);
                    res = Math.max(res, cnt);
                    int cntCross = getKillCntCross(i, j, board, N, M);
                    res = Math.max(res, cntCross);
                }
            }
            
            System.out.println("#" + tc + " " + res);
        }
    }
    
    int getKillCnt(int y, int x, int[][] board, int N, int M){
     	int cnt = board[y][x];
        
        for(int i = 0 ; i < 4; i++){
            int curY = y;
            int curX = x;
            
            for(int j = 0; j < M - 1; j++){
                curY += dy[i];
                curX += dx[i];
                if(curY < 0 || curY >= N || curX < 0 || curX >= N){
                    break;
                }
                cnt += board[curY][curX];
            }
        }
        return cnt;
    }
    
    int getKillCntCross(int y, int x, int[][] board, int N, int M){
     	int cnt = board[y][x];
        
        for(int i = 0 ; i < 4; i++){
            int curY = y;
            int curX = x;
            
            for(int j = 0; j < M - 1; j++){
                curY += dyC[i];
                curX += dxC[i];
                if(curY < 0 || curY >= N || curX < 0 || curX >= N){
                    break;
                }
                cnt += board[curY][curX];
            }
        }
        return cnt;
    }
    
}
