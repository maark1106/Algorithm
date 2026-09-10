import java.util.*;

class Solution {
    
    int[] dy = {1, 0, -1, 0}; // 하(0), 우(1), 상(2), 좌(3)
    int[] dx = {0, 1, 0, -1};

    public int solution(int[][] board) {
        int N = board.length;
        int[][][] cost = new int[N][N][4];
        int answer = Integer.MAX_VALUE;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        
        for (int i = 0; i < 4; i++) {
            cost[0][0][i] = 0;
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0, 0)); // 시작 방향 0
        pq.offer(new Node(0, 0, 0, 1)); // 시작 방향 1
        

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.y == N - 1 && cur.x == N - 1){
                answer = Math.min(answer, cur.cost);
                continue;
            }
            
            for(int i = 0; i < 4; i++){
                int yy = cur.y + dy[i];
                int xx = cur.x + dx[i];
                
                if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                    continue;
                }
                
                if(board[yy][xx] == 1 || Math.abs(i - cur.dir) == 2){
                    continue;
                }
                
                int nextCost = cur.dir == i ? cur.cost + 100 : cur.cost + 600;
                if(nextCost >= cost[yy][xx][i]){
                    continue;
                }
                
                cost[yy][xx][i] = nextCost;
                pq.add(new Node(yy, xx, nextCost, i));
            }
        }

        return answer; 
    }
    
    class Node implements Comparable<Node> {
        int y, x, cost, dir;
        
        Node(int y, int x, int cost, int dir) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.dir = dir;
        }
        
        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }
}
