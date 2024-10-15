// ArrayList를 이용하여 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String target = st.nextToken();

        ArrayList<Character> list = new ArrayList<>();

        for(int i = 0 ; i < str.length();i++){
            list.add(str.charAt(i));

            if(list.size() >= target.length()){
                int idx = 0;
                boolean flag = true;
                for(int j = list.size() - target.length(); j < list.size(); j++){
                    if(list.get(j) != target.charAt(idx)){
                        flag = false;
                        break;
                    }
                    idx++;
                }
                if(flag){
                    for(int j = 0 ; j < target.length(); j++){
                        list.remove(list.size() - 1);
                    }
                }
            }
        }

        if(list.size() == 0){
            System.out.println("FRULA");
        }
        else{
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < list.size();i++){
                sb.append(list.get(i));
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}


// StringBuilder를 사용하여 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        String target = st.nextToken();

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));

            if (sb.length() >= target.length()) {
                if (sb.substring(sb.length() - target.length(), sb.length()).equals(target)) {
                    sb.delete(sb.length() - target.length(), sb.length());
                }
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

