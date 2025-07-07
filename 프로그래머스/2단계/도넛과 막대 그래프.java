import java.util.*;

//도넛 : 1
//막대 : 2
//8자 : 3

class Solution {

    public int[] solution(int[][] edges) {
        int[] res = new int[4];

        HashMap<Integer, Integer> in = new HashMap<>();
        HashMap<Integer, Integer> out = new HashMap<>();

        for(int[] edge : edges){
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
        }

        for(int node: out.keySet()){ // out이 2개 이상 in x -> 시작 노드, else -> 8자++
            if(out.get(node) >=2){
                if(!in.containsKey(node)){
                    res[0] = node;
                }
                else{
                    res[3]++;
                }
            }
        }

        for(int node: in.keySet()){
            if(!out.containsKey(node)){
                res[2]++;
            }
        }

        res[1] = out.get(res[0]) - res[2] - res[3];

        return res;
    }


}
