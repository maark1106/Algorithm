import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    
    static int N;
    static int[] arr;
    static int res;
    static int count;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++)
		{
            st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
            int number = Integer.parseInt(str);
            count = Integer.parseInt(st.nextToken());
            res = 0;
			arr = new int[str.length()];
            N = str.length();
            for(int i = 0 ; i < str.length(); i++){
             	arr[i] =  str.charAt(i) - '0';
            }
            
            DFS(0, 0);
            System.out.println("#" + tc + " " + res);
		}
	}
    
    static void DFS(int depth, int idx){
     	if(depth == count){
            int num = 0;
            for(int i = 0 ; i < N; i++){
                num = num * 10 + arr[i];
            }
            res = Math.max(res, num);
         	return;   
        }
        
        for(int i = idx; i < N; i++){
            for(int j = i + 1; j < N;j++){
             	swap(i, j);
                DFS(depth + 1, i);
				swap(i, j);
            }
        }
    }
    
	  static void swap(int i, int j){
     	int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
