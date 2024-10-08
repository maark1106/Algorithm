import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        boolean[] sw = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) {
                sw[i] = false;
            } else {
                sw[i] = true;
            }
        }

        st = new StringTokenizer(br.readLine());
        int studentNumber = Integer.parseInt(st.nextToken());

        for (int i = 0; i < studentNumber; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            if (num1 == 1) {
                for (int j = num2; j <= N; j += num2) {
                    sw[j] = !sw[j];
                }
            } else {
                int idx = 0;
                while (true) {
                    if (num2 + idx > N || num2 - idx <= 0) {
                        break;
                    }
                    if (sw[num2 + idx] == sw[num2 - idx]) {
                        sw[num2 + idx] = !sw[num2 + idx];
                        if(idx != 0) {
                            sw[num2 - idx] = !sw[num2 - idx];
                        }
                        idx++;
                    } else {
                        break;
                    }
                }
            }
        }

        for(int i = 1 ; i <= N;i++){
            if(sw[i]){
                System.out.print(1 + " ");
            }else{
                System.out.print(0 + " ");
            }

            if(i % 20 == 0){
                System.out.println();
            }
        }
    }
}
