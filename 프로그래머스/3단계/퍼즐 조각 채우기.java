import java.util.*;

/*
    풀이
    1. bfs로 인접한 도형 2 ~ 고유 도형 넘버 부여
    2. 4방향으로 돌려가며 도형 파악하기
    3. 방문하지 않은 도형 번호이고 game_board에서 그대로 넣을 수 있는 지점이 있다면 넣기
    4. 마지막으로 그 도형의 테두리가 0이 인접한 곳이 있는지 확인 
    
    0 1 2
 0  1 0 1 
 1  1 1 0
 2  0 0 0
 
 prev   new
 i j    i j
 0 0 -> 0 2
 1 0 -> 0 1 
 2 0 -> 0 0
 
 newBoard[j][N - 1 - i] = board[i][j];
*/

class Solution {
    
    int N;
    int num;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    HashSet<Integer> endNumber;
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        
        N = table.length;
        num = 2;
        endNumber = new HashSet<>();
        
        boolean[][] visited = new boolean[N][N];
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                if(table[i][j] == 1){
                    changeNumber(table, i, j); // 고유 번호 부여하기
                    num++;
                }
            }
        }
        
        for(int k = 0; k < 4; k++){
            if(k >= 1){ // 회전하기
                int[][] spinTable = new int[N][N];
                
                for(int i = 0 ; i < N; i++){
                    for(int j = 0 ; j < N; j++){
                        spinTable[j][N - 1 - i] = table[i][j];
                    }
                }
                table = spinTable;
            }
            
            //처리가 아니라 현재 한 점을 기준으로 검사했다면 해당 방향에서는 어떠한 기준점을 잡아도 넣을 수 없음
            //다음 회전 때 검사해야 함
            HashSet<Integer> numberVisited = new HashSet<>();
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    //도형이 있는 칸이고 처리하지 않은 도형이라면 
                    int number = table[i][j];
                    if(number != 0 && !endNumber.contains(number)
                                    && !numberVisited.contains(number)){
                        numberVisited.add(number);
                        List<Pos> curPos = new ArrayList<>();
                        // i, j를 상대좌표 0, 0 기준으로 처리
                        getPos(table, curPos, i, j, number);
                        
                        boolean available = checkGameBoard(game_board, curPos, number);
                        if(available){
                            endNumber.add(number);
                            answer+= curPos.size();
                        }
                    }
                }
            }
        }
        
        return answer;
    }
    
    boolean checkGameBoard(int[][] game_board, List<Pos> curPos, int number){
        
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(game_board[i][j] != 0){ 
                    continue;
                }
                // 모든 시작 가능한 지점을 검사해서 놓을 수 있는지 확인
                if(!checkEmpty(i, j, game_board, curPos)){
                    continue;
                }
                
                //만약 통과했다면 실제로 놓아보기
                putBoard(i, j, game_board, curPos, number);
                //놓았다면 핏하게 놓았는지 검토
                if(!checkFitBoard(i, j, game_board, curPos, number)){
                    //핏하지 않다면 다시 0으로 되돌리기
                    reversePutBoard(i, j, game_board, curPos, number);  
                }
                else{ //핏하다면 확정하기
                    return true;
                }
            }
        }
        
        return false;
    }
    
    void putBoard(int y, int x, int[][] game_board, List<Pos> curPos, int number){
        for(Pos cur: curPos){
            int yy = y + cur.y;
            int xx = x + cur.x;            
            game_board[yy][xx] = number;
        }
    }
    
    void reversePutBoard(int y, int x, int[][] game_board, List<Pos> curPos, int number){
        for(Pos cur: curPos){
            int yy = y + cur.y;
            int xx = x + cur.x;            
            game_board[yy][xx] = 0;
        }
    }
    
    boolean checkFitBoard(int y, int x, int[][] game_board, List<Pos> curPos, int number){
        for(Pos cur: curPos){
            int yy = y + cur.y;
            int xx = x + cur.x;            
            
            for(int i = 0 ; i < 4; i++){
                int ny = yy + dy[i];
                int nx = xx + dx[i];
                
                if(ny < 0 || ny >= N || nx < 0 || nx >= N){
                    continue;
                }
                
                if(game_board[ny][nx] == 0){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    boolean checkEmpty(int y, int x, int[][] game_board, List<Pos> curPos){
        for(Pos cur: curPos){
            int yy = y + cur.y;
            int xx = x + cur.x;
            
            if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                return false;
            }
            if(game_board[yy][xx] != 0){
                return false;
            }
        }
        
        return true;
    }
    
    void getPos(int[][] table, List<Pos> curPos, int y, int x, int number){
        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(y, x));
        boolean[][] visited = new boolean[N][N];
        visited[y][x] = true;
        curPos.add(new Pos(0, 0));
        
        while(!q.isEmpty()){
            Pos cur = q.poll();
            
            for(int i = 0 ; i < 4; i++){
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];
                
                if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                    continue;
                }
                
                if(!visited[yy][xx] && table[yy][xx] == number){
                    visited[yy][xx] = true;
                    q.add(new Pos(yy, xx));
                    curPos.add(new Pos(yy - y, xx - x));
                }
            }
        }
    }

    void changeNumber(int[][] table, int y, int x){
        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(y, x));
        table[y][x] = num;
        
        while(!q.isEmpty()){
            Pos cur = q.poll();
            
            for(int i = 0 ; i < 4; i++){
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];
                
                if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                    continue;
                }
                
                if(table[yy][xx] == 1){
                    q.add(new Pos(yy, xx));
                    table[yy][xx] = num;
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
