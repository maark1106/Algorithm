import java.io.*;
import java.util.*;


public class Solution {
	
	/*
		시간복잡도
		N = 100
		N^3까지 가능
		
		
		
		풀이
		모든 지점에서 bfs 수행
		- 방문한 지점은 제외
		N x M 만들어졌다면 넣기
		
		크기 순으로 정렬하기
		크기가 같다면 y좌표가 작은 순으로 정렬하
	 	
	 	
	 */
	
	int[][] board;
	boolean[][] visited;
	int N;
	int[] dy = {1, -1 , 0, 0};
	int[] dx = {0, 0, 1, -1};
	List<Pos> res;
	
	

	
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
			board = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = new ArrayList<>();
			for(int i = 0 ; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(board[i][j] != 0 && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			
			Collections.sort(res);
			System.out.print("#" + tc + " " + res.size() + " ");
			for(int i = 0 ; i < res.size(); i++) {
				Pos cur = res.get(i);
				System.out.print(cur.y + " " + cur.x + " ");
			}
            System.out.println();
		}
	}
	
	void bfs(int y, int x) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(y, x));
		visited[y][x] = true;
		
		int maxY = y;
		int maxX = x;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for(int i = 0; i < 4; i++) {
				int yy = cur.y + dy[i];
				int xx = cur.x + dx[i];
				
				if(yy < 0 || yy >= N || xx < 0 || xx >= N) {
					continue;
				}
				if(visited[yy][xx] || board[yy][xx] == 0) {
					continue;
				}
				
				visited[yy][xx] = true;
				q.add(new Pos(yy, xx));
				maxY = Math.max(maxY, yy);
				maxX = Math.max(maxX, xx);
			}
		}
		
		res.add(new Pos(maxY - y + 1, maxX - x + 1));
	}
	
	class Pos implements Comparable<Pos>{
		int y;
		int x;
		
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		@Override
		public int compareTo(Pos p) {
			if(this.y * this.x == p.y * p.x) { // 넓이가 같다면 y 행 오름차
				return this.y - p.y;
			}
			return this.y * this.x - p.y * p.x; // 넓이 오름차순
		}
	}
	
	
}

