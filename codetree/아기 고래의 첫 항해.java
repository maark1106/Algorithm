import java.util.*;
import java.io.*;

/*
    풀이

    1. 이동 방향
        상 0 하 1 좌 2 우 3

        1. 현재 바라보는 곳
        2. 왼쪽 90도
        3. 오른쪽 90도
        4. 180도

    d = 0 : (상 좌 우 하) 0 2 3 1
    d = 1 : (하 우 좌 상) 1 3 2 0
    d = 2 : (좌 하 상 우) 2 1 0 3
    d = 3 : (우 상 하 좌) 3 0 1 2
    
    2. 이동 방향(1칸으로 이동)이 다 막혀있거나 방문했다면 
        1. 거리가 가까운 순
        2. 거리가 같다면 행 작은거, 행 같다면 열 작은 거 

        이동 시에는 (좌 하 우 상) 순으로 이동

        -> 좌표로 설정하는 것이 아니라 bfs로 현 지점에서 모두 탐색하기
        방문하지 않은 것은 모두 배열에 넣기
        거리순 정렬, y순 정렬, x순 정렬 -> 셋다 오름차순
    
    3. 2번에서 배열에 아무것도 없다면 종료
        

    시간 복잡도
    N x N x N X N

*/



public class Main {

    static int[][] board; // 방문했다면 2
    static int N;
    static Pos fish;
    static StringBuilder sb;
    static int[][] fishDir = {{0, 2, 3, 1}, {1, 3, 2, 0}, {2, 1, 0, 3}, {3, 0, 1, 2}};
    // 1칸 탐색 방향 상 하 좌 우 
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    // 2칸 이상 탐색 방향 좌 하 우 상
    static int[] moveDir = {2, 1, 3, 0};
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int curY = Integer.parseInt(st.nextToken()) - 1;
        int curX = Integer.parseInt(st.nextToken()) - 1;
        int curD = Integer.parseInt(st.nextToken()) - 1;
        fish = new Pos(curY, curX, 0, curD);

        board = new int[N][N];
        int cnt = 0;
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0){
                    cnt++;
                }
            }
        }
        
        //맨 처음에는 그 자리 출력하기
        sb = new StringBuilder();
        sb.append((fish.y + 1) + " " + (fish.x + 1) + "\n");
        board[fish.y][fish.x] = 2; // 방문 처리
        for(int i = 0; i < cnt - 1; i++){
            move();
        }
        
        System.out.print(sb.toString());
    }

    static void move(){
        //맨 처음에는 한 칸 이동할 수 있는지 확인
        if(moveOne()){ // 한 칸으로 이동했다면 탐색할 필요 없음
            return;
        }

        //한 칸으로 못 갔다면 최단거리로 가야됨
        bfs();
    }

    static void bfs(){
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(fish.y, fish.x, 0, fish.dir));
        boolean[][] visited = new boolean[N][N];
        visited[fish.y][fish.x] = true;
        List<Pos> storage = new ArrayList<>();

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for(int i = 0 ; i < 4; i++){
                int yy = cur.y + dy[moveDir[i]]; // 움직이는 건 좌 하 우 상
                int xx = cur.x + dx[moveDir[i]];

                if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                    continue;
                }

                if(board[yy][xx] == 1 || visited[yy][xx]){ // 산호거나 방문했으면 1로 못감
                    continue;
                }

                visited[yy][xx] = true;
                q.add(new Pos(yy, xx, cur.dis + 1, i));
                if(board[yy][xx] == 0){ // 가상으로 이동은 위에서도 할 수 있지만 이전에 실제로 방문하지 않았어야 함
                    storage.add(new Pos(yy, xx, cur.dis + 1, moveDir[i])); // 이 dir도 좌하우상을 적용
                }
            }
        }

        Collections.sort(storage);
        Pos next = storage.get(0);
        fish.y = next.y;
        fish.x = next.x;
        fish.dir = next.dir;
        board[fish.y][fish.x] = 2;
        sb.append((fish.y + 1) + " " + (fish.x + 1) + "\n");

        return;
    }

    static boolean moveOne(){
        for(int i = 0; i < 4; i++){
            int dir = fishDir[fish.dir][i];
            int yy = fish.y + dy[dir];
            int xx = fish.x + dx[dir];

            if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                continue;
            }

            if(board[yy][xx] != 0){ // 산호거나 방문했으면 1로 못감
                continue;
            }

            //한 칸 이동할 수 있으면 이동하고 방향 바꾸기
            board[yy][xx] = 2;
            fish.y = yy;
            fish.x = xx;
            fish.dir = dir;
            sb.append((yy + 1) + " " + (xx + 1) + "\n");
            return true;
        }

        return false;
    }

    static class Pos implements Comparable<Pos>{
        int y;
        int x;
        int dis;
        int dir; // 0, 1, 2, 3

        public Pos(int y, int x, int dis, int dir){
            this.y = y;
            this.x = x;
            this.dis = dis;
            this.dir = dir;
        }

        @Override
        public int compareTo(Pos p){
            if(this.dis == p.dis){
                if(this.y == p.y){
                    return this.x - p.x;
                }
                return this.y - p.y;
            }
            return this.dis - p.dis;
        }
    }
}
