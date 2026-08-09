import java.util.*;
import java.io.*;

/*
    풀이
    1. 각 기사를 기사 board에 위치 시키고 명령 받으면 탐색하여 찾는 것이 아니라 바로 찾을 수 있게 객체로 관리
        - 찾을 때는 객체로 위치를 바로 찾고 움직이면 객체, board모두 이동시켜줘야됨
    2. 명령을 받으면 해당 방향으로 이동시킬 수 있는지 재귀로 탐색하기
        - 만약 이동 방향 끝에가 모두 비어있다면 true return
        - 그렇지 벽이나 끝에 막혀있다면 false return
        - 다른 기사가 위치해 있다면 다시 재귀로 탐색하기(List에 기사 번호 담기)
            (여러 기사라면 1개라도 false일 경우 안됨)
    3. 만약 가능하다면 2번에서 담은 기사 번호로 한칸씩 이동하기(board, 객체 둘다)
        덫 밟은 만큼 체력 -하고 0이하라면 사라지기
    4. Q번의 명령 후 살아남은 기사들의 전체 체력 - 살아남은 기사들의 현제 체력 값 출력

*/

public class Main {

    static int N;
    static int M; //기사 수
    static int Q;
    static int[][] board;
    static int[][] kBoard;
    static Knight[] knights;
    //상 우 하 좌
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        kBoard = new int[N][N];
        knights = new Knight[M + 1];
        int[] prevLife = new int[M + 1];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 1; k <= M; k++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int yLen = Integer.parseInt(st.nextToken());
            int xLen = Integer.parseInt(st.nextToken());
            int life = Integer.parseInt(st.nextToken());
            knights[k] = new Knight(y, x, yLen, xLen, true, life);
            prevLife[k] = life;

            for(int i = y; i < y + yLen; i++){
                for(int j = x; j < x + xLen; j++){
                    kBoard[i][j] = k;
                }
            }
        }

        for(int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            int kNum = Integer.parseInt(st.nextToken());
            //0 1 2 3 -> 상, 우, 하, 좌
            int dir = Integer.parseInt(st.nextToken());
            if(knights[kNum].alive){
                command(kNum, dir);
            }
        }

        int res = 0;
        for(int i = 1; i <= M; i++){
            if(knights[i].alive){
                res += prevLife[i] - knights[i].life;
            }
        }

        System.out.println(res);
    }

    static void command(int kNum, int dir){
        //해당 방향으로 움직일 수 있는지 검사 & 움질일 기사 List에 담기
        HashSet<Integer> moveKnights = new HashSet<>();
        boolean[] visited = new boolean[M + 1];
        boolean isMove = canMove(kNum, dir, moveKnights, visited);
        moveKnights.add(kNum);

        if(!isMove){
            return;
        }
        //움직일 수 있다면 움직이면서 체력 깎기(Knight만 이동)
        //기사들 삭제하기(kBoard, alive = false) 
        moveKnight(moveKnights, kNum, dir);

        //살아남은 기사들로 kBoard 다시 그리기
        int[][] newKBoard = new int[N][N];
        draw(newKBoard);
        kBoard = newKBoard;
    }

    static boolean canMove(int kNum, int dir, HashSet<Integer> moveKnights, boolean[] visited) {        
        Knight cur = knights[kNum];

        int nextY = cur.y + dy[dir];
        int nextX = cur.x + dx[dir];

        for(int i = nextY; i < nextY + cur.yLen; i++){
            for(int j = nextX; j < nextX + cur.xLen; j++){
                if(i < 0 || i >= N || j < 0 || j >= N){
                    return false;
                }

                if(board[i][j] == 2){
                    return false;
                }

                int nextK = kBoard[i][j];

                //다른 기사 중 방문하지 않은 기사
                if(nextK > 0 && nextK != kNum && !visited[nextK]){
                    visited[nextK] = true;
                    moveKnights.add(nextK);
                    if(!canMove(nextK, dir, moveKnights, visited)){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    static void moveKnight(HashSet<Integer> moveKnights, int kNum, int dir){
        for(int num: moveKnights){
            Knight cur = knights[num];
            cur.y += dy[dir];
            cur.x += dx[dir];
            if(kNum == num){
                continue;
            }

            for(int i = cur.y; i < cur.y + cur.yLen; i++){
                for(int j = cur.x; j < cur.x + cur.xLen; j++){
                    if(board[i][j] == 1){
                        cur.life--;
                    }
                }
            }

            if(cur.life <= 0){
                cur.alive = false;
            }
        }
    }

    static void draw(int[][] newKBoard){
        for(int k = 1; k <= M; k++){
            Knight cur = knights[k];
            if(!cur.alive){
                continue;
            }

            for(int i = cur.y; i < cur.y + cur.yLen; i++){
                for(int j = cur.x; j < cur.x + cur.xLen; j++){
                    newKBoard[i][j] = k;
                }
            }
        }
    }

    static class Knight{
        int y;
        int x;
        int yLen;
        int xLen;
        boolean alive;
        int life;

        public Knight(int y, int x, int yLen, int xLen, boolean alive, int life){
            this.y = y;
            this.x = x;
            this.yLen = yLen;
            this.xLen = xLen;
            this.alive = alive;
            this.life = life;
        }
    }
}
