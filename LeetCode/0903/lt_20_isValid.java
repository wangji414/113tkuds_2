import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 左括號入棧
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // 遇到右括號，檢查是否匹配
            else {
                if (stack.isEmpty()) return false; // 沒有對應的左括號
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false; // 不匹配
                }
            }
        }

        return stack.isEmpty(); // 最後必須沒有剩下的左括號
    }
}
/*
解題思路：
1. 使用 Stack 模擬括號匹配過程。
2. 當遇到左括號時，入棧。
3. 當遇到右括號時，檢查棧頂是否為對應的左括號，不符合就回傳 false。
4. 最後檢查 Stack 是否為空，若不為空則代表還有未匹配的左括號。
5. 時間複雜度 O(n)，空間複雜度 O(n)。
*/

