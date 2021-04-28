# LeetCode 498 对角线遍历

## Problem

给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。

## Samples

```
输入:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

输出: 

[1,2,4,7,5,3,6,8,9]
```

解释:

<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/diagonal_traverse.png" alt="img" style="zoom:50%;" /> 

**说明:**

给定矩阵中的元素总数不会超过 100000 。

## Tag

模拟方法

## Solution

本题是遍历题，可以采用模拟行走路径的方法。要确定矩阵的方向，即向上和向下两个方向；要确定每次的转折点。

对于方向，需要一个if语句判断奇偶性；对于转折点，如果是向上行走时的下一条对角线首部，位于向下行走对角线尾端时，找出下一个向上行走对角线头部有两种情况。而如果向下行走时的下一条对角线尾部，位于向上行走对角线尾部时，找出下一个向下行走对角线首部有两种情况。

## Code

```java
package 对角线遍历;

import java.util.Arrays;

class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0)
            return null;
        int m = mat.length;
        int n = mat[0].length;
        int cnt = m * n;
        int direction = 0; // 指示矩阵的变化方向
        int[] arr = new int[cnt];
        int x = 0, y = 0; //定义数组的坐标
        for (int i = 0; i < cnt; i++) {
            arr[i] = mat[x][y];
            if (x == m - 1 && y == n - 1) {
                return arr;
            }
            if (direction % 2 == 0) { // 方向向上
                if (x == 0 && y < n - 1) {
                    y++;
                    direction++;
                }
                else if (x >= 0 && y == n - 1) {
                    x++;
                    direction++;
                }
                else {
                    x--;
                    y++;
                }
            }
            else { //方向向下
                if (x == m - 1 && y >= 0) {
                    y++;
                    direction++;
                }
                else if (x < m - 1 && y == 0) {
                    x++;
                    direction++;
                }
                else {
                    x++;
                    y--;
                }
            }
        }
        return arr;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(Arrays.toString(a.findDiagonalOrder(mat)));
    }
}
```

## Complexity

时间复杂度：O(mn)，m*n是矩阵的大小

空间复杂度：O(1)