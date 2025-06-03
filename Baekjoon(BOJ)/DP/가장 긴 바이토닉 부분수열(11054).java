import java.util.*;
import java.io.*;

class Main {

	/*
		1 8 16 2 3 4 5 30 2 1

		1. 일단 가장 큰 수를 구하기
		2. 처음부터 가장 큰 수까지는 dp를 활용하여 최장 길이 구하기
		3. 반복문을 거꾸로 탐색하면서 자기보다 처음으로 작은거 + 1
		4. 그 이후부터는 자기 - 1 ~ 가장 큰 수까지 탐색하면서 자기보다 처음으로 큰거 + 1하기
	*/

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 2];
        int[][] dp = new int[N + 2][2];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<= N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++){
            int cur = arr[i]; // cur보다 처음으로 작은거 찾아야함
            for(int j = i; j >= 0; j--){
                if(cur > arr[j]){
                    dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
                }
            }
        }

        for(int i = N; i >= 1; i--){
            int cur = arr[i]; // cur보다 처음으로 큰 찾아야함
            for(int j = i + 1; j <= N + 1; j++){
                if(cur > arr[j]){
                    dp[i][1] = Math.max(dp[i][1], dp[j][1] + 1);
                }
            }
        }

        int res = 0;
        for(int i = 1; i<= N; i++){
            res = Math.max(res, dp[i][0] + dp[i][1] - 1);
        }
        System.out.print(res);

    }

}
