import java.util.*;

/*
    1. k진수로 변환하기
    2. 0을 기준으로 파싱해서 숫자를 list에 넣기
        - string.substring 활용
    3. list에 있는 숫자를 소수인지 판단하기
    
*/

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String changeNumber = Long.toString(n, k).toString(); // 진법 변환
        int left = 0;
        int right = 0;        
        String[] strs = changeNumber.split("0");
        
        for(int i = 0 ; i < strs.length; i++){
            if(strs[i].isEmpty()){
                continue;
            }
            if(isPrime(Long.parseLong(strs[i]))){
                answer++;
            }
        }
                
        return answer;
    }
    
    boolean isPrime(long number){
        if(number == 1){
            return false;
        }
        
        for(long i = 2; i * i <= number; i++){
            if(number % i == 0){
                return false;
            }
        }
        
        return true;
    }
}
