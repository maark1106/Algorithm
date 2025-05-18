import java.util.*;
import java.io.*;

class Main{

    int N;
    Homework[] homeworks;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        homeworks = new Homework[N + 1];

        List<Homework> homeworks = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());
            homeworks.add(new Homework(deadLine, cup));
        }

        Collections.sort(homeworks);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < homeworks.size();i++){
            Homework homework = homeworks.get(i);
            pq.add(homework.cup);
            if(pq.size() > homework.deadLine){
                pq.poll();
            }
        }

        int res = 0;
        while(!pq.isEmpty()){
            res += pq.poll();
        }

        System.out.print(res);
    }

    class Homework implements Comparable<Homework>{
        int deadLine;
        int cup;

        public Homework(int deadLine, int cup){
            this.deadLine = deadLine;
            this.cup = cup;
        }

        @Override
        public int compareTo(Homework h){
            return this.deadLine - h.deadLine;
        }
    }

}
