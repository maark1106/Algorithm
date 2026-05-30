import java.util.*;
import java.io.*;

class Solution{
    
    /*
    	시간 복잡도
        N^N -> 시간초과
        
        N x (N - 1) x (N - 2) ... 16
        1 2 3 4.. 16
        
        N^3으로 풀 수 있지 않을까?
        우선순위 큐로 check
        
        13 99 50
        12 70 100
        25 60 100
        
        풀이
		dp
        
    */
    
	public static void main(String args[]) throws Exception{
        new Solution().solution();
	}
    
    double res;
    int N;
    double[][] percent;
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            percent = new double[N][N];
            
            for(int i = 0 ; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N; j++){
                    percent[i][j] = (double)Integer.parseInt(st.nextToken()) * 0.01;
                }
            }
            
            res = 0.0;
            
            boolean[]visited = new boolean[N];
			dfs(0, visited, 1.0);
            
            System.out.printf("#%d %.6f\n", tc, res * 100);
        }
    }
    
    void dfs(int depth, boolean[] visited, double p){
        if(depth == N){
            res = Math.max(res, p);
            return;
        }
        
        if(p < res){
            return;
        }
        
        for(int j = 0 ; j < N; j++){
            if(!visited[j] && percent[depth][j] != 0){
                visited[j] = true;
                dfs(depth + 1, visited, p * percent[depth][j]);
                visited[j] = false;
            }
        }
    }
}
