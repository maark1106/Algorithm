class Solution {
    public int solution(String dirs) {
        int answer = 0;
        
        boolean[][][] visited = new boolean[11][11][4];
        
        int x = 5;
        int y = 5;
        for(int i = 0 ; i < dirs.length(); i++){
            int dir = -1;
            int revDir = -1;
            int ny = y;
            int nx = x;
            if(dirs.charAt(i) == 'U' && y < 10){
                dir = 0;
                revDir = 1;
                ny++;
            }
            else if(dirs.charAt(i) == 'D' && y > 0){
                dir = 1;
                revDir = 0;
                ny--;
            }
            else if(dirs.charAt(i) == 'L' && x > 0){
                dir = 2;
                revDir = 3;
                nx--;
            }
            else if(dirs.charAt(i) == 'R' && x < 10){
                dir = 3;
                revDir = 2;
                nx++;
            }else{
                continue;
            }
            
            if(!visited[y][x][revDir] && !visited[ny][nx][dir]){
                answer++;
                visited[y][x][revDir] = true;
                visited[ny][nx][dir] = true;
            }
            
            y = ny;
            x = nx;
        }
        
    
        
        return answer;
    }
}
