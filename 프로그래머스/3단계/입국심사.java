
import java.util.Arrays;

public class Main {

    public long solution(int n, int[] times) {

        Arrays.sort(times);
        long left = 0;
        long right = (long)n * times[0];

        while(left <= right){
            long mid = (left + right) / 2;
            long count = 0;
            for(int i = 0 ; i < times.length; i++){
                count += mid / times[i];
            }

            if(count < n){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        long result = new Main().solution(n, times);
        System.out.println(result);
    }
}
