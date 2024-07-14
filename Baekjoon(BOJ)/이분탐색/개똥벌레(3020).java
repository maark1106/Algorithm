
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    int N;
    int H;
    List<Integer> arr1 = new ArrayList<>();
    List<Integer> arr2 = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; i++){
            if(i % 2 == 0){
                arr1.add(Integer.parseInt(br.readLine()));
            }
            else{
                arr2.add(Integer.parseInt(br.readLine()));
            }
        }

        Collections.sort(arr1);
        Collections.sort(arr2);

        int result = 0;
        int minCnt = Integer.MAX_VALUE;
        for(int i = 1;i <= H;i++){
            int cnt = 0;
            cnt += getObstacle(arr1, i);
            cnt += getObstacle(arr2, H - i + 1);

            if(cnt < minCnt){
                minCnt = cnt;
                result = 1;
            }
            else if(cnt == minCnt){
                result++;
            }
        }

        System.out.println(minCnt + " " + result);
    }

    int getObstacle(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;

        while(left <= right){
            int mid = (left + right) / 2;

            if(arr.get(mid) < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        return arr.size() - left;
    }
}
