import java.util.*;
import java.io.*;

class Main{

    /*
        1. 각 알파벳의 점수 구하기
        2. 10개를 다 활용했는지 확인
        3. 다 활용했다면 앞자리가 0이 아니고 점수가 가장 작은 알파벳을 0으로 고정하기
        4. 가중치대로 할당하기
    */

    int N;
    String[] strs;
    long res;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        strs = new String[N];
        res = 0;

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            strs[i] = st.nextToken();
        }

        long[] score = new long[10];
        boolean[] first = new boolean[10];
        boolean[] used = new boolean[10];
        for(int i = 0 ; i < N; i++){
            String cur = strs[i];
            long gop = 1;
            for(int j = cur.length() - 1; j >= 0; j--){
                if(j == 0){
                    first[cur.charAt(j) - 'A'] = true;
                }

                used[cur.charAt(j) - 'A'] = true;
                score[cur.charAt(j) - 'A'] += gop;
                gop *= 10;
            }
        }

        int usedCnt = 0;
        for(int i = 0 ; i < 10; i++){
            if(used[i]){
                usedCnt++;
            }
        }

        int[] fixed = new int[10];
        Arrays.fill(fixed, -1);
        int zeroIdx = -1;
        long zeroMax = Long.MAX_VALUE;
        if(usedCnt == 10){
            for(int i = 0 ; i < 10; i++){
                if(!first[i] && zeroMax > score[i]){ // 첫번째가 아니고 score가 낮으면
                    zeroMax = score[i];
                    zeroIdx = i;
                }
            }
            fixed[zeroIdx] = 0;
        }

        for(int i = 9; i > 0; i--){ // score이 가장 큰 인덱스부터 9부여
            int bigIdx = -1;
            long bigScore = 0;
            for(int j = 0; j < 10; j++){
                if(fixed[j] == -1 && bigScore < score[j]){
                    bigScore = score[j];
                    bigIdx = j;
                }
            }
            if(bigIdx != -1) {
                fixed[bigIdx] = i;
            }
        }

        for(int i = 0 ; i < 10; i++){
            res = res + score[i] * fixed[i];
        }

        System.out.print(res);
    }



}

