import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int res = 0;
        int n = cards.length;
        
        HashSet<Integer> cur = new HashSet<>();
        HashSet<Integer> storage = new HashSet<>();
        for(int i = 0 ; i < n / 3; i++){
            cur.add(cards[i]);
        }
        
        for(int i = n / 3; i <= n; i+= 2){
            res++;
            if(i == n){
                break;
            }
            int card1 = cards[i];
            int card2 = cards[i + 1];
            
            storage.add(card1);        
            storage.add(card2);   
            
            boolean flag = false;
            for(Integer card : cur){ // coin 0
                if(cur.contains(n + 1 - card)){
                    cur.remove(card);
                    cur.remove(n + 1 - card);
                    flag = true;                    
                    break;
                }
            }
            
            if(!flag && coin >= 1){ // coin 1
                for(Integer card : cur){
                    if(storage.contains(n + 1 - card)){
                        cur.remove(card);
                        storage.remove(n + 1 - card);
                        flag = true;
                        coin--;
                        break;
                    }
                }
            }
            
            if(!flag && coin >= 2){
                for(Integer card : storage){
                    if(storage.contains(n + 1 - card)){
                        storage.remove(card);
                        storage.remove(n + 1 - card);
                        flag = true;
                        coin-=2;
                        break;
                    }
                }
            }
            
            if(!flag){
                break;
            }
        }
        
        return res;
    }
}
