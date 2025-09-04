class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) swap(nums, start++, end--);
    }
}
/*
解題思路：
1. 從右向左找到第一個下降點 i (nums[i] < nums[i+1])。
2. 從右向左找到第一個大於 nums[i] 的 j，交換 i 與 j。
3. 反轉 i+1 到結尾的子陣列，得到下個字典序排列。
4. 時間 O(n)，空間 O(1)。
*/
