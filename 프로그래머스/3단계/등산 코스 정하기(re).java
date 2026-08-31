import java.util.*;

/*

    풀이
    1. 시작 지점을 모두 pq에 넣기
    2. 각 봉우리에서 해당 출입점까지 최대 시간 중 최소 구하기

*/

class Solution {
    
    List<Node>[] graph;
    boolean[] summit;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {0, Integer.MAX_VALUE};
        
        summit = new boolean[n + 1];
        for(int i = 0 ; i < summits.length; i++){
            summit[summits[i]] = true;
        }
        
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0 ; i < paths.length; i++){
            int a = paths[i][0];
            int b = paths[i][1];
            int dis = paths[i][2];
            graph[a].add(new Node(b, dis));
            graph[b].add(new Node(a, dis));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        
        for(int i = 0 ; i < gates.length; i++){
            pq.add(new Node(gates[i], 0));
            dis[gates[i]] = 0;
        }
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(cur.dist > dis[cur.e]){
                continue;
            }
            
            for(Node next: graph[cur.e]){
                //현재까지 온 비용 중 max vs 다음 이동 비용 중 max
                int cost = Math.max(cur.dist, next.dist);
                
                if(cost >= dis[next.e]){ // 크거나 같으면 이전에 거쳐간 노드가 더 최솟값
                    continue;
                }
                
                dis[next.e] = cost; // 최솟값 갱신
                
                if(!summit[next.e]){ // 봉우리 아닐 때만 넣기
                    pq.add(new Node(next.e, cost));   
                }
                else{ //봉우리면 최솟값 갱신
                    if(answer[1] > dis[next.e]){
                        answer[0] = next.e;
                        answer[1] = dis[next.e];
                    }
                    else if(answer[1] == dis[next.e] && answer[0] > next.e){
                        //거리가 같다면 봉우리 번호가 낮을 때만 갱신
                        answer[0] = next.e;
                        answer[1] = dis[next.e];
                    }
                }
            }
        }
        
        return answer;
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
