package finalexam;

import java.util.Arrays;
import java.util.Scanner;

public class LC15_3Sum_THSRStops {

    public static void threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            if (nums[i] > 0) break; // 提前結束

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    System.out.println(nums[i] + " " + nums[left] + " " + nums[right]);
                    // 跳過重複元素
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        threeSum(nums);
        sc.close();
    }
}
