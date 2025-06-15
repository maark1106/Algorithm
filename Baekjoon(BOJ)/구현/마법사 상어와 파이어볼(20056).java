import java.util.*;
import java.io.*;

class Main {

    /*
        1. 파이어볼 class 만들고 넣어두기
        2. 파이어볼 이동하기
            (0 <-> N - 1 이어짐)
        3. 같은 칸에 있는 파이어볼 하나로 합치기
            반복문 돌면서 새로운 배열에 합치기
            cnt 배열도 관리하기

            새로 만든
        4. 방향 모두 같은지 다른지
            질량, 속력 다 더하고 나누기 / (개수)
            방향 모두 홀수 or 짝수 : 0 2 4 6
            아니다 : 1 3 5 7
            로 이동하기
        5. 질량이 0인 파이어볼 사라지기


    */

    FireBall[][] sumBoard;
    List<FireBall> fireballs;
    int[][] cnt;
    int N;
    int M;
    int K;
    int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireballs = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new FireBall(y, x, m, s, d));
        }

        for (int i = 0; i < K; i++) {
            moveAndSumFireBall();
            divideFireBall();
        }

        int res = sumFireBall();
        System.out.print(res);
    }

    int sumFireBall() {
        int sum = 0;
        for (FireBall fireBall : fireballs) {
            sum += fireBall.m;
        }
        return sum;
    }

    void divideFireBall() {
        fireballs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cnt[i][j] >= 2) { // 2개 이상인 곳은 분리하기
                    int newM = sumBoard[i][j].m / 5;
                    int newS = sumBoard[i][j].s / cnt[i][j];
                    if (newM == 0) { // 질량 0이면 넣지 않음
                        continue;
                    }

                    if (sumBoard[i][j].isSame) { // 0, 2, 4, 6
                        fireballs.add(new FireBall(i, j, newM, newS, 0));
                        fireballs.add(new FireBall(i, j, newM, newS, 2));
                        fireballs.add(new FireBall(i, j, newM, newS, 4));
                        fireballs.add(new FireBall(i, j, newM, newS, 6));
                    } else { // 1, 3, 5, 7
                        fireballs.add(new FireBall(i, j, newM, newS, 1));
                        fireballs.add(new FireBall(i, j, newM, newS, 3));
                        fireballs.add(new FireBall(i, j, newM, newS, 5));
                        fireballs.add(new FireBall(i, j, newM, newS, 7));
                    }
                } else if (cnt[i][j] == 1) { // 하나는 그냥 삽입
                    fireballs.add(new FireBall(i, j, sumBoard[i][j].m,
                            sumBoard[i][j].s, sumBoard[i][j].d));
                }
            }
        }
    }

    void moveAndSumFireBall() { // sumBoard에 합쳐진 질량, 속략 / cnt에 몇 개인지
        sumBoard = new FireBall[N][N];
        cnt = new int[N][N];

        for (FireBall fireball : fireballs) {
            int y = fireball.y;
            int x = fireball.x;
            int m = fireball.m;
            int s = fireball.s;
            int d = fireball.d;

            int ny = (y + (dy[d] * s) % N + N) % N;
            int nx = (x + (dx[d] * s) % N + N) % N;

            if (sumBoard[ny][nx] == null) { // 처음 들어간다면
                sumBoard[ny][nx] = new FireBall(ny, nx, m, s, d);
                cnt[ny][nx] = 1;
            } else {
                sumBoard[ny][nx].m += m;
                sumBoard[ny][nx].s += s;
                cnt[ny][nx]++;
                if ((d % 2) != (sumBoard[ny][nx].d % 2)) { // 방향이 홀, 짝 같다
                    sumBoard[ny][nx].isSame = false;
                }
            }
        }
    }

    class FireBall {
        int y;
        int x;
        int m;
        int s;
        int d;
        boolean isSame;


        public FireBall(int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.s = s;
            this.d = d;
            isSame = true;
        }
    }
}
