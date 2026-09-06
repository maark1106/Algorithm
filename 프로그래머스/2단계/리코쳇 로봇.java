import java.util.*;

class Solution {
    
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    char[][] map;
    int N;
    int M;
    Pos start;
    
    public int solution(String[] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length();
        
        map = new char[N][M];
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R'){
                    start = new Pos(i, j, 0);
                }
            }
        }
        
        answer = bfs();
        
        return answer;
    }
    
    int bfs(){
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.add(start);
        visited[start.y][start.x] = true;
        
        while(!q.isEmpty()){
            Pos cur = q.poll();
            
            for(int i = 0; i < 4; i++){
                int yy = cur.y;
                int xx = cur.x;
                while(true){
                    if(check(yy, xx, i, visited)){
                        yy += dy[i];
                        xx += dx[i];
                    }  
                    else{
                        break;
                    }
                }
                
                if(visited[yy][xx]){
                    continue;
                }
                
                if(map[yy][xx] == 'G'){
                    return cur.dis + 1;
                }
                
                q.add(new Pos(yy, xx, cur.dis + 1));
                visited[yy][xx] = true;
            }
        }
        
        return -1;
    }
    
    boolean check(int yy, int xx, int dir, boolean[][] visited){
        yy += dy[dir];
        xx += dx[dir];
        
        if(yy < 0 || yy >= N || xx < 0 || xx >= M){
            return false;
        }
                
        if(map[yy][xx] == 'D'){
            return false;
        }
        
        return true;
    }
    
    class Pos{
        int y;
        int x;
        int dis;
        
        public Pos(int y, int x, int dis){
            this.y = y;
            this.x = x;
            this.dis = dis;
        }
    }
}
