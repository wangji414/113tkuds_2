class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[curRow].append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) result.append(row);
        return result.toString();
    }
}

public class lt_06_convert {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(sol.convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
        System.out.println(sol.convert("A", 1));              // A
    }
}
