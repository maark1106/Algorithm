package anew_.접두사_1141_그리디;

import java.util.*;
import java.io.*;

class Main {

	/*
			hello
			hi
			h
			run
			rerun
			running

			-> 무조건 작은 거 빼는 게 맞음
			짧은 순으로 정렬하기
			현재부터 뒤까지 탐색해서 겹치면 내꺼 빼버리기
			안겹치면 통과
			size 출력
	*/


    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Word> words = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            words.add(new Word(st.nextToken()));
        }

        Collections.sort(words);

        for(int i = 0 ; i < words.size(); i++){
            String cur = words.get(i).s;
            boolean flag = true;
            for(int j = i + 1; j < words.size();j++){
                String next = words.get(j).s;
                if(next.substring(0, cur.length()).equals(cur)){
                    flag = false;
                    break;
                }
            }

            if(!flag){
                words.remove(i);
                i--;
            }
        }

        System.out.print(words.size());
    }

    class Word implements Comparable<Word>{
        String s;

        public Word(String s){
            this.s = s;
        }

        @Override
        public int compareTo(Word w){
            return this.s.length() - w.s.length();
        }
    }
}
