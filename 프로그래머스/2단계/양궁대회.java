import java.util.*;

/*
    1. 10 9 8 7 6 5 4 3 2 1 0
    2. DFS로 0 ~ 남은 화살 개수 만큼 돌기
        종료 조건은 0개 다 쓴경우 점수 비교 후 바로 return
*/

class Solution {

    int maxScore;
    boolean flag;
    int[] answer;

    public int[] solution(int n, int[] info) {
        answer = new int[11];
        int[] score = new int[11];
        flag = false;
        maxScore = 0;
        DFS(0, n, score, info); // depth, 남은 점수 개수

        if(!flag){
            return new int[]{-1};
        }
        return answer;
    }

    void DFS(int depth, int n, int[] score, int[] info){
        if(depth == 11){

            if(n > 0){
                return; // 남으면 안됨
            }

            int scoreA = 0;
            int scoreB = 0;

            for(int i = 0 ; i < 11; i++){
                if(info[i] == 0 && score[i] == 0){ // 둘다 0이면 안 받기
                    continue;
                }

                if(info[i] >= score[i]){
                    scoreA += 10 - i;
                }
                else{
                    scoreB += 10 - i;
                }
            }

            //B가 점수가 더 높고 기존 최대 점수차보다 크거나 같을 때
            if(scoreA < scoreB && (scoreB - scoreA) >= maxScore){
                if((scoreB - scoreA) == maxScore){
                    for(int i = 10; i >= 0; i--){
                        if(answer[i] > score[i]){
                            return;
                        }else if(answer[i] < score[i]){
                            break;
                        }
                    }
                }

                maxScore = scoreB - scoreA;
                for(int i = 0 ; i < 11; i++){
                    answer[i] = score[i];
                }
                flag = true;
            }
            return;
        }

        for(int i = n; i >= 0; i--){
            score[depth] = i;
            DFS(depth + 1, n - i, score, info);
        }
    }
}
