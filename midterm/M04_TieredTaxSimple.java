package midterm;

import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long totalTax = 0;
        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = computeTax(income);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        long avg = totalTax / n; // 取整數
        System.out.println("Average: " + avg);
        sc.close();
    }

    static long computeTax(long income) {
        long tax = 0;
        if (income <= 120000) {
            tax = income * 5 / 100;
        } else if (income <= 500000) {
            tax = 120000 * 5 / 100 + (income - 120000) * 12 / 100;
        } else if (income <= 1000000) {
            tax = 120000 * 5 / 100
                + (500000 - 120000) * 12 / 100
                + (income - 500000) * 20 / 100;
        } else {
            tax = 120000 * 5 / 100
                + (500000 - 120000) * 12 / 100
                + (1000000 - 500000) * 20 / 100
                + (income - 1000000) * 30 / 100; 
        }
        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 逐筆輸入 n 筆收入，各筆計算稅額 O(1)。
 * 總共 n 次 → O(n)。
 * 輸出與平均計算為 O(1)，不影響整體。
 */
