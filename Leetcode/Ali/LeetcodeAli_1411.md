---
阿里巴巴题库 1411. 给 N x 3 网格图涂色的方案数
---

## Problem

> 你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿 三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。
>
> 给你网格图的行数 n 。
>
> 请你返回给 grid 涂色的方案数。由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
>



> 示例 1：
>
> 输入：n = 1
> 输出：12
> 解释：总共有 12 种可行的方法：
>
> ![img](LeetcodeAli_1411.assets/e1.png)
>
> 示例 2：
>
> 输入：n = 2
> 输出：54
> 示例 3：
>
> 输入：n = 3
> 输出：246
> 示例 4：
>
> 输入：n = 7
> 输出：106494
> 示例 5：
>
> 输入：n = 5000
> 输出：30228214
>
>
> 提示：
>
> n == grid.length
> grid[i].length == 3
> 1 <= n <= 5000

## Tag

- 动归
- 规律题



## Solution

> 找规律题
>
> 我们把满足要求的$ \textit{type}$ 都写出来，一共有 12 种：
>
> 010, 012, 020, 021, 101, 102, 120, 121, 201, 202, 210, 212
> 我们可以把它们分成两类：
>
> ABC 类：三个颜色互不相同，一共有 6 种：012, 021, 102, 120, 201, 210；
>
> ABA 类：左右两侧的颜色相同，也有 6 种：010, 020, 101, 121, 202, 212。
>
> 这样我们就可以把 12 种 $\textit{type}$ 浓缩成了 2 种，尝试写出这两类之间的递推式。我们用 f\[i][0]表示 ABC 类f\[i][1] 表示 ABA 类。在计算时，我们可以将任意一种满足要求的涂色方法带入第 i - 1 行，并检查第 i 行的方案数，这是因为同一类的涂色方法都是等价的：
>
> - 第 i - 1 行是 ABC 类，第 i 行是 ABC 类：以 012 为例，那么第 i 行只能是120 或 201，方案数为 22；
> - 第 i - 1 行是 ABC 类，第 i 行是 ABA 类：以 012 为例，那么第 i 行只能是 101 或 121，方案数为 22；
> - 第 i - 1 行是 ABA 类，第 i 行是 ABC 类：以 010 为例，那么第 i 行只能是 102 或 201，方案数为 2；
> - 第 i - 1 行是 ABA 类，第 i 行是 ABA 类：以 010 为例，那么第 i 行只能是 101，121 或 202，方案数为 3。
>
> 因此我们就可以写出递推式：
>
> $\begin{cases} f[i][0] = 2 * f[i - 1][0] + 2 * f[i - 1][1] \\ f[i][1] = 2 * f[i - 1][0] + 3 * f[i - 1][1] \end{cases}$

### Code—1

```c++
class Solution {
public:
    static constexpr int mod = 1000000007;       
    int numOfWays(int n) {
        int dp[n+1][2];
        dp[1][0] = dp[1][1] = 6;
        for(int i = 2; i <= n ; i++){
        	dp[i][0] = (2LL * dp[i-1][0] + 2LL * dp[i-1][1]) % mod;
			dp[i][1] = (2LL * dp[i-1][0] + 3LL * dp[i-1][1]) % mod; 
		}
		return (dp[n][0] + dp[n][1])% mod;
    }
};
```

### Complexity Analysis

- 时间复杂度：$O(n)$
- 空间复杂度：$O(n^2)$



