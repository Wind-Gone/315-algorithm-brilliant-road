# LeetCode 5 最长回文子串

## Problem

给你一个字符串 `s`，找到 `s` 中最长的回文子串。

## Samples

示例 1：

```
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
```


示例 2：

```
输入：s = "cbbd"
输出："bb"
```


示例 3：

```
输入：s = "a"
输出："a"
```


示例 4：

```
输入：s = "ac"
输出："a"
```

## Tag

dp，String

## Solution

第一个想法是暴力求解，枚举出长度大于1，并且超过当前得到的最长回文子串的长度的子串。然后判断子串是否是回文串，容易写出比较回文串的函数，最后返回substring方法生成子串。由于两个for循环加一次遍历，使时间复杂度为O(n³)，因此这不是最佳的求解方法。因此本题可以采用动态规划的求解思路。需要构建一个状态表示子串是否为回文子串，设子串的下标范围从i到j，注意边界条件j-i<3。首先，dp[i][i]是恒成立的；考虑字符串dp是一个回文串，如果其首尾字符相同，当且仅当s[i…j]是回文子串；进一步地，当且仅当s[i+1, j-1]且第i个字符和第j个字符相同时上述条件成立。考虑特殊情况，当字符串长度为1，它一定是回文的，；当字符串长度为2，只要字母相同就一定是回文的。

## Code

```java
package 最长回文子串;

public class Main {
    public static String longestPalindrome(String s) {
        int N = s.length();
        boolean[][] dp = new boolean[N][N];
        String str = "";
        for (int n = 0; n < N; n++) {
            for (int i = 0; i < N - n; i++) {
                int j = i + n;
                if (n == 0)
                    dp[i][j] = true;
                else if (n == 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j); //只要s[i]=s[j]
                else
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) && (dp[i][j] = dp[i+1][j-1]); //只有s[i+1:j−1]是回文串，并且s的第i和j个字母相同时，s[i:j]才会是回文串
                if (dp[i][j] && n + 1 > str.length()) //不加上n+1>str.length()可能会超时
                    str = s.substring(i, i + n + 1); //提取所需子串
            }
        }
        return str;
    }
    public static void main(String[] args) {
        String s = new String("babad");
        System.out.println(longestPalindrome(s));
    }
}
```

## Complexity

时间复杂度O(n²)

空间复杂度O(n²)
