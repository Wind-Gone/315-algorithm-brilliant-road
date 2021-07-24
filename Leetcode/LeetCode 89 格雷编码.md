# LeetCode 89 格雷编码

## Problem

格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。

格雷编码序列必须以 0 开头。

## Samples

示例 1:

```
输入: 2
输出: [0,1,3,2]
解释:
00 - 0
01 - 1
11 - 3
10 - 2

对于给定的 n，其格雷编码序列并不唯一。
例如，[0,2,3,1] 也是一个有效的格雷编码序列。

00 - 0
10 - 2
11 - 3
01 - 1
```

示例 2:

```
输入: 0
输出: [0]
解释: 我们定义格雷编码序列必须以 0 开头。
     给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     因此，当 n = 0 时，其格雷编码序列为 [0]。
```

## Tag

数学、回溯

## Solution

由n位推导n+1位结果时，n+1位结果包含n位结果，同时包含n位结果中在高位再增加一个位1所形成的令一半结果，但是这一半结果需要与前一半结果镜像排列。

## Code

```java
package 格雷编码;

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<Integer> res = new ArrayList();
    boolean[] visited;

    public List<Integer> grayCode(int n) {
        visited = new boolean[1 << n];
        dfs(0, n);
        return res;
    }

    boolean dfs(int cur, int n) {
        if (res.size() == (1 << n)) {
            return true;
        }
        res.add(cur);
        visited[cur] = true;
        for (int i = 0; i < n; i++) {
            int next = cur ^ (1 << i); //这里改变cur的某一位，用异或
            if (!visited[next] && dfs(next, n)) {
                return true;
            }
        }
        visited[cur] = false;
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int n = 2;
        System.out.println(a.grayCode(n));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(n)