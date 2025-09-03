class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            maxArea = Math.max(maxArea, h * w);

            // 移動高度較小的一邊
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
/*
解題思路：
1. 使用雙指針：左指針從頭，右指針從尾。
2. 每次計算面積後，移動較矮的柱子，因為寬度一定縮小，只有提高高度才可能增加面積。
3. 時間複雜度 O(n)
*/
