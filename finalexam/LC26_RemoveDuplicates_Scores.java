package finalexam;

import java.util.Scanner;

public class LC26_RemoveDuplicates_Scores {

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int write = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[write++] = nums[i];
            }
        }
        return write;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        int newLen = removeDuplicates(nums);
        System.out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i] + (i == newLen - 1 ? "\n" : " "));
        }
        sc.close();
    }
}
