import java.util.*;

class Solution {
    
    int allGem = 0;

    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }

        allGem = set.size();

        HashMap<String, Integer> bucket = new HashMap<>();

        int left = 0, right = 0;
        int resLeft = 0, resRight = 0;
        int minRes = Integer.MAX_VALUE;

        while (true) {
            bucket.put(gems[right], bucket.getOrDefault(gems[right], 0) + 1);

            while (bucket.get(gems[left]) > 1) {
                bucket.put(gems[left], bucket.get(gems[left]) - 1);
                left++;
            }

            if (bucket.size() == allGem) {
                if (right - left + 1 < minRes) {
                    minRes = right - left + 1;
                    resLeft = left;
                    resRight = right;
                }
            }
            right++;
            if (right == gems.length) {
                break;
            }
        }

        answer[0] = resLeft + 1;
        answer[1] = resRight + 1;

        return answer;
    }
}
