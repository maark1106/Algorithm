import java.util.*;
import java.io.*;

class Main{

    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};
    int dir = 0;
    Deque<Node> q;
    int curY;
    int curX;
    int N;
    HashMap<Integer, Character> dirs;
    int res;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        boolean[][] board = new boolean[N + 1][N + 1];
        dirs = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= K; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            board[y][x] = true;
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        for(int i = 1; i<= L;i++){
            st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            dirs.put(second, c);
        }

        q = new ArrayDeque<>();
        q.addLast(new Node(1, 1));
        curY = 1;
        curX = 1;

        res = 0;
        while(true){
            res++;
            curY += dy[dir];
            curX += dx[dir];
            if(isOver()){
                break;
            }

            q.addLast(new Node(curY, curX));
            if(board[curY][curX]){ // 사과가 존재
                board[curY][curX] = false;
            }else{ // 사과 x
                q.removeFirst();
            }

            dirChange();
        }

        System.out.print(res);
    }

    boolean isOver(){
        if(curY <= 0 || curY > N || curX <= 0 || curX > N){
            return true;
        }
        for(Node node : q){
            if(node.y == curY && node.x == curX){
                return true;
            }
        }

        return false;
    }

    void dirChange(){
        if(dirs.containsKey(res)){
            if(dirs.get(res).equals('D')){
                dir = (dir + 1) % 4;
            }
            else{
                dir = (dir + 3) % 4;
            }
        }
    }


    class Node{
        int y;
        int x;

        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
