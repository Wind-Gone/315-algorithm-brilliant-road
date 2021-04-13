---
[蓝桥杯] 七段码
---

## Problem

> ### 题目描述
>
> **本题为填空题，只需要算出结果后，在代码中使用输出语句将所填结果输出即可。**
>
> 小蓝要用七段码数码管来表示一种特殊的文字。
>
> ![图片描述](LanQiao595.assets/uid1580206-20210312-1615527941739)
>
> 上图给出了七段码数码管的一个图示，数码管中一共有 77 段可以发光的二 极管，分别标记为 a, b, c, d, e, f, g*a*,*b*,*c*,*d*,*e*,*f*,*g*。
>
> 小蓝要选择一部分二极管（至少要有一个）发光来表达字符。在设计字符 的表达时，要求所有发光的二极管是连成一片的。
>
> 例如：b*b* 发光，其他二极管不发光可以用来表达一种字符。
>
> 例如 c*c* 发光，其他二极管不发光可以用来表达一种字符。这种方案与上 一行的方案可以用来表示不同的字符，尽管看上去比较相似。
>
> 例如：a, b, c, d, e*a*,*b*,*c*,*d*,*e* 发光，f, g*f*,*g* 不发光可以用来表达一种字符。
>
> 例如：b, f*b*,*f* 发光，其他二极管不发光则不能用来表达一种字符，因为发光 的二极管没有连成一片。
>
> 请问，小蓝可以用七段码数码管表达多少种不同的字符？
>
> ### 运行限制
>
> - 最大运行时间：1s
> - 最大运行内存: 128M

## Tag

> - dfs
> - 并查集

## Solution

> - 这道题一开始的想法其实是手动去数，比如N=1，2，5，6，7这些情况都很容易直接数出来，不过更为科学的方法还是需要通过并查集+dfs来做
>
> - ```c++
>   # 并查集板子
>   
>   int find(int x){
>       if (fa[x] != x)
>           fa[x] = find(fa[x]);
>      	return fa[x];
>   }
>   
>   void unionSet(int x) {
>       x = find(x);
>       y = find(y);
>        if(x != y)     //不在同一根上，进行合并操作      
>           fa[y]=x;
>       return 0; 
>   }
>   ```
>
>   并查集是一种树形的数据结构，顾名思义，它用于处理一些不交集的 合并 及 查询 问题。 它支持两种操作：
>
>   - 查找（Find）：确定某个元素处于哪个子集；
>
>   - 合并（Union）：将两个子集合并成一个集合。
>
>     > 
>     >
>     > - 用 1 ~ 7 来代表 a ~ g；
>     > - 若某两个二极管相邻，那么就在它们之间连一条边；
>     > - 先用 dfs 枚举出二极管的所有亮灭情况；
>     > - 再用 并查集 判断是否只有一个连通块；
>     > - 首先我们用二维数组存相邻的两段，接着利用指数型枚举，求出所有的组合，然后利用并查集判断组合中的段是否连通并且只存在一个连通块即可

### Code

```c++
#include <bits/stdc++.h>
using namespace std;
const int N = 10;
int matrix[N][N], fa[N];
bool state[N] = {false};
int ans = 0;

void initSet()
{
    for (size_t i = 1; i <= 7; i++)
    {
        fa[i] = i;
    }
}

int find(int x)
{
    if (x != fa[x])
        fa[x] = find(fa[x]);
    return fa[x];
}

void mergeSet(int x, int y)
{
    int x1 = find(x);
    int y1 = find(y);
    if (x1 != y1)
        fa[x1] = y1;
    return;
}

void dfs(int digit)
{
    if (digit > 7)	// 深搜的一种情况已经满足
    {
        initSet();
        for (size_t i = 1; i <= 7; i++)
        {
            for (size_t j = 1; j <= 7; j++)
            {
                if (state[i] && state[j] && matrix[i][j])	//如果这两个状态都使用了且联通
                    mergeSet(i, j);			// 合并他们的父节点，即设置为同一个父节点下的集群
            }
        }

        int rootNode = 0;
        for (size_t i = 1; i <= 7; i++)
            if (state[i] && fa[i] == i)
                rootNode++;
        if (rootNode == 1)		// 联通群里只能有一个父节点
            ans++;
        return;
    }
    state[digit] = true;		//选中该节点
    dfs(digit + 1);
    state[digit] = false;		// 不选中该节点
    dfs(digit + 1);
}

int main(int argc, char const *argv[])
{
    //初始化相邻的段
    matrix[1][2] = matrix[1][6] = 1;
    matrix[2][1] = matrix[2][7] = matrix[2][3] = 1;
    matrix[3][2] = matrix[3][7] = matrix[3][4] = 1;
    matrix[4][3] = matrix[4][5] = 1;
    matrix[5][4] = matrix[5][7] = matrix[5][6] = 1;
    matrix[6][1] = matrix[6][7] = matrix[6][5] = 1;
    matrix[7][2] = matrix[7][3] = matrix[7][5] = matrix[7][6] = 1;
    dfs(1);
    cout << ans << endl;
    return 0;
}

```
