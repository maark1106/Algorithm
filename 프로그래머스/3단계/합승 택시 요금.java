import java.util.*;

/*
    플로이드 풀이
    1. 정점이 n이 200이므로 n^3도 가능
    2. 모든 정점을 탐색하여 최소 거리를 구해두기
    3. s지점에서 x 지점을 거쳐 x -> A, x -> B의 최솟값을 구하기
    
*/

class Solution {
    
    final static int MAX = 1000000000;
        
    int[][] dis;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = MAX;
        
        dis = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                dis[i][j] = MAX;
            }
        }
        
        for(int i = 1; i <= n; i++){
            dis[i][i] = 0;
        }
        
        for(int i = 0 ; i < fares.length; i++){
            int n1 = fares[i][0];
            int n2 = fares[i][1];
            int dist = fares[i][2];
            
            dis[n1][n2] = dist;
            dis[n2][n1] = dist;
        }
        
        //플로이드로 갱신
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }
        
        //3. s지점에서 x 지점을 거쳐 x -> A, x -> B의 최솟값을 구하기
        for(int x = 1; x <= n; x++){
            if(dis[s][x] == MAX || dis[x][a] == MAX || dis[x][b] == MAX){
                continue;
            }
            
            int total = dis[s][x] + dis[x][a] + dis[x][b];
            answer = Math.min(answer, total);
        }
        
        return answer;
    }
    
    
    
}

import java.util.*;

/*
    다익스트라 풀이
    
*/

class Solution {
    
    final static int MAX = 1000000000;
    List<Node>[] graph;
        
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = MAX;
    
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0 ; i < fares.length; i++){
            int n1 = fares[i][0];
            int n2 = fares[i][1];
            int dist = fares[i][2];
            
            graph[n1].add(new Node(n2, dist));
            graph[n2].add(new Node(n1, dist));
        }
        
        int[] disS = dijkstra(s, n);
        int[] disA = dijkstra(a, n);
        int[] disB = dijkstra(b, n);
        
        for(int x = 1; x <= n; x++){
            // s -> x (x -> a, x -> b)
            if(disS[x] == MAX || disA[x] == MAX || disB[x] == MAX){
                continue;
            }
            
            int total = disS[x] + disA[x] + disB[x];
            if(answer > total){
                answer = total;
            }
        }
        
        return answer;
    }
    
    int[] dijkstra(int start, int n){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dis = new int[n + 1];
        
        Arrays.fill(dis, MAX);
        pq.add(new Node(start, 0));
        dis[start] = 0;
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            //현재까지 온 거리가 기존 거리보다 크다면 더 이상 탐색할 필요 x
            if(cur.dist > dis[cur.e]){
                continue;
            }
            
            for(Node next: graph[cur.e]){
                if(dis[next.e] > dis[cur.e] + next.dist){
                    dis[next.e] = dis[cur.e] + next.dist;
                    pq.add(new Node(next.e, dis[next.e]));
                }
            }
        }
        
        return dis;
    }
    
    class Node implements Comparable<Node>{
        int e;
        int dist;
        
        public Node(int e, int dist){
            this.e = e;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Node n){
            return this.dist - n.dist;
        }
    }
}
