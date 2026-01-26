import java.util.*;
import java.io.*;

class Main {

    int N;
    int M;
    int[] time;

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = new int[N];

        long min = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, time[i]);
        }

        long left = 1L;
        long right = min * M;
        long res = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0L;
            for (int i = 0; i < N; i++) {
                sum += mid / time[i];
            }
            if (sum >= M) { // 받을 수 있다면
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(res);
    }

}
