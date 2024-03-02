package 괄호변환;

public class Main {

    boolean isCorrect(String p){
        int open = 0;

        for(int i = 0 ; i < p.length();i++){
            if(p.charAt(i) == '('){
                open++;
            }
            else{
                if(open == 0)
                    return false;
                open--;
            }
        }
        return true;
    }

    int getDividePos(String p){
        int lCnt = 0;
        int rCnt = 0;
        for(int i = 0 ; i < p.length();i++){
            if(p.charAt(i) == '(')
                lCnt++;
            else
                rCnt++;

            if(lCnt == rCnt){
                return i;
            }
        }
        return p.length();
    }

    String recursive(String p){

        if(p.isEmpty()){
            return "";
        }

        int pos = getDividePos(p);

        String u = p.substring(0, pos + 1);
        String v = p.substring(pos + 1);

        if(isCorrect(u)){
            v = recursive(v);
            return u + v;
        }
        else{
            String temp = "(" + recursive(v) + ")";

            for(int i = 1;i < u.length() - 1;i++){
                if(u.charAt(i) == '('){
                    temp += ")";
                }
                else{
                    temp += "(";
                }
            }
            return temp;
        }
    }

    public String solution(String p) {
        return recursive(p);
    }

    public static void main(String[] args) {
        String res = new Main().solution("()))((()");
        System.out.println(res);
    }
}