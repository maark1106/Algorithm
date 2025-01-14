import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();

        int aCnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') {
                aCnt++;
            }
        }

        int minBCnt = 1001;
        for (int i = 0; i < str.length(); i++) {
            int bCnt = 0;
            for(int j = i; j < i + aCnt; j++){
                if(str.charAt(j % str.length()) == 'b'){
                    bCnt++;
                }
            }
            minBCnt = Math.min(minBCnt, bCnt);
        }

        System.out.println(minBCnt);
    }
}
