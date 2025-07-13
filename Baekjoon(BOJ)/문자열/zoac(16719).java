package aa.zoac_16719;

import java.util.*;
import java.io.*;

class Main{

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String s = st.nextToken();

        boolean[] visited = new boolean[s.length()];
        for(int i = 0 ; i < s.length(); i++){
            //거꾸로 탐색했을 때 방문안한 글자가 1 이상이고, 방문된 지점 찾기
            char fastChar = 'a'; // 대문자보다 무조건 큰 문자
            int idx = -1;

            int cnt = 0;
            for(int j = s.length() - 1; j >= 0; j--){
                if(visited[j] && cnt > 0){
                    break;
                }

                if(!visited[j]){
                    cnt++;
                    if(fastChar >= s.charAt(j)){
                        fastChar = s.charAt(j);
                        idx = j;
                    }
                }
            }

            visited[idx] = true;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < s.length(); j++){
                if(visited[j]){
                    sb.append(s.charAt(j));
                }
            }

            System.out.println(sb.toString());
        }
    }
}
