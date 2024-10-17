import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] days = new int[N];
        int sum = 0;
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N ; i++){
            days[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i< X ; i++){
            sum += days[i];
        }

        int res = sum;
        int cnt = 1;
        for(int i = X;i < N ; i++){
            sum -= days[i - X];
            sum += days[i];
            if(sum > res){
                res = sum;
                cnt = 1;
            }
            else if(sum == res){
                cnt++;
            }
        }

        if(sum == 0){
            System.out.println("SAD");
        }else{
            System.out.println(res);
            System.out.println(cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
