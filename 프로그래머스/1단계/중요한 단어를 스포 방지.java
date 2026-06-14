import java.util.*;

/*
    시간 복잡도
    
    
    풀이
    1. 공백으로 구분한다.
        - spoiler_ranges로 check을 만든다.
    2. 스포 방지 단어와 스포 방지 단어가 아닌 것을 구분한다.
    3. 스포 방지를 처음부터 탐색한다
        - 노스포 단어에 없다
        - 앞 스포 단어에 없다
    4. 위 조건을 만족한다면 중요 단어
        - 노스포 단어에 포함하기

*/

class Solution {
    
    boolean[] check;
    
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        check = new boolean[20100];
        for(int i = 0 ; i < spoiler_ranges.length; i++){
            for(int j = spoiler_ranges[i][0]; j <= spoiler_ranges[i][1]; j++){
                check[j] = true;
            }
        }
        
        HashSet<String> noSpoiler = new HashSet<>();
        List<String> spoiler = new ArrayList<>();
        
        int left = 0;
        int right = 0;
        if(message.charAt(0) == ' '){
            left++;
            right++;
        }
        while(true){
            while(true){
                right++;
                if(right == message.length() || message.charAt(right) == ' '){
                    break;
                }
            }
            
            String s = message.substring(left, right);
            if(checkSpoiler(left, right - 1)){
                spoiler.add(s);
            }
            else{
                noSpoiler.add(s);
            }
            
            right++;
            left = right;
            if(right >= message.length()){
                break;
            }
        }
        
        for(int i = 0; i < spoiler.size(); i++){
            if(!noSpoiler.contains(spoiler.get(i))){
                answer++;
                noSpoiler.add(spoiler.get(i));
            }
        }
        
        return answer;
    }
    
    boolean checkSpoiler(int left, int right){
        for(int i = left; i <= right; i++){
            if(check[i]){
                return true;
            }
        }
        return false;
    }
    
}
