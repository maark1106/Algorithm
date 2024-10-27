import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] checked = new boolean[1000];

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            boolean flag = false;
            String[] words = str.split(" ");

            for (int j = 0; j < words.length; j++) {
                char lowerChar = Character.toLowerCase(words[j].charAt(0));
                if (lowerChar - 'a' < 0) {
                    continue;
                }
                if (!checked[lowerChar - 'a']) {
                    checked[lowerChar - 'a'] = true;
                    flag = true;
                    words[j] = "[" + words[j].charAt(0) + "]" + words[j].substring(1);
                    String res = "";
                    for (String word : words) {
                        res += word + " ";
                    }
                    System.out.println(res);
                    break;
                }
            }

            if (!flag) {
                for (int j = 0; j < str.length(); j++) {
                    char lowerChar = Character.toLowerCase(str.charAt(j));
                    if (lowerChar - 'a' < 0) {
                        continue;
                    }
                    if (!checked[lowerChar - 'a']) {
                        checked[lowerChar - 'a'] = true;
                        flag = true;
                        String res = str.substring(0, j) + "[" + str.charAt(j) + "]" + str.substring(j + 1);
                        System.out.println(res);
                        break;
                    }
                }
            }

            if (!flag) {
                System.out.println(str);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
