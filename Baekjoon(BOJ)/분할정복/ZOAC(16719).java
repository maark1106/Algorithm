import java.util.*;
import java.io.*;

class Main {
	/*
	*/

	String s;
	String res;
	boolean[] visited;
	StringBuilder sb;

  public static void main(String[] args) throws Exception{
		new Main().solution();
  }

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();		
		sb = new StringBuilder();

		visited = new boolean[s.length()];	
		dfs(0, s.length() - 1);
		
	}

	void dfs(int left, int right){
		if(left > right){
			return;
		}

		int idx = left;
		for(int i = left + 1; i <= right; i++){
			if(s.charAt(idx) > s.charAt(i)){
				idx = i;
			}
		}			

		visited[idx] = true;
		sb = new StringBuilder();		
		for(int i = 0 ; i < s.length(); i++){
			if(visited[i]){
				sb.append(s.charAt(i));
			}		
		}

		System.out.println(sb.toString());

		dfs(idx + 1, right);
		dfs(left, idx - 1);
	}
	
}
