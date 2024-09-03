import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int N = board.length;
        int M = board[0].length;
        
        int[][] storageBoard = new int[N + 1][M + 1];
        
        for(int i = 0 ; i < skill.length; i++){
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
          
            if(type == 1){
                storageBoard[r1][c1] -= degree;
                storageBoard[r2 + 1][c2 + 1] -= degree;
                storageBoard[r1][c2 + 1] += degree;
                storageBoard[r2 + 1][c1] += degree;
            }    
            else{
                storageBoard[r1][c1] += degree;
                storageBoard[r2 + 1][c2 + 1] += degree;
                storageBoard[r1][c2 + 1] -= degree;
                storageBoard[r2 + 1][c1] -= degree;
            }
            
        }
        
        for(int i = 0; i< N + 1;i++){
            for(int j = 1;j < M + 1;j++){
                storageBoard[i][j] += storageBoard[i][j - 1];
            }
        }
        
        for(int i = 1; i < N + 1;i++){
            for(int j = 0;j < M + 1;j++){
                storageBoard[i][j] += storageBoard[i - 1][j];
            }
        }
            
        for(int i = 0; i< N ;i++){
            for(int j = 0; j< M;j++){
                board[i][j] += storageBoard[i][j];
            }
        }
        
        for(int i = 0; i< N;i++){
            for(int j = 0 ;j<M;j++){
                if(board[i][j] > 0){
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
