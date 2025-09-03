class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return ""; 
            }
        }
        return prefix;
    }
}
/*
解題思路：
1. 以第一個字串作為初始前綴。
2. 與後續字串逐一比較，若不是以 prefix 開頭，就縮短 prefix。
3. 持續調整直到符合所有字串，或 prefix 變空字串。
4. 時間複雜度 O(n * m)，其中 n 為字串數量，m 為最短字串長度。
*/
