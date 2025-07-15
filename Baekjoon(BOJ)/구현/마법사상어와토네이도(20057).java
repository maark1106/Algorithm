import java.util.*;
import java.io.*;

class Main{

    /*
        1. (1, 1, 2, 2, 3, 3 ..) 방향 바꿔가면서 이동하기
        2. 1%, 7%, 구역 dir 나누기
            왼, 아래, 오른, 위 대해 비율 설정            
        3. 만약 범위 벗어나면 board추가하지 않고 res에 추가하기
        4. 좌표 갱신해주기
    */

    int N;
    int[][] board;
    int[] dy = {0, 1, 0, -1};
    int[] dx = {-1, 0, 1, 0};
    int curY;
    int curX;
    int res = 0;
    int step1 = 0; // step1 == maxStep, curDir++, step2++
    int step2 = 0; // step2 == 2, maxStep++
    int maxStep = 1;
    int curDir = 0; // curDir은 한번씩 증가
    List<Pos> left = new ArrayList<>();
    List<Pos> up = new ArrayList<>();
    List<Pos> down = new ArrayList<>();
    List<Pos> right = new ArrayList<>();



    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initPos();

        curY = (N + 1) / 2;
        curX = (N + 1) / 2;

        while(true){
            curY += dy[curDir];
            curX += dx[curDir];

            if(curDir == 0){ // left
                createSand(left);
            }
            else if(curDir == 1){ //down
                createSand(down);
            }
            else if(curDir == 2){ // right
                createSand(right);
            }
            else{ // up
                createSand(up);
            }

            step1++;
            if(step1 == maxStep){ // 1,
                step1 = 0;
                step2++;
                curDir = (curDir + 1) % 4;
            }

            if(step2 == 2){
                step2 = 0;
                maxStep++;
            }

            if(curY == 1 && curX == 1){
                break;
            }
        }

        System.out.print(res);
    }

    void createSand(List<Pos> pos){
        int total = board[curY][curX];
        for(Pos curPos: pos){
            int yy = curY + curPos.y;
            int xx = curX + curPos.x;
            int remain = (int)(board[curY][curX] * (curPos.p * 0.01));
            total -= remain;

            if(yy < 1 || yy > N || xx < 1 || xx > N){ // 범위 벗어나면 count
                res += remain;
            }
            else{ // 아니면 기존 board에 더하기
                board[yy][xx] += remain;
            }
        }
        // @자리에 %제외한 남은 값 넣기
        int yy = curY + dy[curDir];
        int xx = curX + dx[curDir];
        if(yy < 1 || yy > N || xx < 1 || xx > N){ // 범위 벗어나면 count
            res += total;
        }
        else{ // 아니면 기존 board에 더하기
            board[yy][xx] += total;
        }

        board[curY][curX] = 0;
    }

    void initPos(){
        left.add(new Pos(-1, 1, 1));
        left.add(new Pos(1, 1, 1));
        left.add(new Pos(-2, 0, 2));
        left.add(new Pos(2, 0, 2));
        left.add(new Pos(-1, 0, 7));
        left.add(new Pos(1, 0, 7));
        left.add(new Pos(-1, -1, 10));
        left.add(new Pos(1, -1, 10));
        left.add(new Pos(0, -2, 5));

        down.add(new Pos(-1, -1, 1));
        down.add(new Pos(-1, 1, 1));
        down.add(new Pos(0, -2, 2));
        down.add(new Pos(0, 2, 2));
        down.add(new Pos(0, -1, 7));
        down.add(new Pos(0, 1, 7));
        down.add(new Pos(1, -1, 10));
        down.add(new Pos(1, 1, 10));
        down.add(new Pos(2, 0, 5));

        right.add(new Pos(-1, -1, 1));
        right.add(new Pos(1, -1, 1));
        right.add(new Pos(-2, 0, 2));
        right.add(new Pos(2, 0, 2));
        right.add(new Pos(-1, 0, 7));
        right.add(new Pos(1, 0, 7));
        right.add(new Pos(-1, 1, 10));
        right.add(new Pos(1, 1, 10));
        right.add(new Pos(0, 2, 5));

        up.add(new Pos(1, -1, 1));
        up.add(new Pos(1, 1, 1));
        up.add(new Pos(0, -2, 2));
        up.add(new Pos(0, 2, 2));
        up.add(new Pos(0, -1, 7));
        up.add(new Pos(0, 1, 7));
        up.add(new Pos(-1, -1, 10));
        up.add(new Pos(-1, 1, 10));
        up.add(new Pos(-2, 0, 5));
    }

    class Pos{
        int y;
        int x;
        int p;

        public Pos(int y, int x, int p){
            this.y = y;
            this.x = x;
            this.p = p;
        }
    }
}
