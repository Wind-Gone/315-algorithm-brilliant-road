# 剑指Offer 47 礼物的最大价值

## Problem

在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

## Samples

示例 1:

```
输入: 
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
```


提示：

- 0 < grid.length <= 200
- 0 < grid[0].length <= 200

## Tag

动态规划

## Solution

考虑使用动态规划，较为基础。建立转移方程`result[i][j]=max(result[i-1][j],result[i][j-1])+grid[i][j]`，result数组保存当前位置可得的最大价值。

## Code

```java
package 礼物的最大价值;
class Solution {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int dp[][] = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(a.maxValue(grid));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(n)