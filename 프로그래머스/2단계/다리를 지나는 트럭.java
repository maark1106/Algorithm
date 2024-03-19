import java.util.*;
import java.io.*;

class Solution {
   	    int solution(int bridge_length, int weight, int[] truck_weights) {
        
        int answer = 0;
        Queue<Integer> bridge = new LinkedList<>();
        
        for(int i = 0; i < bridge_length;i++){
            bridge.add(0);
        }
       
        int sumWeight = 0;
        int truckIdx = 0;
        
        while(!bridge.isEmpty()){
            sumWeight -= bridge.poll(); // 먼저 들어간 차 빠지기
            
            if(truckIdx < truck_weights.length){
                if(sumWeight + truck_weights[truckIdx] <= weight)    {
                    sumWeight += truck_weights[truckIdx];
                    bridge.add(truck_weights[truckIdx++]);
                }
                else{
                    bridge.add(0);
                }
            }
            answer++;
        }
        

        return answer;
    }
}
