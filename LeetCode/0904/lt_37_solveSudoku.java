class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j] == '.'){
                    for(char c='1'; c<='9'; c++){
                        if(isValid(board, i, j, c)){
                            board[i][j] = c;
                            if(backtrack(board)) return true;
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board,int row,int col,char c){
        for(int i=0;i<9;i++){
            if(board[row][i]==c || board[i][col]==c || board[3*(row/3)+i/3][3*(col/3)+i%3]==c)
                return false;
        }
        return true;
    }
}
/*
解題思路：
1. 回溯法填入空格，每次嘗試 '1'-'9'。
2. 若填入合法且後續能解出，則保留；否則回溯。
3. 時間 O(9^(n))，n 為空格數；空間 O(n) 遞迴棧。
*/
