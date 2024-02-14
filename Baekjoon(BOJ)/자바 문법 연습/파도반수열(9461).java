package 파도반수열_9461;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		long[] dp = new long[102];
		dp[1] = dp[2] = dp[3] = 1;
		dp[4] = dp[5] = 2;	
		for(int i = 6 ; i <= 100 ;i++) {
			dp[i] = dp[i - 1] + dp[i - 5];
		}
		
		for(int i = 0 ; i < N ; i++) {
			int num = Integer.parseInt(br.readLine());
			sb.append(dp[num] + "\n");
		}
		System.out.println(sb);
		br.close();
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}

}
