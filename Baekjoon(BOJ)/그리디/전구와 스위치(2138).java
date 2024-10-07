import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int res = Integer.MAX_VALUE;

        int N = Integer.parseInt(st.nextToken());
        String str1 = br.readLine();
        String str2 = br.readLine();
        boolean[] light1 = new boolean[str1.length()];
        boolean[] light2 = new boolean[str1.length()];
        boolean[] copy = new boolean[str1.length()];

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == '1') {
                light1[i] = true;
                copy[i] = true;
            }
            if (str2.charAt(i) == '1') {
                light2[i] = true;
            }
        }

        // 1번째를 누르고 시작하는 경우
        light1[0] = !light1[0];
        light1[1] = !light1[1];
        int cnt1 = 1;
        for (int i = 1; i < str1.length(); i++) {
            if(light1[i - 1] != light2[i - 1]){
                light1[i - 1] = !light1[i - 1];
                light1[i] = !light1[i];
                if(i != str1.length() - 1){
                    light1[i + 1] = !light1[i + 1];
                }
                cnt1++;
            }
        }
        if(light1[N - 1] != light2[N - 1]){
            cnt1 = -1;
        }

        int cnt2 = 0;
        for (int i = 1; i < str1.length(); i++) {
            if(copy[i - 1] != light2[i - 1]){
                copy[i - 1] = !copy[i - 1];
                copy[i] = !copy[i];
                if(i != str1.length() - 1){
                    copy[i + 1] = !copy[i + 1];
                }
                cnt2++;
            }
        }

        if(copy[N - 1] != light2[N - 1]){
            cnt2 = -1;
        }

        if(cnt1 == -1 && cnt2 == -1){
            System.out.println(-1);
        }
        else if(cnt1 == -1 || cnt2 == -1){
            System.out.println(Math.max(cnt1, cnt2));
        }
        else{
            System.out.println(Math.min(cnt1, cnt2));
        }
    }
}
