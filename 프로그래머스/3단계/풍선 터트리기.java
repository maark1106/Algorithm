import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        boolean[] minLeft = new boolean[a.length];
        boolean[] minRight = new boolean[a.length];
        
        int minValue = Integer.MAX_VALUE;
        
        for(int i = 0 ; i < a.length; i++){
            if(a[i] < minValue){
                minValue = a[i];
                minLeft[i] = true;
            }
        }
        
        minValue = Integer.MAX_VALUE;
        for(int i = a.length - 1 ; i >= 0; i--){
            if(a[i] < minValue){
                minValue = a[i];
                minRight[i] = true;
            }
        }

        for(int i = 0 ; i < a.length; i++){
            if(minLeft[i] || minRight[i]){
                answer++;
            }
        }
        
        return answer;
    }
}
