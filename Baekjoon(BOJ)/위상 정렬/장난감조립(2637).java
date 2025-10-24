package 위상정렬.장난감조립_2637;

import java.util.*;
import java.io.*;

/*
    거꾸로 위상정렬
    N의 완제품이 N - 1, N - 2를 가르키는 형식으로 부품의 개수만큼 더한다
*/

class Main{

    int N;
    int M;
    List<Integer>[] graph;
    int[][] count;
    int[] degree;
    int[] outDegree;

    public static void main(String[] args)throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        count = new int[N + 1][N + 1];
        graph = new ArrayList[N + 1];
        degree = new int[N + 1];
        outDegree = new int[N + 1];
        for(int i = 0 ;i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        //graph[i] : i를 만드려면 ~가 필요함
        // count[i][j] : i를 만드는데 j가 필요한 개수
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            degree[b]++;
            outDegree[a]++;
            count[a][b] = cnt;
        }

        topologySort();
    }

    void topologySort(){
        int[] res = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        res[N] = 1;


        while(!q.isEmpty()){
            int cur = q.poll(); // cur이 더 큼

            for(int next: graph[cur]){
                degree[next]--;
                //이전 부품을 만드려면 더큰 제품만드는데 필요한 개수 x 더 큰 제품 수량
                res[next] += count[cur][next] * res[cur];
                if(degree[next] == 0){
                    q.add(next);
                }
            }
        }

        for(int i = 1; i <= N ;i++){
            if(outDegree[i] == 0) {
                System.out.println(i + " " + res[i]);
            }
        }

    }
}
