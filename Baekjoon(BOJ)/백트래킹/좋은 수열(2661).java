
import java.util.Scanner;

public class Main {

    int N;
    StringBuilder sb = new StringBuilder();

    void DFS(int depth) {
        if (depth == N + 1) {
            System.out.println(sb.toString());
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            sb.append(i);
            boolean flag = true;
            for (int j = 1; j <= sb.length() / 2; j++) {
                String str1 = sb.substring(sb.length() - 2 * j, sb.length() - j);
                String str2 = sb.substring(sb.length() - j, sb.length());
                if(str1.equals(str2)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                DFS(depth + 1);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    void solution() throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        DFS(1);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
