class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 0; // 慢指標，追蹤新陣列尾
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;            // 慢指標前進
                nums[slow] = nums[fast]; // 更新新陣列元素
            }
        }
        return slow + 1; // 新陣列長度
    }
}
/*
解題思路：
1. 使用雙指標：
   - slow 指向最後一個唯一元素的位置。
   - fast 遍歷整個陣列。
2. 當 nums[fast] 與 nums[slow] 不同，表示找到新元素，更新 slow+1 的位置。
3. 最終 slow+1 即為新陣列長度，原陣列前 slow+1 個元素即為去重後結果。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
