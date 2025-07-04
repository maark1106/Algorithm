import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] level = new int[N];
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            level[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(level);

        int left = 1;
        int right = 1001000000;
        int res = 0;
        while(left <= right){
            int mid = (left + right) / 2;
            long total = 0L;
            for(int i = 0 ; i < N; i++){
                if(level[i] >= mid){
                    break;
                }

                total += (mid - level[i]);
            }

            if(total <= K){ // 가능하다면
                res = Math.max(res, mid);
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        System.out.print(res);
    }
}
