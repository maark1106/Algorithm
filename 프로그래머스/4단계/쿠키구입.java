import java.util.*;

class Solution {

    public int solution(int[] cookie) {
        int answer = 0;

        int[] sum = new int[cookie.length + 1];
        for(int i = 0; i < cookie.length; i++){
            sum[i + 1] = sum[i] + cookie[i];
        }

        for(int i = 1; i <= cookie.length; i++){
            int left = 0;
            int right = cookie.length;

            while(left < i && right > i){
                int leftCookies = sum[i] - sum[left];
                int rightCookies = sum[right] - sum[i];

                if(leftCookies == rightCookies){
                    if(rightCookies > answer){
                        answer = rightCookies;
                    }
                    break;
                }

                if(leftCookies > rightCookies){
                    left++;
                }else{
                    right--;
                }
            }
        }

        return answer;
    }
}
