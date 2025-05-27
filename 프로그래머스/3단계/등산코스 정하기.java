import java.util.*;

class Solution {
    
    /*
        0. 시작 지점 -> 산봉우리까지 가는 경로 최댓값 중 최솟값 찾기
        1. 시작점 전부 pq에 넣기
        2. 산봉우리 만나면 continue (산봉우리에서 다른 곳 더 안가도 됨)
        3. 지금까지 온 거리가 이미 distance[] 보다 크다면 continue
        4. 작다면 distance[] 갱신        
    */
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        HashSet<Integer> starts = new HashSet<>();
        HashSet<Integer> ends = new HashSet<>();
        List<Node>[] graph = new ArrayList[n + 1];
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        for(int i = 0 ; i < gates.length; i++){
            starts.add(gates[i]);
            distance[gates[i]] = 0;
            pq.add(new Node(gates[i], 0));
        }
        
        for(int i = 0 ; i < summits.length; i++){
            ends.add(summits[i]);
        }
        
        for(int i = 0 ; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0 ; i < paths.length; i++){
            int s = paths[i][0];
            int e = paths[i][1];
            int w = paths[i][2];
            graph[s].add(new Node(e, w));
            graph[e].add(new Node(s, w));
        }
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(ends.contains(cur.e)){
                continue;
            }
            
            if(distance[cur.e] < cur.dis){
                continue;
            }
            
            for(Node next: graph[cur.e]){
                int maxDis = Math.max(cur.dis, next.dis);
                if(distance[next.e] > maxDis){
                    distance[next.e] = maxDis;
                    pq.add(new Node(next.e, distance[next.e]));
                }
            }
        }
        
        int minDis = Integer.MAX_VALUE;
        int minNode = -1;
        for(int i = 1; i<= n; i++){
            if(ends.contains(i)){
                if(minDis > distance[i]){
                    minDis = distance[i];
                    minNode = i;
                }
            }
        }
        
        return new int[]{minNode, minDis};
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
