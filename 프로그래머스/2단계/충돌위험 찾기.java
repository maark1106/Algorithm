import java.util.*;

/*
    풀이
    1. 각 로봇을 초마다 움직여 해당좌표, time에 ++
        - y좌표부터 움직이기, 그 다음 X
        - 목적지 도착했다면 종료하기
    2. 1초 ~ 최대 시간(200)까지 반복문을 돌기
        - 각 구간에 2이상 있으면 ++
    
    시간 복잡도
    (100 + 100) x 100 x 100 = 2000000
    총 이동할 수 있는 거리 x 로봇 수 x 경로 수 
    
*/

class Solution {
    
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        // y, x, k초 -> 개수
        HashMap<String, Integer> storage = new HashMap<>();
        
        int maxTime = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < routes.length; i++){
            int y = points[routes[i][0] - 1][0] - 1;
            int x = points[routes[i][0] - 1][1] - 1;
            
            int time = 0;
            sb.setLength(0);
            sb.append(y + " " + x + " " + time);
            storage.put(sb.toString(), storage.getOrDefault(sb.toString(), 0) + 1);
            
            for(int j = 1; j < routes[i].length; j++){
                int endY = points[routes[i][j] - 1][0] - 1;
                int endX = points[routes[i][j] - 1][1] - 1;
                
                if(y < endY){
                    while(y < endY){
                        time++;
                        y++;
                        sb.setLength(0);
                        sb.append(y + " " + x + " " + time);
                        storage.put(sb.toString(), storage.getOrDefault(sb.toString(), 0) + 1);
                    }
                }
                else if(y > endY){
                    while(y > endY){
                        time++;
                        y--;
                        sb.setLength(0);
                        sb.append(y + " " + x + " " + time);
                        storage.put(sb.toString(), storage.getOrDefault(sb.toString(), 0) + 1);
                    }
                }

                if(x < endX){
                    while(x < endX){
                        time++;
                        x++;
                        sb.setLength(0);
                        sb.append(y + " " + x + " " + time);
                        storage.put(sb.toString(), storage.getOrDefault(sb.toString(), 0) + 1);
                    }
                }
                else if(x > endX){
                    while(x > endX){
                        time++;
                        x--;
                        sb.setLength(0);
                        sb.append(y + " " + x + " " + time);
                        storage.put(sb.toString(), storage.getOrDefault(sb.toString(), 0) + 1);
                    }
                }
            }
            
            maxTime = Math.max(time, maxTime);
        }
        
        for(String s: storage.keySet()){
            if(storage.get(s) >= 2){
                answer++;
            }
        }
        
        return answer;
    }
    
}
