import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        Map<String, Integer> wordMap = new HashMap<>();
        for (String w : words) wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);

        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            for (; j < wordCount; j++) {
                String sub = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                if (!wordMap.containsKey(sub)) break;
                seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                if (seen.get(sub) > wordMap.get(sub)) break;
            }
            if (j == wordCount) result.add(i);
        }
        return result;
    }
}
/*
解題思路：
1. 每個 word 長度相同，總長度 totalLen = wordLen * words.length。
2. 滑動窗口每 totalLen 個字元檢查子字串是否由 words 拼接而成。
3. 使用 HashMap 記錄單詞頻率，確認每個單詞出現次數是否匹配。
4. 時間 O(n * m * k)，n = s 長度, m = words 長度, k = word 長度。
5. 空間 O(m)，存單詞頻率。
*/
