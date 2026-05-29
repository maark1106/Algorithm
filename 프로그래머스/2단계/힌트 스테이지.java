import java.util.*;
import java.io.*;

/*
    시간 복잡도
    n = 16
    2^n x k
    2^16 x 20 = 1024 x 64 x 20 <= 2000000
    
    풀이
    완전탐색 구현
    - 각 스테이지에서 힌트권을 사는 경우 / 사지 않는 경우
    - 힌트권이 있으면 모두 사용해야 됨
    
    1. dfs
        핵심 : 힌트권 구매 개수를 관리한다.
        - 각 스테이지에서 힌트권 삼 / 안 삼 -> 2가지 경우 분리
            - 만약 산다면 구매 비용을 추가한다.
        - 힌트권 개수에 해당하는 비용을 추가한다
        - 최종 스테이지에서 최소값을 갱신한다.
        
    20 + 30분이 걸린 이유 -> 힌트 개수가 해당 stage 배열을 초과하는 엣지 케이스가 존재했다..
*/

class Solution {
    
    int res;
    int N;
    
    public int solution(int[][] cost, int[][] hint) {
        res = Integer.MAX_VALUE;
        N = cost.length; // stage 수
        
        int[] hintCnt = new int[N];
        simulation(0, 0, cost, hint, hintCnt);
        
        return res;
    }
    
    
    void simulation(int depth, int totalCost, int[][] cost, int[][] hint, int[] hintCnt){
        if(depth == N - 1){ // 마지막은 힌트 사지 못함
            int hintMaxCnt = Math.min(N - 1, hintCnt[depth]);
            totalCost += cost[depth][hintMaxCnt];
            res = Math.min(res, totalCost);
            return;
        }
        
        //힌트 안 사는 경우
        if(hint[depth][0] > 0){ // 힌트권 비용이 0이면 무조건 사는 게 이득 
            int curHintCnt = Math.min(N - 1, hintCnt[depth]); // 힌트 개수
            int curCost = totalCost + cost[depth][curHintCnt];
            simulation(depth + 1, curCost, cost, hint, hintCnt);
        }
        
        //힌트 사는 경우
        int[] nextHintCnt = hintCnt.clone();
        
        int curCost = totalCost + hint[depth][0]; // 힌트 구매 비용만큼 비용 추가
        for(int i = 1; i < hint[depth].length; i++){
            int hintNumber = hint[depth][i] - 1;
            nextHintCnt[hintNumber]++;
        }
        
        int curHintCnt = Math.min(N - 1, nextHintCnt[depth]); // 힌트 개수
        curCost += cost[depth][curHintCnt];
        simulation(depth + 1, curCost, cost, hint, nextHintCnt);
    }
    
    
}
