import java.util.*;
import java.io.*;

class Solution{
    
    /*
    	시간 복잡도
        N^2	(1000일 때는 가능)
        
        10000넘어간다면?
        투포인터로
        left + right < M ->  left++
        left + right > M -> right--
		left + right = M -> 종료
            
        풀이
        - N^2 으로 모든 2가지를 고르는 경우의 수를 탐색한다
        만약 maxRes 값보다 큰 경우의 수가 있으면 해당 경우의 수의 값을 저장한다.
    */
    
	public static void main(String args[]) throws Exception{
        new Solution().solution();
	}
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
        	
            int[] weight = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N; i++){
                weight[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(weight);
            
            int left = 0;
            int right = N - 1;
            int res = -1;
            while(left < right){
                int mid = (weight[left] + weight[right]);
                
                if(mid < M){
                    res = Math.max(res, mid);
                    left++;
                }
                else if(mid > M){
                    right--;
                }
                else{
                    res = Math.max(res, mid);
                    break;
                }
            }

           // for(int i = 0; i < N - 1; i++){
           //     int target = M - weight[i];
           //     int left = i + 1;
           //     int right = N - 1;
           //     while(left <= right){
           //         int mid = (left + right) / 2;
                   
           //         if(target >= weight[mid]){
           //             left = mid + 1;
           //         }
           //         else{
           //             right = mid - 1;
           //         }
           //     }
               
           //     if(right > i && weight[right] <= target){
           //         res = Math.max(res, weight[i] + weight[right]);
           //     }
           // }
            
            System.out.println("#" + tc + " " + res);
        }
    }
}
