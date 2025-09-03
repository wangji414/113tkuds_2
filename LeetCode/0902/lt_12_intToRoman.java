class Solution {
    public String intToRoman(int num) {
        // 定義數值對應的羅馬數字
        int[] values =    {1000, 900, 500, 400, 100, 90,  50, 40,  10, 9,   5,  4,  1};
        String[] romans = {"M",  "CM","D", "CD","C", "XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();

        // 逐一檢查數值，能減多少就拼接多少次對應的羅馬符號
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];        // 減去對應數值
                sb.append(romans[i]);    // 加上羅馬數字
            }
        }

        return sb.toString(); // 回傳結果
    }
}
/*
解題思路：
1. 羅馬數字是由固定的數值組合而成 (M, D, C, L, X, V, I 以及特殊的 900, 400, 90, 40, 9, 4)。
2. 使用貪心法：從最大值 (1000, "M") 開始，盡可能減去並拼接對應的羅馬字。
3. 重複此過程直到 num 減為 0。
4. 時間複雜度 O(1)，因為數字最大為 3999，迴圈次數有限。
*/