class Solution {
    
    int res = 0;
    
    public int solution(int n, int[] stations, int w) {
        int posLeft;
        int posRight;
        
        posRight = stations[0] - 1 - w;
        plusStation(1, posRight, w);
        for(int i = 1; i < stations.length; i++){
            posLeft = stations[i - 1] + 1 + w;
            posRight = stations[i] - 1 - w;
            plusStation(posLeft, posRight, w);
        }
        
        posLeft = stations[stations.length - 1] + 1 + w;
        plusStation(posLeft, n, w);
        
        return res;
    }
    
    void plusStation(int posLeft, int posRight, int w){
        int dis = posRight - posLeft + 1;
        int coverDis = 2 * w + 1;
        if(dis <= 0){
            return;
        }
        
        res += dis / coverDis;
        if(dis % coverDis != 0){
            res++;
        }
    }
}
