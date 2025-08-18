import java.util.*;

/*
    개발 언어, 직군, 경력, 소울푸드, 점수  
    1. ""
*/

class Solution {
    
    HashMap<String, List<Integer>> storage;
    
    public int[] solution(String[] info, String[] query) {
        storage = new HashMap<>();
        
        for(int i = 0 ; i < info.length; i++){
            StringBuilder sb = new StringBuilder();
            String[] strs = info[i].split(" ");
            
            createWord(0, "", strs);
        }
        
        for(String s: storage.keySet()){
            Collections.sort(storage.get(s));
        }
        
        int[] answer = new int[query.length];
        for(int i = 0 ; i < query.length; i++){
            query[i] = query[i].replace(" and ", " ");
            String[] strs = query[i].split(" ");
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j < 4; j++){
                sb.append(strs[j]);
            }
            
            if(!storage.containsKey(sb.toString())){
                answer[i] = 0;
                continue;
            }
            
            List<Integer> cur = storage.get(sb.toString());
            if(strs.equals("-")){
                answer[i] = cur.size();
            }
            else{
                int left = 0;
                int right = cur.size() - 1;
                int target = Integer.parseInt(strs[4]);
                while(left <= right){
                    int mid = (left + right) / 2;
                    if(cur.get(mid) >= target){
                        right = mid - 1;
                    }else{
                        left = mid + 1;
                    }
                }
                
                answer[i] = cur.size() - left;
            }
        }
        
        return answer;
    }
    
    void createWord(int depth, String s, String[] strs){
        if(depth == 4){
            if(!storage.containsKey(s)){
                List<Integer> lists = new ArrayList<>();
                storage.put(s, lists);                
            }
            storage.get(s).add(Integer.parseInt(strs[4]));
            return;
        }
        
        createWord(depth + 1, s + "-", strs);
        createWord(depth + 1, s + strs[depth], strs);
    }
}
