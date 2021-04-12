---
[SCOI2009] windy 数
---

## Problem

> windy 定义了一种 windy 数。
>
> 不含前导零且相邻两个数字之差至少为 2 的正整数被称为 windy 数。windy 想知道，在a 和 b 之间，包括a 和 b ，总共有多少个 windy 数？
>
> ### 输入描述
>
> 输入只有一行两个整数，分别表示a 和 b，1 $\leq$ a $\leq $b $\leq 2$ $\times 10^9$。
>
> ### 输出描述
>
> 输出一行一个整数表示答案。
>
> ### 输入输出样例
>
> #### 示例 1
>
> > 输入
>
> ```txt
> 1 10
> ```
>
> > 输出
>
> ```txt
> 9
> ```
>
> ### 运行限制
>
> - 最大运行时间：1s
> - 最大运行内存: 128M

## Tag

> - 动归
> - 数论

## Solution

> - 关于数位DP的讲解提供如下网站进行介绍，都还蛮清晰的
>
>   https://www.cnblogs.com/zhanglichen/p/14590342.html
>
>   https://blog.csdn.net/wust_zzwh/article/details/52100392

### Code

```c++

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int a[100];
ll dp[25][25];

ll dfs(int pos, int pre, bool lead, bool limit)
{
    if (pos == -1)
        return 1;
    if (!limit && !lead && dp[pos][pre] != -1)
        return dp[pos][pre];
    ll ans = 0;
    ll ceiling = limit ? a[pos] : 9;
    for (ll i = 0; i <= ceiling; i++)
    {
        if (abs(i - pre) < 2)
            continue;
        if (lead && i == 0)
            ans += dfs(pos - 1, -5, true, limit && i == ceiling);
        else
            ans += dfs(pos - 1, i, false, limit && i == ceiling);
    }
    if (!limit && !lead)
        dp[pos][pre] = ans;
    return ans;
}

ll solve(ll x)
{
    int pos = 0;
    while (x)
    {
        a[pos++] = x % 10;
        x /= 10;
    }
    memset(dp, -1, sizeof(dp));
    return dfs(pos - 1, -5, true, true);
}

int main(int argc, char const *argv[])
{
    ll a, b;
    cin >> a >> b;
    cout << solve(b) - solve(a - 1);
    return 0;
}

```

### Complexity Analysis

- 时间复杂度：O(NlogN)
- 空间复杂度：O(N²)
