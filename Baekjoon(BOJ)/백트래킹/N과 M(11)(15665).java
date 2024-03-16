public class Main {

    int N;
    int M;
    ArrayList<Integer> numbers = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    void DFS(int[] result, int level){
        if(level == M){
            for(int i = 0 ; i < M; i++){
                sb.append(result[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0 ; i < numbers.size();i++){
            result[level] = numbers.get(i);
            DFS(result, level + 1);
        }
    }

        void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            HashSet<Integer> numberSet = new HashSet<>();
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                numberSet.add(Integer.parseInt(st.nextToken()));
            }

            for (int number : numberSet) {
                numbers.add(number);
            }

            Collections.sort(numbers);

            int[] result = new int[M];
            DFS(result, 0);
            System.out.println(sb);
        }


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
