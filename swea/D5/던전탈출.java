import java.util.*;

public class UserSolution{
	
	final int MAX_GATES = 205;
	int N;
	int mMaxStamina;
	
	int[][] map;
	int[][] gateMap;
	Gate[] gates;
	int[][] adj;
	
	int[][] visited;
	int token;
	
	int[] dy = {1, -1, 0, 0};
	int[] dx = {0, 0, 1, -1};
	
	
	void init(int N, int mMaxStamina, int mMap[][]){
		this.N = N;
		this.mMaxStamina = mMaxStamina;
		this.token = 0;
		
		this.map = new int[N][N];
		this.gateMap = new int[N][N];
		this.visited = new int[N][N];
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				this.map[i][j] = mMap[i][j];
			}
		}
		
		this.gates = new Gate[MAX_GATES];
		this.adj = new int[MAX_GATES][MAX_GATES];
		
		for(int i = 0; i < MAX_GATES; i++) {
			Arrays.fill(this.adj[i], -1);
		}
	}

	void addGate(int mGateID, int mRow, int mCol){
		gates[mGateID] = new Gate(mGateID, mRow, mCol);
		gateMap[mRow][mCol] = mGateID;
		
		token++;
		
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(mRow, mCol, 0));
		visited[mRow][mCol] = token;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(cur.d > 0 && gateMap[cur.y][cur.x] > 0) {
				int otherId = gateMap[cur.y][cur.x];
				if(gates[otherId].active) {
					adj[mGateID][otherId] = cur.d;
					adj[otherId][mGateID] = cur.d;
				}
			}
			
			if(cur.d == mMaxStamina) {
				continue;
			}
			
			for(int i = 0 ; i < 4; i++) {
				int yy = cur.y + dy[i];
				int xx = cur.x + dx[i];
				
				if(yy < 0 || yy >= N || xx < 0 || xx >= N) {
					continue;
				}
				
				if(map[yy][xx] == 0 && visited[yy][xx] != token) {
					visited[yy][xx] = token;
					q.add(new Node(yy, xx, cur.d + 1));
				}
			}
		}
	}

	void removeGate(int mGateID){
		if(gates[mGateID] != null) {
			gates[mGateID].active = false;
			gateMap[gates[mGateID].y][gates[mGateID].x] = 0;
		}
	}

	int getMinTime(int mStartGateID, int mEndGateID){
		int[] dist = new int[MAX_GATES];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[mStartGateID] = 0;
		
		PriorityQueue<DNode> pq = new PriorityQueue<>();
		pq.add(new DNode(mStartGateID, 0));
		
		boolean[] checked = new boolean[MAX_GATES];
		
		while(!pq.isEmpty()) {
			DNode cur = pq.poll();
			
			if(checked[cur.id]) {
				continue;
			}
			checked[cur.id] = true;
			
			if(cur.id == mEndGateID) {
				break;
			}
			
			for(int i = 1; i < MAX_GATES; i++) {
				if(gates[i] != null && gates[i].active && adj[cur.id][i] != -1) {
					if(dist[i] > dist[cur.id] + adj[cur.id][i]) {
						dist[i] = dist[cur.id] + adj[cur.id][i];
						pq.add(new DNode(i, dist[i])); 
					}
				}
			}
		}
		
		return dist[mEndGateID] == Integer.MAX_VALUE ? -1 : dist[mEndGateID];
	}
	
	class Node{
		int y;
		int x;
		int d;
		
		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
	
	class Gate{
		int id;
		int y;
		int x;
		boolean active;
		
		public Gate(int id, int y, int x) {
			this.id = id;
			this.y = y;
			this.x = x;
			this.active = true; 
		}
	}
	
	class DNode implements Comparable<DNode>{
		int id;
		int dist;
		
		public DNode(int id, int dist) {
			this.id = id;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(DNode d) {
			return Integer.compare(this.dist, d.dist);
		}
	}
}
