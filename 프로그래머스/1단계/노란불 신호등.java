import java.util.*;

/*

    시간 복잡도
    (G + Y + R)^n x 5
    각 주기의 최소공배수 x 5
    20^5 x 5 = 3200000 x 5 = 16000000
    but 최소 공배수는 20^5보다 작음
    
    풀이
    최소 공배수만큼 시뮬레이션 돌려서 노란색 겹치는지 확인
    
    각 주기
*/

class Solution {
    
    int N;
    int[][] color;
    int[] seconds;
    
    public int solution(int[][] signals) {
        N = signals.length;
        seconds = new int[N];
        color = new int[N][22];
        for(int i = 0 ; i < N; i++){
            int G = signals[i][0];
            int Y = signals[i][1];
            int R = signals[i][2];
            int idx = 0;
            for(int j = 0; j < G; j++){
                color[i][idx++] = 0;
            }
            for(int j = 0; j < Y; j++){
                color[i][idx++] = 1;
            }
            for(int j = 0; j < R; j++){
                color[i][idx++] = 2;
            }
            seconds[i] = G + Y + R;
        }
        
        int totalTime = seconds[0];
        for(int i = 1; i < N; i++){
            totalTime = lcm(totalTime, seconds[i]);
        }
        
        int time = 0;
        for(int i = 0; i < totalTime; i++){
            time++;
            int cnt = 0;
            for(int j = 0 ; j < N; j++){
                int curColor = color[j][i % seconds[j]];
                if(curColor == 1){
                    cnt++;
                }
            }
            
            if(cnt == N){ // 모두 노란색 되는 처음 순간
                break;
            }
        }
        
        return time == totalTime ? -1 : time;
    }
    
    int gcd(int a, int b){ // 12 18
        while(b != 0){
            int temp = a % b; //a : 18 ,b : 12
            a = b;
            b = temp;
        }
        
        return a;
    }
    
    int lcm(int a, int b){ // 12 18
        return a / gcd(a, b) * b;
    }
}
