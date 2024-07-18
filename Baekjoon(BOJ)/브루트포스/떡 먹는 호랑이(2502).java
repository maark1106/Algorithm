
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= k / 2; i++) {
            for (int j = i; j <= k; j++) {
                int riceCake = countRiceCate(i, j, d);
                if(riceCake == k){
                    System.out.println(i +"\n" + j);
                    System.exit(0);
                } else if (riceCake > k) {
                    break;
                }
            }
        }
    }

    static int countRiceCate(int first, int second, int day) {
        for (int i = 2; i < day; i++) {
            int temp = second;
            second += first;
            first = temp;
        }
        return second;
    }
}
