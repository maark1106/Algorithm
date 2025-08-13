import java.util.*;

class Solution {

    public long solution(String expression) {
        long answer = 0;

        List<HashMap<String, Integer>> score = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            score.add(new HashMap<>());
        }

        init(score);

        expression = expression.replace("+", " + ")
                               .replace("-", " - ")
                               .replace("*", " * ");
        String[] strs = expression.split(" ");

        for (HashMap<String, Integer> curScore : score) {
            Stack<String> left = new Stack<>();
            Stack<String> right = new Stack<>();

            for (String token : strs) {
                if (token.equals("-") || token.equals("+") || token.equals("*")) {
                    while (!right.isEmpty() && curScore.get(right.peek()) >= curScore.get(token)) {
                        left.push(right.pop());
                    }
                    right.push(token);
                } else {
                    left.push(token);
                }
            }

            while (!right.isEmpty()) {
                left.push(right.pop());
            }

            Stack<String> s = new Stack<>();
            while (!left.isEmpty()) {
                s.push(left.pop());
            }

            Stack<Long> number = new Stack<>();
            while (!s.isEmpty()) {
                String cur = s.pop();
                if (cur.equals("-")) {
                    long b = number.pop();
                    long a = number.pop();
                    number.push(a - b);
                } else if (cur.equals("+")) {
                    long b = number.pop();
                    long a = number.pop();
                    number.push(a + b);
                } else if (cur.equals("*")) {
                    long b = number.pop();
                    long a = number.pop();
                    number.push(a * b);
                } else {
                    number.push(Long.parseLong(cur));
                }
            }
            answer = Math.max(answer, Math.abs(number.peek()));
        }

        return answer;
    }

    void init(List<HashMap<String, Integer>> score) {
        score.get(0).put("+", 0);
        score.get(0).put("*", 1);
        score.get(0).put("-", 2);

        score.get(1).put("+", 0);
        score.get(1).put("*", 2);
        score.get(1).put("-", 1);

        score.get(2).put("+", 1);
        score.get(2).put("*", 0);
        score.get(2).put("-", 2);

        score.get(3).put("+", 1);
        score.get(3).put("*", 2);
        score.get(3).put("-", 0);

        score.get(4).put("+", 2);
        score.get(4).put("*", 0);
        score.get(4).put("-", 1);

        score.get(5).put("+", 2);
        score.get(5).put("*", 1);
        score.get(5).put("-", 0);
    }
}
