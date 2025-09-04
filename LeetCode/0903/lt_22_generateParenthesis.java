import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // 若字串長度達到 2 * n，表示已經形成一個合法組合
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // 只要還能加左括號，就遞迴加一個 '('
        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // 回溯
        }

        // 只要右括號數量小於左括號，就可以加 ')'
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // 回溯
        }
    }
}
/*
解題思路：
1. 使用回溯法生成所有可能的括號組合。
2. 條件限制：
   - 左括號數量 (open) 不能超過 n。
   - 右括號數量 (close) 不能超過左括號數量。
3. 當字串長度達到 2 * n 時，表示形成一個合法的組合。
4. 時間複雜度約為 O(4^n / √n)，這是第 n 個 Catalan 數的數量級。
*/

