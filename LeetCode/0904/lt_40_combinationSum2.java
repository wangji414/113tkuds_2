import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] cand, int target, int start, List<Integer> path, List<List<Integer>> res){
        if(target==0){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=start;i<cand.length;i++){
            if(i>start && cand[i]==cand[i-1]) continue; // 去重
            if(cand[i]>target) break;
            path.add(cand[i]);
            backtrack(cand, target-cand[i], i+1, path, res); // 每個元素只能用一次
            path.remove(path.size()-1);
        }
    }
}
/*
解題思路：
1. 先排序 candidates，回溯生成組合。
2. 同一層重複元素跳過，避免重複組合。
3. 每個元素只能使用一次 (i+1)。
4. 時間 O(2^n)，空間 O(n) 遞迴棧。
*/
