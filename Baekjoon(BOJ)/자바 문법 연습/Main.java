package 듣보잡_1764;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashSet<String> names = new HashSet<>();
		for(int i = 0 ; i < N ; i++) {
			String name = br.readLine();
			names.add(name);
		}
		
		ArrayList<String> result = new ArrayList<>();
		for(int i = 0 ; i < M;i++) {
			String name = br.readLine();
			if(names.contains(name)) {
				result.add(name);
			}
		}
		
		Collections.sort(result);
		sb.append(result.size() + "\n");
		for(int i = 0 ; i < result.size(); i++) {
			sb.append(result.get(i) + "\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	
	public static void main(String[] args) throws Exception {
		new Main().solution();
	}

}
