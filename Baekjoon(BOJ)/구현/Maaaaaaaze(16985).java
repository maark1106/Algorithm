import java.util.*;
import java.io.*;

class Main{

    /*
        백트래킹
        1. depth 5까지 각 depth마다 원본에서 90도씩 회전한 값을 새로운 newBoard에 저장하기
            -> 90도로 회전 : newBoard[j][4 - i] = board[i][j]
        2. 다음 depth가기
        3. depth가 5라면 현재 0, 0, 0 -> 4, 4, 4까지 도달할 수 있는지 bfs로 탐색하기
    */

    int res;
    int[] dk = {0, 0, 0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0, 0, 0};
    int[] dx = {0, 0, 1, -1, 0, 0};
    int[][][] board;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        res = 100000000;
        board = new int[5][5][5];
        for(int k = 0 ; k < 5; k++){
            for(int i = 0 ; i < 5; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j <5; j++){
                    board[k][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        boolean[] visited = new boolean[5];
        int[][][] newBoard = new int[5][5][5];
        setBoard(0, visited, newBoard);

        System.out.println(res == 100000000 ? -1 : res);
    }

    void setBoard(int depth, boolean[] visited, int[][][] newBoard){
        if(depth == 5){
            dfs(newBoard, 0);
            return;
        }

        for(int k = 0 ; k < 5; k++){
            if(!visited[k]){
                visited[k] = true;
                for(int i = 0; i < 5; i++){
                    for(int j = 0 ; j < 5; j++){
                        newBoard[depth][i][j] = board[k][i][j];
                    }
                }
                setBoard(depth + 1, visited, newBoard);
                visited[k] = false;
            }
        }
    }

    void dfs(int[][][] board, int depth){
        if(depth == 5){
            bfs(board);
            return;
        }

        int[][][] newBoard = new int[5][5][5];
        for(int k = 0 ; k < 5; k++){
            for(int i = 0 ; i < 5; i++){
                newBoard[k][i] = board[k][i].clone();
            }
        }

        for(int i = 0; i < 4; i++){
            if(i == 0){ //그대로
                dfs(newBoard, depth + 1);
            }
            else if(i == 1){ //시계 방향 90도
                turn(newBoard, depth);
                dfs(newBoard, depth + 1);
            }
            else if(i == 2){ // 180도
                turn(newBoard, depth);
                dfs(newBoard, depth + 1);
            }
            else if(i == 3){ // 반시계 방향 90도
                turn(newBoard, depth);
                dfs(newBoard, depth + 1);
            }
        }
    }

    void turn(int[][][] board, int k){ // 시계 방향으로 90도 회전
        int[][][] copyBoard = new int[5][5][5];
        for(int i = 0 ; i < 5; i++){
            for(int j = 0 ; j < 5; j++){
                copyBoard[i][j] = board[i][j].clone();
            }
        }

        for(int i = 0 ; i < 5; i++){
            for(int j = 0; j < 5; j++){
                board[k][j][4 - i] = copyBoard[k][i][j];
            }
        }
    }

    void bfs(int[][][] board){
        if(board[0][0][0] == 0){
            return;
        }

        Queue<Pos> q = new LinkedList<>();
        boolean[][][] visited = new boolean[5][5][5];
        q.add(new Pos(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            Pos cur = q.poll();
            if(cur.k == 4 && cur.y == 4 && cur.x == 4){
                res = Math.min(res, cur.cnt);
            }

            for(int i = 0 ; i <6; i++){
                int kk = cur.k + dk[i];
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];

                if(kk < 0 || kk >= 5 || yy < 0 || yy >= 5 || xx < 0 || xx >=5){
                    continue;
                }
                if(visited[kk][yy][xx] || board[kk][yy][xx] == 0){
                    continue;
                }

                visited[kk][yy][xx] = true;
                q.add(new Pos(kk, yy, xx, cur.cnt + 1));
            }
        }
    }

    class Pos{
        int k;
        int y;
        int x;
        int cnt;

        public Pos(int k, int y, int x, int cnt){
            this.k = k;
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
