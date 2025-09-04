import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;

        Arrays.sort(nums); // 先排序

        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            // 避免重複 (針對 i)
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                // 避免重複 (針對 j)
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 跳過重複元素
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // 和太小
                    } else {
                        right--; // 和太大
                    }
                }
            }
        }
        return result;
    }
}
/*
解題思路：
1. 先排序，方便去重與雙指針操作。
2. 用兩層迴圈固定前兩個數 nums[i], nums[j]。
3. 在剩下的區間內，用雙指針 (left, right) 找出符合條件的兩數。
4. 遇到重複數字要跳過，避免結果重複。
5. 為避免整數溢位，計算總和時用 long。
6. 時間複雜度 O(n^3)，空間複雜度 O(1) (不計輸出)。
*/
