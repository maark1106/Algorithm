import java.util.*;

/*
    - 원소의 개수를 1, 2, 3... 늘려가면서 탐색
    - 새로운 원소가 그 다음 번호
        
    1. }} {{ 분리하기
    2. },{ 기준으로 자르기
    3. 넣고 정렬하기
    4. , 로 다시 자르면서 새로운 문자 찾으면 Set에 넣고 list에 추가
*/

class Solution {
    public int[] solution(String s) {
        
        s = s.replace("{{", ""); // }} {{ 분리
        s = s.replace("}}", ""); // {{ 분리
        String[] strs = s.split("\\},\\{");
        
        List<String> words = new ArrayList<>();
        for(String word: strs){
            words.add(word);
        }
        
        Collections.sort(words, new Comparator<String>(){
            public int compare(String s1, String s2){
                return Integer.compare(s1.length(), s2.length());
            }
        });
        int[] answer = new int[words.size()];
        Set<Integer> storage = new HashSet<>();
        
        for(int i = 0 ; i < words.size(); i++){
            String[] numbers = words.get(i).split(",");
            for(int j = 0 ; j < numbers.length; j++){
                int num = Integer.parseInt(numbers[j]);
                if(!storage.contains(num)){
                    answer[i] = num;
                    storage.add(num);
                    break;
                }
            }
        }
        
        return answer;
    }
        
}
