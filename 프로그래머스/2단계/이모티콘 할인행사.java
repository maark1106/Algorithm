
public class Main {

    int[] discountStorage;
    int[] answer = {0,0}; // member, price

    public int[] solution(int[][] users, int[] emoticons) {

        discountStorage = new int[emoticons.length];
        DFS(0, users, emoticons);

        return answer;
    }

    void DFS(int depth, int[][] users, int[] emoticons) {
        if(depth == emoticons.length){
            int member = 0;
            int price = 0;

            for(int j = 0 ; j < users.length;j++) {
                int curPrice = getTotalPrice(users[j][0], emoticons);
                if (curPrice < users[j][1]) {
                    price += curPrice;
                } else {
                    member++;
                }
            }

            if(member > answer[0]){
                answer[0] = member;
                answer[1] = price;
            }
            else if(member == answer[0]){
                if(price > answer[1]){
                    answer[1] = price;
                }
            }
            return;
        }

        for(int i = 0 ; i < 4;i++){
            discountStorage[depth] = (i + 1) * 10;
            DFS(depth + 1, users, emoticons);
        }
    }


    int getTotalPrice(int userMaxDiscount, int[] emoticons) {
        int sum = 0;
        for(int i = 0 ; i <emoticons.length;i++){
            if(userMaxDiscount <= discountStorage[i]){
                sum += emoticons[i] * ((double)(100 - discountStorage[i]) * 0.01);
            }
        }
        return sum;
    }
}
