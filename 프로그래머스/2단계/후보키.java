
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    List<Set<Integer>> candidateKeys = new ArrayList<>();
    int answer = 0;
    int N;
    int M;

    public int solution(String[][] relation) {
        N = relation.length;
        M = relation[0].length;

        for (int i = 1; i <= M; i++) {
            DFS(new HashSet<>(),0, i, relation);
        }

        return answer;
    }

    void DFS(Set<Integer> currentSet,int depth, int maxDepth, String[][] relation) {

        if (currentSet.size() == maxDepth) {
            if (isUnique(currentSet, relation) && isMinimal(currentSet)) {
                candidateKeys.add(new HashSet<>(currentSet));
                answer++;
            }
            return;
        }

        for (int i = depth; i < M; i++) {
            currentSet.add(i);
            DFS(currentSet,i + 1, maxDepth, relation);
            currentSet.remove(i);
        }
    }

    boolean isMinimal(Set<Integer> currentSet) {
        for (Set<Integer> key : candidateKeys) {
            if(currentSet.containsAll(key)){
                return false;
            }
        }
        return true;
    }

    boolean isUnique(Set<Integer> currentSet, String[][] relation) {
        Set<String> uniqueSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int index : currentSet) {
                sb.append(relation[i][index]);
            }
            if(!uniqueSet.contains(sb.toString())){
                uniqueSet.add(sb.toString());
            }
            else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
        int result = new Main().solution(relation);
        System.out.println(result);
    }
}
