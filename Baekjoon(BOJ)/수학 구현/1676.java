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
 * n�� �ִ��� 500�̹Ƿ� ���� ��� x
 * 0�� ������ 2�� 5�� ���� ������ ����
 * 5�� 2���� ������ 5�� ���� ����
 */

