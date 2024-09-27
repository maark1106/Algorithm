import java.util.*;

class Solution {
    
    int N;
    int[][] garo = {
            {0,-1,0,-1}, {0,1,0,1},
            {-1,0,0,-1}, {-1,1,0,0},
            {0,0,1,-1}, {0,1,1,0},
            {-1,0,-1,0},{1,0,1,0}
    };

    int[][] sero = {
            {-1,0,-1,0}, {1,0,1,0},
            {0,-1, -1, 0}, {1,-1,0,0},
            {0,0,-1,1}, {1,0,0,1},
            {0,-1,0,-1},{0,1,0,1}
    };

    public int solution(int[][] board) {
        int answer = 0;

        N = board.length;
        answer = BFS(board);

        return answer;
    }

    int BFS(int[][] board){
        int res = 0;
        boolean[][][][] visited = new boolean[N][N][N][N];
        Queue<Pos> q = new LinkedList<>();
        visited[0][0][0][1] = true;
        q.add(new Pos(0,0,0,1,0));

        while(!q.isEmpty()){
            Pos cur = q.poll();         
            if(cur.y2 == N - 1 && cur.x2 == N - 1){
                res = cur.count;
                break;
            }

            if(cur.y1 == cur.y2){ // 가로
                for(int i = 0 ; i < 8; i++){
                    int yy1 = cur.y1 + garo[i][0];
                    int xx1 = cur.x1 + garo[i][1];
                    int yy2 = cur.y2 + garo[i][2];
                    int xx2 = cur.x2 + garo[i][3];

                    if(yy1 < 0 || yy1 >= N || xx1 < 0 || xx1 >= N ||
                            yy2 < 0 || yy2 >= N || xx2 < 0 || xx2 >= N){
                        continue;
                    }
                    if(visited[yy1][xx1][yy2][xx2]){
                        continue;
                    }

                    if(i == 0 || i == 1 || i == 6 || i == 7){
                        if(board[yy1][xx1] == 1 || board[yy2][xx2] == 1){
                            continue;
                        }
                    }
                    else if(i == 2 || i == 3){
                        if(cur.y1 == 0 || cur.y2 == 0 ||
                                board[cur.y1 - 1][cur.x1] == 1 || board[cur.y2 - 1][cur.x2] == 1){
                            continue;
                        }
                    }
                    else{
                        if(cur.y1 == N - 1 || cur.y2 == N - 1 ||
                                board[cur.y1 + 1][cur.x1] == 1 || board[cur.y2 + 1][cur.x2] == 1){
                            continue;
                        }
                    }

                    visited[yy1][xx1][yy2][xx2] = true;
                    q.add(new Pos(yy1, xx1, yy2, xx2, cur.count + 1));
                }
            }
            else{ // 세로
                for(int i = 0 ; i < 8; i++){
                    int yy1 = cur.y1 + sero[i][0];
                    int xx1 = cur.x1 + sero[i][1];
                    int yy2 = cur.y2 + sero[i][2];
                    int xx2 = cur.x2 + sero[i][3];

                    if(yy1 < 0 || yy1 >= N || xx1 < 0 || xx1 >= N ||
                            yy2 < 0 || yy2 >= N || xx2 < 0 || xx2 >= N){
                        continue;
                    }
                    if(visited[yy1][xx1][yy2][xx2]){
                        continue;
                    }

                    if(i == 0 || i == 1 || i == 6 || i == 7){
                        if(board[yy1][xx1] == 1 || board[yy2][xx2] == 1){
                            continue;
                        }
                    }
                    else if(i == 2 || i == 3){
                        if(cur.x1 == 0 || cur.x2 == 0 ||
                                board[cur.y1][cur.x1 - 1] == 1 || board[cur.y2][cur.x2 - 1] == 1){
                            continue;
                        }
                    }
                    else{
                        if(cur.x1 == N - 1 || cur.x2 == N - 1 ||
                                board[cur.y1][cur.x1 + 1] == 1 || board[cur.y2][cur.x2 + 1] == 1){
                            continue;
                        }
                    }

                    visited[yy1][xx1][yy2][xx2] = true;
                    q.add(new Pos(yy1, xx1, yy2, xx2, cur.count + 1));
                }
            }
        }

        return res;
    }

    class Pos{
        int y1;
        int x1;
        int y2;
        int x2;
        int count;

        Pos(int y1, int x1, int y2, int x2, int count){
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
            this.count = count;
        }       
    }
}
