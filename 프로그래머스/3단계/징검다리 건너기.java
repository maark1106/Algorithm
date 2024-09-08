import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;

        Deque<Integer> q = new ArrayDeque();

        for(int i = 0; i < stones.length;i++){

            while(!q.isEmpty() && q.getLast() < stones[i]){
                q.removeLast();
            }

            q.addLast(stones[i]);
            
            if(i == k - 1){
                answer = q.getFirst();
            }
            
            if(i < k){
                continue;
            }

            if(q.getFirst() == stones[i - k]){
                q.removeFirst();
            }

            if(answer > q.getFirst()){
                answer = q.getFirst();
            }
        }
        
         if(stones.length == k){
            return q.getFirst();
        }

        return answer;
    }
}
