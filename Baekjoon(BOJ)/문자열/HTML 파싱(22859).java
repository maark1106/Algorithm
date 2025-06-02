import java.util.*;
import java.io.*;

class Main {


	/*
			1. <div 로 시작하는 것과 <p로 시작하는 것만 List에 넣기
			2. <div 로 시작하면 substring후 "title : " + 자른거 출력			
			3. <p> 를 만나면 처음부터 탐색하면서 < ?? > 이거는 건너뛰고 문자열 sb에 더하기(단 이전 문자가 공백인데 또 공백 오는 경우 제외)
			4. String.trim 앞뒤 공백 지우기				
	*/

	List<String> strs;
	StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws Exception{
		String s = "\"\"";
		System.out.print(s.equals(""));
		// new Main().solution();
  }

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		strs = new ArrayList<>();
		
		int start = 0;
		while(start < s.length() - 5){
			if(s.substring(start, start + 4).equals("<div")){
				int idx = start + 10;
				while(s.charAt(idx) != '>'){					
					idx++;					
				}
				strs.add(s.substring(start, idx + 1)); //  <div title="title_name_1"> 저장
				start = idx; // >로 이동
			}
			else if(s.substring(start, start + 3).equals("<p>")){
				int idx = start + 2;
				while(!s.substring(idx, idx + 4).equals("</p>")){					
					idx++;					
				}
				strs.add(s.substring(start, idx)); // <p>paragraph 4 
				start = idx + 3; // >로 이동 
			}

			start++;
		}

		for(String str : strs){
			if(str.startsWith("<div")){
				printTitle(str);
			}
			else if(str.startsWith("<p>")){
				printTagP(str);
			}
		}

		System.out.println(sb.toString());
	}

	void printTitle(String s){
		String s1 = s.substring(12, s.length() - 2);
		sb.append("title : " + s1 + "\n");
	}

	void printTagP(String s){
		StringBuilder sb1 = new StringBuilder();

		int idx = 0;
		while(true){
			if(s.charAt(idx) == '<'){
				while(s.charAt(idx) != '>'){
					idx++;
				}				
			}
			idx++;
			if(idx >= s.length()){
				break;
			}
			if(s.charAt(idx) == '<'){				
				continue;
			}
			
			if(sb1.length() == 0 || s.charAt(idx) != ' ' || (s.charAt(idx) == ' ' && sb1.charAt(sb1.length() - 1) != ' ')){
				sb1.append(s.charAt(idx));
			}			
		}

		sb.append(sb1.toString().trim() + "\n");
	}
}
