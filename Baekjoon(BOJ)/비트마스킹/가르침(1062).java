import java.util.*;
import java.io.*;

class Main{

    int N;
    int K;
    int[] words;
    HashSet<Integer> basic;
    int learn;
    int res;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new int[N];
        res = 0;

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j = 0 ; j < s.length(); j++){
                words[i] |= (1 << (s.charAt(j) - 'a'));
            }
        }

        if(K < 5){
            System.out.println(0);
            return;
        }

        basic = new HashSet<>();
        basic.add('a' - 'a');
        basic.add('c' - 'a');
        basic.add('i' - 'a');
        basic.add('t' - 'a');
        basic.add('n' - 'a');
        learn = 0;
        learn += ((1 << ('a' - 'a')) | (1 << ('c' - 'a')) | (1 << ('i' - 'a')) |
                (1 << ('n' - 'a')) | (1 << ('t' - 'a')));

        dfs(0, 0);
        System.out.print(res);
    }

    void dfs(int depth, int idx){
        if(depth == K - 5){
            int cnt = 0;
            for(int i = 0 ; i <N; i++){
                if((words[i] & learn) == words[i]){
                    cnt++;
                }
            }
            res = Math.max(res, cnt);
            return;
        }

        for(int i = idx; i < 26; i++){
            if(basic.contains(i)){
                continue;
            }

            learn |= (1 << i);
            dfs(depth + 1, i + 1);
            learn &= ~(1 << i);
        }
    }
}
