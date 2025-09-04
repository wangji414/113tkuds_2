import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] cand, int target, int start, List<Integer> path, List<List<Integer>> res){
        if(target==0){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=start;i<cand.length;i++){
            if(cand[i]<=target){
                path.add(cand[i]);
                backtrack(cand, target-cand[i], i, path, res);
                path.remove(path.size()-1);
            }
        }
    }
}
/*
解題思路：
1. 回溯法，從 start 開始嘗試每個候選數。
2. 可重複使用同一個元素 (i 不加 1)。
3. 當 target==0 時，將當前組合加入結果。
*/
