# LeetCode 97 交错字符串

## Problem

给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。

两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
提示：a + b 意味着字符串 a 和 b 连接。

## Samples

示例 1：

<img src="https://assets.leetcode.com/uploads/2020/09/02/interleave.jpg" alt="img" style="zoom:67%;" /> 

```
输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
输出：true
```


示例 2：

```
输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
输出：false
```

示例 3：

```
输入：s1 = "", s2 = "", s3 = ""
输出：true
```


提示：

- 0 <= s1.length, s2.length <= 100
- 0 <= s3.length <= 200
- s1、s2、和 s3 都由小写英文字母组成

## Tag

动态规划

## Solution

target 的每个字符都是从 s1（向下）或者 s2（向右）拿到的，所以只要判断是否存在这条 target 路径即可；

于是可定义 boolean[][] dp ，`dp[i][j]` 代表 s1 前 i 个字符与 s2 前 j 个字符拼接成 s3 的 i+j 字符，也就是存在目标路径能够到达 i ,j ；
状态方程：

边界 1：`dp[0][0] = true`;
边界 2：if i=0 : `dp[0]dp[j] = s2[0-j) equals s3[0,j)` 遇到 false 后面可以直接省略
边界 3：if j=0 : `dp[i]dp[0] = s1[0-i) equals s3[0,i)` 遇到 false 后面可以直接省略

其他情况，到达（i，j）可能由（i-1,j）点向下一步，选择 s1[i-1] 到达；也可能由 （i,j-1） 点向右一步，选择 s2[j-1] 到达；
`dp[i,j] = (dp[i-1][j] &&s3[i+j-1] == s1[i-1]) || (dp[i][j-1] && s3[i+j-1] == s2[j-1])`。

## Code

```java
package 交错字符串;
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (s3.length() != m + n) return false;
        // 动态规划，dp[i,j]表示s1前i字符能与s2前j字符组成s3前i+j个字符；
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= m && s1.charAt(i-1) == s3.charAt(i-1); i++) {
            dp[i][0] = true; // 不相符直接终止
        }
        for (int j = 1; j <= n && s2.charAt(j-1) == s3.charAt(j-1); j++) {
            dp[0][j] = true; // 不相符直接终止
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1))
                        || (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
            }
        }
        return dp[m][n];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        System.out.println(a.isInterleave(s1, s2, s3));
    }
}
```

## Complexity

时间复杂度：O(mn)

空间复杂度：O(n)