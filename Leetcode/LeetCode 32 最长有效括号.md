# LeetCode 32 最长有效括号

## Problem

给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

## Samples

示例 1：

```
输入：s = "(()"
输出：2
解释：最长有效括号子串是 "()"
```

示例 2：

```
输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"
```

示例 3：

```
输入：s = ""
输出：0
```


提示：

- 0 <= s.length <= $3 * 10^4$
- s[i] 为 '(' 或 ')'

## Tag

动态规划

## Solution

可以知道以‘(’结尾的子串对应的dp值必定为0，我们只需要求解‘)’在dp数组中对应位置的值。

如果倒数第二个‘)’是一个有效子字符串的一部分，对于最后一个‘)’ ，如果它是一个更长子字符串的一部分，那么它一定有一个对应的‘(’，且它的位置在倒数第二个‘)’所在的有效子字符串的前面。

最后的答案即为dp数组中的最大值。

## Code

```java
package 最长有效括号;
class Solution {
    public int longestValidParentheses(String s) {
        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {   // 遇到右括号，匹配左括号
                if (s.charAt(i - 1) == '(') {   // 遇到左括号，更新匹配长度
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') { // 前面至少有两个字符，且匹配到左括号
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s = ")()())";
        System.out.println(a.longestValidParentheses(s));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(n)