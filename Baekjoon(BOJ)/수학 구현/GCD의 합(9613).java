package GCD�� ��;

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
 * scanner�� �ؼ� �ٷ� Ǯ������ bufferedread�� ������ ������ ���� �ð��� ���� �ɷ���
 * �� �ٿ� �������� �Է� �޴� ��� stringTokenizer�� �Է� �޾�
 * hasMoreTokens�� ��� �迭�� ��������� ��
 *  
 */

