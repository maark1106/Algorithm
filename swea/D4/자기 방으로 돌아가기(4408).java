import java.util.*;
import java.io.*;

class Solution{
    
    /*
    	시간 복잡도
        N <= 400
        N x N X N
        
        풀이 
        1. 처음부터 돌려도 상관 없음
        - 1번 잡고 뒤에 N개 돌려서 겹치지 않으면 추가
        - visited 로 하나씩 방문 체크하면 오래걸림
        - 그냥 q에 넣고 start와 end만 비교하고 걸리는 거 없으면 q 뒤에 넣기
    */
    
	int N;
    int[][] room; // start : 0, end : 1
    int res;
    
	public static void main(String args[]) throws Exception{
        new Solution().solution();
	}
    
    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            room = new int[N][2];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                if(start > end){
                    int temp = start;
                    start = end;
                    end = temp;
                }

                 /* 
                      start  end
                        짝 -> 짝 : start - 1
                        짝 -> 홀 : start - 1, end + 1
                        홀 -> 짝 : 그대로
                        홀 -> 홀 : end + 1
                 */

                if(start % 2 == 0 && end % 2 == 0){
                    start--;
                }
                else if(start % 2 == 0 && end % 2 == 1){
                    start--;
                    end++;
                }
                else if(start % 2 == 1 && end % 2 == 1){
                    end++;
                }

                room[i][0] = start;
                room[i][1] = end;
            }
			Arrays.sort(room, (a, b) -> Integer.compare(a[0], b[0]));
            res = 0;
            move();

            System.out.println("#" + tc + " " + res);
        }
    }
    
    void move(){
        boolean[] visited = new boolean[N];
        
        for(int i = 0; i < N; i++){
            if(visited[i]){
                continue;
            }
            res++;
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            for(int j = i + 1; j < N; j++){
                if(visited[j]){
                    continue;
                }
                boolean flag = true;
                
                for(Integer prev: q){
                    int prevStart = room[prev][0];
                    int prevEnd = room[prev][1];
                    
                    int curStart = room[j][0];
                    int curEnd = room[j][1];
                    if(prevStart > curEnd || prevEnd < curStart){
                        continue;
                    }
                    else{
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    q.add(j);
                    visited[j] = true;
                }
            }
        }
    }
}
