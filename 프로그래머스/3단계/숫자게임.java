import java.util.*;

class Solution {
    
    int N;
    
    public int solution(int[] A, int[] B) {
        int answer = -1;
        
        Arrays.sort(A);
        Arrays.sort(B);
        N = A.length;
                
        int right = N - 1;
        int cnt = 0;
        for(int i = N - 1; i >= 0; i--){
            if(A[i] < B[right]){
                right--;
                cnt++;
            }            
        }
        
        answer = cnt;
        return answer;
    }
}
