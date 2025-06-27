package anew_.최소비용구하기2_11779;

import java.util.*;
import java.io.*;

class Main{

    List<Node>[] graph;
    int N;
    int M;
    int start;
    int end;
    int[] prev;
    int[] dist;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        prev = new int[N + 1];
        for(int i = 1; i<= N; i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        dijkstra();
        getResult();
    }

    void getResult(){
        List<Integer> res = new ArrayList<>();

        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = prev[cur];
        }

        System.out.println(dist[end]);
        System.out.println(res.size());
        for(int i = res.size() - 1; i >= 0; i--){
            System.out.print(res.get(i) + " ");
        }
    }

    void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[N + 1];
        Arrays.fill(dist, 1000000000);
        dist[start] = 0;

        pq.add(new Node(start, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[cur.e] < cur.cost){
                continue;
            }

            for(Node next: graph[cur.e]){
                // 최적의 거리보다 현재까지 온 거리 + 다음 노드 거리가 더 작으면
                if(dist[next.e] <= dist[cur.e] + next.cost){
                    continue;
                }

                dist[next.e] = dist[cur.e] + next.cost;
                pq.add(new Node(next.e, dist[next.e]));
                prev[next.e] = cur.e;
            }
        }
    }


    class Node implements Comparable<Node>{
        int e;
        int cost;

        public Node(int e, int cost){
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n){
            return this.cost - n.cost;
        }
    }
}
