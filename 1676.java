package ex01;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();

		int sum = 1, count = 0;
		
		for(int i = 2; i <= num;i++) {
			int n = i;
			while(n % 5 == 0) {
				count++;
				n /= 5;
			}
		}
		
		System.out.println(count);
	}		
}

/*
 * n의 최댓값이 500이므로 직접 계산 x
 * 0의 개수는 2와 5의 작은 개수와 동일
 * 5가 2보다 적으니 5의 개수 세기
 */

