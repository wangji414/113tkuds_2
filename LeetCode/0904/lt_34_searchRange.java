class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{find(nums, target, true), find(nums, target, false)};
    }

    private int find(int[] nums, int target, boolean first){
        int idx = -1, left = 0, right = nums.length -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                idx = mid;
                if(first) right = mid -1;
                else left = mid +1;
            } else if(nums[mid] < target) left = mid +1;
            else right = mid -1;
        }
        return idx;
    }
}
/*
解題思路：
1. 二分搜尋分別找第一個與最後一個 target 出現的位置。
2. 時間 O(log n)，空間 O(1)。
*/
