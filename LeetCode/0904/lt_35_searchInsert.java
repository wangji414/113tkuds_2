class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < target) left = mid +1;
            else right = mid;
        }
        return left;
    }
}
/*
解題思路：
1. 二分搜尋找第一個大於等於 target 的位置。
2. 時間 O(log n)，空間 O(1)。
*/
