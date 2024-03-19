import java.util.Stack;

public class Main {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Stack<Integer> go = new Stack<>(); // 배달
        Stack<Integer> back = new Stack<>(); // 수거

        for(int i = 0; i < n;i ++){
            if(deliveries[i] != 0){
                go.add(i);
            }
        }

        for(int i = 0; i < n;i ++){
            if(pickups[i] != 0){
                back.add(i);
            }
        }


        while(!go.isEmpty() || !back.isEmpty()){
            int goPos = 0;
            if(!go.isEmpty()){
                goPos = go.peek();
            }

            int backPos = 0;
            if(!back.isEmpty()){
                backPos = back.peek();
            }

            int maxPos = Math.max(goPos, backPos); // 이동 거리는 배달/수거 중 가장 먼 거리 x 2
            answer += (maxPos + 1) * 2;

            int cnt = cap;
            while(!go.isEmpty() && cnt > 0){
                int idx = go.peek();
                int goCnt = deliveries[idx];
                if(goCnt <= cnt){ // 더 많이 가져왔을 때
                    go.pop();
                    cnt -= goCnt;
                }
                else{// goCnt > cnt
                    deliveries[idx] -= cnt;
                    cnt = 0;
                }
            }

            cnt = cap;
            while(!back.isEmpty() && cnt > 0){
                int idx = back.peek();
                int backCnt = pickups[idx];
                if(backCnt <= cnt){
                    back.pop();
                    cnt -= backCnt;
                }
                else{
                    pickups[idx] -= cnt;
                    cnt  = 0;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] deliveries = {1,0,3,1,2};
        int[] pickups = {0, 3, 0, 4, 0};
        long answer = new Main().solution(4, 5, deliveries,pickups );
        System.out.println(answer);
    }
}
