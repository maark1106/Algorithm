import java.util.*;

/*
    풀이1
    1. 가장 늦게 나올 수 있는 시간을 구해야 함.
    2. 인원 제한이 있으므로 막지막 문 닫고 타야됨.
        + 넉넉하다면 막차에 타는 게 젤 늦음
    3. 단 같은 시간에 오면 뒤에 서야 하니까 인원 초과하는 지 고려
    
    - 타임테이블을 숫자로 변경
    - timetable 오름차순 정렬
    - 0000 ~ 마지막 셔틀 출발 시간까지 1분 단위로 설정
    - 로직대로 앞에서부터 몇명 보내고 콘도 탈 수 있는지 확인

    풀이2
    1. n번 버스 보내는 시뮬레이션
    2. 이때 마지막 버스에 타는 것이 콘 입장에서 가장 늦은 시간
    3. 만약 마지막 버스에서 사람이 더 탈 수 있으면 버스 도착 시간에 콘이 타는 것이 가장 늦은 시간
      하지만 사람이 다 찼다면 마지막 사람이 탄 시간 -1 이 콘이 탈 수 있는 마지막 시간
      (같은 시간에 오면 맨 뒤로 가므로)
    
    시간복잡도
    2000 x log2000
    
*/

class Solution {
    
    int[] timeTable;
    
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        timeTable = new int[timetable.length];
        for(int i = 0 ; i < timeTable.length; i++){
            String[] s = timetable[i].split(":");
            int hour = Integer.parseInt(s[0]);
            int minute = Integer.parseInt(s[1]);
            timeTable[i] = hour * 60 + minute;
        }
        
        Arrays.sort(timeTable);
        
        int idx = 0;
        for(int i = 0; i < n; i++){
            int curTime = 9 * 60 + i * t;
            int lastTime = 0;
            int cnt = 0;
            
            while(cnt < m && idx < timeTable.length && timeTable[idx] <= curTime){
                lastTime = timeTable[idx];
                idx++;
                cnt++;
            }
            
            if(i == n - 1){ // 마지막 탑승에서
                int time = 0;
                if(cnt < m){ // 자리 남아있다면 출발 시간이 마지막 시간
                    time = curTime;
                }
                else{ // 자리가 남지 않았다면 마지막 동료 - 1
                    time = lastTime - 1;
                }
                
                String hour = String.format("%02d", time / 60);
                String minute = String.format("%02d", time % 60);
                answer = hour + ":" + minute;
            }
        }
        
        return answer;
    }
    
    
}
