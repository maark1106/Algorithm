
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    int N, M;
    char[][] board;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    boolean[] visited = new boolean[30];
    int result = 0;

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                board[i][j] = str.charAt(j);
            }
        }

        visited[board[0][0] - 'A'] = true;
        BFS(0,0, 1);
        System.out.println(result);
    }

    void BFS(int y, int x, int count) {
        if(count >= result){
            result = count;
        }

        for(int i = 0 ; i < 4;i++){
            int yy = y + dy[i];
            int xx = x + dx[i];
            if(yy < 0 || yy >= N || xx < 0 || xx >=M)
                continue;

            if(!visited[board[yy][xx] - 'A']){
                visited[board[yy][xx] - 'A'] = true;
                BFS(yy, xx, count + 1);
                visited[board[yy][xx] - 'A'] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

}
