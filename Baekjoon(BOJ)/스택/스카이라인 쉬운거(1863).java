import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Stack<Integer> s = new Stack<>();

        int res = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int line = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            while(!s.isEmpty() && s.peek() > height){
                s.pop();
            }

            if(height == 0){
                continue;
            }

            if(s.isEmpty() || s.peek() != height){
                s.push(height);
                res++;
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
