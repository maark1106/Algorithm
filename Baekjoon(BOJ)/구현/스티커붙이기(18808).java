import java.util.*;
import java.io.*;

class Main{

    /*
        1. 스티커를 저장하기
        2. 맨 왼쪽부터 모눈종이에 공간이 있는지 탐색
            - 1인 구간(스티커)이 범위 벗어났는지
            - 이미 다른 스티커 있는지
            -> 두 조건 만족하면 해당 범위에 스티커 붙이기

            만족 안하면 90도씩 돌려서 맞나보기
        3. 공간이 없다면 다음 좌표로 이동하여 탐색하기
        4. 모든 스티커 붙이기
    */

    int N;
    int M;
    int K;
    int[][] board;
    List<Sticker> stickers;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        stickers = new ArrayList<>();

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] paper = new int[r][c];
            for(int j = 0; j < r; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0 ; k < c; k++){
                    paper[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            stickers.add(new Sticker(r, c, paper));
        }

        for(int k = 0 ; k < K; k++){
            Sticker cur = stickers.get(k);
            boolean flag = false;
            for(int d = 0; d < 4; d++){
                if(d != 0){
                    cur = getSpinSticker(cur);
                }
                for(int i = 0 ; i < N; i++){
                    for(int j = 0; j < M; j++){
                        if(isAvailable(i, j, cur)){
                            stick(i, j, cur);
                            flag = true;
                            break;
                        }
                    }
                    if(flag) break;
                }
                if(flag) break;
            }
        }

        int res = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(board[i][j] == 1){
                    res++;
                }
            }
        }
        System.out.println(res);
    }

    Sticker getSpinSticker(Sticker cur){
        int[][] paper = new int[cur.c][cur.r];
        Sticker spin = new Sticker(cur.c, cur.r, paper);

        for(int i = 0 ; i < cur.r; i++){
            for(int j = 0 ; j < cur.c; j++){
                spin.paper[j][spin.c - 1 - i] = cur.paper[i][j];
            }
        }
        return spin;
    }

    void stick(int y, int x, Sticker cur){
        for(int i = 0; i < cur.r; i++){
            for(int j = 0; j < cur.c; j++){
                if(cur.paper[i][j] == 1){
                    int yy = y + i;
                    int xx = x + j;
                    board[yy][xx] = 1;
                }
            }
        }
    }

    boolean isAvailable(int y, int x, Sticker cur){
        for(int i = 0; i < cur.r; i++){
            for(int j = 0; j < cur.c; j++){
                if(cur.paper[i][j] == 1){
                    int yy = y + i;
                    int xx = x + j;
                    if(yy < 0 || yy >= N || xx < 0 || xx >= M){ //board밖에 1인 경우
                        return false;
                    }
                    if(board[yy][xx] == 1){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    class Sticker{
        int r;
        int c;
        int[][] paper;

        public Sticker(int r, int c, int[][] paper){
            this.r = r;
            this.c = c;
            this.paper = paper;
        }
    }
}
