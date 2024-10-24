import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    int N;
    int M;
    int[] path;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        path = new int[101];

        for(int i = 0 ; i < N + M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            path[s] = e;
        }

        BFS();
    }

    void BFS() {
        boolean[] visited = new boolean[101];
        Queue<Pos> q = new LinkedList<>();
        visited[1] = true;
        q.add(new Pos(0, 1));

        while(!q.isEmpty()){
            Pos cur = q.poll();

            if(cur.p == 100){
                System.out.println(cur.count);
                break;
            }

            for(int i = 1; i <= 6;i++){
                if(cur.p + i > 100){
                    continue;
                }

                if(visited[cur.p + i]){
                    continue;
                }

                if(path[cur.p + i] != 0){
                    if(!visited[path[cur.p + i]]) {
                        q.add(new Pos(cur.count + 1, path[cur.p + i]));
                        visited[path[cur.p + i]] = true;
                        visited[cur.p + i] = true;
                    }
                }else{
                    q.add(new Pos(cur.count + 1, cur.p + i));
                    visited[cur.p + i] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    class Pos{
        int count;
        int p;

        public Pos(int count, int p) {
            this.count = count;
            this.p = p;
        }
    }
}
