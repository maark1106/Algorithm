import java.util.*;

/*
    풀이
    1 2 3 4 5 6 7 8 9 10 11 12
    o o o o o
      o o o
      o o
            o o
    1. 시작 지점 오름차순으로 정렬, 같다면 end 내림차순
    2. 카메라++
    3. end보다 이하에 있는 차량은 pass
        end를 계속 갱신해줘야 함. min으로
    4. 기존 카메라로 단속 못한다면 다시 카메라 ++ 하고 반복 
*/

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        
        int idx = 1;
        int answer = 1;
        int cur = routes[0][1];
        while(idx < routes.length){
            if(routes[idx][0] > cur){ // 이전 카메라로 현재 차를 단속할 수 없다면
                cur = routes[idx][1];
                answer++;
                idx++;
            }
            else{
                cur = Math.min(cur, routes[idx][1]);
                idx++;   
            }
        }
        
        return answer;
    }
}
