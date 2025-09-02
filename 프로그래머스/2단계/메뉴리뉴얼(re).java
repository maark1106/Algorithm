import java.util.*;

/*
    1. 모든 orders를 DFS로 파싱하기
    2. 파싱한 문자열을 map에 추가하기(2이상부터)
    3. map을 순회, 코스에 해당하는 길이의 개수가 2이상이라면 result에 추가
    4. result 정렬
*/

class Solution {
    
    Map<String, Integer> storage = new HashMap<>();
    StringBuilder sb;
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer;
        sb = new StringBuilder();
        
        for(int i = 0 ; i < orders.length; i++){
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = String.valueOf(chars);
        }
        
        for(String s: orders){
            DFS(0, 0, s);
        }
        
        List<String> list = new ArrayList<>();
        
        for(int i = 0 ; i < course.length; i++){
            int cnt = 0;
            for(String s: storage.keySet()){
                if(s.length() == course[i]){
                    cnt = Math.max(cnt, storage.get(s));
                }
            }
            
            if(cnt < 2){
                continue;
            }
            
            for(String s: storage.keySet()){
                if(s.length() == course[i] && storage.get(s) == cnt){
                    list.add(s);
                }
            }
        }
        
        Collections.sort(list);
        answer = new String[list.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    void DFS(int depth, int idx, String s){
        if(depth == s.length()){
            return;
        }
        
        for(int i = idx; i < s.length(); i++){
            sb.append(s.charAt(i));
            if(sb.length() >= 2){
                storage.put(sb.toString(), storage.getOrDefault(sb.toString(), 0) + 1);
            }
            DFS(depth + 1, i + 1, s);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
}
