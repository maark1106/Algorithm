import java.util.*;

class Solution {

    int answer;

    int[] order;
    int N;
    boolean[] visited;

    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;

        N = n;
        order = new int[dist.length];
        visited = new boolean[dist.length];

        dfs(0, weak, dist);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    // 친구 이동 거리의 모든 순열 생성
    void dfs(int depth, int[] weak, int[] dist) {
        if (depth == dist.length) {
            runFriend(weak, dist);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            order[depth] = dist[i];

            dfs(depth + 1, weak, dist);

            visited[i] = false;
        }
    }

    // 현재 친구 투입 순서로 모든 시작점 검사
    void runFriend(int[] weak, int[] dist) {
        int weakCount = weak.length;

        for (int start = 0; start < weakCount; start++) {
            int friendCount = 1;

            // 첫 번째 친구가 start 취약점에서 출발
            int friendStart = weak[start];
            int friendDistance = order[0];

            // start부터 원형으로 취약점 확인
            for (int k = 0; k < weakCount; k++) {
                int idx = (start + k) % weakCount;

                // 현재 친구의 출발점부터 현재 취약점까지 시계 방향 거리
                int gap = (weak[idx] - friendStart + N) % N;

                // 현재 친구가 도달할 수 없는 경우
                if (gap > friendDistance) {
                    friendCount++;

                    // 더 이상 투입할 친구가 없음
                    if (friendCount > dist.length) {
                        break;
                    }

                    // 현재 미점검 취약점에서 다음 친구 출발
                    friendStart = weak[idx];
                    friendDistance = order[friendCount - 1];
                }
            }

            // 모든 취약점을 점검한 경우만 정답 갱신
            if (friendCount <= dist.length) {
                answer = Math.min(answer, friendCount);
            }
        }
    }
}
