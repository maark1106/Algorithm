import java.util.*;

/*
    풀이
    1. 마지막부터 탐색하여 배달, 수거 포인트 2개로 나누기
    2. 배달, 수거를 해야하는 끝지점 x 2하고 해당 개수만큼 재고 차감
    3. 만약 트럭 공간이 남는다면 그만큼 앞으로 이동 후 남은 개수 차감
    4. 두 포인터가 -1이라면 종료

*/

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int dIdx = -1;
        int pIdx = -1;
        
        for(int i = n - 1; i >= 0; i--){
            if(deliveries[i] != 0){
                dIdx = i;
                break;
            }
        }
        
        for(int i = n - 1; i >= 0; i--){
            if(pickups[i] != 0){
                pIdx = i;
                break;
            }
        }
        
        // 둘중 하나라도 남아있다면
        while(pIdx >= 0 || dIdx >= 0){
            int curIdx = Math.max(pIdx, dIdx);
            answer += (curIdx + 1) * 2;
            
            int dCap = cap;
            int pCap = cap;
            
            // 배달부터
            while(dCap > 0 && dIdx >= 0){
                // 트럭 담을 수 있는 용량보다 양이 많다면
                if(deliveries[dIdx] > dCap){
                    deliveries[dIdx] -= dCap;
                    dCap = 0;
                }
                else{
                    dCap -= deliveries[dIdx];
                    deliveries[dIdx] = 0;
                    
                    // 다음 배달할 집 찾기
                    while(dIdx >= 0 && deliveries[dIdx] == 0){
                        dIdx--;
                    }
                }
            }
            
            // 수거하기
            while(pCap > 0 && pIdx >= 0){
                if(pickups[pIdx] > pCap){
                    pickups[pIdx] -= pCap;
                    pCap = 0;
                }
                else{
                    pCap -= pickups[pIdx];
                    pickups[pIdx] = 0;
                    
                    // 다음 수거할 집 찾기
                    while(pIdx >= 0 && pickups[pIdx] == 0){
                        pIdx--;
                    }
                }
            }
        }
        
        return answer;
    }
    
 
    
}
