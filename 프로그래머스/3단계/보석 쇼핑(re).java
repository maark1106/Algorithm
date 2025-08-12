import java.util.*;

/*
    1. gems의 Set을 구함
    2. HashMap에 넣어두기
    3. 0번 넣어둔 상태로 시작
    4. 현재 보석 넣기
        while
            만약 map.get(left)의 값이 2이상이다
            left 빼기
        set.size가 모두 모았다면 최소값 비교
*/

class Solution {
    
    HashSet<String> dic;
    HashMap<String, Integer> storage;
    HashSet<String> curDic;
        
    public int[] solution(String[] gems) {        
        init(gems);
        int answer = Integer.MAX_VALUE;
        
        storage.put(gems[0], 1);
        curDic.add(gems[0]);
        if(dic.size() == 1){
            return new int[]{1, 1};
        }
        
        int left = 0;
        int resLeft = 0;
        int resRight = 0;
        for(int i = 1; i < gems.length; i++){
            String gem = gems[i];
            storage.put(gem, storage.get(gem) + 1); // gem 넣어주고 curDic에도 추가
            curDic.add(gem);             
            
            while(left < i && storage.get(gems[left]) > 1){
                storage.put(gems[left], storage.get(gems[left]) - 1); // 하나 빼기
                left++;
            }
            
            if(curDic.size() == dic.size()){ // 만약 다 모았다면 구간 비교
                if(answer > (i - left)){
                    answer = i - left;
                    resRight = i;
                    resLeft = left;
                }                
            }
        }
        
        return new int[]{resLeft + 1, resRight + 1};
    }
    
    void init(String[] gems){
        dic = new HashSet<>();
        for(int i = 0; i < gems.length; i++){
            dic.add(gems[i]);
        }
        storage = new HashMap<>();
        for(String name: dic){
            storage.put(name, 0);
        }
        curDic = new HashSet<>();
    }
}
