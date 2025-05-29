import java.util.*;

class Solution {
    
    Character[][] boards;    
    int N;
    int M;
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        N = m;
        M = n;
        
        boards = new Character[N][M];
        for(int i = 0 ; i < board.length; i++){
            for(int j = 0 ; j < board[i].length(); j++){
                boards[i][j] = board[i].charAt(j);
            }
        }
        
        int res = 0;
        while(true){                        
            int cnt = checkErase();
            if(cnt == 0){
                break;
            }
            res += cnt;
            moveDown();
        }
        
        
        return res;
    }
    
    void moveDown(){
        for(int i = 0; i < M; i++){
            for(int j = N - 1; j >= 0;j--){
                if(!boards[j][i].equals('*')){
                    char target = boards[j][i];
                    int idx = j;
                    boards[j][i] = '*';
                    
                    while(idx < N && boards[idx][i].equals('*')){
                        idx++;
                    }
                    boards[idx - 1][i] = target;
                }
            }
        }
    }
    
    int checkErase(){
        int cnt = 0;
        List<int[]> store = new ArrayList<>();
        
        for(int i = 0; i < N - 1; i++){
            for(int j = 0 ; j < M - 1; j++){
                if(boards[i][j].equals(boards[i + 1][j]) && boards[i][j].equals(boards[i][j + 1])
                  && boards[i][j].equals(boards[i + 1][j + 1])){
                    store.add(new int[]{i ,j});   
                    store.add(new int[]{i + 1,j});   
                    store.add(new int[]{i ,j + 1});   
                    store.add(new int[]{i + 1,j + 1});   
                }
            }
        }
        
        for(int i = 0; i < store.size(); i++){
            int[] cur = store.get(i);
            if(!boards[cur[0]][cur[1]].equals('*')){
                cnt++;
            }
            boards[cur[0]][cur[1]] = '*';
        }
        
        return cnt;
    }
}
