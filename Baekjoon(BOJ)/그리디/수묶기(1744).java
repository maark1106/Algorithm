import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Integer> minusList = new ArrayList<>();
        List<Integer> plusList = new ArrayList<>();
        int res = 0;
        for(int i = 0 ; i < N;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num <= 0){
                minusList.add(num);
            }
            else if(num == 1){
                res++;
            }
            else{
                plusList.add(num);
            }
        }

        Collections.sort(minusList);
        if(minusList.size() % 2 == 1){
            res += minusList.get(minusList.size() - 1);
            minusList.remove(minusList.size() - 1);
        }

        for(int i = 0 ; i < minusList.size() / 2; i++){
            int num1 = minusList.get(i * 2);
            int num2 = minusList.get(i * 2 + 1);
            res += num1 * num2;
        }

        Collections.sort(plusList, Collections.reverseOrder());
        if(plusList.size() % 2 == 1){
            res += plusList.get(plusList.size() - 1);
            plusList.remove(plusList.size() - 1);
        }
        for(int i = 0 ; i < plusList.size() / 2;i++){
            int num1 = plusList.get(i * 2);
            int num2 = plusList.get(i * 2 + 1);
            res += num1 * num2;
        }

        System.out.print(res);
    }
}
