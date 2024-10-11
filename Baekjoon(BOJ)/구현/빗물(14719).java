import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] block = new int[M];

        st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < M; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        left.add(block[0]);

        for (int i = 1; i < M; i++) {
            right.add(block[i]);
        }

        int res = 0;
        for(int i = 1; i < M - 1; i++){
            right.remove(block[i]);
            if(block[i] >= left.peek() || block[i] >= right.peek()){
                left.add(block[i]);
                continue;
            }

            res += Math.min(left.peek(), right.peek()) - block[i];
            left.add(block[i]);
        }

        System.out.println(res);
    }
}
