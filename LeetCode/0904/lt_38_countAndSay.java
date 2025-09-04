class Solution {
    public String countAndSay(int n) {
        if(n==1) return "1";
        String prev = countAndSay(n-1);
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for(int i=1;i<prev.length();i++){
            if(prev.charAt(i)==prev.charAt(i-1)) count++;
            else{
                sb.append(count).append(prev.charAt(i-1));
                count=1;
            }
        }
        sb.append(count).append(prev.charAt(prev.length()-1));
        return sb.toString();
    }
}
/*
解題思路：
1. 遞迴生成前一項。
2. 遍歷前一項計數相同連續數字，生成本項。
3. 時間 O(2^n)，空間 O(n) 遞迴棧。
*/
