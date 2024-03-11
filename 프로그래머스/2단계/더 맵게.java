import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for(int i = 0 ; i < scoville.length;i++){
            pQ.add(scoville[i]);
        }
        
        while(pQ.size() > 1){
            if(pQ.peek() >= K){
                break;
            }
            
            int min1 = pQ.poll();
            int min2 = pQ.poll();
            pQ.add(min1 + min2 * 2);
            answer++;
        }
        
        if(pQ.size() == 1 && pQ.peek() < K){
            return -1;
        }
        
        return answer;
    }
}
