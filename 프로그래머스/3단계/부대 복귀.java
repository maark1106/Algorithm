import java.util.*;


/*
    1. 지역-지역까지 거리가 동일 -> bfs
    2. 그래프 만들기
    3. 시작 지점부터 bfs로 탐색하기
    4. des에서 모든 목적지까지 최단 경로들을 구한다.
     
    bfs로 풀고 가중치 대비해서 다익스트라로 풀어보기
*/

class Solution {
    
    List<Node>[] graph;
    int[] distance;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new ArrayList[n + 1];
        distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0 ; i < roads.length; i++){
            graph[roads[i][0]].add(new Node(roads[i][1], 1));
            graph[roads[i][1]].add(new Node(roads[i][0], 1));
        }
        
        dijkstra(n, destination);
        int[] res = new int[sources.length];
        for(int i = 0 ; i < sources.length; i++){
            res[i] = distance[sources[i]] == Integer.MAX_VALUE ? -1 : distance[sources[i]];
        }
        
        return res;
    }
    
    void dijkstra(int n, int des){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(des, 0));
        distance[des] = 0;
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            for(Node next: graph[cur.e]){
                int newDis = distance[cur.e] + next.dis;
                if(newDis < distance[next.e]){
                    distance[next.e] = newDis;
                    q.add(new Node(next.e, newDis));
                }
            }
        }
    }
    
    class Node implements Comparable<Node>{
        int e;
        int dis;
        
        public Node(int e, int dis){
            this.e = e;
            this.dis = dis;
        }
        
        @Override
        public int compareTo(Node n){
            return this.dis - n.dis;
        }
    }
    
    
    
}
