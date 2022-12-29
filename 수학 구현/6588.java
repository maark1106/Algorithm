package ex01;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		boolean[] arr1 = new boolean[1000001];
		
		for(int i  = 2; i*i <= 1000000;i++ ) {
			if(!arr1[i]) {
				for(int j = 2; i*j <= 1000000;j++) {
					arr1[i*j] = true;
				}
			}
		}
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			
			int num = Integer.parseInt(bf.readLine());
			if(num == 0)
				break;
			boolean flag = true;
			
			for(int i = 3 ; i < num;i++) {
				if(!arr1[i] && !arr1[num - i]) {
					System.out.println(num + " = " + i + " + " + (num - i));
					flag = false;
					break;
				}
			}
			
			if(flag == true) {
				System.out.println("Goldbach's conjecture is wrong.");
			}
		}				
	}
}
