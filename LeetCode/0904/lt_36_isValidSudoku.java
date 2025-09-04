class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i=0;i<9;i++){
            boolean[] row = new boolean[9], col = new boolean[9], cube = new boolean[9];
            for(int j=0;j<9;j++){
                if(board[i][j] != '.' && !check(row, board[i][j])) return false;
                if(board[j][i] != '.' && !check(col, board[j][i])) return false;
                int r = 3*(i/3)+j/3, c = 3*(i%3)+j%3;
                if(board[r][c] != '.' && !check(cube, board[r][c])) return false;
            }
        }
        return true;
    }
    private boolean check(boolean[] seen, char c){
        int idx = c - '1';
        if(seen[idx]) return false;
        seen[idx] = true;
        return true;
    }
}
/*
解題思路：
1. 檢查每行、每列、每個 3x3 子格是否有重複數字。
2. 使用布林陣列記錄出現過的數字。
3. 時間 O(9*9)，空間 O(9)。
*/
