import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0 ; i < numbers.length; i++){
            String s = Long.toBinaryString(numbers[i]);
            StringBuilder sb = new StringBuilder(s);
            int sLength = s.length();
            
            int depth = 1;
            while(Math.pow(2, depth) - 1 < sLength){
                depth++;
            }
            
            int size = (int)Math.pow(2, depth) - 1;
            for(int j = 0 ; j < size - sLength; j++){
                sb.insert(0, "0");
            }
            
            if(isAvailable(sb.toString())){
                answer[i] = 1;
            }else{
                answer[i] = 0;
            }
        }
        
        return answer;
    }
        
    boolean isAvailable(String s){
        if(s.length() == 1){
            return true;
        }
        int mid = s.length() / 2;
        String left = s.substring(0, mid);
        String right = s.substring(mid + 1, s.length());
        
        if(s.charAt(mid) == '0'){
            if(isAllZero(left) && isAllZero(right)){
                return true;
            }
            return false;
        }
            
        return isAvailable(left) && isAvailable(right);
    }
        
    
    boolean isAllZero(String s){
        for(int i = 0 ; i < s.length(); i++){
            if(s.charAt(i) == '1'){
                return false;
            }
        }
        return true;
    }
}
