import java.util.*;


/*  
    
    26^11
    
    26
    z
    
    27
    aa
    
    52
    az
    
    53
    ba
    
    28 -> 26 + 2
    ab
    (26^1 *(a - a + 1)) + (26^0 + 1)
    
    bac
    (26^2 * (b - a + 1)) + (26^1 *(a - a + 1)) + (26^0 * (c - a + 1))
    
    57
    aa, ab az
    ba bb bc bd be
    
    풀이
    1. bans를 숫자로 변환하기
    2. bans 정렬하기
    3. N보다 작은 개수 세기(k)
    4. N + k번째 문자열 구하기
    
    
*/

class Solution {
    
    long[] banNumbers;
    
    public String solution(long n, String[] bans) {

        banNumbers = new long[bans.length];
        for(int i = 0 ; i < banNumbers.length; i++){
            banNumbers[i] = getLength(bans[i]);
        }
        
        Arrays.sort(banNumbers);
        
        for(int i = 0 ; i < banNumbers.length; i++){
            if(n >= banNumbers[i]){ //지워진 걸 고려해서 비교
                n++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            n--;
            sb.append((char)('a' + (n % 26)));
            n /= 26;
        }
        
        String answer = sb.reverse().toString();
        
        return answer;
    }
    
    
    long getLength(String s){
        long idx = 1L;        
        long res = 0L;
        for(int i = s.length() - 1; i >= 0; i--){
            res += (idx * (s.charAt(i) - 'a' + 1));
            idx *= 26;
        }
        return res;
    }
    
    
    
}
