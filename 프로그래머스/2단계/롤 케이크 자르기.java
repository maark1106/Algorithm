import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] topping) {
            int answer = 0;

            HashMap<Integer, Integer> bro1 = new HashMap<>();
            HashMap<Integer, Integer> bro2 = new HashMap<>();

            for(int i = 0 ; i < topping.length;i++){
                bro1.put(topping[i], bro1.getOrDefault(topping[i], 0) + 1);
            }

            for(int i = 0 ; i < topping.length - 1;i++){

                int cake = topping[i];
                if(bro1.get(cake) == 1){
                    bro1.remove(cake);
                }
                else{
                    bro1.put(cake, bro1.get(cake) - 1);
                }

                bro2.put(cake, bro2.getOrDefault(cake, 1));

                if(bro1.size() == bro2.size()){
                    answer++;
                }
            }

            return answer;
        }
}
