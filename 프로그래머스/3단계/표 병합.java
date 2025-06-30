import java.util.*;
import java.io.*;

class Solution {
    
    /*
        분리집합으로 하는데 2차원 -> 1차원으로 변경
        
        1. update
            1-1. 인자 4개 update는 그냥 변경
            1-2. 인자 3개 update는 셀 돌면서 변경
        2. merge 
            union 하기
        3. unmerge
            자기 자신으로 부모 되돌리기
            나머지 값은 EMPTY 넣기 
            r c 위치는 값 유지
        4. print 
            print하기
    
    */
    
    List<String> res;    
    String[][] board;
    int[] parents;
    
    public String[] solution(String[] commands) {
        board = new String[50][50];
        parents = new int[2500];
        res = new ArrayList<>();
        
        for(int i = 0 ; i < 2500; i++){
            parents[i] = i;
        }
        
        for(int i = 0 ; i < 50; i++){
            Arrays.fill(board[i], "EMPTY");
        }
        
        for(int k = 0 ; k < commands.length; k++){
            String[] strs = commands[k].split(" ");
            if(strs[0].equals("UPDATE")){
                if(strs.length == 4){ // update는 그냥 변경
                    int r = Integer.parseInt(strs[1]) - 1;
                    int c = Integer.parseInt(strs[2]) - 1; 
                    int p = find(r * 50 + c);
                    board[p / 50][p % 50] = strs[3];
                }
                else{
                    changeAllStr(strs[1], strs[2]);
                }
            }
            else if(strs[0].equals("MERGE")){
                int r1 = Integer.parseInt(strs[1]) - 1;
                int c1 = Integer.parseInt(strs[2]) - 1;       
                int r2 = Integer.parseInt(strs[3]) - 1;
                int c2 = Integer.parseInt(strs[4]) - 1;    
                int p1 = r1 * 50 + c1;
                int p2 = r2 * 50 + c2;
                int root1 = find(p1);
                int root2 = find(p2);
                if(root1 == root2)){
                    continue;
                }
                
                String s1 = board[root1 / 50][root1 % 50];
                String s2 = board[root2 / 50][root2 % 50];
                String s = s1.equals("EMPTY") ? s2 : s1;
                
                union(p1, p2);
                int p = find(p1);
                board[p / 50][p % 50] = s;
            }
            else if(strs[0].equals("UNMERGE")){
                int r = Integer.parseInt(strs[1]) - 1;
                int c = Integer.parseInt(strs[2]) - 1;
                int p = find(r * 50 + c);
                String s = board[p / 50][p % 50];
                
                List<Integer> storage = new ArrayList<>();
                for(int i = 0; i < 50; i++){
                    for(int j = 0 ; j < 50; j++){
                        if(find(i * 50 + j) == p){
                            storage.add(i * 50 + j);
                        }
                    }
                }
                
                for(int num: storage){
                    parents[num] = num;
                    board[num / 50][num % 50] = "EMPTY";
                }
                
                board[r][c] = s;
            }
            else if(strs[0].equals("PRINT")){
                int r = Integer.parseInt(strs[1]) - 1;
                int c = Integer.parseInt(strs[2]) - 1;
                int p = find(r * 50 + c);
                res.add(board[p / 50][p % 50]);
            }
        }
        
        String[] answer = new String[res.size()];
        for(int i = 0 ; i < answer.length; i++){
            answer[i] = res.get(i);
        }
        
        return answer;
    }
    
    void union(int a, int b){
        a = find(a);
        b = find(b);
        
        if(a != b){
            parents[b] = a;
        }       
    }
    
    int find(int a){
        if(parents[a] == a){
            return a;
        }
        
        return parents[a] = find(parents[a]);
    }
    
    void changeAllStr(String s1, String s2){
        for(int i = 0 ; i < 50; i++){
            for(int j = 0 ;j < 50; j++){
                if(board[i][j].equals(s1)){
                    board[i][j] = s2;
                }
            }
        }
    }
}
