public int solution(int [][]board)
    {
        int answer = 0;

        int row = board.length;
        int col = board[0].length;

        if(row == 1 || col == 1){
            for(int i = 0 ; i < row;i++){
                for(int j = 0 ;j< col;j++){
                    if(board[i][j] == 1){
                        return 1;
                    }
                }
            }
            return answer;
        }

        for(int i = 1; i< row ;i++){
            for(int j = 1; j< col;j++){
                if(board[i][j] == 0){
                    continue;
                }

                board[i][j] = Math.min(board[i - 1][j - 1],Math.min(board[i][j - 1], board[i - 1][j])) + 1;
                if(board[i][j] * board[i][j] > answer){
                    answer = board[i][j] * board[i][j];
                }
            }
        }

        return answer;
    }
