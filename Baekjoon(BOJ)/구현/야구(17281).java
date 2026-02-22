import java.util.*;
import java.io.*;

class Main{


    /*
        1. 각 라인마다 조합 구하기
          - 단, 0번 선수는 3번타자으로 고정하기
        2. 해당 조합으로 3아웃될 때까지 계속 시뮬 돌리기
          - 각라인 최고점이면 갱신하기
    */

    int N;
    int[][] game;
    int res;
    int th;
    int totalScore;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }


    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        game = new int[N][9];
        res = 0;

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                game[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] player = new int[9];
        boolean[] visited = new boolean[9];
        visited[0] = true;
        player[3] = 0; // 선수 idx가 들어감
        dfs(player, visited, 0);

        System.out.println(res);
    }

    void dfs(int[] player, boolean[] visited, int depth){
        if(depth == 9){
            gamePlay(player);
            return;
        }

        if(depth == 3){
            dfs(player, visited, depth + 1);
        }
        else{
            for(int i = 0 ; i < 9; i++){
                if(!visited[i]){
                    visited[i] = true;
                    player[depth] = i;
                    dfs(player, visited, depth + 1);
                    visited[i] = false;
                }
            }
        }
    }

    void gamePlay(int[] player){
        totalScore = 0;
        th = 0;

        for(int i = 0 ; i < N; i++){
            play(player, i);
        }

        res = Math.max(res, totalScore);
    }

    void play(int[] player, int idx){
        int out = 0;
        boolean[] seat = new boolean[4];
        int score = 0;

        while(true){
            if(out == 3){
                break;
            }

            int hit = game[idx][player[th]];
            th = (th + 1) % 9;
            if(hit == 0){
                out++;
            }
            else if(hit == 1){
                if(seat[3]){
                    seat[3] = false;
                    score++;
                }
                if(seat[2]){
                    seat[3] = true;
                    seat[2] = false;
                }
                if(seat[1]){
                    seat[2] = true;
                    seat[1] = false;
                }
                seat[1] = true;
            }
            else if(hit == 2){
                if(seat[3]){
                    seat[3] = false;
                    score++;
                }
                if(seat[2]){
                    seat[2] = false;
                    score++;
                }
                if(seat[1]){
                    seat[3] = true;
                    seat[1] = false;
                }
                seat[2] = true;
            }
            else if(hit == 3){
                if(seat[3]){
                    seat[3] = false;
                    score++;
                }
                if(seat[2]){
                    seat[2] = false;
                    score++;
                }
                if(seat[1]){
                    seat[1] = false;
                    score++;
                }
                seat[3] = true;
            }
            else if(hit == 4){
                if(seat[3]){
                    seat[3] = false;
                    score++;
                }
                if(seat[2]){
                    seat[2] = false;
                    score++;
                }
                if(seat[1]){
                    seat[1] = false;
                    score++;
                }
                score++;
            }
        }
        totalScore += score;
    }

}
