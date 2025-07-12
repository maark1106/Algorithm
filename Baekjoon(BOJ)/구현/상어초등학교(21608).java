import java.util.*;
import java.io.*;

class Main {

    int N;
    int[][] board;
    int[][] friends;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        board = new int[N * N][N * N];
        friends = new int[N * N + 1][4];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int myNumber = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                friends[myNumber][j] = Integer.parseInt(st.nextToken());
            }

            playSeat(myNumber, friends[myNumber]);
        }

        System.out.print(getRes());
    }

    void playSeat(int myNumber, int[] friends) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    continue;
                }

                Seat seat = new Seat(i, j);

                for (int k = 0; k < 4; k++) {
                    int yy = i + dy[k];
                    int xx = j + dx[k];

                    if (yy < 0 || yy >= N || xx < 0 || xx >= N) {
                        continue;
                    }
                    if (containsLikeFriends(yy, xx, friends)) {
                        seat.like++;
                    }
                    if (board[yy][xx] == 0) {
                        seat.empty++;
                    }
                }
                seats.add(seat);
            }
        }

        Collections.sort(seats);
        Seat curSeat = seats.get(0);
        board[curSeat.y][curSeat.x] = myNumber;
    }

    int getRes() {
        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                int myNumber = board[i][j];

                for (int k = 0; k < 4; k++) {
                    int yy = i + dy[k];
                    int xx = j + dx[k];

                    if (yy < 0 || yy >= N || xx < 0 || xx >= N) {
                        continue;
                    }

                    if (containsLikeFriends(yy, xx, friends[myNumber])) {
                        cnt++;
                    }
                }

                if (cnt == 1) {
                    res++;
                } else if (cnt == 2) {
                    res += 10;
                } else if (cnt == 3) {
                    res += 100;
                } else if (cnt == 4) {
                    res += 1000;
                }
            }
        }

        return res;
    }


    boolean containsLikeFriends(int y, int x, int[] friends) {
        for (int i = 0; i < 4; i++) {
            if (board[y][x] == friends[i]) {
                return true;
            }
        }
        return false;
    }

    class Seat implements Comparable<Seat> {
        int like;
        int empty;
        int y;
        int x;

        public Seat(int y, int x) {
            this.like = 0;
            this.empty = 0;
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Seat s) {
            if (this.like == s.like) {
                if (this.empty == s.empty) {
                    if (this.y == s.y) {
                        return this.x - s.x;
                    }
                    return this.y - s.y;
                }
                return s.empty - this.empty;
            }
            return s.like - this.like;
        }
    }

}
