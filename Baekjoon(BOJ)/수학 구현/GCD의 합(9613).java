package GCD의 합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int gcd(int a,int b) {
		
		if(a % b == 0)
			return b;
		else
			return gcd(b, a % b);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		while(n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int count = Integer.parseInt(st.nextToken());
			int[] arr = new int[count];
			int idx = 0;
			while(st.hasMoreTokens()) {
				arr[idx++] = Integer.parseInt(st.nextToken());
			}
			long sum = 0;
			for(int i = 0 ;  i< idx -1;i++) {
				for(int j = i + 1;j < idx; j++) {
					sum += gcd(arr[i],arr[j]);
				}
			}
			System.out.println(sum);
		}
	}
}

/*
 * scanner로 해서 바로 풀었지만 bufferedread의 사용법을 익히기 위해 시간이 많이 걸렸음
 * 한 줄에 공백으로 입력 받는 경우 stringTokenizer로 입력 받아
 * hasMoreTokens로 끊어서 배열에 저장해줘야 함
 *  
 */

