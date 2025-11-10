import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
import java.io.*;
 
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int tc = 1; tc <= 10; tc++)
        {
             
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
             
            int res  = 0;
            for(int i = 2; i <= N - 3; i++){
                int maxHeight = Math.max(Math.max(arr[i - 2], arr[i - 1]), Math.max(arr[i + 1], arr[i + 2]));
                if(arr[i] - maxHeight > 0){
                    res+= arr[i] - maxHeight;
                }
            }
             
            System.out.println("#" +  tc + " " + res);
        }
    }
}
