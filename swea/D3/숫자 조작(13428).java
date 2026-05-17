import java.util.*;
import java.io.*;


class Solution{
    
    /*
    	시간 복잡도
        N
        
        풀이
        1. char로 만든다
        큰 수 
        - 맨 앞자리보다 가장 큰 수를 찾아 교환한다.
        - 없다면 2번째, 3번째 자리수 탐색..

        작은 수
        - 맨 앞자리보다 가장 작은 수를 찾아 교환
        - 없다면 2번째, 3번째..
        
        2. 대신 첫번째 수를 고를 때 0은 제외함
    */
    
	public static void main(String args[]) throws Exception	{
        new Solution().solution();
	}
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1 ; tc <= T; tc++){
            String s = br.readLine();
            char[] minNum = new char[s.length()];
			char[] maxNum = new char[s.length()];
            for(int i = 0 ; i < s.length(); i++){
                minNum[i] = s.charAt(i);
                maxNum[i] = s.charAt(i);
            }
            
            getMinNumber(minNum);
			getMaxNumber(maxNum);
            
            String min = new String(minNum);
            String max = new String(maxNum);
            System.out.println("#" + tc + " " + min + " " + max);
        }
    }
    
    void getMinNumber(char[] minNum){ // 자기보다 오른쪽 자릿 수 중 가장 작은 거 
        int N = minNum.length;
        for(int i = 0 ; i < N - 1; i++){
            int idx = i;
            char min = minNum[i];
            for(int j = i + 1; j < N; j++){
                if(min > minNum[j]){
                    if(i == 0 && minNum[j] == '0'){ // 맨 앞 자리에는 0이 올 수 없음
                        continue;
                    }
                    idx = j;
                    min = minNum[j];
                }
                else if(idx != i && min == minNum[j]){//
                    idx = j;
                    min = minNum[j];
                }
            }
            if(idx != i){ //바꿀 게 있다면	
                char temp = minNum[idx];
                minNum[idx] = minNum[i];
                minNum[i] = temp;
                break;
            }
        }
    }
    
    void getMaxNumber(char[] maxNum){ // 자기보다 오른쪽 자릿 수 중 가장 큰 거 
        int N = maxNum.length;
        for(int i = 0 ; i < N - 1; i++){
			int idx = i;
            char max = maxNum[i];
            for(int j = i + 1; j < N; j++){			
                if(max < maxNum[j]){
                    idx = j;
                   	max = maxNum[j];
                }
                 else if(idx != i &&  max == maxNum[j]){//
                     idx = j;
                     max = maxNum[j];
                 }
            }
            if(idx != i){ //바꿀 게 있다면	
                char temp = maxNum[idx];
                maxNum[idx] = maxNum[i];
                maxNum[i] = temp;
                break;
            }
        }
    }
    
}


import java.util.*;
import java.io.*;


class Solution{
    
    /*
    	시간 복잡도
        N
        
        풀이
        1. char로 만든다
        큰 수 
        - 맨 앞자리보다 가장 큰 수를 찾아 교환한다.
        - 없다면 2번째, 3번째 자리수 탐색..

        작은 수
        - 맨 앞자리보다 가장 작은 수를 찾아 교환
        - 없다면 2번째, 3번째..
        
        2. 대신 첫번째 수를 고를 때 0은 제외함
    */
    int N;
    String max;
    String min;
    
	public static void main(String args[]) throws Exception	{
        new Solution().solution();
	}
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1 ; tc <= T; tc++){
            String s = br.readLine();
            N = s.length();
            char[] num = new char[s.length()];

            for(int i = 0 ; i < s.length(); i++){
                num[i] = s.charAt(i);
            }

            max = s;
            min = s;
            
            for(int i = 0 ; i < N - 1; i++){
                for(int j = i + 1; j < N; j++){
                    char temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                    
                    if(num[0] != '0'){
                        String newNumber = new String(num);
                        if(newNumber.compareTo(max) > 0){
                            max = newNumber;
                        }
                        if(newNumber.compareTo(min) < 0){
                            min = newNumber;
                        }
                    }
                    num[j] = num[i];
                    num[i] = temp;
                }
            }
            
            System.out.println("#" + tc + " " + min + " " + max);
        }
    }
    
}
