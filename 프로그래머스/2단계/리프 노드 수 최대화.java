import java.util.*;

/*
    시간 복잡도
    완탐 2^30지만 가지치기
    
    풀이
    1. 2 x 3의 조합으로 split_limit 이전 모두 완탐
    2. 총 div, 이전 div, 총 분배도, 총 leaf 수
    3. 현재 depth의 최댓값 = min(이전 div * (2 or 3), dist_limit - 총 div)
        - 만약 min이 dist_limit - 총 div 다 채웠다면 탐색 중지
        - 2 * 3 * .. > dist_limit 탐색 중지
*/

class Solution {
    
    int distLimit;
    int splitLimit;
    int res;
    
    public int solution(int dist_limit, int split_limit) {
        
        distLimit = dist_limit;
        splitLimit = split_limit;
        res = 0;
        
        // 이번 depth자식 낳을 준비 leaf, used(분배노드 사용한 수), 현재 split, 자식 못 낳는 leaf
        dfs(1, 0, 1, 0); 
        
        return res;
    }
    
    void dfs(int cur, int usedDiv, int split, int endLeaf){
        res = Math.max(res, cur + endLeaf);
        
        for(int branch = 2; branch <= 3; branch++){
            long curLimit = (long)split * branch;
            if(curLimit > splitLimit){
                continue;
            }
            
            // 이전 모든 리프노드를 분배노드로, 만약 초과됐다면 제한까지만
            int curDiv = Math.min(cur, distLimit - usedDiv);
            
            // if(curDiv <= 0){ // 활용 가능한 분배노드가 없다면 더 탐색 x
            //     continue;
            // }
            
            // 새로 생기는 노드 수 -> 다음 자식 낳을 준비 leaf
            int nextNode = curDiv * branch;
            
            //지금까지 사용한 분배노드
            int nextUsedDiv = usedDiv + curDiv;
            
            //지금까지 사용한 split
            int nextSplit = split * branch;
            
            //넘기지 못한 노드는 리프 확정
            int nextEndLeaf = endLeaf + cur - curDiv;
            dfs(nextNode , nextUsedDiv, nextSplit, nextEndLeaf);
        }
    }
    
}
