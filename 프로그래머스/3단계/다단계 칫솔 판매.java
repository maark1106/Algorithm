import java.util.*;
import java.io.*;

/*

    시간 복잡도
    seller x log(Enroll)
    
    풀이
    referral i번째 밑 -> enroll의 i번째 
    
    0. 이름을 number로 바꾸기
    1. 아래 -> 위로 향하는 그래프를 만든다.
    2. seller 배열만큼 돌아 그래프 탐색하면서 분배하기
    3. 총 합계 구하기
*/


class Solution {
    
    HashMap<String, Integer> sum;
    HashMap<String, String> graph;
    int N;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        N = enroll.length;
        graph = new HashMap<>();
        sum = new HashMap<>();
        for(int i = 0 ; i < N; i++){
            graph.put(enroll[i], referral[i]);
            sum.put(enroll[i], 0);
        }
        
        for(int i = 0 ; i < seller.length; i++){
            calculate(seller[i], amount[i] * 100);
        }
        
        int[] res = new int[N];
        for(int i = 0 ; i < N; i++){
            res[i] = sum.get(enroll[i]);
        }
        
        return res;
    }
    
    void calculate(String name, int amount){
        if(amount == 0 || name.equals("-")){
            return;
        }
        
        String motherName = graph.get(name);
        int motherAmount = (int)(amount * 0.1);
        int curAmount = amount - motherAmount;
        sum.put(name, sum.get(name) + curAmount);
        calculate(motherName, motherAmount);
    }
}
