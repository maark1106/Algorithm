class Solution {
    
    List<String> res;
    int endY;
    int endX;    
    int N;
    int M;
    int K;
    int[] dy = {1, 0, 0, -1}; // 아래, 왼, 오, 위
    int[] dx = {0, -1, 1, 0};
    Character[] dir = {'d', 'l', 'r', 'u'};
    boolean flag = false;
    String answer = "";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {         
        endY = r - 1;
        endX = c - 1;
        N = n;
        M = m;
        K = k;
        
        int dis = Math.abs(y - endY) + Math.abs(x - endX);
        if(Math.abs(K - dis) % 2 == 1){
            return "impossible"; 
        }
        
        DFS(x - 1, y - 1, "");     
        if(answer.isEmpty()){
            return "impossible";
        }
        
        return answer;
    }
    
    void DFS(int y, int x, String s){    
        if(flag){
            return;
        }
        
        if(s.length() == K){
            if(y == endY && x == endX){
                answer = s;
                flag = true;
            }
            return;
        }
        
        int dis = Math.abs(y - endY) + Math.abs(x - endX);
        if(dis > K - s.length()){
            return;
        }
        
        for(int i = 0; i < 4; i++){
            int yy = y + dy[i];
            int xx = x + dx[i];
            if(yy < 0 || yy >= N || xx < 0 || xx >= M){ 
                continue;
            }
            
            DFS(yy, xx, s + dir[i]);
        }
    }
}
