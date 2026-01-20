import java.util.*;
import java.io.*;

class Main{

    /*
        1. dfs로 배열 복사하기
        2. 상하좌우로 움직이기
        3. return 조건 설정
        4. depth가 오면 가장 큰 블럭 검사하기
        5. -> : 오른쪽부터 검사
           <- : 왼쪽부터 검사
            cur에서 끝까지 가기
            벗어나면 멈추고 이동
            크기가 다른 블럭 만나도 멈추고 이동
            크기가 같고 방문을 안했다면 합치기

    */

    int N;
    int res;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][N];
        res = 0;
        for(int i = 0;  i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, board);
        System.out.print(res);
    }

    void dfs(int depth, int[][] board){
        if(depth == 5){
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    if(res < board[i][j]){
                        res = board[i][j];
                    }
                }
            }
            return;
        }

        for(int dir = 0 ; dir < 4; dir++){
            if(dir == 0){
                int[][] newBoard = new int[N][N];
                for(int i = 0 ; i < N; i++){
                    for(int j = 0 ; j < N; j++){
                        newBoard[i][j] = board[i][j];
                    }
                }
                moveLeft(newBoard);
                dfs(depth + 1, newBoard);
            }
            if(dir == 1){
                int[][] newBoard = new int[N][N];
                for(int i = 0 ; i < N; i++){
                    for(int j = 0 ; j < N; j++){
                        newBoard[i][j] = board[i][j];
                    }
                }
                moveRight(newBoard);
                dfs(depth + 1, newBoard);
            }
            if(dir == 2){
                int[][] newBoard = new int[N][N];
                for(int i = 0 ; i < N; i++){
                    for(int j = 0 ; j < N; j++){
                        newBoard[i][j] = board[i][j];
                    }
                }
                moveUp(newBoard);
                dfs(depth + 1, newBoard);
            }
            if(dir == 3){
                int[][] newBoard = new int[N][N];
                for(int i = 0 ; i < N; i++){
                    for(int j = 0 ; j < N; j++){
                        newBoard[i][j] = board[i][j];
                    }
                }
                moveDown(newBoard);
                dfs(depth + 1, newBoard);
            }
        }
    }

    void moveLeft(int[][] board){
        for(int i = 0; i < N; i++){
            boolean[][] visited = new boolean[N][N];
            for(int j = 1; j < N; j++){
                if(board[i][j] == 0){
                    continue;
                }
                int cur = j;

                while(cur > 0 && board[i][cur - 1] == 0){
                    cur--;
                }

                if(cur == 0){ // 끝까지 갔으면 그냥 끝에 두기
                    int temp = board[i][j];
                    board[i][j] = 0;
                    board[i][cur] = temp;
                }
                else{
                    if(board[i][cur - 1] != board[i][j]){ //다르다면 현재 위치 두기
                        int temp = board[i][j];
                        board[i][j] = 0;
                        board[i][cur] = temp;
                    }else{ //같고 방문한적이 없다면 합치기
                        if(!visited[i][cur - 1]){
                            int temp = board[i][cur - 1] * 2;
                            board[i][j] = 0;
                            board[i][cur - 1] = temp;
                            visited[i][cur - 1] = true;
                        }
                        else{ //같지만 방문했다
                            int temp = board[i][j];
                            board[i][j] = 0;
                            board[i][cur] = temp;
                        }
                    }
                }
            }
        }
    }

    void moveRight(int[][] board){
        for(int i = 0; i < N; i++){
            boolean[][] visited = new boolean[N][N];
            for(int j = N - 2; j >= 0; j--){
                if(board[i][j] == 0){
                    continue;
                }
                int cur = j;

                while(cur < N - 1 && board[i][cur + 1] == 0){
                    cur++;
                }

                if(cur == N - 1){ // 끝까지 갔으면 그냥 끝에 두기
                    int temp = board[i][j];
                    board[i][j] = 0;
                    board[i][cur] = temp;
                }
                else{
                    if(board[i][cur + 1] != board[i][j]){ //다르다면 현재 위치 두기
                        int temp = board[i][j];
                        board[i][j] = 0;
                        board[i][cur] = temp;
                    }else{ //같고 방문한적이 없다면 합치기
                        if(!visited[i][cur + 1]){
                            int temp = board[i][cur + 1] * 2;
                            board[i][j] = 0;
                            board[i][cur + 1] = temp;
                            visited[i][cur + 1] = true;
                        }
                        else{
                            int temp = board[i][j];
                            board[i][j] = 0;
                            board[i][cur] = temp;
                        }
                    }
                }
            }
        }
    }

    void moveUp(int[][] board){
        for(int j = 0; j < N; j++){
            boolean[][] visited = new boolean[N][N];
            for(int i = 1; i < N; i++){
                if(board[i][j] == 0){
                    continue;
                }
                int cur = i;

                while(cur > 0 && board[cur - 1][j] == 0){
                    cur--;
                }

                if(cur == 0){ // 끝까지 갔으면 그냥 끝에 두기
                    int temp = board[i][j];
                    board[i][j] = 0;
                    board[cur][j] = temp;
                }
                else{
                    if(board[cur - 1][j] != board[i][j]){ //다르다면 현재 위치 두기
                        int temp = board[i][j];
                        board[i][j] = 0;
                        board[cur][j] = temp;
                    }else{ //같고 방문한적이 없다면 합치기
                        if(!visited[cur - 1][j]){
                            int temp = board[cur - 1][j] * 2;
                            board[i][j] = 0;
                            board[cur - 1][j] = temp;
                            visited[cur - 1][j] = true;
                        }
                        else{
                            int temp = board[i][j];
                            board[i][j] = 0;
                            board[cur][j] = temp;
                        }
                    }
                }
            }
        }
    }

    void moveDown(int[][] board){
        for(int j = 0; j < N; j++){
            boolean[][] visited = new boolean[N][N];
            for(int i = N - 2; i >= 0; i--){
                if(board[i][j] == 0){
                    continue;
                }
                int cur = i;

                while(cur < N - 1 && board[cur + 1][j] == 0){
                    cur++;
                }

                if(cur == N - 1){ // 끝까지 갔으면 그냥 끝에 두기
                    int temp = board[i][j];
                    board[i][j] = 0;
                    board[cur][j] = temp;
                }
                else{
                    if(board[cur + 1][j] != board[i][j]){ //다르다면 현재 위치 두기
                        int temp = board[i][j];
                        board[i][j] = 0;
                        board[cur][j] = temp;
                    }else{ //같고 방문한적이 없다면 합치기
                        if(!visited[cur + 1][j]){
                            int temp = board[cur + 1][j] * 2;
                            board[i][j] = 0;
                            board[cur + 1][j] = temp;
                            visited[cur + 1][j] = true;
                        }
                        else{
                            int temp = board[i][j];
                            board[i][j] = 0;
                            board[cur][j] = temp;
                        }
                    }
                }
            }
        }
    }


}
