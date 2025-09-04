class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            if(nums[left] <= nums[mid]){
                if(target >= nums[left] && target < nums[mid]) right = mid -1;
                else left = mid +1;
            } else{
                if(target > nums[mid] && target <= nums[right]) left = mid +1;
                else right = mid -1;
            }
        }
        return -1;
    }
}
/*
解題思路：
1. 旋轉排序陣列的二分搜尋。
2. 判斷 mid 左半段或右半段是升序，根據 target 調整左右邊界。
3. 時間 O(log n)，空間 O(1)。
*/
