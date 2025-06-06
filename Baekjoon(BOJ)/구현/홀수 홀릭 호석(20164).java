import java.util.*;
import java.io.*;

class Main {


	/*
			1. dfs로 s를 짜르는데
				1 : 전체 개수 res랑 비교
				2 : 두개 더하기(최종 값 + 해서 다음)
				3 이상 : 반복문을 돌려 2지점을 찾기
						substring으로 3등분하여(최종값 홀수 + 해서) 다음 dfs 보내기
			2.
	*/

    int maxRes;
    int minRes;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String s = st.nextToken();
        maxRes = 0;
        minRes = Integer.MAX_VALUE;
        int cnt = getCnt(s);
        dfs(s, cnt);
        System.out.print(minRes + " " + maxRes);
    }

    void dfs(String s, int cnt){
        if(s.length() == 1){
            maxRes = Math.max(maxRes, cnt);
            minRes = Math.min(minRes, cnt);
            return;
        }
        else if(s.length() == 2){
            int n1 = Integer.parseInt(s.substring(0, 1));
            int n2 = Integer.parseInt(s.substring(1, 2));
            String result = String.valueOf(n1 + n2);
            int newCnt = getCnt(result);
            dfs(result, cnt + newCnt);
        }
        else{
            for(int i = 1; i < s.length() - 1; i++){ // 첫번째 잘리는 지점
                for(int j = i + 1; j < s.length(); j++){ // 두번째 잘리는 지점
                    int n1 = Integer.parseInt(s.substring(0, i));
                    int n2 = Integer.parseInt(s.substring(i, j));
                    int n3 = Integer.parseInt(s.substring(j, s.length()));
                    String result = String.valueOf(n1 + n2 + n3);
                    int newCnt = getCnt(result);
                    dfs(result, cnt + newCnt);
                }
            }
        }
    }

    int getCnt(String s){
        int cnt = 0;
        for(int i = 0 ; i < s.length(); i++){
            if((s.charAt(i) - '0') % 2 == 1){
                cnt++;
            }
        }
        return cnt;
    }
}
