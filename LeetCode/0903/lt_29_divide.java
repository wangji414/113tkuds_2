class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
/*
解題思路：
1. 使用雙指標，slow 指向新陣列尾，fast 遍歷原陣列。
2. 當 nums[fast] != val 時，把它寫入 nums[slow]。
3. 返回 slow 作為新長度。
4. 時間 O(n)，空間 O(1)。
*/