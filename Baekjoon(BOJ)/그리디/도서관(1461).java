import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    int N,M;

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> plusQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusQ = new PriorityQueue<>(Collections.reverseOrder());

        st = new StringTokenizer(br.readLine());

        //양수와 음수 나눠 담기
        for(int i = 0 ; i < N;i++){
            int num = Integer.parseInt(st.nextToken());
            if(num > 0){
                plusQ.add(num);
            }
            else{
                minusQ.add(Math.abs(num));
            }
        }

        //가장 먼 지점 찾기
        //가장 먼 지점은 돌아오지 않아도 되므로 한 번만 더해주면 된다
        int start = 0;
        if(plusQ.isEmpty()){
            start = minusQ.peek();
        }
        else if(minusQ.isEmpty()){
            start = plusQ.peek();
        }
        else{
            start = Math.max(plusQ.peek(), minusQ.peek());
        }

        int result = 0;
        while(!plusQ.isEmpty()){
            //가장 먼 지점에서 M권씩 가져가기
            result += plusQ.peek() * 2;
            for(int i = 0 ; i < M;i++){
                plusQ.poll();
            }
        }

        while(!minusQ.isEmpty()){
            result += minusQ.peek() * 2;
            for(int i = 0 ; i < M;i++){
                minusQ.poll();
            }
        }

        //먼 지점을 왔다 갔다 계산했으므로 한 번 빼주기
        result -= start;

        System.out.println(result);
    }



    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
