import java.util.*;
import java.io.*;


/*
    풀이
    1. 미생물을 해당 좌표에 배치한다.(번호로 배치)
        (만약 이미 미생물이 존재한다면 덮어씀)
    2. 다시 검사하여 미생물이 나눠져 존재한다면 해당 미생물을 모두 제거한다.
    3. 남아있는 미생물을 넓이 순으로 정렬한다.
    4. 새 board에 넓이 순서대로 x좌표가 작고, 그 다음 y좌표가 작은 순 (board y만 거꾸로 보기) 곳을 탐색하여 들어갈 수 있다면 들어간다.
    5. 다시 전부 탐색하여 맞닿아 있는 부분의 곱을 구한다.    

    
*/

public class Main {

    static int[][] board;
    static int N;
    static int M;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static HashSet<Integer> removeList;
    static List<Virus> virusList;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            init(i, startY, startX, endY, endX);
            removeDivideVirus(); // 나뉜 바이러스 삭제 끝나고 바이러스만 남음
            searchNewBoardAndInit(); // 새로운 배양 용기에 재배양하기
            int res = getNearVirus();
            sb.append(res + "\n");
        }

        System.out.println(sb.toString());
    }

     static void init(int num, int startY, int startX, int endY, int endX){
        for(int i = startY; i < endY; i++){
            for(int j = startX; j < endX; j++){
                board[i][j] = num;
            }
        }
    }

    static void removeDivideVirus(){
        boolean[][] visited = new boolean[N][N]; 
        boolean[] checked = new boolean[M + 1];
        removeList = new HashSet<>();
        ArrayList<Virus> allVirusList = new ArrayList<>();
        virusList = new ArrayList<>();
        
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                int num = board[i][j];
                if(num == 0 || visited[i][j]){ // 방문했거나 미생물 없으면 pass
                    continue;
                }

                if(checked[num]){ // 미생물인데 처음 보는 곳이고 이미 검사한 num이라면 분리된 거 
                    visited[i][j] = true;
                    removeList.add(num);
                }
                else{ // 방문 안했다면 bfs로 모든 지점 체크해주기
                    markVirus(visited, num, i, j, allVirusList);
                    checked[num] = true;
                }
            }
        }

        removeVirus(removeList); // 나뉜 미생물은 제거 
        for(Virus virus: allVirusList){ // 구한 모든 미생물의 넓이 중, 삭제 리스트에 없는 것만 다시 담기
            if(!removeList.contains(virus.num)){
                virusList.add(new Virus(virus.num, virus.width));
            }
        }
    }

    static void markVirus(boolean[][] visited, int num, int y, int x, ArrayList<Virus> allVirusList){
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(y, x));
        visited[y][x] = true;
        int cnt = 1;

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for(int i = 0 ; i < 4; i++){
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];

                if(yy < 0 || yy >= N || xx < 0 || xx >=N){
                    continue;
                }
                
                if(board[yy][xx] == num && !visited[yy][xx]){ // 해당 미생물이고 방문하지 않았다면 check
                    visited[yy][xx] = true;
                    cnt++;
                    q.add(new Pos(yy, xx));
                }
            }
        }

        allVirusList.add(new Virus(num, cnt));
    }

    static void removeVirus(HashSet<Integer> removeList){
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                int num = board[i][j];
                if(removeList.contains(num)){
                    board[i][j] = 0;
                }
            }
        }
    }

    static void searchNewBoardAndInit(){
        Collections.sort(virusList); // 넓이 순, 번호 작은 순으로 선택하기
        
        int[][] newBoard = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        List<Pos>[] posList = new ArrayList[M + 1];
        for(int i = 1; i <= M; i++){
            posList[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                //그 전에 가장 왼쪽 위에를 (0, 0) 기준으로 해당 좌표에서 얼마나 떨어져있나 확인하기 (상대 좌표 구하기)
                int num = board[i][j];
                if(num != 0 && !visited[i][j]){
                    getPos(posList[num], i, j, visited, num);
                }
            }
        }

        for(Virus virus: virusList){ // 새 board에 넓이 순서대로 x좌표가 작고, 그 다음 y좌표가 작은 순
            List<Pos> curPosList = posList[virus.num];

            boolean flag = false;
            for(int j = 0 ; j < N; j++){
                for(int i = 0; i < N; i++){
                    if(newBoard[i][j] != 0){
                        continue;
                    }

                    if(isAvailableSeat(i, j, curPosList, newBoard, virus.num)){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    break;
                }
            }
        }

        board = newBoard; // 배양 용기 교체
    }

    static void getPos(List<Pos> posList, int y, int x, boolean[][] visited, int num){
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(y, x));
        visited[y][x] = true;
        posList.add(new Pos(0, 0)); // 맨 왼쪽 위에를 기준

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for(int i = 0 ; i < 4; i++){
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];

                if(yy < 0 || yy >= N || xx < 0 || xx >=N){
                    continue;
                }
                
                if(board[yy][xx] == num && !visited[yy][xx]){ // 해당 미생물이고 방문하지 않았다면 check
                    visited[yy][xx] = true;
                    q.add(new Pos(yy, xx));
                    posList.add(new Pos(yy - y, xx - x)); // 맨 왼쪽(0, 0)을 기준으로 상대 좌표 추가
                }
            }
        }
    }

    static boolean isAvailableSeat(int y, int x, List<Pos> curPosList, int[][] newBoard, int num){
        for(Pos cur: curPosList){
            int yy = y + cur.y;
            int xx = x + cur.x;

            if(yy < 0 || yy >= N || xx < 0 || xx >=N){ // 범위 벗어나면 재배양 못함
                return false;
            }

            if(newBoard[yy][xx] != 0){ // 이미 미생물 있다면 배양 못함
                return false;
            }
        }

        for(Pos cur: curPosList){ // 새로운 배양 용기에 재배양하기
            int yy = y + cur.y;
            int xx = x + cur.x;
            newBoard[yy][xx] = num;
        }
        return true;
    }

    static int getNearVirus(){
        boolean[] checked = new boolean[M + 1];

        int res = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int num = board[i][j];
                if(num != 0 && !checked[num]){
                    res += addNearVirus(num, i, j, checked);
                    checked[num] = true;
                }
            }
        }
        return res;
    }

    static int addNearVirus(int num, int y, int x, boolean[] checked){
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Pos(y, x));
        visited[y][x] = true;
        HashSet<Integer> storage = new HashSet<>();

        int myWidth = 1;
        while(!q.isEmpty()){
            Pos cur = q.poll();

            for(int i = 0 ; i < 4; i++){
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];

                if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                    continue;
                }

                if(board[yy][xx] == 0 || visited[yy][xx]){
                    continue;
                }
                
                if(board[yy][xx] == num){ // 같은 미생물이면 이동만하기
                    visited[yy][xx] = true;
                    q.add(new Pos(yy, xx));
                    myWidth++;
                }
                else if(board[yy][xx] != num){ // 인접한 다른 미생물만 추가
                    storage.add(board[yy][xx]);
                }
            }
        }

        int res = 0;
        for(Virus virus: virusList){
            if(storage.contains(virus.num) && !checked[virus.num]){ // 이전에 이미 계산한 거 제외
                res += myWidth * virus.width;
            }
        }
        return res;
    }

    static class Pos{
        int y;
        int x;

        public Pos(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static class Virus implements Comparable<Virus>{ 
        int num;
        int width;

        public Virus(int num, int width){
            this.num = num;
            this.width = width;
        }

        @Override // 넓이 순, 번호 작은 순 
        public int compareTo(Virus v){
            if(this.width == v.width){
                return this.num - v.num;
            }
            return v.width - this.width; // 넓이가 큰 순
        }
    }
}
