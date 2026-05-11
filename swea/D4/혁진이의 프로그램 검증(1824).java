import java.util.*;
import java.io.*;

/*
	시간 복잡도
    2 x 20 x 4 x 16
    
	1. 처음 위치를 q에 넣는다
    2. visited[y][x][dir][num]을 방문한 적이 있으면 제외
    3. ?면 4 방향을 모두 넣는다
    4. q가 끝나기 전에 @에 도달하면 YES
*/

class Solution{
    
    char[][] board;
    int N;
    int M;
    boolean[][][][] visited;
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    
	public static void main(String args[]) throws Exception{
		new Solution().solution();
	}
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new char[N][M];
            visited = new boolean[N][M][4][16];
            for(int i = 0 ; i < N; i++){
                String s = br.readLine();
                for(int j = 0 ; j  <M; j++){
                    board[i][j] = s.charAt(j);
                }
            }
            
            boolean flag = move();
            if(flag){
                System.out.println("#" + tc + " YES");
            }
            else{
				System.out.println("#" + tc + " NO");
            }
        }
    }
    
    boolean move(){
        // 상, 하, 좌, 우
        // 0   1   2   3
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0, 3, 0));
        visited[0][0][3][0] = true;
        boolean flag = false;
        
        while(!q.isEmpty()){
            Pos cur = q.poll();
            int y = cur.y;
            int x = cur.x;
            int dir = cur.dir;
            int mem = cur.mem;
            
            if(board[y][x] == '<'){
            	dir = 2;    
            }
            else if(board[y][x] == '>'){
            	dir = 3;    
            }
            else if(board[y][x] == '^'){
            	dir = 0;    
            }
            else if(board[y][x] == 'v'){
            	dir = 1;    
            }
            else if(board[y][x] == '_'){
            	dir = (mem == 0 ? 3 : 2);    
            }
            else if(board[y][x] == '|'){
            	dir = (mem == 0 ? 1 : 0);     
            }
            else if(board[y][x] >= '0' && board[y][x] <= '9'){
            	mem = board[y][x] - '0';    
            }
            else if(board[y][x] == '+'){
            	mem = (mem + 1) % 16; 
            }
            else if(board[y][x] == '-'){
            	mem = (mem + 15) % 16;   
            }
            else if(board[y][x] == '@'){
            	flag = true;
                break;
            }
            // 다음 가기 전 방문 검사
            int yy = (y + dy[i] + N) % N;
			int xx = (x + dx[i] + M) % M;
            
            if(board[y][x] == '?'){
            	for(int i = 0 ; i < 4 ; i++){
                    yy = (y + dy[i] + N) % N;
                    xx = (x + dx[i] + M) % M;
                    
                    if(!visited[yy][xx][i][mem]){
                        visited[yy][xx][i][mem] = true;
                        q.add(new Pos(yy, xx, i, mem));
                    }
                }
            }
            else{
                if(!visited[yy][xx][dir][mem]){
                    visited[yy][xx][dir][mem] = true;
                    q.add(new Pos(yy, xx, dir, mem));
                }
            }
        }
        
        return flag;
    }
    
    class Pos{
        int y;
        int x;
        int dir;
        int mem;
        
        public Pos(int y, int x, int dir, int mem){
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.mem = mem;
        }
    }
}
