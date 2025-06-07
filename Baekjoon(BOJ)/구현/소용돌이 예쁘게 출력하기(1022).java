package anew_.소용돌이예쁘게출력하기_1022;

import java.util.*;
import java.io.*;

/*
    -3 -2 -1  0  1  2  3
    --------------------
-3 |37 36 35 34 33 32 31
-2 |38 17 16 15 14 13 30
-1 |39 18  5  4  3 12 29
 0 |40 19  6  1  2 11 28
 1 |41 20  7  8  9 10 27
 2 |42 21 22 23 24 25 26
 3 |43 44 45 46 47 48 49


 1. 0 ~ 1억까지 구하기
 2. 오, 위, 왼, 아 순으로 반복
 3. 2번 할 때마다  하나씩 증가하기
 4. -5000 ~ 5000까지 저장하므로 [10002[10002]선언
 5. i + 5000, j + 5000에 저장하기
 6. 출력

*/

class Main {

    int[][] board;
    int[] dy = {0, -1, 0, 1};
    int[] dx = {1, 0, -1, 0};
    int y = 0;
    int x = 0;
    int num = 1;
    int total = 0;
    int sY;
    int sX;
    int eY;
    int eX;

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sY = Integer.parseInt(st.nextToken());
        sX = Integer.parseInt(st.nextToken());
        eY = Integer.parseInt(st.nextToken());
        eX = Integer.parseInt(st.nextToken());

        board = new int[eY - sY + 1][eX - sX + 1];

        int dir = 0;
        int cnt = 0;
        int dnum = 1;

        while (!isFinish()) {
            if(y >= sY && y <= eY && x >= sX && x <= eX){
                board[y - sY][x - sX] = num;
            }

            num++;
            cnt++;

            y += dy[dir];
            x += dx[dir];

            if(cnt == dnum){
                cnt = 0;
                if(dir == 1 || dir == 3)
                    dnum++;

                dir = (dir + 1) % 4;
            }
        }

        StringBuilder sb = new StringBuilder();
        int maxNumber = 0;
        for (int i = 0; i < eY - sY + 1; i++) {
            for (int j = 0; j < eX - sX + 1; j++) {
                maxNumber = Math.max(maxNumber, board[i][j]);
            }
        }

        int length = String.valueOf(maxNumber).length();
        for (int i = 0; i < eY - sY + 1; i++) {
            for (int j = 0; j < eX - sX + 1; j++) {
                for (int k = 0; k < length - String.valueOf(board[i][j]).length(); k++) {
                    sb.append(" ");
                }
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }


    boolean isFinish(){
        if(board[0][0] != 0 && board[0][eX - sX] != 0
        && board[eY - sY][0] != 0 && board[eY - sY][eX - sX] != 0){
            return true;
        }
        return false;
    }
}
