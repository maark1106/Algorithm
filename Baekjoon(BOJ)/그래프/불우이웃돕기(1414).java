import java.util.*;
import java.io.*;

class Main{

    int N;
    int[] parents;
    List<Edge> edges;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j = 0; j < N; j++){
                char c = s.charAt(j);
                if(c >= 'a' && c <= 'z'){
                    edges.add(new Edge(i, j, c - 'a' + 1));
                }
                else if(c >= 'A' && c <= 'Z'){
                    edges.add(new Edge(i, j, c - 'A' + 27));
                }
            }
        }

        parents = new int[N];
        for(int i = 0 ; i < N; i++){
            parents[i] = i;
        }

        Collections.sort(edges);
        int cnt = 0;
        int res = 0;
        for(int i = 0 ; i < edges.size(); i++){
            Edge cur = edges.get(i);
            if(union(cur.s, cur.e)){
                cnt++;
            }
            else{
                res += cur.dis;
            }

            if(cnt == N - 1){
                for(int j = i + 1; j < edges.size(); j++){
                    res += edges.get(j).dis;
                }
                break;
            }
        }

        System.out.print(cnt == N - 1 ? res : -1);

    }

    int find(int a){
        if(parents[a] == a){
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b){
            return false;
        }

        parents[a] = b;
        return true;
    }

    class Edge implements Comparable<Edge>{
        int s;
        int e;
        int dis;

        public Edge(int s, int e, int dis){
            this.s = s;
            this.e = e;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge e){
            return Integer.compare(this.dis, e.dis);
        }
    }
}
