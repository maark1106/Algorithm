import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> s = new Stack<>();
		long res = 0;
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			while(!s.isEmpty() && height >= s.peek()) {
				s.pop();
			}
			res += s.size();
			s.push(height);
		}
		
		System.out.println(res);
	}
	
	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

}
