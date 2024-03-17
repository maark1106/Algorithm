public class Main {

    List<Long> storage = new ArrayList<>();

    void DFS(int depth, int end, int before, long sum){
        if(depth == end){
            storage.add(sum);
            return;
        }

        for(int i = 0 ; i <= 9;i++){
            if(before > i){
                DFS(depth + 1, end, i, sum * 10 + i);
            }
        }
    }

    long solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 1 ; i <= 10;i++){
            DFS(0, i, 10, 0);
        }

        if(N > storage.size()){
            return -1;
        }

        return storage.get(N - 1);
    }

    public static void main(String[] args) throws IOException {
        long result = new Main().solution();
        System.out.println(result);
    }
}
