import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();
        int N = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            String[] strs = s.split("\\.");
            map.put(strs[1], map.getOrDefault(strs[1], 0) + 1);
        }

        List<String> res = new ArrayList<>(map.keySet());

        Collections.sort(res);
        for(int i = 0 ; i < res.size(); i++){
            System.out.println(res.get(i) + " " + map.get(res.get(i)));
        }
    }
}
