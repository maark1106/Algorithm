import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] h = new int[1001];

        int maxPos = 0;
        int maxHeight = 0;
        int start = 1001;
        int end = 0;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            h[pos] = height;
            if(maxHeight < h[pos]){
                maxPos = pos;
                maxHeight = h[pos];
            }

            if(start > pos){
                start = pos;
            }
            if(end < pos){
                end = pos;
            }
        }

        int res = 0;
        int curMax = 0;
        for(int i = start; i < maxPos; i++){
            if(curMax < h[i]){
                curMax = h[i];
            }
            res += curMax;
        }
        curMax = 0;

        for(int i = end; i > maxPos; i--){
            if(curMax < h[i]){
                curMax = h[i];
            }
            res += curMax;
        }

        res += h[maxPos];

        System.out.println(res);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
