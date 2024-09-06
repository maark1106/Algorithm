import java.util.*;

public class Solution{
    
    HashMap<String, Integer> bucket = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        String[] answer;
        
        for(int i = 0 ; i < orders.length; i++){
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = String.valueOf(chars);
        }
        
        
        for(int i = 0 ; i < course.length; i++){
            for(String target : orders){
                DFS(target, "", 0, course[i]);
            }                        
        }
        
        List<String> res = new ArrayList<>();
        for(int i = 0 ; i < course.length; i++){
            int maxCnt = 0;
            for(String key : bucket.keySet()){
                if(key.length() == course[i] && maxCnt < bucket.get(key)){
                    maxCnt = bucket.get(key);
                }
            }
            
            if(maxCnt < 2){
                continue;
            }
            
            for(String key : bucket.keySet()){
                if(key.length() == course[i] && maxCnt == bucket.get(key)){
                    res.add(key);
                }
            }
        }
        
        Collections.sort(res);
                
        answer = new String[res.size()];
        for(int i = 0 ; i < res.size();i++){
            answer[i] = res.get(i);
        }
        
        return answer;
    }

    void DFS(String target, String s, int depth, int len){
        if(s.length() == len){
            bucket.put(s, bucket.getOrDefault(s, 0) + 1);
            return;
        }
        
        for(int i = depth; i < target.length(); i++){
            DFS(target, s + target.charAt(i), i + 1, len);
        }
    }

}
