package anew_.aanew.달력_20207;

import java.util.*;
import java.io.*;

class Main {

    /*
        1. 시작이 빠른 순, 끝이 긴 순으로 정렬
        2. 만약 이미 차있다면 다음 줄로 가기
        3.
    */

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Pair> pairs = new ArrayList<>();
        for(int i = 0 ;i < N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pairs.add(new Pair(s, e));
        }

        Collections.sort(pairs);

        boolean[][] board = new boolean[1001][367];
        for(int i = 0 ; i < N; i++){
            Pair cur = pairs.get(i);

            int idx = 0;
            while(true){
                boolean flag = true;
                for(int j = cur.s; j <= cur.e; j++){
                    if(board[idx][j]){
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    for(int j = cur.s; j <= cur.e; j++){
                        board[idx][j] = true;
                    }
                    break;
                }
                idx++;
            }
        }

        int res = 0;
        for(int i = 1; i <= 365; i++){
            if(!board[0][i]){
                continue;
            }

            int maxHeight = 1;
            int right = i;
            while(right <= 365){
                boolean flag = false;
                for(int j = 0; j <= 1000; j++){ // height
                    if(board[j][right]){
                        maxHeight = Math.max(maxHeight, j + 1);
                        flag = true;
                    }
                }
                if(!flag){
                    break;
                }
                right++;
            }
            res += maxHeight * (right - i);
            i = right;
        }

        System.out.print(res);
    }

    class Pair implements Comparable<Pair>{
        int s;
        int e;

        public Pair(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Pair p){
            if(this.s == p.s){
                return p.e - this.e;
            }
            return this.s - p.s;
        }
    }
}
