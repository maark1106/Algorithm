package 분리집합.친구네트워크_4195;

import java.util.*;
import java.io.*;

public class Main {

    int N;
    StringTokenizer st;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    HashMap<String, Integer> persons;
    int[] parents;
    int[] size;
    int idCounter;

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            getNetwork();
        }
    }

    void getNetwork() throws IOException {
        persons = new HashMap<>();
        parents = new int[2 * N]; // 부모, 친구 수
        size = new int[2 * N];
        idCounter = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name1 = st.nextToken();
            String name2 = st.nextToken();
            grantIdNumber(name1);
            grantIdNumber(name2);

            union(persons.get(name1), persons.get(name2));
        }
    }

    void grantIdNumber(String name) {
        if(!persons.containsKey(name)){
            persons.put(name, idCounter);
            parents[idCounter] = idCounter;
            size[idCounter] = 1;
            idCounter++;
        }
    }

    void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if(A != B){
            if(size[A] < size[B]){
                parents[A] = B;
                size[B] += size[A];
            }
            else{
                parents[B] = A;
                size[A] += size[B];
            }
        }

        System.out.println(size[A] > size[B] ? size[A] : size[B]);
    }

    int find(int a) {
        if(a == parents[a]){
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}
