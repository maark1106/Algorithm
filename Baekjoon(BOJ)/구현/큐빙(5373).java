package aaa.큐빙_5373;

import java.util.*;
import java.io.*;

class Main{

    /*
        빨 초 오 파 흰 노
        0 1  2  3 4 5
        앞 왼 뒤 오 위 아래
        red+
        흰4 (2, 0), (2, 1), (2, 2)-> 파3 (0, 0), (1, 0), (2, 0)
        -> 노5 (0 ,2), (0, 1), (0 ,0) -> 초1 (2, 2), (1, 2), (0, 2)
    */

    char[][][] cube;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cube = new char[6][3][3];

        int N = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i <N; i++){
            init();

            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M; j++){
                String s = st.nextToken();
                if(s.charAt(0) == 'F'){
                    spinRed(s.charAt(1));
                }
                else if(s.charAt(0) == 'L'){
                    spinGreen(s.charAt(1));
                }
                else if(s.charAt(0) == 'R'){
                    spinBlue(s.charAt(1));
                }
                else if(s.charAt(0) == 'B'){
                    spinOrange(s.charAt(1));
                }
                else if(s.charAt(0) == 'U'){
                    spinWhite(s.charAt(1));
                }
                else if(s.charAt(0) == 'D'){
                    spinYellow(s.charAt(1));
                }
            }

            for(int y = 0 ; y < 3; y++){
                for(int x = 0 ; x < 3; x++){
                    System.out.print(cube[4][y][x]);
                }
                System.out.println();
            }
        }
    }

    void spinPlus(int num){
        char temp = cube[num][0][0];
        cube[num][0][0] = cube[num][2][0];
        cube[num][2][0] = cube[num][2][2];
        cube[num][2][2] = cube[num][0][2];
        cube[num][0][2] = temp;

        temp = cube[num][0][1];
        cube[num][0][1] = cube[num][1][0];
        cube[num][1][0] = cube[num][2][1];
        cube[num][2][1] = cube[num][1][2];
        cube[num][1][2] = temp;
    }

    void spinMinus(int num){
        char temp = cube[num][0][0];
        cube[num][0][0] = cube[num][0][2];
        cube[num][0][2] = cube[num][2][2];
        cube[num][2][2] = cube[num][2][0];
        cube[num][2][0] = temp;

        temp = cube[num][0][1];
        cube[num][0][1] = cube[num][1][2];
        cube[num][1][2] = cube[num][2][1];
        cube[num][2][1] = cube[num][1][0];
        cube[num][1][0] = temp;
    }

    void spinRed(char dir){
        //흰4 (2, 0), (2, 1), (2, 2)-> 파3 (0, 0), (1, 0), (2, 0)
        // -> 노5 (0 ,2), (0, 1), (0 ,0) -> 초1 (2, 2), (1, 2), (0, 2)
        if(dir == '+'){
            spinPlus(0);

            char temp = cube[1][2][2];
            cube[1][2][2] = cube[5][0][2];
            cube[5][0][2] = cube[3][0][0];
            cube[3][0][0] = cube[4][2][0];
            cube[4][2][0] = temp;

            temp = cube[1][1][2];
            cube[1][1][2] = cube[5][0][1];
            cube[5][0][1] = cube[3][1][0];
            cube[3][1][0] = cube[4][2][1];
            cube[4][2][1] = temp;

            temp = cube[1][0][2];
            cube[1][0][2] = cube[5][0][0];
            cube[5][0][0] = cube[3][2][0];
            cube[3][2][0] = cube[4][2][2];
            cube[4][2][2] = temp;
        }
        else{
            spinMinus(0);

            char temp = cube[1][2][2];
            cube[1][2][2] = cube[4][2][0];
            cube[4][2][0] = cube[3][0][0];
            cube[3][0][0] = cube[5][0][2];
            cube[5][0][2] = temp;

            temp = cube[1][1][2];
            cube[1][1][2] = cube[4][2][1];
            cube[4][2][1] = cube[3][1][0];
            cube[3][1][0] = cube[5][0][1];
            cube[5][0][1] = temp;

            temp = cube[1][0][2];
            cube[1][0][2] = cube[4][2][2];
            cube[4][2][2] = cube[3][2][0];
            cube[3][2][0] = cube[5][0][0];
            cube[5][0][0] = temp;
        }
    }

    void spinGreen(char dir){
        //흰4 (0, 0), (1, 0), (2, 0)-> 빨0 (0, 0), (1, 0), (2, 0)
        // -> 노5 (0 ,0), (1, 0), (2 ,0) -> 오2 (2, 2), (1, 2), (0, 2)
        if(dir == '+'){
            spinPlus(1);

            char temp = cube[2][2][2];
            cube[2][2][2] = cube[5][0][0];
            cube[5][0][0] = cube[0][0][0];
            cube[0][0][0] = cube[4][0][0];
            cube[4][0][0] = temp;

            temp = cube[2][1][2];
            cube[2][1][2] = cube[5][1][0];
            cube[5][1][0] = cube[0][1][0];
            cube[0][1][0] = cube[4][1][0];
            cube[4][1][0] = temp;

            temp = cube[2][0][2];
            cube[2][0][2] = cube[5][2][0];
            cube[5][2][0] = cube[0][2][0];
            cube[0][2][0] = cube[4][2][0];
            cube[4][2][0] = temp;
        }
        else{
            spinMinus(1);

            char temp = cube[2][2][2];
            cube[2][2][2] = cube[4][0][0];
            cube[4][0][0] = cube[0][0][0];
            cube[0][0][0] = cube[5][0][0];
            cube[5][0][0] = temp;

            temp = cube[2][1][2];
            cube[2][1][2] = cube[4][1][0];
            cube[4][1][0] = cube[0][1][0];
            cube[0][1][0] = cube[5][1][0];
            cube[5][1][0] = temp;

            temp = cube[2][0][2];
            cube[2][0][2] = cube[4][2][0];
            cube[4][2][0] = cube[0][2][0];
            cube[0][2][0] = cube[5][2][0];
            cube[5][2][0] = temp;
        }
    }

    void spinBlue(char dir){
        //흰4 (2, 2), (1, 2), (0, 2)-> 오2 (0, 0), (1, 0), (2, 0)
        // -> 노5 (2 ,2), (1, 2), (0 ,2) -> 빨0 (2, 2), (1, 2), (0, 2)
        if(dir == '+'){
            spinPlus(3);

            char temp = cube[0][2][2];
            cube[0][2][2] = cube[5][2][2];
            cube[5][2][2] = cube[2][0][0];
            cube[2][0][0] = cube[4][2][2];
            cube[4][2][2] = temp;

            temp = cube[0][1][2];
            cube[0][1][2] = cube[5][1][2];
            cube[5][1][2] = cube[2][1][0];
            cube[2][1][0] = cube[4][1][2];
            cube[4][1][2] = temp;

            temp = cube[0][0][2];
            cube[0][0][2] = cube[5][0][2];
            cube[5][0][2] = cube[2][2][0];
            cube[2][2][0] = cube[4][0][2];
            cube[4][0][2] = temp;
        }
        else{
            spinMinus(3);

            char temp = cube[0][2][2];
            cube[0][2][2] = cube[4][2][2];
            cube[4][2][2] = cube[2][0][0];
            cube[2][0][0] = cube[5][2][2];
            cube[5][2][2] = temp;

            temp = cube[0][1][2];
            cube[0][1][2] = cube[4][1][2];
            cube[4][1][2] = cube[2][1][0];
            cube[2][1][0] = cube[5][1][2];
            cube[5][1][2] = temp;

            temp = cube[0][0][2];
            cube[0][0][2] = cube[4][0][2];
            cube[4][0][2] = cube[2][2][0];
            cube[2][2][0] = cube[5][0][2];
            cube[5][0][2] = temp;
        }
    }

    void spinOrange(char dir){
        //흰4 (0, 0), (0, 1), (0, 2)-> 초1 (2, 0), (1, 0), (0, 0)
        // -> 노5 (2 ,2), (2, 1), (2, 0) -> 파3 (0, 2), (1, 2), (2, 2)
        if(dir == '+'){
            spinPlus(2);

            char temp = cube[3][0][2];
            cube[3][0][2] = cube[5][2][2];
            cube[5][2][2] = cube[1][2][0];
            cube[1][2][0] = cube[4][0][0];
            cube[4][0][0] = temp;

            temp = cube[3][1][2];
            cube[3][1][2] = cube[5][2][1];
            cube[5][2][1] = cube[1][1][0];
            cube[1][1][0] = cube[4][0][1];
            cube[4][0][1] = temp;

            temp = cube[3][2][2];
            cube[3][2][2] = cube[5][2][0];
            cube[5][2][0] = cube[1][0][0];
            cube[1][0][0] = cube[4][0][2];
            cube[4][0][2] = temp;
        }
        else{
            spinMinus(2);

            char temp = cube[3][0][2];
            cube[3][0][2] = cube[4][0][0];
            cube[4][0][0] = cube[1][2][0];
            cube[1][2][0] = cube[5][2][2];
            cube[5][2][2] = temp;

            temp = cube[3][1][2];
            cube[3][1][2] = cube[4][0][1];
            cube[4][0][1] = cube[1][1][0];
            cube[1][1][0] = cube[5][2][1];
            cube[5][2][1] = temp;

            temp = cube[3][2][2];
            cube[3][2][2] = cube[4][0][2];
            cube[4][0][2] = cube[1][0][0];
            cube[1][0][0] = cube[5][2][0];
            cube[5][2][0] = temp;
        }
    }

    void spinWhite(char dir){
        //빨0 (0, 0), (0, 1), (0, 2)-> 초1 (0, 0), (0, 1), (0, 2)
        // -> 오2 (0 ,0), (0, 1), (0, 2) -> 파3 (0, 0), (0, 1), (0, 2)
        if(dir == '+'){
            spinPlus(4);

            char temp = cube[3][0][0];
            cube[3][0][0] = cube[2][0][0];
            cube[2][0][0] = cube[1][0][0];
            cube[1][0][0] = cube[0][0][0];
            cube[0][0][0] = temp;

            temp = cube[3][0][1];
            cube[3][0][1] = cube[2][0][1];
            cube[2][0][1] = cube[1][0][1];
            cube[1][0][1] = cube[0][0][1];
            cube[0][0][1] = temp;

            temp = cube[3][0][2];
            cube[3][0][2] = cube[2][0][2];
            cube[2][0][2] = cube[1][0][2];
            cube[1][0][2] = cube[0][0][2];
            cube[0][0][2] = temp;
        }
        else{
            spinMinus(4);

            char temp = cube[3][0][0];
            cube[3][0][0] = cube[0][0][0];
            cube[0][0][0] = cube[1][0][0];
            cube[1][0][0] = cube[2][0][0];
            cube[2][0][0] = temp;

            temp = cube[3][0][1];
            cube[3][0][1] = cube[0][0][1];
            cube[0][0][1] = cube[1][0][1];
            cube[1][0][1] = cube[2][0][1];
            cube[2][0][1] = temp;

            temp = cube[3][0][2];
            cube[3][0][2] = cube[0][0][2];
            cube[0][0][2] = cube[1][0][2];
            cube[1][0][2] = cube[2][0][2];
            cube[2][0][2] = temp;
        }
    }

    void spinYellow(char dir){
        //빨0 (2, 0), (2, 1), (2, 2)-> 파3 (2, 0), (2, 1), (2, 2)
        // -> 오2 (2 ,0), (2, 1), (2, 2) -> 초1 (2, 0), (2, 1), (2, 2)
        if(dir == '+'){
            spinPlus(5);

            char temp = cube[1][2][0];
            cube[1][2][0] = cube[2][2][0];
            cube[2][2][0] = cube[3][2][0];
            cube[3][2][0] = cube[0][2][0];
            cube[0][2][0] = temp;

            temp = cube[1][2][1];
            cube[1][2][1] = cube[2][2][1];
            cube[2][2][1] = cube[3][2][1];
            cube[3][2][1] = cube[0][2][1];
            cube[0][2][1] = temp;

            temp = cube[1][2][2];
            cube[1][2][2] = cube[2][2][2];
            cube[2][2][2] = cube[3][2][2];
            cube[3][2][2] = cube[0][2][2];
            cube[0][2][2] = temp;
        }
        else{
            spinMinus(5);

            char temp = cube[1][2][0];
            cube[1][2][0] = cube[0][2][0];
            cube[0][2][0] = cube[3][2][0];
            cube[3][2][0] = cube[2][2][0];
            cube[2][2][0] = temp;

            temp = cube[1][2][1];
            cube[1][2][1] = cube[0][2][1];
            cube[0][2][1] = cube[3][2][1];
            cube[3][2][1] = cube[2][2][1];
            cube[2][2][1] = temp;

            temp = cube[1][2][2];
            cube[1][2][2] = cube[0][2][2];
            cube[0][2][2] = cube[3][2][2];
            cube[3][2][2] = cube[2][2][2];
            cube[2][2][2] = temp;
        }
    }

    void init(){

        // 빨 초 오 파 흰 노
        // 0 1  2  3 4 5
        for(int i = 0; i < 3; i++){
            Arrays.fill(cube[0][i], 'r');
        }
        for(int i = 0; i < 3; i++){
            Arrays.fill(cube[1][i], 'g');
        }
        for(int i = 0; i < 3; i++){
            Arrays.fill(cube[2][i], 'o');
        }
        for(int i = 0; i < 3; i++){
            Arrays.fill(cube[3][i], 'b');
        }
        for(int i = 0; i < 3; i++){
            Arrays.fill(cube[4][i], 'w');
        }
        for(int i = 0; i < 3; i++){
            Arrays.fill(cube[5][i], 'y');
        }
    }


}
