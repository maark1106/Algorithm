import java.util.*;

/*

    풀이
    1. stones 원소 값이 200000000이기 때문에 시뮬레이션하면 시간초과
    2. 한 명 지나갈 때마다 모든 돌이 1 감소
    3. 만약 0이면 점프 가능
        -> 하지만 k칸 이상 연속 0이면 더 이상 가지 못함
    4. 그럼 연속되는 k칸 중 최댓값 중 최솟값 구하기
    
    max = 0
    2 4 5 -> 2 max = 2
    4 5 3 -> 3 max = 3
    5 3 2 -> 2 max = 3
    
    근데 반복문을 돌리면서 탐색하기에는 시간 초과 -> 힙 사용
    한 칸마다 최댓값을 꺼낸다. -> 만약 k만큼 구간이 지났다면 폐기한다. 
    

*/

class Solution {
    public int solution(int[] stones, int k) {
        PriorityQueue<Stone> pq = new PriorityQueue<>();
        
        // 처음 k개만큼 넣기
        for(int i = 0 ; i < k; i++){
            pq.add(new Stone(i, stones[i]));
        }
        
        int answer = pq.peek().power;
        
        for(int i = k; i < stones.length; i++){
            //이미 지난 돌이라면 폐기
            while(!pq.isEmpty() && pq.peek().num <= i - k){
                pq.poll();
            }
            
            pq.add(new Stone(i, stones[i]));
            if(pq.peek().power < answer){
                answer = pq.peek().power;
            }
        }
        
        return answer;
    }
    
    
    
    class Stone implements Comparable<Stone>{
        int num;
        int power;
        
        public Stone(int num, int power){
            this.num = num;
            this.power = power;
        }
        
        @Override
        public int compareTo(Stone s){
            return s.power - this.power;
        }
    }
    
    
}
