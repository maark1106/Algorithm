import java.util.*;


class Solution {
    
    int answer = 0;
    int col;
    int row;
    boolean[] visited;
    Set<String> keys;
    
    public int solution(String[][] relation) {
        
        row = relation.length;
        col = relation[0].length;
        visited = new boolean[col];
        keys = new HashSet<>();
        
        dfs(0, 0, relation);
        
        List<String> res = new ArrayList<>();
        for(String s: keys){            
            res.add(s);
        }
        
        for(String s: keys){ 
            for(int i = res.size() - 1; i >= 0; i--){
                if(!s.equals(res.get(i))){
                    String[] strs = s.split(" ");
                    boolean flag = true;
                    String cur = res.get(i);
                    for(int j = 0; j < strs.length; j++){ //s를 모두 포함한다면 res지우기
                        if(!cur.contains(strs[j])){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        res.remove(i);
                    }
                }
            }
        }
        
        return res.size();
    }
    
    void dfs(int depth, int idx, String[][] relation){
        if(depth == col){            
            return;
        }                
        
        for(int i = idx ; i < col; i++){
            visited[i] = true;
            checkUnique(relation);
            dfs(depth + 1, i + 1, relation);
            visited[i] = false;
        }
    }
    
    void checkUnique(String[][] relation){
        Set<String> storage = new HashSet<>();              
        
        for(int i = 0; i < row; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j < col; j++){
                if(visited[j]){
                    sb.append(relation[i][j]);
                }
            }
            storage.add(sb.toString());
        }
        
        if(storage.size() == row){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < col; i++){
                if(visited[i]){
                    sb.append(i + " ");
                }
            }
            keys.add(sb.toString());
        }
    }
}
