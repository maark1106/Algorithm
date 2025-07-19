import java.util.*;
import java.io.*;

class Main {

    /*
        0. -1 지점을 저장해두기, top, bottom
        1. 확산하기
            - 0보다 큰 지점에 대해 벽면 x, 청정기 x 인 지점 세고 /5 하여 확산
            - r, c 부분을 확산한 만큼 빼기
        2.
            - top에서는 거꾸로 부터 한칸씩 댕기기(위위 지점)
            - bottom에서는 아래서부터 한칸씩 댕기기
        3. T만큼 반복
        4. 미세먼지 개수 세기

    */

    int[][] board;
    int N;
    int M;
    int T;
    int top = 0;
    int bottom = 0;
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1) {
                    if (top == 0) {
                        top = i;
                    } else {
                        bottom = i;
                    }
                }
            }
        }

        for (int i = 0; i < T; i++) {
            spread();
            windTop();
            windBottom();
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0) {
                    res += board[i][j];
                }
            }
        }

        System.out.println(res);
    }

    void windTop() {
        for (int i = top - 1; i > 0; i--) {
            board[i][0] = board[i - 1][0];
        }

        for (int i = 1; i < M; i++) {
            board[0][i - 1] = board[0][i];
        }

        for (int i = 1; i <= top; i++) {
            board[i - 1][M - 1] = board[i][M - 1];
        }

        for (int i = M - 1; i >= 2; i--) {
            board[top][i] = board[top][i - 1];
        }

        board[top][1] = 0;
    }

    void windBottom() {
        for (int i = bottom + 1; i < N - 1; i++) {
            board[i][0] = board[i + 1][0];
        }

        for (int i = 0; i < M - 1; i++) {
            board[N - 1][i] = board[N - 1][i + 1];
        }

        for (int i = N - 1; i >= bottom + 1; i--) {
            board[i][M - 1] = board[i - 1][M - 1];
        }

        for (int i = M - 1; i >= 2; i--) {
            board[bottom][i] = board[bottom][i - 1];
        }

        board[bottom][1] = 0;
    }

    void spread() {
        int[][] newBoard = new int[N][M];
        for (int i = 0; i < N; i++) { // 겹치면 안되니까 새로 저장
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0) {
                    int size = board[i][j] / 5;
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) { // 퍼짐 가능 영역에만
                        int yy = i + dy[k];
                        int xx = j + dx[k];
                        if (yy < 0 || yy >= N || xx < 0 || xx >= M) {
                            continue;
                        }
                        if (board[yy][xx] == -1) {
                            continue;
                        }
                        cnt++;
                        newBoard[yy][xx] += size;
                    }

                    board[i][j] -= cnt * size;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] += newBoard[i][j];
            }
        }
    }
}
