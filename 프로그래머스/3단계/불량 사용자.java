import java.util.*;

class Solution {

    HashSet<String> storage = new HashSet<>();
    HashSet<String> result = new HashSet<>();
    boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        for(int i = 0 ; i < banned_id.length; i++){
            banned_id[i] = banned_id[i].replace('*', '.');
        }

        visited = new boolean[user_id.length];
        DFS(user_id, banned_id, 0);

        return result.size();
    }

    void DFS(String[] user_id, String[] banned_id, int depth) {
        if (depth == banned_id.length) {
            List<String> list = new ArrayList<>(storage);
            Collections.sort(list);
            result.add(String.join("", list));
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (!visited[i]) {
                if (user_id[i].matches(banned_id[depth])) {
                    visited[i] = true;
                    storage.add(user_id[i]);
                    DFS(user_id, banned_id, depth + 1);
                    storage.remove(user_id[i]);
                    visited[i] = false;
                }

            }
        }
    }
}
