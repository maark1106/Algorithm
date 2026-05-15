import java.util.*;
import java.io.*;

class Solution{
    
    /*
    	시간 복잡도
        N = 1000
        N^2까지
        
        풀이
        1 0 0  
  1    0 1 0
  2	   1 0 1
  3    0 1 0
  
  		0 1 0
   1   1 0 1
   2   0 1 0
   3   1 0 1
   
   		0 0 1
    1  0 1 0
    2  1 0 1
    3  0 1 0

		1. K == 0이면 현재 위치 출력
        2. 가운데 & 홀수 : 0
        	가운데 & 짝수 : 1
        3. 왼쪽 & 홀수 : 1
        	왼쪽 & 짝수 : 0
        4. 오른쪽 & 홀수 : 1
        	오른쪽 & 짝수 : 0
		
    */
    
	public static void main(String args[]) throws Exception{
		new Solution().solution();
	}
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T ; tc++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int cur = 0;
            for(int i = 0 ; i <= 2; i++){
                if(s.charAt(i) == 'o'){
                    cur = i;
                }
            }
            int K = Integer.parseInt(st.nextToken());
           	
            int res = 0;
            if(K == 0){
                res = cur;
            }
            else{
                if(cur == 1){
                    if(K % 2 == 0){
                        res = 1;
                    }
                    else{
                        res = 0;
                    }
                }
                else{
                    if(K % 2 == 0){
                        res = 0;
                    }
                    else{
                        res = 1;
                    }
                }
                
            }
            
            
            System.out.println("#" + tc + " " + res);
        }
    }
}
