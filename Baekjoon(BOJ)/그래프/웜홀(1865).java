import java.util.*;
import java.io.*;

class Main{

    List<Node> nodes;
    int N;
    int M;
    int W;
    int INF = 1000000000;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int TC = Integer.parseInt(st.nextToken());
        for(int t = 0; t < TC; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            nodes = new ArrayList<>();

            for(int i = 0 ; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                nodes.add(new Node(S, E, T));
                nodes.add(new Node(E, S, T));
            }
            for(int i = 0 ; i < W; i++){
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                nodes.add(new Node(S, E, -T));
            }

            bellmanford(0);
        }
    }

    void bellmanford(int start){
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        //from : v, to : w
        for(int i = 1 ; i <= N; i++){
            for(Node node: nodes){
                if(dist[node.to] > dist[node.from] + node.cost){
                    dist[node.to] = dist[node.from] + node.cost;
                }
            }
        }

        for(Node node: nodes){
            if(dist[node.to] > dist[node.from] + node.cost){
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

    class Node{
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }


}
