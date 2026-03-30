import java.util.*;
import java.io.*;

class Main{

	/*
		    1. distance[][][][] 이동거리
        2. 먼지 방문 수열 구하기
        3. 청소기 ~ 먼지까지 이동거리 더하기
        4. 방문 수열대로 탐색하며 거리 더하기
        5. 만약 갈 수 없다면 바로 return하기

        bfs로 미리 point간 거리 구해두고 dfs로 조합을 만들어 최단 거리 찾기
	*/
	int N;
	int M;
	char[][] board;
	int[][][][] distance;
	List<Pos> dirty;
	Pos start;
	int res;
	int[] dy = {1, -1, 0, 0};
	int[] dx = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while(true){
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			if(N == 0 && M == 0){
				break;
			}
			board = new char[N][M];
			distance = new int[N][M][N][M];
			dirty = new ArrayList<>();

			for(int i = 0 ; i < N; i++){
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for(int j = 0; j < M; j++){
					board[i][j] = s.charAt(j);
					if(board[i][j] == 'o'){
						start = new Pos(i, j, 0);
						board[i][j] = '.';
					}
					if(board[i][j] == '*'){
						dirty.add(new Pos(i, j, 0));
					}
				}
			}

			bfs(start.y, start.x);
			for(int i = 0; i < dirty.size(); i++){
				bfs(dirty.get(i).y, dirty.get(i).x);
			}

			int[] storage = new int[dirty.size()];
			boolean[] visited = new boolean[dirty.size()];
			res = Integer.MAX_VALUE;
			dfs(0, storage, visited);
			System.out.println(res == Integer.MAX_VALUE ? -1 : res);
		}
	}

	void dfs(int depth, int[] storage, boolean[] visited){
		if(depth == dirty.size()){
			int dis = distance[start.y][start.x][dirty.get(storage[0]).y][dirty.get(storage[0]).x];
			if(dis == 0){
				return;
			}
			for(int i = 0 ; i < storage.length - 1; i++){
				Pos prev = dirty.get(storage[i]);
				Pos next = dirty.get(storage[i + 1]);
				int curDis = distance[prev.y][prev.x][next.y][next.x];
				if(curDis == 0){
					return;
				}
				dis += curDis;
			}

			res = Math.min(res, dis);
			return;
		}

		for(int i = 0; i < dirty.size(); i++){
			if(!visited[i]){
				visited[i] = true;
				storage[depth] = i;
				dfs(depth + 1, storage, visited);
				visited[i] = false;
			}
		}
	}

	void bfs(int y, int x){
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new Pos(y, x, 0));
		visited[y][x] = true;

		while(!q.isEmpty()){
			Pos cur = q.poll();

			for(int i = 0 ; i < 4; i++){
				int yy = cur.y + dy[i];
				int xx = cur.x + dx[i];

				if(yy < 0 || yy >= N || xx < 0 || xx >= M){
					continue;
				}
				if(visited[yy][xx] || board[yy][xx] == 'x'){
					continue;
				}

				q.add(new Pos(yy, xx, cur.dis + 1));
				visited[yy][xx] = true;
				distance[y][x][yy][xx] = cur.dis + 1;
			}
		}
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
