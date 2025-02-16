import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int truckSize = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        int[] store = new int[N + 1];
        Arrays.fill(store, truckSize);

        List<Box> boxes = new ArrayList<>();
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            boxes.add(new Box(s, e, cnt));
        }

        Collections.sort(boxes);
        int res = 0;
        for(int i = 0 ; i < M; i++){
            Box cur = boxes.get(i);
            int min = Integer.MAX_VALUE;
            for(int j = cur.end - 1; j >= cur.start; j--){
                if(min > store[j]){
                    min = store[j];
                }
            }

            int minusBox = Math.min(min, cur.cnt);
            for(int j = cur.end - 1; j >= cur.start; j--){
                store[j] -= minusBox;
            }
            res += minusBox;
        }

        System.out.println(res);
    }

    class Box implements Comparable<Box>{
        int start;
        int end;
        int cnt;

        public Box(int s, int e, int cnt){
            this.start = s;
            this.end = e;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Box b){
            if(this.end == b.end){
                return this.start - b.start;
            }
            return this.end - b.end;
        }
    }
}
