package 문자열집합_14425;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashSet<String> lists = new HashSet<>();
		for(int i = 0 ; i <N ; i++) {
			String str = br.readLine();
			lists.add(str);
		}
		
		int result = 0;
		for(int i = 0; i < M; i++) {
			String str = br.readLine();
			if(lists.contains(str)) {
				result++;
			}
		}
		
		System.out.println(result);
		br.close();
	}
	
	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

}
