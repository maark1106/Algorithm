import java.util.*;
import java.io.*;

/*
    풀이
    1. 택배 놓기
    해당 열의 넓이만큼 비어있는지 확인해서 떨어뜨리기

    2. 택배 빼기
    - 왼쪽, 오른쪽 측면에서 가장 모든 면이 벽과 맞닿아 있는 것 중 가장 작은 번호 선택
    - 제거하기
    - 제거된 거 바로 위에 검사하기
    - 내려갈 수 있다면 내려보내기(몇 칸 내려갈 수 있나 확인)
    - 만약 없다면 여기서 stop


*/

public class Main {

    int N;
    int M;
    int[][] board;
    Box[] boxes;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boxes = new Box[101];

        board = new int[N + 1][N + 1];
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            boxes[num] = new Box(num, y, x);

            fall(num, y, x, startX);
        }

        for(int i = 0; i < M; i++){
            int startY;
            if(i % 2 == 0){
                startY = outLeft();
            }
            else{
                startY = outRight();
            }

            boolean[] visited = new boolean[101];
            for(int k = startY; k >= 1; k--){
                for(int j = 1; j <= N; j++){
                    int number = board[k][j];
                    if(number != 0 && !visited[number]){
                        visited[number] = true;
                        checkAndFall(k, j, number);
                    }
                }
            }
        }
    }

    void checkAndFall(int endY, int startX, int number){ //왼쪽 아래 모서리
        int cnt = 0;
        for(int i = endY + 1; i <= N; i++){
            boolean flag = true;
            for(int j = startX; j < startX + boxes[number].x; j++){
                if(board[i][j] != 0){
                    flag = false;
                    break;
                }
            }
            if(!flag){
                break;
            }
            cnt++;
        }

        if(cnt == 0){
            return;
        }

        for(int i = endY; i > endY - boxes[number].y; i--){// 왼쪽 아래 모서리 기준으로 0으로 만들기
            for(int j = startX; j < startX + boxes[number].x; j++){
                board[i][j] = 0;
            }
        }

        for(int i = endY + cnt; i > endY + cnt - boxes[number].y; i--){ // cnt만큼 내려서 채워넣기
            for(int j = startX; j < startX + boxes[number].x; j++){
                board[i][j] = number;
            }
        }
    }

    int outLeft(){
        boolean[] visited = new boolean[101];
        int boxNumber = 101;
        int boxStartY = 0;
        int boxStartX = 0;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                int number = board[i][j];
                if(number != 0 && !visited[number]){
                    visited[number] = true;
                    int startY = i;
                    int endY = i + boxes[number].y - 1;
                    int startX = 1;
                    int endX = j - 1;
                    if(leftCheck(startY, endY, startX, endX)){
                        if(boxNumber > number){
                            boxNumber = number;
                            boxStartY = i;
                            boxStartX = j;
                        }
                    }
                    break;
                }
            }
        }

        for(int i = boxStartY; i < boxStartY + boxes[boxNumber].y; i++){
            for(int j = boxStartX; j < boxStartX + boxes[boxNumber].x; j++){
                board[i][j] = 0;
            }
        }

        System.out.println(boxNumber);

        return boxStartY - 1; // 왼쪽 모서리 - 1 y부터 검사하면 됨
    }

    boolean leftCheck(int startY, int endY, int startX, int endX){
        for(int i = startY; i <= endY; i++){
            for(int j = startX; j <= endX; j++){
                if(board[i][j] != 0){
                    return false;
                }
            }
        }

        return true;
    }

    int outRight(){
        boolean[] visited = new boolean[101];
        int boxNumber = 101;
        int boxStartY = 0;
        int boxStartX = 0;

        for(int i = 1; i <= N; i++){
            for(int j = N; j >= 1; j--){
                int number = board[i][j];
                if(number != 0 && !visited[number]){
                    visited[number] = true;
                    int startY = i;
                    int endY = i + boxes[number].y - 1;
                    int startX = N;
                    int endX = j + 1;
                    if(rightCheck(startY, endY, startX, endX)){
                        if(boxNumber > number){
                            boxNumber = number;
                            boxStartY = i;
                            boxStartX = j; // 오른쪽 위 모서리
                        }
                    }
                    break;
                }
            }
        }

        for(int i = boxStartY; i < boxStartY + boxes[boxNumber].y; i++){
            for(int j = boxStartX; j > boxStartX - boxes[boxNumber].x; j--){
                board[i][j] = 0;
            }
        }

        System.out.println(boxNumber);

        return boxStartY - 1; // 오른쪽 위 모서리 - 1 y부터 검사하면 됨
    }

    boolean rightCheck(int startY, int endY, int startX, int endX){
        for(int i = startY; i <= endY; i++){
            for(int j = startX; j >= endX; j--){
                if(board[i][j] != 0){
                    return false;
                }
            }
        }

        return true;
    }

    void fall(int num, int y, int x, int startX){
        int end;
        boolean flag = true;
        for(end = 1; end <= N; end++){ // 얼마나 내려갈 수 있나 보기
            for(int j = startX; j < startX + x; j++){
                if(board[end][j] != 0){
                    flag = false;
                    break;
                }
            }
            if(!flag){
                break;
            }
        }

        int startY = end - y;
        for(int i = startY; i < startY + y; i++){ // 해당 범위만큼 Num으로 색칠하기
            for(int j = startX; j < startX + x; j++){
                board[i][j] = num;
            }
        }
    }

    class Box{
        int num;
        int y;
        int x;

        public Box(int num, int y, int x){
            this.num = num;
            this.y = y;
            this.x = x;
        }
    }
}
