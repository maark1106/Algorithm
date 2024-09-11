import java.util.*;

// 이분탐색 사용 
class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int maxValue = 0;
        for(int stone : stones){
            if(maxValue < stone){
                maxValue = stone;
            }
        }
        
        int left = 1;
        int right = maxValue;
        
        while(left <= right){
            int mid = (left + right) / 2;
            
            int cnt = 0;
            boolean flag = true;
            for(int i = 0 ; i < stones.length; i++){
                if(stones[i] < mid){
                    cnt++;
                }else{
                    cnt = 0;
                }
                
                if(cnt == k){
                    flag = false;
                    break;
                }
            }
            
            if(flag){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        
        answer = right;
        
        return answer;
    }
}

//Deque 사용
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
