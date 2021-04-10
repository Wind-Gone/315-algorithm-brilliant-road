# LeetCode 718 最长重复子数组

## Problem

给两个整数数组 `A` 和 `B` ，返回两个数组中公共的、长度最长的子数组的长度。

## Sample

```
输入：
A: [1,2,3,2,1] 
B: [3,2,1,4,7] 
输出：3 
解释：
长度最长的公共子数组是 [3, 2, 1] 。
```

## Tag

字符串、dp

## Solution

字符串的问题暴力解会超时，一般要想到动态规划。

我们在计算 `dp[i][j]`的时候：


若当前两个元素值相同，即 A[i] == B[j]，则说明当前元素可以构成公共子数组，所以还要加上它们的前一个元素构成的最长公共子数组的长度(在原来的基础上加 1)，此时状态转移方程：`dp[i][j] = dp[i - 1][j - 1] + 1`。


若当前两个元素值不同，即 A[i] != B[j]，则说明当前元素无法构成公共子数组(就是：当前元素不能成为公共子数组里的一员)。因为公共子数组必须是连续的，而此时的元素值不同，相当于直接断开了，此时状态转移方程：`dp[i][j] = 0`。

## Code

```java
package 最长重复子数组;
class Solution {
    public int findLength(int[] A, int[] B) {
        int n = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    n = Math.max(n, dp[i][j]);
                }
            }
        }
        return n;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] A = {1,2,3,2,1};
        int[] B = {3,2,1,4,7};
        System.out.println(a.findLength(A, B));
    }
}
```

## Complexity

时间复杂度：O(A.length×B.length)

空间复杂度：O(A.length×B.length)