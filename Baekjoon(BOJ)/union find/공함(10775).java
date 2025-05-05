import java.util.*;
import java.io.*;

class Main {

    int G;
    int P;
    int[] parent;

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        G = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        parent = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parent[i] = i;
        }

        int res = 0;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int pNum = find(num);
            if (pNum == 0) {
                break;
            } else {
                union(pNum, pNum - 1);
            }
            res++;
        }

        System.out.print(res);
    }

    void union(int a, int b){
        a = find(a);
        b = find(b);
        parent[a] = b;
    }

    int find(int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }
}
