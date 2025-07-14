package 문자열.문자열압축_level2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    class Solution {

        public int solution(String s) {

            int res = s.length();
            for (int i = 1; i <= s.length() / 2; i++) {
                res = Math.min(res, pressWord(s, i));
            }

            return res;
        }

        int pressWord(String str, int i) {

            StringBuilder sb = new StringBuilder();
            int s = 0;
            int e = i;
            String cur = str.substring(s, e);
            int cnt = 1;

            while (true) {
                s = e;
                e = s + i;
                if (e > str.length()) {
                    e = str.length();
                }
                String next = str.substring(s, e);
                if (cur.equals(next)) {
                    cnt++;
                } else {
                    if (cnt > 1) {
                        sb.append(cnt);
                    }
                    sb.append(cur);
                    cnt = 1;
                    cur = next;
                }

                if (e == str.length()) {
                    break;
                }
            }

            if (cnt > 1) {
                sb.append(cnt);
            }
            sb.append(cur);
            return sb.length();
        }
    }


}
