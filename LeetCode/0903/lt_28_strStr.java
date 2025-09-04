class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i <= n - m; i++) {
            if (haystack.substring(i, i + m).equals(needle)) return i;
        }
        return -1;
    }
}
/*
解題思路：
1. 窗口大小為 needle 長度，從 haystack 開始逐一比對。
2. 若找到相同子字串，回傳起始索引。
3. 時間 O((n-m+1)*m)，空間 O(1) (不計 substring 可能複製)。
*/
