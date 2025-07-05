package new_.백준.단어맞추기_9081;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            printNextWord(s);
        }
    }

    void printNextWord(String s){
        Character[] strs = new Character[s.length()];
        for(int i = 0 ; i < s.length();i++){
            strs[i] = s.charAt(i);
        }
        int N = s.length();
        boolean flag = false;
        for(int i = N - 2; i >= 0; i--){
            char target = strs[i];
            for(int j = N - 1;j > i; j--){
                if(target < strs[j]){
                    char temp = strs[i];
                    strs[i] = strs[j];
                    strs[j] = temp;

                    Character[] newStr = new Character[N - 1 - i];
                    int idx = i + 1;
                    for(int k = 0; k < newStr.length;k++){
                        newStr[k] = strs[idx++];
                    }
                    Arrays.sort(newStr);
                    idx = 0;
                    for(int k = i + 1; k < N;k++){
                        strs[k] = newStr[idx++];
                    }
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
        }

        for(int i = 0; i < strs.length; i++){
            System.out.print(strs[i]);
        }
        System.out.println();
    }
}
