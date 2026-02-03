import java.util.*;
import java.io.*;


class Main{

    /*
        1. 가장 큰 수 : M을 최대한 길게 가져가기 -> K 있으면 붙이기
        K가 연속이면 자르기
        K가 없으면 M을 단일값으로

        2. 가장 작은 수  : M을 최대한 길게 가져가기 -> K는 안 붙이기

        10^(M - 1)
        K는 그냥 5 이어붙이기
    */

    char[] str;
    int length;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String s = st.nextToken();
        str = s.toCharArray();
        length = str.length;

        getBigNumber();
        getSmallNumber();
    }

    void getBigNumber(){
        StringBuilder big = new StringBuilder();
        int mCount = 0;

        for(int i = 0 ; i < length; i++){
            if(str[i] == 'M'){
                mCount++;
            }
            else{
                big.append(5);
                for(int j = 0; j < mCount;j++){
                    big.append(0);
                }
                mCount = 0;
            }
        }

        for(int i = 0 ; i < mCount; i++){
            big.append(1);
        }

        System.out.println(big.toString());
    }

    void getSmallNumber(){
        StringBuilder small = new StringBuilder();
        int mCount = 0;

        for(int i = 0 ; i < length; i++){
            if(str[i] == 'M'){
                mCount++;
            }
            else{
                if(mCount > 0) {
                    small.append(1);
                    mCount--;
                }
                for(int j = 0 ; j < mCount; j++){
                    small.append(0);
                }
                small.append(5);
                mCount = 0;
            }
        }

        if(mCount > 0){
            small.append(1);
            mCount--;
        }
        for(int i = 0 ; i < mCount; i++){
            small.append(0);
        }

        System.out.println(small.toString());
    }


}
