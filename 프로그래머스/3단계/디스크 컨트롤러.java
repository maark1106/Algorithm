import java.util.*;

/*
    
    풀이
    1. jobs를 시작 시간 순으로 정렬한다
        pq생성
            - 작업 시간 오름차순
            - 작업 요청 시간 오름차순
            - 작업 번호 오름차순
    2. pq에 맨 처음 작업을 넣는다.
    3. pq에서 poll하고 현재 시간을 계산한다.(이전까지 시간 + 작업 시간)
    4. jobs에서 현재 시간보다 짧거나 같은 작업을 모두 넣는다
    5. 3 ~ 4를 반복한다.

*/

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<Job> pq = new PriorityQueue<>((j1, j2) -> {
           if(j1.runningTime == j2.runningTime){
               if(j1.requestTime == j2.requestTime){
                   return Integer.compare(j1.num, j2.num);
               }
               return Integer.compare(j1.requestTime, j2.requestTime);
           }
            return Integer.compare(j1.runningTime, j2.runningTime);
        });
        
        List<Job> jobList = new ArrayList<>();
        for(int i = 0; i < jobs.length; i++){
            jobList.add(new Job(jobs[i][1], jobs[i][0], i));
        }
        
        // jobs requestTime이 오름차순으로 안 주어질 수 있으므로 정렬
        Collections.sort(jobList, (j1, j2) -> {
            return Integer.compare(j1.requestTime, j2.requestTime);    
        });
        
        // 처음 작업은 수동으로 넣어주기
        int startTime = jobList.get(0).requestTime;
        int idx = 0;
        while(idx < jobs.length && jobList.get(idx).requestTime == startTime){
            pq.add(jobList.get(idx));
            idx++;
        }
        
        int curTime = startTime;
        
        while(!pq.isEmpty()){
            Job cur = pq.poll();
            curTime += cur.runningTime;
            answer += curTime - cur.requestTime; // 작업별 소요 시간 = (현재 작업 끝난시간 - 작업 요청 시간)
            
            // 스케쥴링 큐는 비어있는데 다음 작업이 현재 시간보다 뒤에 있는 경우
            // 뒷 작업 시작 시간으로 이동해줘야 함
            if(pq.isEmpty() && idx < jobs.length 
               && jobList.get(idx).requestTime > curTime){
                curTime = jobList.get(idx).requestTime;
            }
            
            while(idx < jobs.length && jobList.get(idx).requestTime <= curTime){
                pq.add(jobList.get(idx));
                idx++;
            }
        }
        
        return answer / jobs.length;
    }
    
    class Job{
        int runningTime;
        int requestTime;
        int num;
        
        public Job(int runningTime, int requestTime, int num){
            this.runningTime = runningTime;
            this.requestTime = requestTime;
            this.num = num;
        }
    }
}
