
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    class UserInfo {
        String uid;
        boolean isEnter;

        public UserInfo(String uid, boolean isEnter) {
            this.uid = uid;
            this.isEnter = isEnter;
        }
    }

    class Solution {
        public String[] solution(String[] record) {
            Queue<String> result = new LinkedList<>();
            Queue<UserInfo> queue = new LinkedList<>();
            HashMap<String, String> map = new HashMap<>();

            for (String r : record) {
                String[] split = r.split(" ");
                if(r.contains("Enter")){
                    queue.add(new UserInfo(split[1], true));

                    map.put(split[1], split[2]);
                }
                else if(r.contains("Leave")){
                    queue.add(new UserInfo(split[1], false));
                }
                else{
                    map.put(split[1], split[2]);
                }
            }

            for (UserInfo userInfo : queue) {
                if(userInfo.isEnter == true){
                    result.add(map.get(userInfo.uid) + "님이 들어왔습니다.");
                }
                else{
                    result.add(map.get(userInfo.uid) + "님이 나갔습니다.");
                }
            }

            return result.stream()
                    .toArray(String[]::new);
        }
    }
}
