import java.util.*;
import java.io.*;


public class Main{

    /*
        1. 분리집합으로 문제를 해결한다
        2. 두 집합을 union 할 때 더 적은 비용을 부모로 한다
        3. 1번부터 부모를 찾음
        4. check되지 않았다면 check하고 더함
            (check 됐다면 넘어감)
        5. 만약 k보다 크다면 oh no 출력
    */
    
    int N;
    int M;
    int k;
    int[] parents;
    int[] cost;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
       
        parents = new int[N + 1];
        cost = new int[N + 1];
        for(int i = 1; i<=N;i++){
            parents[i] = i;
        }
        
        for(int i = 0; i < M;i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            union(v, w);
        }
        
        boolean[] visited = new boolean[N + 1];
        int sum = 0;
        for(int i = 1; i <= N;i++){
            int parent = find(i);
            if(!visited[parent]){
                visited[parent] = true;
                sum += cost[parent];
            }
        }
        
        System.out.print(sum > k ? "Oh no" : sum);
    }
    
    
    void union(int v, int w){
        v = find(v);
        w = find(w);
        
        if(v != w){
            if(cost[v] > cost[w]){ // w가 부모가 되어야 함
                parents[v] = w;
            }
            else{
                parents[w] = v;
            }
        }
    }
    
    int find(int v){
        if(parents[v] == v){
            return v;
        }
        
        return parents[v] = find(parents[v]);
    }
}
