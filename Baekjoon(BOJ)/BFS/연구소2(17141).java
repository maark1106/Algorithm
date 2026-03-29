import java.util.*;
import java.io.*;

class Main{

    /*
    	dfs 조합 + bfs

        virus: 3
        1. 2라고 써진 칸에서 M개를 선택하기
            배열 복사하기
        2. 해당 칸을 3으로 변경하기
        3. bfs를 통해 모든 0, 2 칸을 물들이기
        4. bfs 끝나고 만약 0이나 2가 존재하지 않는다면 flag true로 변경
    */

	int N;
	int M;
	int[][] board;
	List<Pos> storage;
	boolean flag = false;
	int res = Integer.MAX_VALUE;
	int[] dy = {1, -1, 0, 0};
	int[] dx = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		storage = new ArrayList<>();
		board = new int[N][N];
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++){
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 2){
					storage.add(new Pos(i, j));
				}
			}
		}

		List<Pos> list = new ArrayList<>();
		dfs(0, 0, list);
		System.out.println(flag ? res : -1);
	}

	void dfs(int depth, int idx, List<Pos> list){
		if(depth == M){
			bfs(list);
			return;
		}

		for(int i = idx; i < storage.size(); i++){
			list.add(new Pos(storage.get(i).y, storage.get(i).x));
			dfs(depth + 1, i + 1, list);
			list.remove(depth);
		}
	}

	void bfs(List<Pos> list){
		int[][] copyBoard = new int[N][N];
		for(int i = 0 ; i < N; i++){
			for(int j = 0 ; j < N; j++){
				copyBoard[i][j] = board[i][j];
			}
		}

		Queue<QPos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		for(int i = 0 ; i < M; i++){
			Pos cur = list.get(i);
			q.add(new QPos(cur.y, cur.x, 0));
			copyBoard[cur.y][cur.x] = 3;
			visited[cur.y][cur.x] = true;
		}

		int maxTime = 0;
		while(!q.isEmpty()){
			QPos cur = q.poll();
			maxTime = Math.max(cur.time, maxTime);

			for(int i = 0 ; i < 4; i++){
				int yy = cur.y + dy[i];
				int xx = cur.x + dx[i];
				if(yy < 0 || yy >= N || xx < 0 || xx >= N){
					continue;
				}
				if(visited[yy][xx] || copyBoard[yy][xx] == 1){
					continue;
				}
				visited[yy][xx] = true;
				q.add(new QPos(yy, xx, cur.time + 1));
			}
		}

		for(int i = 0 ; i < N; i++){
			for(int j = 0 ; j < N; j++){
				if(board[i][j] != 1 && !visited[i][j]){
					return;
				}
			}
		}
		flag = true;
		res = Math.min(res, maxTime);
	}

	class QPos{
		int y;
		int x;
		int time;

		public QPos(int y, int x, int time){
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	class Pos{
		int y;
		int x;

		public Pos(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
