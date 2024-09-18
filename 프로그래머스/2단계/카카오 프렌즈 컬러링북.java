import java.util.*;

class Solution {
    
    int[] dy = {1,-1,0,0};
    int[] dx = {0, 0, -1, 1};
    boolean[][] visited;
    int N;
    int M;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        N = m;
        M = n;

        visited = new boolean[N][M];
        
        for(int i = 0 ;i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visited[i][j] && picture[i][j] != 0){
                    int count = BFS(picture, i, j);
                    maxSizeOfOneArea = Math.max(count, maxSizeOfOneArea);
                    numberOfArea++;
                }
            }
        }
                
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    int BFS(int[][] picture, int y, int x){
        visited[y][x] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        
        int cnt = 0;                        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            cnt++;
            
            for(int i = 0 ; i < 4; i++){
                int yy = cur[0] + dy[i];
                int xx = cur[1] + dx[i];
                
                if(yy < 0 || yy >= N || xx < 0 || xx >= M){
                    continue;
                }
                
                if(visited[yy][xx] || picture[yy][xx] != picture[y][x]){
                    continue;
                }
                
                visited[yy][xx] = true;
                q.add(new int[]{yy, xx});
            }
        }
        
        return cnt;
    }
}
