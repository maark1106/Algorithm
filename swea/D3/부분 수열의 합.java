import java.util.*;
import java.io.*;

class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // DP 배열: dp[i]는 합이 i가 되는 경우의 수
            // 문제 조건에 따라 K까지만 필요하므로 K + 1 크기로 할당
            int[] dp = new int[K + 1];
            
            // 기본값 설정: 합이 0인 경우의 수는 1가지 (아무것도 선택 안 함)
            dp[0] = 1;

            for (int i = 0; i < N; i++) {
                int num = arr[i];
                // 중복 선택을 방지하기 위해 뒤에서부터 순회
                for (int j = K; j >= num; j--) {
                    dp[j] += dp[j - num];
                }
            }

            System.out.println("#" + tc + " " + dp[K]);
        }
    }
}
