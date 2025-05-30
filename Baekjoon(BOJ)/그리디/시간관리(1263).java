import java.util.*;
import java.io.*;

class Main {

	/*
			1. 최대한 늦게 시작해야 함
			2. 그래도 일단 마감까지 들어오기는 해야함
			3. 마감 시간 기준으로 정렬
			4. 제일 늦게 시작할 수 있는 시간 : 첫번째 마감시간(S0) - 첫번째 소요시간(T0)
			5. 이거 ~ 0까지 중 가능하다 : print 해당 시간
			6. 0까지 갔는데 안됐다 print - 1
			7. 
	*/

	int N;	
	List<Work> works;

	public static void main(String[] args) throws Exception{
    new Main().solution();
  }

  void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		works = new ArrayList<>();
		for(int i = 0 ; i < N;i++){
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			works.add(new Work(time, endTime));
		}

		Collections.sort(works);

		int startTime = works.get(0).endTime - works.get(0).time;
		for(int i = startTime; i >= 0; i--){
			int curTime = i;
			boolean flag = true;
			for(Work work : works){
				curTime += work.time;
				if(curTime > work.endTime){
					flag = false;
					break;
				}
			}

			if(flag){
				System.out.println(i);
				return;
			}
		}

		System.out.println(-1);
	}

	class Work implements Comparable<Work>{
		int time;
		int endTime;

		public Work(int time, int endTime){
			this.time = time;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Work w){
			return this.endTime - w.endTime;
		}
	}
}
