import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		for(int i = 0 ; i  <num ; i++) {		
			int studentNum = sc.nextInt();
			int[] student = new int[studentNum];
			double avg = 0;
			
			for(int j = 0 ;j < studentNum; j++) {
				student[j] = sc.nextInt();
				avg += student[j];
			}
			
			avg /= studentNum;
			int count = 0;
			
			for(int score: student) {
				if(score > avg) {
					count++;
				}
			}
			System.out.printf("%.3f%%\n",(double)count / studentNum * 100);
		}
		
	}
}
