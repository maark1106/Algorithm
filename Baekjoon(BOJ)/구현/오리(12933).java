import java.util.*;
import java.io.*;

class Main {

    List<Duck> ducks;

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        ducks = new ArrayList<>();

        for(int i = 0 ; i < s.length(); i++){
            if(!isDuck(s.charAt(i))){
                System.out.print(-1);
                return;
            }
        }

        for(Duck duck : ducks){
            if(duck.cnt != 0){
                System.out.println(-1);
                return;
            }
        }

        System.out.print(ducks.size());
    }

    boolean isDuck(char c){
        for(Duck duck : ducks){
            if(c == 'q'){
                if(duck.cnt == 0){
                    duck.cnt++;
                    return true;
                }
            }
            else if(c == 'u'){
                if(duck.cnt == 1){
                    duck.cnt++;
                    return true;
                }
            }
            else if(c == 'a'){
                if(duck.cnt == 2){
                    duck.cnt++;
                    return true;
                }
            }
            else if(c == 'c'){
                if(duck.cnt == 3){
                    duck.cnt++;
                    return true;
                }
            }
            else if(c == 'k'){
                if(duck.cnt == 4){
                    duck.cnt = 0;
                    return true;
                }
            }
        }

        if(c == 'q'){ // 오리 새로 넣어줘야 함
            ducks.add(new Duck(1));
            return true;
        }
        return false;
    }

    class Duck{
        int cnt;

        public Duck(int cnt){
            this.cnt = cnt;
        }
    }
}
