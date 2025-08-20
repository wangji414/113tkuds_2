package midterm;

import java.util.*;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if (isPalindrome(s)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        sc.close();
    }

    static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char cLeft = s.charAt(left);
            char cRight = s.charAt(right);

            // 忽略非英文字母
            if (!Character.isLetter(cLeft)) {
                left++;
                continue;
            }
            if (!Character.isLetter(cRight)) {
                right--;
                continue;
            }

            // 比較忽略大小寫
            if (Character.toLowerCase(cLeft) != Character.toLowerCase(cRight)) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }
}
