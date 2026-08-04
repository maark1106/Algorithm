import java.util.*;

/*
    풀이
    1. 간선 리스트 만들기
    2. PQ 활용하여 가중치 오름차순 정렬
    3. 모든 간선 넣기
    4. 간선 하나씩 뽑기
        - 같은 집합 : 무시
        - 다른 집합 : 비용 추가
    5. 종료 : 정점 N개일 때 간선 N - 1개면 모두 이어짐

*/

class Solution {
    
    int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> {
            return Integer.compare(e1.dist, e2.dist);
        });
        
        for(int i = 0 ; i < costs.length; i++){
            pq.add(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }
        
        int edgeCnt = 0;
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(union(cur.v1, cur.v2)){ // 섬을 이은 경우
                edgeCnt++;
                answer += cur.dist;
            }
        }
        
        return answer;
    }
    
    public boolean union(int a, int b){
        a = find(a);
        b = find(b);
        
        if(a != b){
            parent[a] = b;
            return true;
        }
        
        return false;
    }
    
    public int find(int a){
        if(parent[a] == a){
            return a;
        }
        
        return parent[a] = find(parent[a]);
    }
    
    class Edge{
        int v1;
        int v2;
        int dist;
        
        public Edge(int v1, int v2, int dist){
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }
    
    
    
}
