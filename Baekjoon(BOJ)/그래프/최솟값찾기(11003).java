package 최솟값찾기_11003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	class Pair{
		int index;
		int value;
		public Pair(int index, int value) {
			this.index = index;
			this.value= value;
		}
	}

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		Deque<Pair> dq= new LinkedList<>();
		int[] result = new int[N + 2];
		for(int i = 1; i<= N ; i++) {
			int cur = Integer.parseInt(st.nextToken());
			while(!dq.isEmpty() && dq.getLast().value > cur) {
				dq.removeLast();
			}
			dq.addLast(new Pair(i, cur));
			if(i - L >= dq.getFirst().index) {
				dq.removeFirst();
			}
			
			result[i] = dq.getFirst().value;
		}
		
		for(int i = 1; i<=N;i++) {
			sb.append(result[i] + " ");
		}
		
		System.out.println(sb);
		br.close();
	}
	
	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

}
