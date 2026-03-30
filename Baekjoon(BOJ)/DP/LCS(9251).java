import java.util.*;
import java.io.*;

class Main{

	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s1 = br.readLine();
		String s2 = br.readLine();

		int[][] dp = new int[s1.length() + 1][s2.length() + 1];

		for(int i = 0; i < s1.length(); i++){
			for(int j = 0 ; j < s2.length(); j++){
				if(s1.charAt(i) == s2.charAt(j)){
					dp[i + 1][j + 1] = dp[i][j] + 1;
				}
				else{
					dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}

		System.out.print(dp[s1.length()][s2.length()]);
	}


}

