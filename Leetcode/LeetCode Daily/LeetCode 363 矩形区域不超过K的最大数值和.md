# LeetCode 363 矩形区域不超过K的最大数值和

## Problem

给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。

题目数据保证总会存在一个数值和不超过 k 的矩形区域。

## Samples

示例 1：

<img src="https://assets.leetcode.com/uploads/2021/03/18/sum-grid.jpg" alt="img" style="zoom:50%;" /> 

```
输入：matrix = [[1,0,1],[0,-2,3]], k = 2
输出：2
解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
```


示例 2：

```
输入：matrix = [[2,2,-1]], k = 3
输出：3
```

## Tag

枚举、前缀和

## Solution

枚举矩形的上下边界，计算边界内每列的元素和，将其转化为数组的最大区间和。类似于[LeetCode560](https://leetcode-cn.com/problems/subarray-sum-equals-k/)

定义长度为n的数组a的前缀和
$$
S_i = \begin{cases} 0&i=0\\ a_0+a_1+\cdots+a_{i-1}&1\le i\le n \end{cases}
$$

则区间 `[l,r)`的区间和 $a_l+a_{l+1}+\cdots+a_{r-1}$ 可以表示为$S_r-S_l$。

枚举r，上述问题的约束$S_r-S_l\le k$可以转换为$S_l\ge S_r-k$。 要使$S_r-S_l$尽可能大，则需要寻找最小的满足$S_l\ge S_r-k$的$S_l$。我们可以在枚举r的同时维护一个存储$S_i\ (i<r)$的有序集合，则可以在$O(\log n)$的时间内二分找到符合要求的$S_l$。

## Code

```java
package 矩形区域不超过K的最大数值和;
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] + matrix[i - 1][j - 1] - s[i - 1][j - 1];
            }
        }
        long res = (long)-1e18;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int a = i + 1; a <= m; a++){
                    for(int b = j + 1; b <= n; b++){
                        long sum = s[a][b] + s[i][j] - s[a][j] - s[i][b];
                        if (sum <= k)
                            res = Math.max(res, sum);
                    }
                }
            }
        }
        return (int)res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[][] matrix = {{1,0,1},{0,-2,3}};
        System.out.println(a.maxSumSubmatrix(matrix, 2));
    }
}
```

## Code_Ref

```java
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) { // 枚举上边界
            int[] sum = new int[n];
            for (int j = i; j < m; ++j) { // 枚举下边界
                for (int c = 0; c < n; ++c) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                TreeSet<Integer> sumSet = new TreeSet<Integer>();
                sumSet.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        ans = Math.max(ans, s - ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return ans;
    }
}
```

## Complexity

时间复杂度：$O(m^2n\log n)$

空间复杂度：$O(n)$