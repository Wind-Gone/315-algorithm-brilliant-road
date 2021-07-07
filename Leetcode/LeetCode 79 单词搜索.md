# LeetCode 79 单词搜索

## Problem

给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

## Samples

示例 1：

<img src="https://assets.leetcode.com/uploads/2020/11/04/word2.jpg" alt="img" style="zoom:50%;" /> 

```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
```

示例 2：

<img src="https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg" alt="img" style="zoom:50%;" /> 

```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true
```

示例 3：

<img src="https://assets.leetcode.com/uploads/2020/10/15/word3.jpg" alt="img" style="zoom:50%;" /> 

```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false
```


提示：

- m == board.length
- n = board[i].length
- 1 <= m, n <= 6
- 1 <= word.length <= 15
- board 和 word 仅由大小写英文字母组成

## Tag

回溯

## Solution

遍历每一个格子，然后根据规则往相邻方向走，判断是否符合字符串。一个格子是可以往四个方向遍历的，当一个方向遍历不下去（下标越界）或者是这个当前的空格字符和字符串对应的位置不匹配，那么需要返回false。然后四个方向只要有一个成功了，那么结果就算成功，所以四次结果需要取或。

## Code

```java
package 单词搜索;
class Solution {
    public boolean exist(char[][] board, String word) {

        char arr[] = word.toCharArray();
        boolean res = false;
        for(int i = 0; i < board.length; i ++) {
            for(int j = 0; j < board[0].length; j ++) {
                if(board[i][j] == arr[0] && dfs(board,arr,i,j,0))
                    res = true;
            }
            if(res)
                return true;
        }
        return false;
    }

    public boolean dfs(char[][] board,char[] arr,int i,int j,int k) {

        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != arr[k])
            return false;

        if(k == arr.length - 1) {
            return true;
        }
        char temp = board[i][j];
        board[i][j] = ',';

        boolean res = dfs(board,arr,i-1,j,k+1) || dfs(board,arr,i,j-1,k+1) ||
                dfs(board,arr,i+1,j,k+1) || dfs(board,arr,i,j+1,k+1);

        board[i][j] = temp;
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println(a.exist(board, word));
    }
}
```

## Complexity

时间复杂度：O(mn)

空间复杂度：O(mn)