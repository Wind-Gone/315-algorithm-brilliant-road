# LeetCode 72 编辑距离

## Problem

给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

- 插入一个字符
- 删除一个字符
- 替换一个字符

## Samples

示例 1：

```
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
```


示例 2：

```
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
```

## Tag

字符串、dp

## Solution

对一个单词有三种操作，那么对两个单词`A`和`B`则有6种操作，但注意到，对单词` A `删除一个字符和对单词 `B` 插入一个字符是等价的。同理，对单词 `B` 删除一个字符和对单词 `A` 插入一个字符也是等价的；对单词 `A` 替换一个字符和对单词 `B` 替换一个字符是等价的。

因此两个单词的操作也是如下的三种：

- 在单词 `A` 中插入一个字符；
- 在单词 `B` 中插入一个字符；
- 修改单词 `A` 的一个字符。

分别分析上面的三种操作（以样例1为例，设`A="ros",B="horse"`）

- 题目要求的是horse到ros的操作数，那么想到为了在A中插入字符，如果ro和horse的距离为a，那么A和B的距离不会超过a+1；
- 同理如果ros和hors的距离为b，那么A和B的距离不会超过b+1；
- 同理如果ro和hors的距离为c，那么A和B的距离不会超过c+1

故A和B的编辑距离为`min(a+1,b+1,c+1)`

因此有，

字符串 A 为空，如从 null 转换到 ro，显然编辑距离为字符串 B 的长度，这里是 2；

字符串 B 为空，如从 horse 转换到 null ，显然编辑距离为字符串 A 的长度，这里是 5

如上所述，当我们获得`D[i][j-1]`，`D[i-1][j]` 和 `D[i-1][j-1]` 的值之后就可以计算出 `D[i][j]`。

`D[i][j-1]` 为 A 的前 i 个字符和 B 的前 j - 1 个字符编辑距离的子问题。即对于 B 的第 j 个字符，我们在 A 的末尾添加了一个相同的字符，那么 `D[i][j]` 最小可以为 `D[i][j-1] + 1`；

`D[i-1][j]` 为 A 的前 i - 1 个字符和 B 的前 j 个字符编辑距离的子问题。即对于 A 的第 i 个字符，我们在 B 的末尾添加了一个相同的字符，那么 `D[i][j]` 最小可以为 `D[i-1][j] + 1`；

`D[i-1][j-1]` 为 A 前 i - 1 个字符和 B 的前 j - 1 个字符编辑距离的子问题。即对于 B 的第 j 个字符，我们修改 A 的第 i 个字符使它们相同，那么 `D[i][j]` 最小可以为 `D[i-1][j-1] + 1`。特别地，如果 A 的第 i 个字符和 B 的第 j 个字符原本就相同，那么我们实际上不需要进行修改操作。在这种情况下，`D[i][j]` 最小可以为 `D[i-1][j-1]`。


那么我们可以写出如下的状态转移方程：

若 A 和 B 的最后一个字母相同：
$$
D[i][j]=min(D[i][j−1]+1,D[i−1][j]+1,D[i−1][j−1])=1+min(D[i][j−1],D[i−1][j],D[i−1][j−1]−1)
$$
若 A 和 B 的最后一个字母不同：
$$
D[i][j]=1+min(D[i][j−1],D[i−1][j],D[i−1][j−1])
$$
对于边界情况，一个空串和一个非空串的编辑距离为` D[i][0] = i `和 `D[0][j] = j`，`D[i][0] `相当于对 word1 执行 i 次删除操作，`D[0][j]` 相当于对 word1执行 j 次插入操作。

## Code

```java
package 编辑距离;
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s1 = "horse";
        String s2 = "ros";
        System.out.println(a.minDistance(s1, s2));
    }
}
```

## Complexity

时间复杂度：O(word1.length × word2.length)

空间复杂度：O(word1.length × word2.length)