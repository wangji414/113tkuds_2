import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                if (sum < target) {
                    left++; 
                } else if (sum > target) {
                    right--; 
                } else {
                    return target; 
                }
            }
        }

        return closest; 
    }
}
/*
解題思路：
1. 將陣列排序，方便使用雙指針。
2. 固定一個數 nums[i]，然後用雙指針在剩下區間內找另外兩個數。
3. 每次計算總和，若更接近 target，更新 closest。
4. 根據 sum 與 target 的比較，決定移動左右指針。
5. 時間複雜度 O(n^2)，空間複雜度 O(1)。
*/
