package 퇴사_14501;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] time = new int[17];
		int[] pay = new int[17];
		for(int i = 1;i <= N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] result = new int[17];
		for(int i = N; i > 0; i--) {
			int pos = i + time[i];
			result[i] = result[i + 1];
			if(pos > N + 1) {
				continue;
			}
			int total = pay[i] + result[pos];					
			result[i] = Math.max(result[i], total);			
		}		

		System.out.println(result[1]);
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}

}
