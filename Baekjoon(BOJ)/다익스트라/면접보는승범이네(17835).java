import java.util.*;
import java.io.*;

class Main{

    int N;
    int M;
    int K;
    List<Node>[] graph;
    Set<Integer> spot;
    long[] dist;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 1; i<= N; i++){
            graph[i] = new ArrayList<>();
        }

        spot = new HashSet<Integer>();
        for(int i = 1; i<= M ; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[v].add(new Node(u, w));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K; i++){
            int k = Integer.parseInt(st.nextToken());
            spot.add(k);
        }


        dijkstra();
        int maxNum = 0;
        long maxDist = 0L;
        for(int i = N; i >= 1; i--){
            if(dist[i] >= maxDist){
                maxDist = dist[i];
                maxNum = i;
            }
        }

        System.out.println(maxNum);
        System.out.println(maxDist);
    }

    void dijkstra(){
        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(Integer num: spot){
            dist[num] = 0;
            pq.add(new Node(num, 0));
        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[cur.e] < cur.dist){
                continue;
            }

            for(Node next: graph[cur.e]){
                if(dist[next.e] > dist[cur.e] + next.dist){
                    dist[next.e] = dist[cur.e] + next.dist;
                    pq.add(new Node(next.e, dist[next.e]));
                }
            }
        }
    }

    class Node implements Comparable<Node>{
        int e;
        long dist;

        public Node(int e, long dist){
            this.e = e;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o){
            return Long.compare(this.dist, o.dist);
        }
    }
}
