package finalexam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LC17_PhoneCombos_CSShift {

    private static final String[] KEYPAD = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    };

    public static void letterCombinations(String digits) {
        if (digits.length() == 0) return;

        List<String> result = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), result);

        for (String s : result) {
            System.out.println(s);
        }
    }

    private static void backtrack(String digits, int index, StringBuilder path, List<String> result) {
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        String letters = KEYPAD[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, index + 1, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine();
        letterCombinations(digits);
        sc.close();
    }
}
