# LeetCode 131 分割回文串

## Problem

给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

回文串 是正着读和反着读都一样的字符串。

## Samples

示例 1：

```
输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]
```

示例 2：

```
输入：s = "a"
输出：[["a"]]
```


提示：

- 1 <= s.length <= 16
- s 仅由小写英文字母组成

## Tag

回溯、动态规划

## Solution

用指针 start 试着去切，切出一个回文串，基于新的 start，继续往下切，直到 start 越界。每次基于当前的 start，可以选择不同的 i，切出 start 到 i 的子串，我们枚举出这些选项 i：

- 切出的子串满足回文，将它加入部分解 temp 数组，并继续往下切（递归）；
- 切出的子串不是回文，跳过该选择，不落入递归，继续下一轮迭代

## Code

```java
package 分割回文串;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        char[] charArray = s.toCharArray();
        // 预处理
        // 状态：dp[i][j] 表示 s[i][j] 是否是回文
        boolean[][] dp = new boolean[len][len];
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (charArray[left] == charArray[right] && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }

        Deque<String> stack = new ArrayDeque<>();
        dfs(s, 0, len, dp, stack, res);
        return res;
    }

    public void dfs(String s, int index, int len, boolean[][] dp, Deque<String> path, List<List<String>> res) {
        if (index == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < len; i++) {
            if (dp[index][i]) {
                path.addLast(s.substring(index, i + 1));
                dfs(s, i + 1, len, dp, path, res);
                path.removeLast();
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s = "aab";
        System.out.println(a.partition(s));
    }
}
```

## Complexity

时间复杂度：O(2^n)

空间复杂度：O(n×2^n)