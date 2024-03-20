import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    int N;
    int result = Integer.MAX_VALUE;
    int[][] power;
    boolean[] visited;

    void DFS(int depth, int before){
        if(depth == N / 2){
            getDiff();
            return;
        }

        for(int i = before; i < N;i++){
            if(!visited[i]) {
                visited[i] = true;
                DFS(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    private void getDiff() {

        int sumLink = 0;
        int sumStart = 0;
        for(int i = 0 ; i < N;i++){
            for(int j = i + 1;j < N;j++){
                if(visited[i] && visited[j]){
                    sumStart += power[i][j] + power[j][i];
                }
                else if(!visited[i] && !visited[j]){
                    sumLink += power[i][j] + power[j][i];
                }
            }
        }


        result = Math.min(result, Math.abs(sumStart - sumLink));
        if(result == 0){
            System.out.println(0);
            System.exit(0);
        }
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        power = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                power[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0);
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
