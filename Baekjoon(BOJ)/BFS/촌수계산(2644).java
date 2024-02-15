package 촌수계산_2644;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	int N, M, start, end;
	ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	int[] dist;	
	
	int bfs() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.remove();
			if(cur == end) {
				return dist[end];
			}
			
			for(int next : graph.get(cur)) {
				if(!visited[next]) {
					visited[next] = true;
					dist[next] = dist[cur] + 1;
					queue.add(next);
				}
			}
		}
		return -1;
	}

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());		
		for(int i = 0;i <= N ; i++) {
			graph.add(new ArrayList<>());
		}
		dist = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());

		for(int i = 0 ; i <M ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
				
		System.out.println(bfs());
		
		br.close();
	}
	
	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

}
