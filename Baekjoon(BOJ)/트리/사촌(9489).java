import java.util.*;
import java.io.*;

class Main{


    /*
        1. q에 첫번째 숫자 넣기
        2. 순서대로 연속된 숫자를 자식으로 넣기
        3. 해당 숫자를 q에 넣기
       	-> 반복
       	4. 부모는 다르지만 조부모가 같다면 ++
    */

	int N;
	int K;
	int[] arr;
	int[] parent;

	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while(true){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			if(N == 0 && K == 0){
				break;
			}

			parent = new int[N + 1];
			arr = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			int kIdx = 0;
			for(int i = 0 ; i < N; i++){
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i] == K){
					kIdx = i;
				}
			}

			if(N == 1){
				System.out.println(0);
				continue;
			}


			makeTree();
			int res = 0;
			if(parent[kIdx] == 0 || parent[kIdx] == -1){
				System.out.println(0);
				continue;
			}
			
			int kGrandParent = parent[parent[kIdx]];
			for(int i = 1; i < N; i++){
				if(parent[i] == 0 || parent[i] == parent[kIdx]){
					continue;
				}
				if(kGrandParent == parent[parent[i]]){
					res++;
				}
			}

			System.out.println(res);
		}
	}

	void makeTree(){
		parent[0] = -1;
		int idx = 0;
		for(int i = 1; i < N; i++){
			parent[i] = idx;
			if((i < N - 1) && arr[i] + 1 != arr[i + 1]){
				idx++;
			}
		}
	}
}
