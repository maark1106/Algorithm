
import java.util.HashMap;

public class Main {

    int[][] graph = new int[51][51];
    int[] presentScore = new int[51];
    int[] totalPresent = new int[51];

    HashMap<String, Integer> nameNumber = new HashMap<>();
    int N; // 친구 수

    public int solution(String[] friends, String[] gifts) {

        N = friends.length;
        for(int i = 0 ; i < N;i++){
            nameNumber.put(friends[i], i);
        }

        for(int i = 0 ; i < gifts.length;i++){
            String[] names = gifts[i].split(" ");
            int giveNumber = nameNumber.get(names[0]); // 준 사람
            int receiveNumber = nameNumber.get(names[1]); // 받은 사람
            presentScore[giveNumber]++; //준 사람은 선물지수 + 1
            presentScore[receiveNumber]--; // 받은 사람은 선물지수 -1

            graph[giveNumber][receiveNumber]++; // 선물 관계도 업데이트
        }

        for(int i = 0 ; i < N  - 1; i++){
            for(int j = i + 1;j < N ;j++){
                if(graph[i][j] > graph[j][i]){ // i가 j보다 선물 준 개수가 많을 때
                    totalPresent[i]++;
                }
                else if(graph[i][j] < graph[j][i]){ // j가 i보다 선물 준 개수가 많을 때
                    totalPresent[j]++;
                }
                else{ // 준 선물이 같을 때 선물 지수 비교
                    if(presentScore[i] > presentScore[j]){
                        totalPresent[i]++;
                    }
                    else if(presentScore[i] < presentScore[j]){
                        totalPresent[j]++;
                    }
                    //선물 지수가 같다면 아무도 못 받음
                }
            }
        }

        int answer = totalPresent[0];
        for(int i = 1 ; i < N ; i++){
            answer = Math.max(answer,totalPresent[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
        int res = new Main().solution(friends, gifts);
        System.out.println(res);
    }
}
