public class Main {
	 
	 public int[] solution(int[] sequence, int k) {	        	       
	        int[] answer = new int[2];
	        
	        int n = sequence.length;
	        int minLength = n + 1;
	        int l = 0, r = -1;
	        int sum = 0;
	        
	        while(r < n) {
	        	if(sum < k) {
	        		if(++r < n) { // r 증가 후 범위 체크
	        			sum += sequence[r];
	        		}
	        	}
	        	else if(sum == k) {
	        		if(minLength > r - l + 1) {
	        			minLength = r - l + 1;
	        			answer[0] = l;
	        			answer[1] = r;
	        		}
	        		sum -= sequence[l++];
	        	}
	        	else {
	        		sum -= sequence[l++];
	        	}
	        }	        
		               	     	        
	        return answer;
	    }

}
