import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        while(N-- > 0){
            int[][] visited = new int[102][102];
            st = new StringTokenizer(br.readLine());
            int team = Integer.parseInt(st.nextToken());
            int problem = Integer.parseInt(st.nextToken());
            int myTeam = Integer.parseInt(st.nextToken()) - 1;
            int log = Integer.parseInt(st.nextToken());

            Team[] teams = new Team[team];
            for(int i = 0 ; i < team;i++){
                teams[i] = new Team(i, 0, 0, 0);
            }

            for(int i = 0; i < log; i++){
                st = new StringTokenizer(br.readLine());
                int id = Integer.parseInt(st.nextToken()) - 1;
                int pId = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());

                teams[id].last = i;
                teams[id].post++;
                if(visited[id][pId] == 0){
                    teams[id].total += score;
                    visited[id][pId] = score;
                } else if (visited[id][pId] < score) {
                    teams[id].total += score - visited[id][pId];
                    visited[id][pId] = score;
                }
            }

            Arrays.sort(teams);

            for(int i = 0 ; i < team;i++){
                if(teams[i].num == myTeam){
                    System.out.println(i + 1);
                }
            }
        }
    }


    class Team implements Comparable<Team>{
        int num;
        int total;
        int last;
        int post;

        public Team(int num, int total, int last, int post) {
            this.num = num;
            this.total = total;
            this.last = last;
            this.post = post;
        }

        @Override
        public int compareTo(Team team){
            if(this.total == team.total){ // 점수 내림차순
                if(this.post == team.post){ // 제출 오름차순
                    return this.last - team.last;// 시간 오름차순
                }
                return this.post - team.post;
            }
            return team.total - this.total;
        }
    }

}
