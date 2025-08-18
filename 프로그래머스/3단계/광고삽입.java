import java.util.*;

/*
    1. 0 ~ play time까지 일차원 배열로 표시
    2. logs도 stard, end 각각 넣기
    3. adv_time까지 최댓값 계산
    4. i++마다 
        벗어나는 범위 빼주기
        새로 들어오는 범위 더해주기
        -> 최댓값 비교 후 갱신

*/

class Solution {
    
    int maxIdx;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        int playTime = getTime(play_time.split(":"));
        int advTime = getTime(adv_time.split(":"));
        if(playTime == advTime){
            return "00:00:00";
        }
        
        long[] startTimes = new long[playTime + 1];
        long[] endTimes = new long[playTime + 1];
        long[] sumTimes = new long[playTime + 1];
        
        for(int i = 0 ; i < logs.length; i++){
            String[] strs = logs[i].split("-");
            String[] start = strs[0].split(":");
            String[] end = strs[1].split(":");
            
            int startTime = getTime(start);
            int endTime = getTime(end);
            startTimes[startTime]++;
            endTimes[endTime]++;
        }
        
        // 맨 처음 삽입 시 계산        
        long cnt = 0;        
        for(int i = 0 ; i < playTime; i++){ 
            cnt += startTimes[i];
            cnt -= endTimes[i];
            sumTimes[i] = cnt;                     
        }
        
        long maxSumTime = 0;
        long maxTime = 0;
        long sumTime = 0;        
        for(int i = 0; i < advTime; i++){
            sumTime += sumTimes[i];
        }
        
        maxSumTime = sumTime;
        
        for(int i = advTime; i <= playTime; i++){
            sumTime -= sumTimes[i - advTime];
            sumTime += sumTimes[i];
            
            if(sumTime > maxSumTime){
                maxSumTime = sumTime;
                maxTime = i - (advTime - 1);
            }
        }
        
        int h = (int)maxTime / 3600;
        int m = (int)maxTime % 3600 / 60;
        int s = (int)maxTime % 60;
        StringBuilder sb = new StringBuilder();
        if(h < 10){
            sb.append(0);            
        }
        sb.append(h + ":");
        if(m < 10){
            sb.append(0);            
        }
        sb.append(m + ":");
        if(s < 10){
            sb.append(0);            
        }
        sb.append(s);
        
        return sb.toString();
    }
    
    int getTime(String[] strs){
        return Integer.parseInt(strs[0]) * 3600 
            + Integer.parseInt(strs[1]) * 60 + Integer.parseInt(strs[2]);
    }
} 
