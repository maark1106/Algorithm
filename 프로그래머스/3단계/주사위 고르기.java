import java.util.*;

/*
    1. 모든 조합 구하기
    2. 조합에서 sum을 sumA, sumB 나눠서 list에 넣고 오름차순 정렬
    3. A의 한가지 값으로 sumB list를 이분탐색 진행 -> right의 index + 1이 큰 개수
    4. maxCnt 값 갱신

*/

class Solution {
    
    int[] answer;
    int maxCnt;
    boolean[] visited;
    int N;
    
    public int[] solution(int[][] dice) {
        maxCnt = 0;
        N = dice.length;
        answer = new int[N / 2];
        visited = new boolean[N];
        
        DFS(0, 0, dice);
        
        return answer;
    }
    
    void DFS(int depth, int idx, int[][] dice){
        if(depth == N / 2){ // 조합은 visited로 구분된 상태 
            List<Integer> sumA = new ArrayList<>();
            List<Integer> sumB = new ArrayList<>();
            
            sumOfAB(0, 0, 0, sumA, dice, true);
            sumOfAB(0, 0, 0, sumB, dice, false);
            
            Collections.sort(sumA);
            Collections.sort(sumB);
            
            int totalCnt = 0;
            for(int sum: sumA){
                totalCnt += getIdx(sum, sumB);                
            }
            
            if(totalCnt > maxCnt){
                maxCnt = totalCnt;
                int idx1 = 0;
                for(int j = 0 ; j < N;j++){
                    if(visited[j]){
                        answer[idx1++] = j + 1;
                    }
                }
            }
        }
        
        for(int i = idx; i < N; i++){
            visited[i] = true;
            DFS(depth + 1, i + 1, dice);
            visited[i] = false;
        }
    }
    
    int getIdx(int target, List<Integer> sumB){
        int left = 0;
        int right = sumB.size() - 1;
        
        while(left <= right){
            int mid = (left + right) / 2;
            
            if(target > sumB.get(mid)){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        
        return right + 1;
    }
    
    void sumOfAB(int depth, int idx, int sum, List<Integer> sumList,
                 int[][] dice, boolean isA){
        if(depth == N / 2){
            sumList.add(sum);
            return;
        }
        
        for(int i = idx; i < N; i++){
            if((visited[i] && isA) || (!visited[i] && !isA)){ // check된 건 A임
                for(int j = 0 ; j < 6; j++){
                    sumOfAB(depth + 1, i + 1, sum + dice[i][j], sumList, dice, isA);
                }
            }
        }
    }
}
