import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    
	public void solution() throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		HashMap<Integer, Integer> numbers = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreElements()) {
			int num = Integer.parseInt(st.nextToken());
			if(numbers.containsKey(num)) {
				numbers.put(num, numbers.get(num) + 1);
			}
			else {
				numbers.put(num, 1);
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreElements()) {
			int target = Integer.parseInt(st.nextToken());
			if(numbers.containsKey(target)) {
				sb.append(numbers.get(target) + " ");
			}
			else {
				sb.append(0 + " ");
			}
		}
		
		System.out.println(sb);
		br.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
