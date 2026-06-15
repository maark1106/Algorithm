import java.util.*;

/*

4 3 7 1 2 5
4 3 7 

    시간 복잡도
    완탐 -> m x n x
    
    풀이
    완탐 시뮬레이션으로 푼다 -> 시간초과
    1. 맨 위쪽부터 왼쪽으로 배치한다.
    2. 해당 범위 중 안 떨어진다면 바로 return한다.
    3. 해당 범위 중 떨어지는 구간이 있다면 가장 큰 구간을 기록한다.
    4. 만약 더 큰 구간이 나오면(뒤늦게 떨어지는 구간) 갱신한다.
    
    
    1. deque를 사용하여 가로의 최소값 구하기
    2. 해당 배열로 다시 높이만큼 슬라이딩 윈도우로 최소값 구하기
    3. 
*/

class Solution {
    
    static int MAX = Integer.MAX_VALUE;
    
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[] answer = new int[2];
        int temp = m;
        m = n;
        n = temp;
        
        int[][] board = new int[n][m];
        int[][] minBoard = new int[n][m];
        int[][] minResult = new int[n][m];
        
        for(int i = 0 ; i < n; i++){
            Arrays.fill(board[i], MAX);
        }
        
        for(int i = 0 ; i < drops.length; i++){
            int y = drops[i][0];
            int x = drops[i][1];
            board[y][x] = i + 1;
        }
        
        for(int i = 0; i < n; i++){
            Deque<Integer> deque = new LinkedList<>();
            for(int j = 0; j < w; j++){ //초기 w개 넣기
                while(!deque.isEmpty() // 오름차순으로 관리
                     && board[i][deque.peekLast()] > board[i][j]){
                    deque.pollLast();
                }
                deque.addLast(j);
            }
            
            minBoard[i][0] = board[i][deque.peekFirst()];
            
            for(int j = 1; j <= m - w; j++){
                if(deque.peekFirst() < j){// 유효기간 지나면 폐기
                    deque.pollFirst();
                }
                
                int cur = j + w - 1;
                while(!deque.isEmpty()
                     && board[i][deque.peekLast()] > board[i][cur]){
                    deque.pollLast();
                }
                deque.addLast(cur);
                minBoard[i][j] = board[i][deque.peekFirst()];
            }
        }
        
        for(int j = 0; j <= m - w; j++){
            Deque<Integer> deque = new LinkedList<>();
            for(int i = 0; i < h; i++){ //초기 h개 넣기
                while(!deque.isEmpty()
                     && minBoard[deque.peekLast()][j] > minBoard[i][j]){
                    deque.pollLast();
                }
                deque.addLast(i);
            }
            
            minResult[0][j] = minBoard[deque.peekFirst()][j];
            
            for(int i = 1; i <= n - h; i++){
                if(deque.peekFirst() < i){// 유효기간 지나면 폐기
                    deque.pollFirst();
                }
                
                int cur = i + h - 1;
                while(!deque.isEmpty()
                     && minBoard[deque.peekLast()][j] > minBoard[cur][j]){
                    deque.pollLast();
                }
                deque.addLast(cur);
                minResult[i][j] = minBoard[deque.peekFirst()][j];
            }
        }
        
        int maxRes = 0;
        for(int i = 0 ; i <= n - h; i++){
            for(int j = 0; j <= m - w; j++){
                if(minResult[i][j] > maxRes){
                    maxRes = minResult[i][j];
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }
        
        return answer;
    }
    
    
    
    
}
