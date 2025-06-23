package anew_.게리맨더링2_17779;

import java.util.*;
import java.io.*;

class Main {

    /*
        1. x, y, d1, d2 좌표 잡기
        2. 경계선 표시
        3. 경계선 안에 5로 채우기
        4. 나머지 경계 값 돌면서 1 ~ 4채우기
        5. 영역별로 사람 구해서 가장 큰 지역 - 가장 작은 지역 값 구하기
        6. 가장 최소값 찾기

    */

    int N;
    int[][] board;
    int[][] area;
    int res;

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        if (j + d1 + d2 <= N && 1 <= i - d1
                                && i + d2 <= N) {
                            markOutline(i, j, d1, d2);
                            markInline();
                            divideArea(i, j, d1, d2);
                            getPeople();
                        }
                    }
                }
            }
        }

        System.out.print(res);
    }

    void getPeople() {
        int[] cnt = new int[6];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                cnt[area[i][j]] += board[i][j];
            }
        }

        int maxSum = 0;
        int minSum = Integer.MAX_VALUE;

        for (int i = 1; i <= 5; i++) {
            maxSum = Math.max(maxSum, cnt[i]);
            minSum = Math.min(minSum, cnt[i]);
        }

        res = Math.min(res, maxSum - minSum);
    }

    void divideArea(int y, int x, int d1, int d2) {
        for (int i = 1; i < y; i++) {
            for (int j = 1; j <= x + d1; j++) {
                if (area[i][j] == 5) {
                    break;
                }
                area[i][j] = 1;
            }
        }

        for (int i = 1; i <= y - d1 + d2; i++) {
            for (int j = N; j > x + d1; j--) {
                if (area[i][j] == 5) {
                    break;
                }
                area[i][j] = 2;
            }
        }

        for (int i = y; i <= N; i++) {
            for (int j = 1; j < x + d2; j++) {
                if (area[i][j] == 5) {
                    break;
                }
                area[i][j] = 3;
            }
        }

        for (int i = y - d1 + d2 + 1; i <= N; i++) {
            for (int j = N; j >= x + d2; j--) {
                if (area[i][j] == 5) {
                    break;
                }
                area[i][j] = 4;
            }
        }
    }

    void markOutline(int y, int x, int d1, int d2) {
        area = new int[N + 1][N + 1];
        for (int i = 0; i <= d1; i++) { // 오른 위
            area[y - i][x + i] = 5;
        }

        for (int i = 0; i <= d2; i++) { // 오른 왼
            area[y - d1 + i][x + d1 + i] = 5;
        }

        for (int i = 0; i <= d1; i++) { // 오른 위
            area[y + d2 - i][x + d2 + i] = 5;
        }

        for (int i = 0; i <= d2; i++) { // 오른 위
            area[y + i][x + i] = 5;
        }
    }

    void markInline() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (area[i][j] == 5) {
                    int p1 = j;
                    for (int k = j + 1; k <= N; k++) {
                        if (area[i][k] == 5) { // 처음 만난 5 다음부터 다시 5 나올때까지 탐색
                            p1 = k;
                            break;
                        }
                    }

                    for (int k = j; k <= p1; k++) { // 처음 5 ~ 마지막 5까지 5로 채우기
                        area[i][k] = 5;
                    }
                    break;
                }
            }
        }
    }
}
