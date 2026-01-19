import java.util.*;
import java.io.*;

class Main{

    /*
        1. 1번부터 K번까지 이동하기
        2. horse 좌표로 관리하기
        3. if 다음 이동칸 == 흰칸
            빨간 칸
            파란 칸 or 칸 밖
        4. 이동하고 그 위에 것들은 좌표만 이동하기
        5. 새로운 칸에 갔다면 다시 해당 좌표 순서 바꾸기
            이동하기 전에 자기보다 큰 게 몇번인지 list에 넣기
            이동하려는 칸에 몇 개가 있는지 구하기
            칸 색깔에 따라 번호 부여하기
    */

    int N;
    int K;
    int[][] board;
    int res;
    Horse[] horses;
    int[] dy = {0, 0, 0, -1, 1};
    int[] dx = {0, 1, -1, 0, 0};
    boolean flag;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        horses = new Horse[K];
        flag = true;

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i= 0 ; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            horses[i] = new Horse(y, x, dir, 1);
        }

        res = 1;
        while(true){
            if(res > 1000){
                res = -1;
                break;
            }

            moveHorse();
            if(!flag){
                break;
            }
            res++;
        }

        System.out.print(res);
    }

    void moveHorse(){
        for(int i = 0 ; i < K; i++){
            List<Integer> topList = new ArrayList<>();
            getTopList(topList, i);

            int curDir = horses[i].dir;
            int yy = horses[i].y + dy[curDir];
            int xx = horses[i].x + dx[curDir];

            if(yy < 0 || yy >= N || xx < 0 || xx >= N
                    || board[yy][xx] == 2){
                if(curDir == 1){
                    curDir = 2;
                }
                else if(curDir == 2){
                    curDir = 1;
                }
                else if(curDir == 3){
                    curDir = 4;
                }
                else if(curDir == 4){
                    curDir = 3;
                }

                yy = horses[i].y + dy[curDir];
                xx = horses[i].x + dx[curDir];

                if(yy < 0 || yy >= N || xx < 0 || xx >= N
                        || board[yy][xx] == 2){ // 방향 바꾼다음 이동칸도 밖이거나 파란색이면 방향만 바꾸기
                    horses[i].dir = curDir;
                }
                else if(board[yy][xx] == 1){ // 빨간 타일이라면
                    int downCnt = getDownCnt(yy, xx);
                    horses[i].dir = curDir;
                    moveRed(downCnt, topList, yy, xx);
                }
                else{ //그게 아니라면 방향도 바꾸고 이동도 하기
                    horses[i].dir = curDir;
                    int downCnt = getDownCnt(yy, xx);
                    moveBlueAndWhite(downCnt, topList, yy, xx);
                }
            }
            else if(board[yy][xx] == 0){ // 하얀 타일이라면
                int downCnt = getDownCnt(yy, xx);
                moveBlueAndWhite(downCnt, topList, yy, xx);
            }
            else if(board[yy][xx] == 1){ // 빨간 타일이라면
                int downCnt = getDownCnt(yy, xx);
                moveRed(downCnt, topList, yy, xx);
            }

            if(endCheck()){
                flag = false;
                return;
            }
        }
    }

    boolean endCheck(){
        int[][] cntBoard = new int[N][N];
        for(Horse horse: horses){
            cntBoard[horse.y][horse.x]++;
            if(cntBoard[horse.y][horse.x] >= 4){
                return true;
            }
        }
        return false;
    }

    void moveBlueAndWhite(int downCnt, List<Integer> topList, int y, int x){
        int minTh = Integer.MAX_VALUE;
        for(int i = 0 ; i < topList.size(); i++){
            Horse curHorse = horses[topList.get(i)];
            minTh = Math.min(minTh, curHorse.th);
        }

        for(int idx: topList){
            horses[idx].y = y;
            horses[idx].x = x;
            horses[idx].th = horses[idx].th - (minTh - 1) + downCnt;
        }
    }

    void moveRed(int downCnt, List<Integer> topList, int y, int x){
        List<Integer> sortedHorses = new ArrayList<>();
        int minTh = Integer.MAX_VALUE;
        for(int i = 0 ; i < topList.size(); i++){
            Horse curHorse = horses[topList.get(i)];
            minTh = Math.min(minTh, curHorse.th);
        }

        int curIdx = minTh;
        for(int i = 0 ; i < topList.size(); i++){ // 오름차순으로 담기
            for(int j = 0 ; j < topList.size(); j++){
                if(horses[topList.get(j)].th == curIdx){
                    sortedHorses.add(topList.get(j));
                    curIdx++;
                    break;
                }
            }
        }

        for(int i = 0 ; i < sortedHorses.size() / 2; i++){
            int temp = horses[sortedHorses.get(i)].th;
            horses[sortedHorses.get(i)].th = horses[sortedHorses.get(sortedHorses.size() - i - 1)].th;
            horses[sortedHorses.get(sortedHorses.size() - i - 1)].th = temp;
        }

        for(int idx: topList){
            horses[idx].y = y;
            horses[idx].x = x;
            horses[idx].th = horses[idx].th - (minTh - 1) + downCnt;
        }
    }

    int getDownCnt(int y, int x){
        int downCnt = 0;
        for(int i = 0 ; i < K; i++){
            if(horses[i].y == y && horses[i].x == x){
                downCnt++;
            }
        }
        return downCnt;
    }

    void getTopList(List<Integer> topList, int myNumber){
        for(int i = 0 ; i < K; i++){
            if(horses[myNumber].y == horses[i].y && horses[myNumber].x == horses[i].x &&
                    horses[myNumber].th <= horses[i].th){
                topList.add(i);
            }
        }
    }

    class Horse{
        int y;
        int x;
        int dir;
        int th;

        public Horse(int y, int x, int dir, int th){
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.th = th;
        }
    }
}
