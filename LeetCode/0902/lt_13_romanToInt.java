import java.util.*;
class Solution {
    public int romanToInt(String s) {
        // 建立羅馬數字對應表
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        // 從左到右掃描
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));

            // 若當前數字 < 後一個數字，表示需要減去 (例如 IV = 4)
            if (i + 1 < s.length() && value < map.get(s.charAt(i + 1))) {
                total -= value;
            } else {
                total += value;
            }
        }
        return total; // 回傳轉換後的整數
    }
}
/*
解題思路：
1. 建立羅馬數字與對應整數的映射表。
2. 從左到右遍歷字串：
   - 如果當前字元對應的數值 < 下一個字元的數值，代表是「減法情況」(如 IV=4, IX=9)，需減去。
   - 否則加上當前數值。
3. 遍歷完後得到結果。
4. 時間複雜度 O(n)，其中 n = 字串長度。
*/
