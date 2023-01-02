
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		String s = new String();
		
		while(num-- > 0) {
			s = br.readLine();
			int sum = 0;
			int check = 1;
			for(int i = 0 ;  i<s.length();i++) {
				if(s.charAt(i) == 'O') {
					sum += check++;
				}
				else {
					check = 1;
				}
			}
			System.out.println(sum);
		}
	}

}
