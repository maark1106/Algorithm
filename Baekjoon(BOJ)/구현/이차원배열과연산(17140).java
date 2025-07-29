package aa.이차원배열과연산_17140;

import java.util.*;
import java.io.*;

class Main{

    /*
        1. n, m 크기 비교
            n >= m -> m을 늘리기
            n < m  -> n을 늘리기
        2. Map에 개수를 저장하기
        3. Pair class에 저장하고 sort하기(0 제외)
        4. 재배열하기
        5. n, m의 크기는 max(이전 값, 현재 heap 크기 x 2) -> 갱신
    */

    int r;
    int c;
    int k;
    int[][] board;
    int n;
    int m;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[101][101];
        n = 3;
        m = 3;

        for(int i = 0 ; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 3; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = false;
        int res = 0;
        while(true){
            if(board[r - 1][c - 1] == k){
                flag = true;
                break;
            }
            res++;
            if(res == 101){
                break;
            }
            if(n >= m){
                for(int i = 0; i < n; i++){
                    Map<Integer, Integer> storage = new HashMap<>();
                    for(int j = 0; j < m; j++){
                        if(board[i][j] != 0){
                            storage.put(board[i][j],
                                    storage.getOrDefault(board[i][j], 0) + 1);
                        }
                    }
                    List<Pair> nums = new ArrayList<>();
                    for(Integer num: storage.keySet()){
                        int cnt = storage.get(num);
                        nums.add(new Pair(num, cnt));
                    }
                    Collections.sort(nums);
                    m = Math.min(100, Math.max(m, nums.size() * 2));
                    for(int j = 0 ; j < nums.size(); j++){
                        board[i][j * 2] = nums.get(j).number;
                        board[i][j * 2 + 1] = nums.get(j).cnt;
                    }
                    for(int j = nums.size() * 2; j <= m; j++){
                        board[i][j] = 0;
                    }
                }
            }else{
                for(int j = 0; j < m; j++){
                    Map<Integer, Integer> storage = new HashMap<>();
                    for(int i = 0; i < n; i++){
                        if(board[i][j] != 0){
                            storage.put(board[i][j],
                                    storage.getOrDefault(board[i][j], 0) + 1);
                        }
                    }
                    List<Pair> nums = new ArrayList<>();
                    for(Integer num: storage.keySet()){
                        int cnt = storage.get(num);
                        nums.add(new Pair(num, cnt));
                    }
                    Collections.sort(nums);
                    n = Math.min(100, Math.max(n, nums.size() * 2));
                    for(int i = 0 ; i < nums.size(); i++){
                        board[i * 2][j] = nums.get(i).number;
                        board[i * 2 + 1][j] = nums.get(i).cnt;
                    }

                    for(int i = nums.size() * 2; i <= n; i++){
                        board[i][j] = 0;
                    }
                }
            }
        }

        System.out.print(flag ? res : -1);
    }

    class Pair implements Comparable<Pair>{
        int number;
        int cnt;

        public Pair(int number, int cnt){
            this.number = number;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair p){
            if(this.cnt == p.cnt){
                return this.number - p.number;
            }
            return this.cnt - p.cnt;
        }
    }
}
