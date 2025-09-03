import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); 

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

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
        return result;
    }
}
/*
解題思路：
1. 先將陣列排序，方便去重與雙指針操作。
2. 固定一個數 nums[i]，再用雙指針 (left, right) 找另外兩個數。
3. 若總和為 0，加入結果，並移動指針時避免重複。
4. 若總和 < 0，移動左指針；若總和 > 0，移動右指針。
5. 時間複雜度 O(n^2)。
*/
