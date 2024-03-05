public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Integer[] height = new Integer[N]; // 키를 저장할 배열
        Integer[] distance = new Integer[N - 1]; //키 차이를 저장할 배열
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N;i++){
            height[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < N - 1; i++){ // 인접한 원생들의 키 차이 저장
            distance[i] = height[i + 1] - height[i];
        }

        Arrays.sort(distance); // 키 차이를 오름차순으로 정렬
        int result = 0;
        for(int i = 0 ; i < N - K;i++){ // 키 차이중 N - K를 선택하는 것이 N개를 K개의 그룹으로 묶는 것과 같다
            result += distance[i];
        }

        System.out.println(result);
    }
