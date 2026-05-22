import java.io.*;
import java.util.*;
 
 
public class Solution {
 
     
 
    /*
      
        시간 복잡도
        N^2
         
        풀이
        1. 먼저 지뢰를 제외한 모든 칸에 인접한 지뢰가 몇개인지 구한다
            - 지뢰가 있는 곳에서 각 방면 ++
        2. 0인 부분부터 골라서 클릭하기
            - 자기 주위도 visited 처리 하고
            - 만약 또 0이면 q에 넣고 연속적으로 주변 방문 처리하기
        3.0을 다 찾았다면 다시 반복문 돌면서 no 지뢰, no 방문인 부분 ++
             
         
         
     */
     
 
    char[][] board;
    boolean[][] visited;
    int[][] cnt;
    int N;
    int[] dy = {1, 1, 1, -1, -1, -1, 0, 0};
    int[] dx = {0, 1, -1, 0, 1, -1, 1, -1};
 
     
    public static void main(String[] args) throws Exception{
        new Solution().solution();
    }
 
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            board = new char[N][N];
            cnt = new int[N][N];
            visited = new boolean[N][N];
            for(int i = 0 ; i < N; i++) {
                String s = br.readLine();
                for(int j = 0; j < N; j++) {
                    board[i][j] = s.charAt(j);
                }
            }
             
            getBombCnt();
             
            int res = 0;
            for(int i = 0 ; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(board[i][j] != '*' && !visited[i][j]
                            && cnt[i][j] == 0) { // 한번에 여러 개 할 수 있는 애들만
                        bfs(i, j);
                        res++;
                    }
                }
            }
             
            for(int i = 0 ; i < N; i++) { // 0이 아닌 애들은 1개씩 밖에 못하니까 ++
                for(int j = 0; j < N; j++) {
                    if(board[i][j] != '*' && !visited[i][j]) {
                        res++;
                    }
                }
            }
             
            System.out.println("#" + tc + " " + res);
        }
    }
     
    void bfs(int y, int x) {
        Queue<Pos> q = new LinkedList<>();
        visited[y][x] = true;
        q.add(new Pos(y, x));
         
        while(!q.isEmpty()) {
            Pos cur = q.poll();
             
            for(int i = 0 ; i < 8; i++) {
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];
                 
                if(yy < 0 || yy >= N || xx < 0 || xx >= N) {
                    continue;
                }
                 
                if(!visited[yy][xx] && cnt[yy][xx] == 0) {
                    q.add(new Pos(yy, xx));
                }
                visited[yy][xx] = true;
            }
        }
    }
     
     
    void getBombCnt() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] == '*') {
                    continue;
                }
                 
                for(int k = 0; k < 8; k++) {
                    int yy = i + dy[k];
                    int xx = j + dx[k];
                    if(yy < 0 || yy >= N || xx < 0 || xx >= N) {
                        continue;
                    }
                    if(board[yy][xx] == '*') {
                        cnt[i][j]++;
                    }
                }
            }
        }
    }
     
    class Pos{
        int y;
        int x;
         
        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
         
    }
}
