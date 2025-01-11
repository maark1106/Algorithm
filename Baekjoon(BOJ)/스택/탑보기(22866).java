import java.io.*;
import java.util.*;

public class Main {

    int N;
    int[] height;
    int[] totalCnt;
    int[] closeBuilding;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        height = new int[N + 1];
        totalCnt = new int[N + 1];
        closeBuilding = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(height[1], 1));

        for (int i = 2; i <= N; i++) {
            while (!stack.isEmpty() && stack.peek().h <= height[i]) {
                stack.pop();
            }
            if (stack.size() >= 1) {
                closeBuilding[i] = stack.peek().index;
            }
            stack.push(new Pair(height[i], i));
            totalCnt[i] += stack.size() - 1;
        }
        stack = new Stack<>();
        stack.push(new Pair(height[N], N));

        for (int i = N - 1; i >= 1; i--) {
            while (!stack.isEmpty() && stack.peek().h <= height[i]) {
                stack.pop();
            }
            if (stack.size() >= 1) {
                if (closeBuilding[i] == 0 || Math.abs(i - closeBuilding[i]) > Math.abs(i - stack.peek().index)) {
                    closeBuilding[i] = stack.peek().index;
                }
            }
            stack.push(new Pair(height[i], i));
            totalCnt[i] += stack.size() - 1;
        }

        for (int i = 1; i <= N; i++) {
            if (totalCnt[i] == 0) {
                System.out.println(0);
            }
            else {
                System.out.println(totalCnt[i] + " " + closeBuilding[i]);
            }
        }
    }

    class Pair {
        int h;
        int index;

        public Pair(int h, int index) {
            this.h = h;
            this.index = index;
        }
    }
}
