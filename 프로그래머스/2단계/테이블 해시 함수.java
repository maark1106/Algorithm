package 테이블해시함수;

import java.util.Arrays;

public class Main {

    int N;
    int M;

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        N = data.length;
        M = data[0].length;

        Arrays.sort(data, (o1, o2)->{
            if(o1[col - 1] == o2[col - 1]){
                return Integer.compare(o2[0],o1[0]); //내림차순 정렬
            }
            return o1[col - 1] - o2[col - 1];
        });

        int result = 0;
        for(int i = row_begin - 1; i<= row_end - 1;i++){
            int finalI = i + 1;
            int dataTotal = Arrays.stream(data[i])
                    .map(j -> j % finalI)
                    .sum();
            result ^= dataTotal;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        int[][] data ={{2,2,6},{1,5,10},{4,2,9},{3,8,3}};
        int col = 2;
        int row_begin = 2;
        int row_end = 3;
        int res = new Main().solution(data, col, row_begin, row_end);
    }
}
