# LeetCode 44 通配符匹配

## Problem

给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

```
'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
```

两个字符串完全匹配才算匹配成功。

说明:

- s 可能为空，且只包含从 a-z 的小写字母。
- p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。

## Samples

示例 1:

```
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```

示例 2:

```
输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
```

示例 3:

```
输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
```

示例 4:

```
输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
```

示例 5:

```
输入:
s = "acdcb"
p = "a*c?b"
输出: false
```

## Tag

动态规划

## Solution

采用动态规划，如果p的当前字符为'?'，则不用考虑当前字符是否相等，`dp[i][j] = dp[i-1][j-1]`；如果p的当前字符为'*'，则可能*继续匹配上一个字符，或者不匹配，`dp[i][j] = dp[i-1][j] || dp[i][j-1]`；如果p既不是'?'也不是'*'，则判断当前字符是否相等， `dp[i][j] = s.charAt(i) == p.charAt(j) && dp[i-1][j-1]`；边界值`dp[0][0] = true`，`dp[i][0] = false` ；`dp[0][i]` 需要看p是不是‘\*’，是'\*'则为true，遇到不是'\*'的后面全返回false。

## Code

```java
package 通配符匹配;
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // dp[i][j]表示从s开头起长度为i的字串和从p开头起长度为j的字串是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;    // 空字符和空字符匹配
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        for (int j = 1; j <= n; j++) {
            if(pp[j - 1] == '*') {  // '*'的索引,暂时不用,j移动一位
                dp[0][j] = true;
            }
            else break;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pp[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];    // 当前'*'匹配一个空字符
                }
                else if (pp[j - 1] == '?' || ss[i - 1] == pp[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];    // 前面ss[0,i-2]和pp[0,j-2]能匹配且当前字符能匹配
                }
                else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[m][n];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s = "adceb", p = "*a*b";
        System.out.println(a.isMatch(s, p));
    }
}
```

## Complexity

时间复杂度：O(mn)

空间复杂度：O(mn)