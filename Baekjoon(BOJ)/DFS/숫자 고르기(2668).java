package new_.숫자고르기_2668;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    int N;
    int[] numbers;
    boolean[] visited;
    List<Integer> result;
    int cnt;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        numbers = new int[N + 1];
        visited = new boolean[N + 1];
        result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N ; i++){
            visited[i] = true;
            DFS(i, i);
            visited[i] = false;
        }

        System.out.println(result.size());

        for(int num : result){
            System.out.println(num);
        }
    }

    void DFS(int start, int cur){
        if(numbers[cur] == start){
            result.add(start);
            return;
        }

        if(!visited[numbers[cur]]){
            visited[numbers[cur]] = true;
            DFS(start, numbers[cur]);
            visited[numbers[cur]] = false;
        }
    }



    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
