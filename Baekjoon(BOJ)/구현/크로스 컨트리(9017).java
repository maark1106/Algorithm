import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int T = Integer.parseInt(st.nextToken());

        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            int[] teamNumber = new int[N];
            int[] count = new int[201];
            for(int i = 0 ; i < N;i++){
                teamNumber[i] = Integer.parseInt(st.nextToken());
                count[teamNumber[i]]++;
            }

            HashSet<Integer> sixTeam = new HashSet<>();
            for(int i = 1 ; i <= 200; i++){
                if(count[i] == 6){
                    sixTeam.add(i);
                }
            }

            HashMap<Integer, Team> map = new HashMap<>();
            for(int number : sixTeam){
                map.put(number, new Team(number, 0, 0, 0));
            }

            int score = 1;
            for(int i = 0 ; i < N;i++){
                if(sixTeam.contains(teamNumber[i])){
                    Team team = map.get(teamNumber[i]);
                    if(team.cnt < 4){
                        team.sum += score;
                    } else if (team.cnt == 4) {
                        team.five = score;
                    }
                    team.cnt++;
                    map.put(teamNumber[i], team);

                    score++;
                }
            }

            ArrayList<Team> teams = new ArrayList<>();
            for(Team team : map.values()){
                teams.add(team);
            }
            Collections.sort(teams);

            System.out.println(teams.get(0).num);
        }
    }

    class Team implements Comparable<Team>{
        int num;
        int cnt;
        int five;
        int sum;

        public Team(int num, int cnt, int five, int sum) {
            this.num = num;
            this.cnt = cnt;
            this.five = five;
            this.sum = sum;
        }

        @Override
        public int compareTo(Team team) {
            if(this.sum == team.sum){
                return this.five - team.five;
            }
            return this.sum - team.sum;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
