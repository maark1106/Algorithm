
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    int[][] board;
    int result;
    boolean[] visited = new boolean[12];

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        board = new int[12][12];
        while(T-- > 0){
            result = 0;
            for(int i = 0 ; i < 11;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j =  0 ; j < 11;j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            DFS(0, 0);
            System.out.println(result);

            Arrays.fill(visited, false);
        }
    }

    void DFS(int number, int sum) {
        if(number == 11){
            if(sum > result){
                result = sum;
            }
            return;
        }

        for(int i = 0 ; i < 11;i++){
            if(!visited[i] && board[number][i] != 0){
                visited[i] = true;
                DFS(number + 1, sum + board[number][i]);
                visited[i] = false;
            }
        }
    }
}
