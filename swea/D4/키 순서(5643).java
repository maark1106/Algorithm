import java.util.*;
import java.io.*;

class Solution{
    
    /*
    	시간 복잡도
        	500 * 500
            
         풀이
         1. 단방향 그래프 만들기
         2. 1~N까지 돌아가면서 out 개수++
         3. 1을 검사중이면 나머지 2 ~ N까지는 in개수에 ++
         4. in + out == N - 1이면 정확한 키++
    */
    
    int N;
    int M;
    int[] in;
    int[] out;
    int[] height;
    List<Integer>[] graph;
    
	public static void main(String args[]) throws Exception	{
		new Solution().solution();
	}
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
       	int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            
            height = new int[N + 1];
            in = new int[N + 1];
			out = new int[N + 1];
            graph = new ArrayList[N + 1];
            for(int i = 1; i <= N; i++){
                graph[i] = new ArrayList<>();
            }
            for(int i = 0 ; i < M ; i++){
                st = new StringTokenizer(br.readLine());
                int small = Integer.parseInt(st.nextToken());
                int big = Integer.parseInt(st.nextToken()); // small -> big / 
                graph[small].add(big);
            }
            
            for(int i = 1; i <= N; i++){
                compareHeight(i);
            }
            
            int res = 0;
            for(int i = 1; i <= N; i++){
                if(in[i] + out[i] == N - 1){
                    res++;
                }
            }
            
            System.out.println("#" + tc + " " + res);
        }
    }
    
    void compareHeight(int start){
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited= new boolean[N + 1000];
        for(Integer next: graph[start]){
            q.add(next);
            out[start]++;
            in[next]++;
            visited[next] = true;
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            for(Integer next: graph[cur]){
                if(!visited[next]){
                    q.add(next);
                    out[start]++;
                    in[next]++;
                    visited[next] = true;
                }
            }
        }
    }
}
