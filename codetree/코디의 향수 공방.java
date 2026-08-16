import java.util.*;
import java.io.*;

/*
    풀이
    1. 가장 처음에 list만들기
    2. 맨 뒤에 동적으로 추가해야 하므로 arrayList
    3. 폐기 옵션은 존재 여부를 알아야 하므로 삭제하지 않고 boolean으로 관리
    4. dp로 K의 최소값은 몇개로 만들 수 있을지 탐색
    5. 탑, 미들 정하고 바텀은 이분탐색으로 N^2logN
*/

public class Main {

    static List<Integer> perfumes;
    static HashSet<Integer> removes;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int Q = Integer.parseInt(st.nextToken());
        perfumes = new ArrayList<>();
        removes = new HashSet<>();

        for(int i = 0 ; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if(num == 1){
                int N = Integer.parseInt(st.nextToken());
                for(int j = 0 ; j < N; j++){
                    int s = Integer.parseInt(st.nextToken());
                    perfumes.add(s);
                }
            }
            else if(num == 2){
                int s = Integer.parseInt(st.nextToken());
                perfumes.add(s);
            }
            else if(num == 3){
                int idx = Integer.parseInt(st.nextToken()) - 1;
                if(idx >= perfumes.size() || removes.contains(idx)){
                    System.out.println(-1);
                }
                else{
                    removes.add(idx);
                    System.out.println(perfumes.get(idx));
                }

            }
            else if(num == 4){
                int K = Integer.parseInt(st.nextToken());
                System.out.println(blend(K));
            }
            else if(num == 5){
                int K = Integer.parseInt(st.nextToken());
                System.out.println(makeAllPerfume(K));
            }
        }
    }

    static int blend(int K){
            Set<Integer> set = new HashSet<>();
            for(int i = 0; i < perfumes.size(); i++){
                if(!removes.contains(i)){
                    set.add(perfumes.get(i));
                }
            }

            List<Integer> blendPerfume = new ArrayList<>(set);
            Collections.sort(blendPerfume);
            int N = blendPerfume.size();
            int[] dp = new int[K + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for(int i = 0 ; i < N; i++){
                int cur = blendPerfume.get(i);
                for(int j = cur; j <= K; j++){
                    if(dp[j - cur] != Integer.MAX_VALUE){ // 이전에 도달할 수 있을 때만
                        dp[j] = Math.min(dp[j], dp[j - cur] + 1);
                    }
                }
            }

            return dp[K] == Integer.MAX_VALUE ? -1 : dp[K];
        }

        static int makeAllPerfume(int K){
            List<Integer> blendPerfume = new ArrayList<>();
            for(int i = 0 ; i < perfumes.size(); i++){
                if(!removes.contains(i)){
                    blendPerfume.add(perfumes.get(i));
                }
            }
            int N = blendPerfume.size();
            int total = 0;
            Collections.sort(blendPerfume);

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    int left = 0;
                    int right = N - 1;
                    // target 이상인 첫 지점 찾기
                    int target = K - blendPerfume.get(i) - blendPerfume.get(j);
                    while(left <= right){
                        int mid = (left + right) / 2;

                        if(target <= blendPerfume.get(mid)){
                            right = mid - 1;
                        }
                        else{
                            left = mid + 1;
                        }
                    }

                    int cnt = N - left;
                    total += cnt;
                }
            }

            return total;
        }



}
