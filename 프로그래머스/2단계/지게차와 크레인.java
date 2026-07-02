import java.util.*;

/*
    풀이
    1. 단일 알파벳이 나오면 남아있는 배열을 전체 탐색
        - 해당 알파벳 4면중 하나가 범위 벗어난다면 " "로 변환
        - 그렇지 않고 " "으로 계속 연결되어 있는 경우 끝 범위가 범위 벗어난다면 " "로 변환
    2. 이중 알파벳이 나오면 그냥 모두 탐색하여 지워버리기
    단 검사할 때 동시에 이뤄져야 하므로 복사해서 해야됨.
    
    시간복잡도
    100 x 50 x 50 = 250000
    
*/

class Solution {
    
    char[][] board;
    int N;
    int M;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        N = storage.length + 2;
        M = storage[0].length() + 2;
        board = new char[N][M];
        for(int i = 0 ; i < N; i++){
            Arrays.fill(board[i], ' ');
        }
        for(int i = 1; i < N - 1; i++){
            for(int j = 1; j < M - 1; j++){
                board[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
        
        for(int i = 0 ; i < requests.length; i++){
            String cur = requests[i];
            if(cur.length() == 2){
                removeAnywhere(cur.charAt(0));
            }
            else{
                removeOutline(cur.charAt(0));
            }
        }
        
        int cnt = 0;
        for(int i = 1; i < N - 1; i++){
            for(int j = 1; j < M - 1; j++){
                if(board[i][j] == ' '){
                    cnt++;
                }
            }
        }
        
        return (N - 2) * (M - 2) - cnt;
    }
    
    void removeAnywhere(char target){
        for(int i = 1; i < N - 1; i++){
            for(int j = 1; j < M - 1; j++){
                if(board[i][j] == target){
                    board[i][j] = ' ';
                }
            }
        }
    }
    
    void removeOutline(char target){
        char[][] copyBoard = new char[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < M; j++){
                copyBoard[i][j] = board[i][j];
            }
        }
        
        searchOut(copyBoard, target);
    }
    
    void searchOut(char[][] copyBoard, char target){
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new Pos(0, 0));
        visited[0][0] = true;
        
        while(!q.isEmpty()){
            Pos cur = q.poll();
            
            for(int i = 0 ; i < 4; i++){
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];
                
                if(yy < 0 || yy >= N || xx < 0 || xx >= M){
                    continue;
                }
                if(visited[yy][xx]){
                    continue;
                }
                if(copyBoard[yy][xx] == target){
                    board[yy][xx] = ' ';
                    visited[yy][xx] = true;
                }
                else if(copyBoard[yy][xx] == ' '){
                    visited[yy][xx] = true;
                    q.add(new Pos(yy, xx));   
                }
            }
        }
    }
    
    class Pos{
        int y;
        int x;
        
        public Pos(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
