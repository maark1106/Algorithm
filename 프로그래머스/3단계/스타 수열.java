import java.util.*;

class Solution {
    
    HashMap<Integer,Integer> storage = new HashMap<>();
    
    public int solution(int[] a) {
        int answer = 0;
        
        for(int i = 0 ; i < a.length; i++){
            storage.put(a[i], storage.getOrDefault(a[i], 0) + 1);
        }
        
        for(int num : storage.keySet()){
            if(answer >= storage.get(num)){
                continue;
            }
            
            int count = 0;
            for(int i = 0; i < a.length - 1; i++){
                if(a[i] != num && a[i + 1] != num){
                    continue;
                }
                if(a[i] == num && a[i + 1] == num){
                    continue;
                }
                
                i++;
                count++;
            }
            
            answer = Math.max(count, answer);
        }
        
        return answer * 2;
    }
}
