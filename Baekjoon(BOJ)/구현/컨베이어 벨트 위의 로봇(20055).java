package anew_.컨베이어벨트위의로봇_20055;

import java.util.*;
import java.io.*;

class Main{

    /*
        1. 칸의 개수 검사하여 K인지 확인
        2. robots를 boolean으로 관리하여 한칸씩 밀기
        3. robot[N] = false;
           다음 칸 내구도 남음 && 로봇 없음
           이동, 내구도 감소
        4. robot[1] 내구도 남음 -> robot 올림
            내구도 감소
        5. 반복
    */

    int N;
    int K;
    boolean[] robots;
    int[] board;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        robots = new boolean[2 * N + 1];
        board = new int[2 * N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= 2 * N; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        int res = 0;
        while(isAvailable()){ //칸의 개수 검사하여 K인지 확인
            moveRobotsAndBoard(); // 한 칸씩 밀기
            robots[N] = false; // N위치는 무조건 내리기
            moveIfAvailable();
            robots[N] = false;
            putFirstBoard();
            res++;
        }

        System.out.print(res);
    }

    boolean isAvailable(){
        int cnt = 0;
        for(int i= 1; i<= 2*N; i++){
            if(board[i] == 0){
                cnt++;
            }
        }

        return cnt < K;
    }

    void moveRobotsAndBoard(){ // 로봇과 칸 같이 이동
        boolean temp = robots[2*N];
        int temp1 = board[2*N];
        for(int i = 2*N; i>= 2; i--){
            robots[i] = robots[i - 1];
            board[i] = board[i - 1];
        }
        robots[1] = temp;
        board[1] = temp1;
    }

    void moveIfAvailable(){ // N번 칸에는 무조건 로봇 없음
        for(int i = N - 1; i >= 1; i--){
            if(robots[i] && !robots[i + 1] && board[i + 1] > 0){ // 내 다음칸에 로봇이 없고 내구도가 남아있다면
                robots[i + 1] = true;
                robots[i] = false;
                board[i + 1]--;
            }
        }
    }

    void putFirstBoard(){
        if(board[1] > 0){
            robots[1] = true;
            board[1]--;
        }
    }
}
