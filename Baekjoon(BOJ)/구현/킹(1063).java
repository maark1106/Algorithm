package anew_.킹_1063_구현;

import java.util.*;
import java.io.*;

class Main {

	/*
R : 한 칸 오른쪽으로
L : 한 칸 왼쪽으로
B : 한 칸 아래로
T : 한 칸 위로
RT : 오른쪽 위 대각선으로
LT : 왼쪽 위 대각선으로
RB : 오른쪽 아래 대각선으로
LB : 왼쪽 아래 대각선으로

		A1 A2 5 // 킹 돌
		B
		L
		LB
		RB
		LT

		A1 킹
		A2 돌

		1. 킹을 그냥 이동시킨다
		2. isKingMove()
		3. 만약 돌과 겹친다면 isDollMove()
		4. 만약 둘 다 가능하면 둘다 이동시킨다
		5. 마지막 킹, 돌 위치 출력

		A1 -> 1을 y, A - 'A'를 x라고 두기
	*/

    //
    int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};
    int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String s1 = st.nextToken();
        int kingY = s1.charAt(1) - '0';
        int kingX = s1.charAt(0) - 'A' + 1;


        String s2 = st.nextToken();
        int dolY = s2.charAt(1) - '0';
        int dolX = s2.charAt(0) - 'A' + 1;

        HashMap<String, Integer> map = new HashMap<>();
        map.put("R", 0);
        map.put("L", 1);
        map.put("B", 2);
        map.put("T", 3);
        map.put("RT", 4);
        map.put("LT", 5);
        map.put("RB", 6);
        map.put("LB", 7);

        int N = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N; i++){
            String s = br.readLine();
            int idx = map.get(s);

            int nextKingY = kingY + dy[idx];
            int nextKingX = kingX + dx[idx];
            int nextDolY;
            int nextDolX;

            if(isMove(nextKingY, nextKingX)){
                if(nextKingY == dolY && nextKingX == dolX){	// 같다면 이 조건까지 만족시켜야 함
                    nextDolY = dolY + dy[idx];
                    nextDolX = dolX + dx[idx];
                    if(isMove(nextDolY, nextDolX)){
                        dolY = nextDolY;
                        dolX = nextDolX;
                        kingY = nextKingY;
                        kingX = nextKingX;
                    }
                }
                else{ //같지 않다면 dol이 아니어도 움직일 수 있음
                    kingY = nextKingY;
                    kingX = nextKingX;
                }
            }
        }

        String king = Character.toString(64 + kingX);
        System.out.println(king + kingY);

        String dol = Character.toString(64 + dolX);
        System.out.println(dol + dolY);
    }

    boolean isMove(int y, int x){
        if(y <= 0 || y > 8 || x <= 0 || x > 8){
            return false;
        }
        return true;
    }
}
