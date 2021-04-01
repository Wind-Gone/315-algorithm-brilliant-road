# LeetCode 200 岛屿数量

## Problem

给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

## Samples

示例 1：

```
输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1
```


示例 2：

```
输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3
```

## Tag

DFS

## Solution

参考了题解中一个大神的解法，他归纳了DFS在图中应用模板，见下面的链接。

https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/

本题的思路是，将给定的二维数组看成无向图，扫描到‘1’将进行深度优先搜索，同时应该在搜索过的位置标记防止再次搜索。DFS的次数即为岛屿数量。

## Code

```java
package 岛屿数量;

public class Main {
    public static int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {
                if (grid[i][j] == '1') {
                    dfsGrid(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    static void dfsGrid(char[][] grid, int row, int col) {	//无向图的深度优先搜索
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0)
            return;

        if (grid[row][col] != '1')
            return;

        grid[row][col] = '2';
        dfsGrid(grid, row - 1, col);
        dfsGrid(grid, row + 1, col);
        dfsGrid(grid, row, col - 1);
        dfsGrid(grid, row, col + 1);
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1','1','0','0','0'}, {'1','1','0','0','0'},
                {'0','0','1','0','0'}, {'0','0','0','1','1'}};
        System.out.println(numIslands(grid)); //3
    }
}
```

## Complexity

时间复杂度：O(mn)，m，n表示二维网格的行列

空间复杂度：最坏情况O(mn)，表示整片陆地