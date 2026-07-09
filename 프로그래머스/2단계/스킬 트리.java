import java.util.*;

/*
    풀이
    1. skill_trees중 skill에 포함되지 않은 건 건너뛰기
    2. skill 중 맨 처음 문자가 먼저 나오면 바로 전 체크하지 않고 체크
    3. 그 다음 거일 때 자기 바로 전 게 체크 되어있는지 확인
        - 만약 체크 안되어있다면 false
*/

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i = 0 ; i < skill_trees.length; i++){
            if(isPossible(skill, skill_trees[i])){
                answer++;
            }
        }
        
        return answer;
    }
    
    boolean isPossible(String skill, String skill_tree){
        HashSet<Character> storage = new HashSet<>();
        boolean[] isLearn = new boolean[26];
        for(int i = 0; i < skill.length(); i++){
            storage.add(skill.charAt(i));
        }
        
        for(int i = 0; i < skill_tree.length(); i++){
            char cur = skill_tree.charAt(i);
            if(!storage.contains(cur)){
                continue;
            }
            
            int idx = 0;
            for(int j = 0 ;j < skill.length(); j++){ // 자기 순서 찾기
                if(skill.charAt(j) == cur){
                    idx = j;
                    break;
                }
            }
            
            if(idx == 0){ // 맨 처음이면 이전 검사 x
                isLearn[cur - 'A'] = true;
            }
            else{ // 맨 처음이 아니면 이전 검사
                char prev = skill.charAt(idx - 1);
                if(!isLearn[prev - 'A']){ //안 배웠다면
                    return false;
                }
                isLearn[cur - 'A'] = true;
            }
        }
        
        return true;
    }
    
}

