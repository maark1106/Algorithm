import java.util.*;
import java.io.*;
 
class Solution{
	public static void main(String args[]) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringBuilder sb = new StringBuilder(); 
 
		for(int tc = 1; tc <= 10; tc++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int[][] board = new int[16][16];
			
			Pos start = null;
			Pos end = null;
			for(int i = 0 ; i < 16; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for(int j = 0; j < 16; j++) {
					board[i][j] = s.charAt(j) - '0';
					if(board[i][j] == 2) {
						start = new Pos(i, j);
					}
					else if(board[i][j] == 3) {
						end = new Pos(i, j);
					}
				}
			}
			
			int res = bfs(start, end, board);
			sb.append("#" + tc + " " + res + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int bfs(Pos start, Pos end, int[][] board) {
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[16][16];
		int[] dy = {1, -1, 0, 0};
		int[] dx = {0, 0, 1, -1};
		
		visited[start.y][start.x] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int yy = cur.y + dy[i];
				int xx = cur.x + dx[i];
				
				if(yy < 0 || yy >= 16 || xx < 0 || xx >=16) {
					continue;
				}
				
				if(visited[yy][xx] || board[yy][xx] == 1) {
					continue;
				}
				
				if(board[yy][xx] == 3) {
					return 1;
				}
				
				visited[yy][xx] = true;
				q.add(new Pos(yy, xx));
			}
		}
		
		return 0;
	}
	
	static class Pos{
		int y;
		int x;
		
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
