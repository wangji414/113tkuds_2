class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length(), sign = 1;
        long result = 0;

        // skip leading spaces
        while (i < n && s.charAt(i) == ' ') i++;

        // check sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // convert digits
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            if (sign * result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign * result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            i++;
        }

        return (int)(sign * result);
    }
}

public class lt_08_myAtoi {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.myAtoi("42"));            // 42
        System.out.println(sol.myAtoi("   -42"));        // -42
        System.out.println(sol.myAtoi("4193 with words")); // 4193
        System.out.println(sol.myAtoi("words and 987"));   // 0
        System.out.println(sol.myAtoi("-91283472332"));    // -2147483648
    }
}
