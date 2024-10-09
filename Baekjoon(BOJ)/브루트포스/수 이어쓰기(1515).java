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

        String str = st.nextToken();
        int num = 1; //
        int idx = 0;

        while(true){
            String cur = "" + num;
            for(int i = 0 ; i < cur.length(); i++){
                if(str.charAt(idx) == cur.charAt(i)){
                    idx++;
                }
                if(idx == str.length()){
                    break;
                }
            }
            num++;
            if(idx == str.length()){
                break;
            }
        }

        System.out.println(num - 1);
    }
}
