import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
 
class Solution {
   HashMap<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        //1. info를 백트래킹으로 - 포함하여 조합만들기
        //2. 정렬하기
        //3. and 제거하여 String 만들기
        //4. 이분탐색으로 찾기

        for (int i = 0; i < info.length; i++) {
            String[] p = info[i].split(" ");
            makeSentence(p, "", 0);
        }

        for (String s : map.keySet()) {
            Collections.sort(map.get(s));
        }

        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] q = query[i].split(" ");
            answer[i] = map.containsKey(q[0]) ? binarySearch(q[0], Integer.parseInt(q[1])) : 0;
        }

        return answer;
    }

    int binarySearch(String key, int score) {
        List<Integer> list = map.get(key);
        int start = 0, end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return list.size() - start;
    }

    void makeSentence(String[] p, String s, int count) {

        if (count == 4) {
            if (!map.containsKey(s)) {
                List<Integer> arr = new ArrayList<>();
                map.put(s, arr);
            }
            map.get(s).add(Integer.parseInt(p[4]));
            return;
        }

        makeSentence(p, s + "-", count + 1);
        makeSentence(p, s + p[count], count + 1);
    }
}
