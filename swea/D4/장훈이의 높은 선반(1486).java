import java.io.*;
import java.util.*;
 
public class Solution {
     
    /*
         
        시간 복잡도
        2^N
         
        풀이
        1. 더할 수 있는 모든 조합을 구하기
            -> dfs
            무조건 완탐해야 모든 케이스 구할 수 있음
        2. 이상인 것중에 가장 작은 거 구하기
         
         
     */
     
 
    int N;
    int B;
    int[] height;
    int res;
 
     
    public static void main(String[] args) throws Exception{
        new Solution().solution();
    }
 
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        int T = Integer.parseInt(st.nextToken());
         
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            height = new int[N];
             
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }
             
            res = Integer.MAX_VALUE;
            dfs(0, 0);
            System.out.println("#" + tc + " " + res);
        }
 
         
         
    }
     
    void dfs(int depth, int sum) {
        if(depth == N) {
            if(sum >= B) {
                res = Math.min(res, sum - B);
            }
            return;
        }
         
        dfs(depth + 1, sum);
        dfs(depth + 1, sum + height[depth]);
    }
     
     
}
