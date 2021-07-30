# LeetCode 77 组合

## Problem

给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。

你可以按 任何顺序 返回答案。

## Samples

示例 1：

```
输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

示例 2：

```
输入：n = 1, k = 1
输出：[[1]]
```


提示：

- 1 <= n <= 20
- 1 <= k <= n

## Tag

回溯、剪枝

## Solution

以输入n = 4, k = 2为例，我们可以发现如下递归结构：

- 如果组合里有1，那么需要在 [2, 3, 4] 里再找1个数；
- 如果组合里有2，那么需要在 [3, 4] 里再找1个数。注意：这里不能再考虑1，因为包含1的组合，在第1种情况中已经包含。

依次类推（后面部分省略），以上描述体现的递归结构是：在以n结尾的候选数组里，选出若干个元素

## Code

```java
package 组合;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点
        for (int i = begin; i <= n; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(n, k, i + 1, path, res);
            // 深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int n = 4;
        int k = 2;
        System.out.println(a.combine(n, k));
    }
}
```

## Complexity

时间复杂度：O(k×C(n,k))

空间复杂度：O(n)