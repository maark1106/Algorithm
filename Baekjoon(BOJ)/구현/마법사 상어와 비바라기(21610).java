package anew_.마법사상어와비바라기_21610;

import java.util.*;
import java.io.*;


public class Main {

    /*

            1. 구름을 고정된 칸에 생성
            2. 구름이 방향에 맞게 이동(1과 N이 이어지는 거 주의)
            3. 구름이 있는 칸 물의 양++
            4. 구름 모두 사라짐
            5. 대각선 검사
                - 범위 내에 있고 물이 있으면 cnt++
                - cnt만큼 물 증가
            6. 방금 구름이 사라진 칸이 아니고 && 물의 양이 2 이상인 모든 칸에 구름 생성
            7. 물의 합 구하기
    */

    int N;
    int M;
    boolean[][] cloud;
    boolean[][] prevCloud;
    int[][] water;
    int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cloud = new boolean[N][N];
        water = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //구름 고정칸 생성
        cloud[N - 1][0] = true;
        cloud[N - 1][1] = true;
        cloud[N - 2][0] = true;
        cloud[N - 2][1] = true;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            moveCloud(d, s);
            rainCloud();
            copyWater();
            createCloud();
        }

        int res = getSumWater();
        System.out.print(res);
    }

    int getSumWater() {
        int cnt = 0;
        for(int i = 0; i <N ;i++){
            for(int j = 0; j < N;j++){
                cnt += water[i][j];
            }
        }
        return cnt;
    }

    void createCloud() {
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N;j++){
                if(water[i][j] >= 2 && !prevCloud[i][j]){
                    cloud[i][j] = true;
                    water[i][j] -= 2;
                }
            }
        }
    }

    void copyWater() {
        int[][] copyWater = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (prevCloud[i][j]) { // 물이 증가한 칸(구름이 있던 칸)
                    int cnt = 0;
                    for (int k = 1; k <= 7; k += 2) {
                        int y = i + dy[k];
                        int x = j + dx[k];

                        if (y >= 0 && y < N && x >= 0 && x < N
                                && water[y][x] >= 1) {
                            cnt++;
                        }
                    }
                    copyWater[i][j] += cnt;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                water[i][j] += copyWater[i][j];
            }
        }
    }

    void rainCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cloud[i][j]) {
                    water[i][j]++;
                }
            }
        }

        cloud = new boolean[N][N];
    }

    void moveCloud(int d, int s) {
        boolean[][] newCloud = new boolean[N][N];
        prevCloud = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cloud[i][j]) {
                    int y = (i + (s % N) * dy[d - 1] + N) % N;
                    int x = (j + (s % N) * dx[d - 1] + N) % N;
                    newCloud[y][x] = true;
                    prevCloud[y][x] = true;
                }
            }
        }

        cloud = newCloud;
    }


}
