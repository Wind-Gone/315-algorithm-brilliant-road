# LeetCode N皇后

## Problem 51 N皇后

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

### Samples

示例 1：

```
输入：n = 4
输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
解释：如上图所示，4 皇后问题存在两个不同的解法。
```


示例 2：

```
输入：n = 1
输出：[["Q"]]
```


提示：

- 1 <= n <= 9
- 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。

### Tag

递归、回溯

### Solution

一行一行查找皇后的放置位置, 回溯的过程中标记皇后所在两条对角线以及列。

### Code

```java
package N皇后;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // 初始化一个棋盘
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }
        // 从第0行开始，递归搜索每一行可以放皇后的位置（0 --> n-1）
        NQueens(n, chess, 0, result);
        return result;
    }

    public void NQueens(int n, char[][] chess, int row, List<List<String>> result) {
        char[][] tempChess = new char[n][n];
        // 初始化临时棋盘
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tempChess[i][j] = chess[i][j];
            }
        }
        if (row == n) { // 所有行都搜索完了（0 --> n-1）
            List<String> temp = new ArrayList<>();
            for (char[] line : tempChess) {
                temp.add(new String(line));
            }
            result.add(temp);
            return;
        }
        // 遍历row行的每一个位置判断是否可以放置皇后
        for (int col = 0; col < n; col++) {
            if (isSafe(n, tempChess, row, col)) {
                for (int j = 0; j < n; j++) {
                    tempChess[row][j] = '.'; // 那么这一行的其余位置全部放'.'
                }
                tempChess[row][col] = 'Q'; // 而这个位置放一个皇后
                NQueens(n, tempChess, row + 1, result); // 然后递归地搜索下一行
            }
        }
    }

    private boolean isSafe(int n, char[][] chess, int row, int col) {
        // 判断列是否有危险
        for (int i = 0; i < n; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        // 判断左上对角线是否有危险
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        // 判断左下对角线是否有危险
        for (int i = row, j = col; i < n && j < n; i++, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        // 判断右上对角线是否有危险
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        // 判断右下对角线是否有危险
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        // 都没有危险则安全
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        System.out.println(a.solveNQueens(4));
    }
}
```

### Complexity

时间复杂度：O(n!)

空间复杂度：O(n)

## Problem 52 N皇后Ⅱ

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。

### Samples

示例 1：

```
输入：n = 4
输出：2
解释：如上图所示，4 皇后问题存在两个不同的解法。
```


示例 2：

```
输入：n = 1
输出：1
```


提示：

- 1 <= n <= 9
- 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。

### Tag

回溯

### Solution

方法和前一题类似，考虑每一行棋子所在的列位置，使之成为排列组合，这就保证了棋子既不在一行上也不在一列上，接着，每轮DFS都遍历上一轮插入的元素是否和之前插入的元素在对角线上，如果是则剪枝，以此避免出现在对角线上。

### Code

```java
package N皇后Ⅱ;

import java.util.Arrays;

class Solution {
    int res = 0;
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        backtrack(n, board, 0);
        return res;
    }

    public boolean isSafe(int n, int row, int col, char[][] board) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
    public void backtrack(int n, char[][] board, int row) {
        if (row == n) {
            res++;
            return ;
        }
        for (int col = 0; col < n; col++) {
            if (!isSafe(n, row, col, board)) {
                continue; // 剪枝
            }
            board[row][col] = 'Q';
            backtrack(n, board, row + 1);
            board[row][col] = '.';
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        System.out.println(a.totalNQueens(4));
    }
}
```

### Complexity

时间复杂度：O(n!)

空间复杂度：O(n)