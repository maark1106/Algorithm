import java.util.*;
import java.io.*;

class Main{

    /*
        1. dfs 수행
        2. 네 방향으로 탐색하기(이전 방향에서 왔다면 제외)
        3. <-이면 왼쪽부터, ->이면 오른쪽부터, 위면 위쪽부터 , 아래면 아래쪽부터 탐색
        4. 벽이나 구슬에 막히기 전까지 이동하기
        5. 10번이면 return하기
        6. 최솟값 출력
        7. 또한 파랑, 빨강 동시에 빠져도 안됨
    */

    int N, M;
    int res;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] board = new char[N][M];

        Circle red = new Circle(0, 0);
        Circle blue = new Circle(0, 0);
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j =0 ; j < M;j++){
                if(s.charAt(j) == 'R'){
                    red = new Circle(i, j);
                    board[i][j] = '.';
                }
                else if(s.charAt(j) == 'B'){
                    blue = new Circle(i, j);
                    board[i][j] = '.';
                }
                else{
                    board[i][j] = s.charAt(j);
                }
            }
        }

        res = 11;
        dfs(board, 1, -1, red, blue); // dir
        System.out.println(res == 11 ? -1 : res);
    }

    void dfs(char[][] board, int depth, int prevDir, Circle red, Circle blue){
        if(depth == 11){
            return;
        }

        for(int i = 0 ; i < 4; i++){
            if(i == prevDir){
                continue;
            }

            if(i == 0){ // <-
                Circle newRed = new Circle(red.y, red.x);
                Circle newBlue = new Circle(blue.y, blue.x);
                if(!moveLeft(board, newRed, newBlue, depth)){
                    dfs(board, depth + 1, i, newRed, newBlue);
                }
            }
            else if(i == 1){ // ->
                Circle newRed = new Circle(red.y, red.x);
                Circle newBlue = new Circle(blue.y, blue.x);
                if(!moveRight(board, newRed, newBlue, depth)){
                    dfs(board, depth + 1, i, newRed, newBlue);
                }
            }
            else if(i == 2){ // 위
                Circle newRed = new Circle(red.y, red.x);
                Circle newBlue = new Circle(blue.y, blue.x);
                if(!moveUp(board, newRed, newBlue, depth)){
                    dfs(board, depth + 1, i, newRed, newBlue);
                }
            }
            else if(i == 3){ // 아래
                Circle newRed = new Circle(red.y, red.x);
                Circle newBlue = new Circle(blue.y, blue.x);
                if(!moveDown(board, newRed, newBlue, depth)){
                    dfs(board, depth + 1, i, newRed, newBlue);
                }
            }
        }
    }


    boolean moveLeft(char[][] board, Circle red, Circle blue, int depth){
        boolean redFlag = false;
        boolean blueFlag = false;

        if(red.x <= blue.x){ // red먼저
            while(board[red.y][red.x - 1] == '.' &&
                    (red.y != blue.y || red.x - 1 != blue.x)){
                red.x--;
            }

            if(board[red.y][red.x - 1] == 'O'){
                red.x--;
                redFlag = true;
            }
            while(board[blue.y][blue.x - 1] == '.' &&
                    (red.y != blue.y || red.x != blue.x - 1)){
                blue.x--;
            }

            if(board[blue.y][blue.x - 1] == 'O'){
                blue.x--;
                blueFlag = true;
            }
        }
        else{ // blue 먼저
            while(board[blue.y][blue.x - 1] == '.' &&
                    (red.y != blue.y || red.x != blue.x - 1)){
                blue.x--;
            }

            if(board[blue.y][blue.x - 1] == 'O'){
                blue.x--;
                blueFlag = true;
            }

            while(board[red.y][red.x - 1] == '.' &&
                    (red.y != blue.y || red.x - 1 != blue.x)){
                red.x--;
            }

            if(board[red.y][red.x - 1] == 'O'){
                red.x--;
                redFlag = true;
            }
        }

        if(redFlag && !blueFlag){ // 레드만 빠졌다면
            res = Math.min(res, depth);
        }

        if(!redFlag && !blueFlag){
            return false;
        }
        else{
            return true;
        }
    }

    boolean moveRight(char[][] board, Circle red, Circle blue, int depth){
        boolean redFlag = false;
        boolean blueFlag = false;

        if(red.x >= blue.x){ // red먼저
            while(board[red.y][red.x + 1] == '.' &&
                    (red.y != blue.y || red.x + 1 != blue.x)){
                red.x++;
            }

            if(board[red.y][red.x + 1] == 'O'){
                red.x++;
                redFlag = true;
            }

            while(board[blue.y][blue.x + 1] == '.' &&
                    (red.y != blue.y || red.x != blue.x + 1)){
                blue.x++;
            }

            if(board[blue.y][blue.x + 1] == 'O'){
                blue.x++;
                blueFlag = true;
            }
        }
        else{ // blue 먼저
            while(board[blue.y][blue.x + 1] == '.' &&
                    (red.y != blue.y || red.x != blue.x + 1)){
                blue.x++;
            }

            if(board[blue.y][blue.x + 1] == 'O'){
                blue.x++;
                blueFlag = true;
            }

            while(board[red.y][red.x + 1] == '.' &&
                    (red.y != blue.y || red.x + 1 != blue.x)){
                red.x++;
            }

            if(board[red.y][red.x + 1] == 'O'){
                red.x++;
                redFlag = true;
            }
        }

        if(redFlag && !blueFlag){ // 레드만 빠졌다면
            res = Math.min(res, depth);
        }

        if(!redFlag && !blueFlag){
            return false;
        }
        else{
            return true;
        }
    }

    boolean moveUp(char[][] board, Circle red, Circle blue, int depth){
        boolean redFlag = false;
        boolean blueFlag = false;

        if(red.y <= blue.y){ // red먼저
            while(board[red.y - 1][red.x] == '.' &&
                    (red.y - 1 != blue.y || red.x != blue.x)){
                red.y--;
            }

            if(board[red.y - 1][red.x] == 'O'){
                red.y--;
                redFlag = true;
            }

            while(board[blue.y - 1][blue.x] == '.' &&
                    (red.y != blue.y - 1 || red.x != blue.x)){
                blue.y--;
            }

            if(board[blue.y - 1][blue.x] == 'O'){
                blue.y--;
                blueFlag = true;
            }
        }
        else{ // blue 먼저
            while(board[blue.y - 1][blue.x] == '.' &&
                    (red.y != blue.y - 1 || red.x != blue.x)){
                blue.y--;
            }

            if(board[blue.y - 1][blue.x] == 'O'){
                blue.y--;
                blueFlag = true;
            }

            while(board[red.y - 1][red.x] == '.' &&
                    (red.y - 1 != blue.y || red.x != blue.x)){
                red.y--;
            }

            if(board[red.y - 1][red.x] == 'O'){
                red.y--;
                redFlag = true;
            }
        }

        if(redFlag && !blueFlag){ // 레드만 빠졌다면
            res = Math.min(res, depth);
        }

        if(!redFlag && !blueFlag){
            return false;
        }
        else{
            return true;
        }
    }

    boolean moveDown(char[][] board, Circle red, Circle blue, int depth){
        boolean redFlag = false;
        boolean blueFlag = false;

        if(red.y >= blue.y){ // red먼저
            while(board[red.y + 1][red.x] == '.' &&
                    (red.y + 1 != blue.y || red.x != blue.x)){
                red.y++;
            }

            if(board[red.y + 1][red.x] == 'O'){
                red.y++;
                redFlag = true;
            }

            while(board[blue.y + 1][blue.x] == '.' &&
                    (red.y != blue.y + 1 || red.x != blue.x)){
                blue.y++;
            }

            if(board[blue.y + 1][blue.x] == 'O'){
                blue.y++;
                blueFlag = true;
            }
        }
        else{ // blue 먼저
            while(board[blue.y + 1][blue.x] == '.' &&
                    (red.y != blue.y + 1 || red.x != blue.x)){
                blue.y++;
            }

            if(board[blue.y + 1][blue.x] == 'O'){
                blue.y++;
                blueFlag = true;
            }

            while(board[red.y + 1][red.x] == '.' &&
                    (red.y + 1 != blue.y || red.x != blue.x)){
                red.y++;
            }

            if(board[red.y + 1][red.x] == 'O'){
                red.y++;
                redFlag = true;
            }
        }

        if(redFlag && !blueFlag){ // 레드만 빠졌다면
            res = Math.min(res, depth);
        }

        if(!redFlag && !blueFlag){
            return false;
        }
        else{
            return true;
        }
    }


    class Circle{
        int y;
        int x;

        public Circle(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
