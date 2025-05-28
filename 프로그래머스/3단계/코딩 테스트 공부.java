import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0, maxCop = 0;
        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        int[][] dist = new int[maxAlp + 2][maxCop + 2];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[alp][cop] = 0;

        // PriorityQueue: {시간, alp, cop}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, alp, cop});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int time = cur[0], ca = cur[1], cc = cur[2];

            if (ca >= maxAlp && cc >= maxCop) return time;

            if (dist[ca][cc] < time) continue; // 이미 더 짧은 경로로 방문함

            // 1. 알고리즘 공부
            if (ca + 1 <= maxAlp && dist[ca + 1][cc] > time + 1) {
                dist[ca + 1][cc] = time + 1;
                pq.offer(new int[]{time + 1, ca + 1, cc});
            }

            // 2. 코딩 공부
            if (cc + 1 <= maxCop && dist[ca][cc + 1] > time + 1) {
                dist[ca][cc + 1] = time + 1;
                pq.offer(new int[]{time + 1, ca, cc + 1});
            }

            // 3. 문제 풀기
            for (int[] p : problems) {
                if (ca >= p[0] && cc >= p[1]) {
                    int na = Math.min(maxAlp, ca + p[2]);
                    int nc = Math.min(maxCop, cc + p[3]);
                    int newTime = time + p[4];
                    if (dist[na][nc] > newTime) {
                        dist[na][nc] = newTime;
                        pq.offer(new int[]{newTime, na, nc});
                    }
                }
            }
        }

        return dist[maxAlp][maxCop]; // fallback
    }
}
