package 키로거_5397;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));				
		
		int N = Integer.parseInt(br.readLine());
		
		for(int k = 0 ; k < N ; k++) {
			String str = br.readLine();
			StringBuilder result = new StringBuilder();
			
			Stack<String> left = new Stack<>();
			Stack<String> right = new Stack<>();
			
			for(int i = 0 ; i < str.length();i++) {
				char ch = str.charAt(i);
				if(ch == '<') {
					if(!left.isEmpty()) {
						right.push(left.pop());
					}
				}
				else if(ch == '>') {
					if(!right.isEmpty()) {
						left.push(right.pop());
					}
				}
				else if(ch == '-') {
					if(!left.isEmpty()) {
						left.pop();
					}
				}
				else {
					left.push(String.valueOf(ch));
				}
			}
			
			while(!left.isEmpty()) {
				right.add(left.pop());
			}
			
			while(!right.isEmpty()) {
				result.append(right.pop());
			}
			
			System.out.println(result.toString());
		}		
		br.close();
	}
	
	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

}
