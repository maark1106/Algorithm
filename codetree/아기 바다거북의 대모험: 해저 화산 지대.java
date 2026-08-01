import java.util.*;
import java.io.*;

/*
    풀이
    화산 + 거북이 + 산호초  / 마그마 따로 관리
    1. 거북이 최단 경로 순으로 이동
        - 도착하면 거북이 제거 & 기록
    2. 화산 압력 증가
    3. 화산 분출
        - 이미 처리한 화산 처리 잘하기
    4. 화산 / 거북이 board 대조해서 화석화 하기
    5. 분출한 마그마 압력 0 초기화

    거북이칸 : 산호초 1, 거북이 2, 마그마 3 
    마그마칸 : 현재까지 마그마 누적
    임계치 저장

*/

public class Main {
    
    static int N;
    static int M;
    static int K;
    static List<Turtle> turtles;
    static int turtleCount;
    static int[][] board;
    static int[][] volcano; // 화산
    static int[][] magma; // 화산에 차오르는 압력
    static boolean[] endTurtle; // 화석되거나 목적지 도달하거나
    static int[] res;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        endTurtle = new boolean[M];
        res = new int[M];
        board = new int[N][N];
        magma = new int[N][N];
        volcano = new int[N][N];
        turtles = new ArrayList<>();

        Arrays.fill(res, -1);

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < M; i++){ // 거북이
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            turtles.add(new Turtle(i, y, x));
            board[y][x] = 2;
        }

        for(int i = 0 ; i < K; i++){ // 화산
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            volcano[y][x] = m;
        }

        turtleCount = 0;
        for(int i = 1; i <= 100; i++){
            if(turtleCount == M){ // 거북이 다 탈출했다면 종료
                break;
            }
            
            // 거북이 영차
            for(int j = 0 ; j < M; j++){
                if(!endTurtle[j]){ // 끝난 거북이가 아닌 것만
                    if(moveTurtle(turtles.get(j))){
                        res[j] = i;
                    }
                }
            }

            // 마그마 증가
            increaseMagma();

            // 화산 분출 및 연쇄반응
            bomb();
        }

        for(int i = 0 ; i < M; i++){
            System.out.println(res[i]);
        }
    }

    public static boolean moveTurtle(Turtle turtle){
        int minMoveNum = Integer.MAX_VALUE;
        int moveIdx = 0;

        // 우하좌상 순으로 탐색
        for(int i = 0; i < 4; i++){
            int yy = turtle.y + dy[i];
            int xx = turtle.x + dx[i];

            if(!isAvailable(yy, xx)){
                continue;
            }

            // 우하좌상으로 탐색하되 만약 더 최적화된 경로가 있다면 방향 갱신
            boolean[][] visited = new boolean[N][N];
            visited[turtle.y][turtle.x] = true;
            int dis = getMoveCnt(yy, xx, visited);
            if(dis < minMoveNum){
                minMoveNum = dis;
                moveIdx = i;
            }
        }

        // 이동할 수 있다면 거북이 옮기고 목적지 도달했다면 종료처리
        if(minMoveNum != Integer.MAX_VALUE){
            board[turtle.y][turtle.x] = 0;
            turtle.y = turtle.y + dy[moveIdx];
            turtle.x = turtle.x + dx[moveIdx];
            board[turtle.y][turtle.x] = 2;

            if(turtle.y == N - 1 && turtle.x == N - 1){
                turtleCount++;
                endTurtle[turtle.num] = true;
                board[N - 1][N - 1] = 0; // 거북이가 최종지점에 막고 있으면 안됨
                return true; // 거북이 목적지 도달
            }
        }

        return false; // 도달 못함
    }

    public static int getMoveCnt(int y, int x, boolean[][] visited){
        Queue<int[]> q = new ArrayDeque<>();
        visited[y][x] = true;
        q.add(new int[]{y, x, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == N - 1 && cur[1] == N - 1){ // 목적지 도달 시 return
                return cur[2];
            }

            for(int i = 0 ; i < 4; i++){
                int yy = cur[0] + dy[i];
                int xx = cur[1] + dx[i];

                if(!isAvailable(yy, xx) || visited[yy][xx]){
                    continue;
                }

                q.add(new int[]{yy, xx, cur[2] + 1});
                visited[yy][xx] = true;
            }
        }

        return Integer.MAX_VALUE; // 도달할 수 없음
    }

    public static boolean isAvailable(int y, int x){
        if(y < 0 || y >= N || x < 0 || x >= N){
            return false;
        }

        if(board[y][x] == 1 || board[y][x] == 2){
            return false;
        }

        return true;
    }

    public static void increaseMagma(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(volcano[i][j] > 0){
                    magma[i][j] += 10;
                }
            }
        }
    }

    public static void bomb(){
        int[][] fever = new int[N][N];
        boolean[][] visited = new boolean[N][N]; // 방문한 화산인지?
        Queue<int[]> mountains = new ArrayDeque<>();

        // 압력으로만 분출할 수 있는 화산 모두 넣기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(volcano[i][j] > 0 && magma[i][j] >= volcano[i][j]){
                    mountains.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while(!mountains.isEmpty()){
            int[] cur = mountains.poll();
            int curFever = volcano[cur[0]][cur[1]];
            fever[cur[0]][cur[1]] += curFever;

            for(int i = 0 ; i < 4; i++){
                int y = cur[0];
                int x = cur[1];
                int remainFever = curFever / 2;
                while(remainFever > 0){
                    int yy = y + dy[i];
                    int xx = x + dx[i];

                    if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                        break;
                    }

                    if(board[yy][xx] == 1){
                        break;
                    }

                    fever[yy][xx] += remainFever;
                    //연쇄 폭발로 새롭게 폭발 준비가 된 화산을 q에 넣어주기
                    if(volcano[yy][xx] > 0 && !visited[yy][xx]
                        && magma[yy][xx] + fever[yy][xx] >= volcano[yy][xx]){
                            mountains.add(new int[]{yy, xx});
                            visited[yy][xx] = true;
                        }
                    remainFever /= 2;
                    y = yy;
                    x = xx;
                }
            }
        }

        for(Turtle turtle: turtles){
            if(!endTurtle[turtle.num] && fever[turtle.y][turtle.x] >= 20){
                endTurtle[turtle.num] = true;
                turtleCount++;
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(visited[i][j]){ // 분출한 화산 초기화
                    magma[i][j] = 0;
                }
            }
        }
    }

    static class Turtle{
        int num;
        int y;
        int x;

        public Turtle(int num, int y, int x){
            this.num = num;
            this.y = y;
            this.x = x;
        }
    }
}
