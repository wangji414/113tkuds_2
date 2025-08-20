package midterm;

import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long gcd = gcd(a, b);
        // 先除後乘，避免溢位(a*b 可能超過 long)
        long l = (a / gcd) * b;

        System.out.println("GCD: " + gcd);
        System.out.println("LCM: " + l);
        sc.close();
    }

    static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

/*
 * Time Complexity: O(log min(a, b))
 * 說明：
 * 單次 gcd 遞迴為 O(log min(a, b))。
 * LCM 計算 (a/g)*b 為 O(1)。
 * 因此總體為 O(log min(a, b))。
 */

