import java.util.*;
import java.io.*;

class Main {

    int[][] board;
    int N;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];

        for(int i = 1; i<=N ; i++){
            String s = br.readLine();
            for(int j = 1; j<=N ;j++){
                if(s.charAt(j - 1) == '1'){
                    board[i][j] = 1;
                }
                else{
                    board[i][j] = 0;
                }
            }
        }

        System.out.print(recursive(1, 1, N, N)); // sY ,sX, eY, eX;
    }

    String recursive(int sY, int sX, int eY, int eX){
        if(sY == eY && sX == eX){
            return "" + board[sY][sX];
        }

        String s1 = recursive(sY, sX, (sY + eY) / 2, (sX + eX) / 2); // 왼쪽 위
        String s2 = recursive(sY, (sX + eX) / 2 + 1, (sY + eY) / 2, eX); // 오른쪽 위
        String s3 = recursive((sY + eY) / 2 + 1, sX, eY, (sX + eX) / 2); // 왼쪽 아래
        String s4 = recursive((sY + eY) / 2 + 1, (sX + eX) / 2 + 1, eY, eX); // 오른쪽 아래

        if(s1.equals(s2) && s1.equals(s3) && s1.equals(s4) && s1.length() == 1){
            return s1;
        }
        else{
            return "(" + s1 + s2 + s3 + s4 + ")";
        }
    }
}
