package 분리집합.집합의표현_1717;

import java.util.*;
import java.io.*;

public class Main {

    int[] parent;

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (number == 0) {
                union(a, b);
            } else {
                if (find(a) == find(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if(A != B){
            if(A < B){
                parent[B] = A;
            }
            else{
                parent[A] = B;
            }
        }
    }

    int find(int a) {
        if (parent[a] == a) {
            return a;
        }

        parent[a] = find(parent[a]);
        return parent[a];
    }
}
