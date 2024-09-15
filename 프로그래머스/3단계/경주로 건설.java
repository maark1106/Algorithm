//DFS
class Solution {
    
    int N;    
    
    int[] dy ={1,-1,0,0};
    int[] dx = {0,0,1,-1};    
    
    public int solution(int[][] board) {
        int answer = 0;
        
        N = board.length;
                
        for(int i = 0 ; i < N;i++){
            for(int j = 0; j< N;j++){
                if(board[i][j] != 1){
                   board[i][j] = Integer.MAX_VALUE - 1000;
                }
            }
        }
        
        
        DFS(board, 0, 0, 0, -1); //board, y, x, sum, dir
        
        answer = board[N - 1][N - 1];
        return answer;
    }
    
    void DFS(int[][]board, int y, int x, int sum, int dir){ 

        if(sum >= board[y][x] + 500){
            return;
        }
        
        board[y][x] = Math.min(board[y][x], sum);
        
        if(y == N - 1 && x == N - 1){
            return;
        }
        
        
        for(int i = 0 ;i < 4; i++){
            int yy = y + dy[i];
            int xx = x + dx[i];
            
            if(yy < 0 || yy >= N || xx < 0 || xx >= N ||
                board[yy][xx] == 1){
                continue;
            }
                               
            if(dir == i || dir == -1){
                DFS(board, yy, xx, sum + 100, i);
            }
            else{
                DFS(board, yy, xx, sum + 600, i);
            }
            
        }
    }
}

//BFS
class Solution {
    
    int[] dy = {1,-1,0,0};
    int[] dx = {0,0, 1, -1};
    
    
    public int solution(int[][] board) {
        int answer = 0;
        
        answer = BFS(board);
        
        return answer;
    }
    
    int BFS(int[][] board){
        int[][][] costBoard = new int[board.length][board.length][4];
        int N = board.length;
        int res = Integer.MAX_VALUE;
        
        for(int i = 0 ; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                for(int k = 0 ; k < 4;k++){
                    costBoard[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0, 0, -1));
        
        while(!q.isEmpty()){
            Pos cur = q.poll();
            if(cur.y == N - 1 && cur.x == N - 1){
                res = Math.min(res, cur.cost);
                continue;
            }
            
            for(int i = 0 ;i < 4; i++){
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];
                
                if(yy < 0 || yy >= N || xx < 0 || xx >= N || board[yy][xx] == 1) {
                    continue;
                }
                
                int curCost = cur.cost;
                if(cur.dir == i || cur.dir == -1){
                    curCost += 100;
                }
                else{
                    curCost += 600;
                }
                
                if(curCost < costBoard[yy][xx][i]){
                    costBoard[yy][xx][i] = curCost;
                    q.add(new Pos(yy, xx, curCost, i));
                }
            }
        }
        
        return res;
    }
    
    
    class Pos{
        int y;
        int x;
        int cost;
        int dir;
        
        Pos(int y, int x, int cost, int dir){
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.dir = dir;
        }
    }
}
