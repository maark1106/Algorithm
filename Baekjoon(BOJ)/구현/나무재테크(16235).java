import java.util.*;
import java.io.*;

class Main{

    int N;
    int M;
    int K;
    int[][] energy;
    int[][] board;
    List<Tree> trees;
    int[] dy = {-1, -1, -1, 1, 1, 1, 0, 0};
    int[] dx = {0, 1, -1, 0, 1, -1, -1, 1};

    public static void main(String[] args)throws Exception{
        new Main().solution();
    }

    void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        energy = new int[N][N];
        board = new int[N][N];
        trees = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            Arrays.fill(board[i], 5);
        }


        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                energy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(y, x, age));
        }

        for(int i = 0 ; i < K; i++){
            List<Tree> removeTrees = new ArrayList<>();
            liveSpring(removeTrees);
            liveSummer(removeTrees);
            liveAutumn();
            liveWinter();
        }

        System.out.println(trees.size());
    }

    void liveSpring(List<Tree> removeTrees){
        Collections.sort(trees);
        List<Tree> newTrees = new ArrayList<>();

        for(int i = 0 ; i < trees.size(); i++){
            Tree cur = trees.get(i);
            if(board[cur.y][cur.x] >= cur.age){
                board[cur.y][cur.x] -= cur.age;
                cur.age++;
                newTrees.add(cur);
            }
            else{
                removeTrees.add(cur);
            }
        }
        trees = newTrees;
    }

    void liveSummer(List<Tree> removeTrees){
        for(Tree tree: removeTrees){
            board[tree.y][tree.x] += tree.age / 2;
        }
    }

    void liveAutumn(){
        List<Tree> copyTree = new ArrayList<>(trees);
        for(Tree tree: copyTree){
            if(tree.age % 5 == 0){
                for(int i = 0; i < 8; i++){
                    int yy = tree.y + dy[i];
                    int xx = tree.x + dx[i];

                    if(yy < 0 || yy >= N || xx < 0 || xx >= N){
                        continue;
                    }

                    trees.add(new Tree(yy, xx, 1));
                }
            }
        }
    }

    void liveWinter(){
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                board[i][j] += energy[i][j];
            }
        }
    }


    class Tree implements Comparable<Tree>{
        int y;
        int x;
        int age;

        public Tree(int y, int x, int age){
            this.y = y;
            this.x = x;
            this.age = age;
        }

        @Override
        public int compareTo(Tree t){
            return this.age - t.age;
        }
    }



}
