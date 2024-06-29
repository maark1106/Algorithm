import java.util.*;

class Solution {
   public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            sum1 += queue1[i];
        }
        for (int i = 0; i < queue2.length; i++) {
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }

        if ((sum1 + sum2) % 2 == 1) {
            return -1;
        }
       if(sum1 == 0 && sum2 == 0){
            return 0;
        }

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if(sum1 == sum2){
                return answer;
            }
            else if (sum1 > sum2) {
                int num = q1.poll();
                q2.add(num);
                sum1 -= num;
                sum2 += num;
            }
            else if(sum1 < sum2){
                int num = q2.poll();
                q1.add(num);
                sum2 -= num;
                sum1 += num;
            }
            answer++;
            if(answer > (queue1.length + queue2.length) * 2){
                return -1;
            }
        }

        return -1;
    }
}
