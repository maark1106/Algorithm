import java.util.*;

class Solution {
    int N;
    int M;
    int endX;
    int endY;
    int totalK;
    int[] dy = {1, 0, 0, -1};
    int[] dx = {0, -1, 1, 0};
    String answer;
    String s = "dlru";
    // d l r u
    boolean flag = false;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "impossible";

        N = n;
        M = m;
        endY = r;
        endX = c;
        totalK = k;

        DFS(x, y, 0, "");


        return answer;
    }

    void DFS(int y, int x, int count, String str) {
        if(flag){
            return;
        }

        if(count == totalK){
            answer = str;
            flag = true;
            return;
        }

        for(int i= 0; i < 4; i++){
            int yy = y + dy[i];
            int xx = x + dx[i];

            if(yy <= 0 || yy > N || xx <= 0 || xx > M){
                continue;
            }

            int dis = Math.abs(yy - endY) + Math.abs(xx - endX);
            if(totalK - (count + 1) >= dis && (totalK - (count + 1) - dis) % 2 == 0){
                DFS(yy, xx, count + 1, str + s.charAt(i));
            }
        }
    }
}
