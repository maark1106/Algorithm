import java.util.*;
import java.io.*;

class Main {

	/*
			1. 현재 A, B, C 물통의 상태를 관리해여 함
			C < A -> C 전부 주기
			C > A -> A만큼 주기


			dp[201][201][201]에서 해당값이 true라면 return

			한 과정에서
			A -> B
			A -> C
			B -> A
			B -> C
			C -> B
			C -> A
	*/

    boolean[][][] dp;
    int A;
    int B;
    int C;


    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dp = new boolean[A + 1][B + 1][C + 1];

        dfs(0, 0, C);
        List<Integer>res = new ArrayList<>();
        for(int i = 0; i <=C; i++){
            for(int j = 0; j <= B;j++){
                if(dp[0][j][i]){
                    res.add(i);
                }
            }
        }

        // for(int i = 0; i<= A;i++){
        // 	for(int j =0; j<=B;j++){
        // 		for(int k = 0; k <=C;k++){
        // 			if(dp[i][j][k]){
        // 				System.out.println(i + " " + j + " " + k);
        // 			}
        // 		}
        // 	}
        // }

        Collections.sort(res);
        for(Integer i: res){
            System.out.print(i + " ");
        }
    }

    void dfs(int curA, int curB, int curC){
        if(curA < 0 || curA > A || curB < 0 || curB > B || curC < 0 || curC > C){
            return;
        }
        if(dp[curA][curB][curC]){
            return;
        }

        dp[curA][curB][curC] = true;
        if(curA > 0){
            dfs(0, curB + curA, curC); // a가 b다 주는 경우
            dfs(curA - (B - curB), B, curC); // a가 b 채워주기만 하는 경우
            dfs(0, curB, curA + curC); // a가 c다 주는 경우
            dfs(curA - (C - curC), curB, C);
        }
        if(curB > 0){
            dfs(curA + curB, 0, curC);  // b -> a
            dfs(A, curB - (A - curA), curC);
            dfs(curA, 0, curB + curC); // b -> c
            dfs(curA, curB - (C - curC), C);
        }
        if(curC > 0){
            dfs(curA + curC, curB, 0); // c -> a
            dfs(A, curB, curC - (A - curA)); //
            dfs(curA, curB + curC, 0); // c -> b
            dfs(curA, B, curC - (B - curB));
        }
    }

}
