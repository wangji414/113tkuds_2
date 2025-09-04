import java.util.*;

class Solution {
    // 數字到字母的映射
    private static final String[] KEYS = {
        "",    // 0
        "",    // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs",// 7
        "tuv", // 8
        "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;

        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // 若已處理到最後，加入結果
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = KEYS[digits.charAt(index) - '0']; // 取當前數字對應的字母
        for (char c : letters.toCharArray()) {
            current.append(c);                  // 選擇一個字母
            backtrack(result, current, digits, index + 1); // 遞迴處理下一位
            current.deleteCharAt(current.length() - 1);    // 回溯，移除最後一個字母
        }
    }
}
/*
解題思路：
1. 建立數字到字母的映射表，模擬電話按鍵。
2. 使用回溯法：
   - 每次取出當前數字對應的所有字母，逐一嘗試拼接。
   - 當長度等於輸入的 digits 時，加入結果。
3. 時間複雜度 O(3^n * 4^m)，n 是 3 種字母的數字數量，m 是 4 種字母的數字數量。
4. 空間複雜度 O(n)，主要來自遞迴深度。
*/
