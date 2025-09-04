import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) stack.push(i);
                else maxLen = Math.max(maxLen, i - stack.peek());
            }
        }
        return maxLen;
    }
}
/*
解題思路：
1. 使用 Stack 保存索引，遇到 '(' 入棧，遇到 ')' 出棧。
2. 若棧空，更新基準索引；否則計算有效長度。
3. 時間 O(n)，空間 O(n)。
*/
