import java.util.*;
import java.io.*;

class Main{

    List<ArrayList<Integer>> height;
    int[] degrees;
    int N;
    int M;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }


    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        height = new ArrayList<>();
        degrees = new int[N + 1];
        for(int i = 0; i <= N; i++){
            height.add(new ArrayList<>());
        }

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            height.get(s).add(e);
            degrees[e]++;
        }

        topologySort();
    }

    void topologySort(){
        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();

        for(int i = 1; i<= N; i++){
            if(degrees[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            res.add(cur);
            for(Integer next: height.get(cur)){
                degrees[next]--;
                if(degrees[next] == 0){
                    q.add(next);
                }
            }
        }

        for(Integer num: res){
            System.out.print(num + " ");
        }
    }
}
