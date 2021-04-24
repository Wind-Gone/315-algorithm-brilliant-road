# LeetCode 组合总和

## Summary

今天每日一题是组合总和Ⅳ，因此决定把这一类题整理一下。主要采用的是回溯的方法。

## 39 组合总和

### Problem

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

- 所有数字（包括 target）都是正整数。
- 解集不能包含重复的组合。 

### Samples

示例 1：

```
输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
```


示例 2：

```
输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```


提示：

- 1 <= candidates.length <= 30
- 1 <= candidates[i] <= 200
- candidate 中的每个元素都是独一无二的。
- 1 <= target <= 500

### Tag

回溯

### Solution

对于这类寻找所有可行解的题，我们都可以尝试用「搜索回溯」的方法来解决。

回到本题，我们定义递归函数 dfs(target, combine, idx) 表示当前在 candidates 数组的第 idx 位，还剩 target 要组合，已经组合的列表为 combine。递归的终止条件为 target <= 0 或者 candidates 数组被全部用完。那么在当前的函数中，每次我们可以选择跳过不用第 idx 个数，即执行 dfs(target, combine, idx + 1)。也可以选择使用第 idx 个数，即执行 dfs(target - candidates[idx], combine, idx)，注意到每个数字可以被无限制重复选取，因此搜索的下标仍为 idx。

更形象化地说，如果我们将整个搜索过程用一个树来表达，即如下图呈现，每次的搜索都会延伸出两个分叉，直到递归的终止条件，这样我们就能不重复且不遗漏地找到所有可行解

### Code

```java
package 组合总和;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(a.combinationSum(candidates, target));
    }
}
```

### Complexity

时间复杂度：O(n)

空间复杂度：O(n)

## 40 组合总和Ⅱ

### Problem

给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

- 所有数字（包括目标数）都是正整数。
- 解集不能包含重复的组合。 

### Samples

示例 1:

```
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```


示例 2:

```
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```

### Tag

回溯

### Solution

由于我们需要求出所有和为target的组合，并且每个数只能使用一次，因此我们可以使用递归 + 回溯的方法来解决这个问题：

- 我们用dfs(pos,rest)表示递归的函数，其中pos表示我们当前递归到了数组candidates中的第pos个数，而rest表示我们还需要选择和为rest的数放入列表作为一个组合；


- 对于当前的第pos个数，我们有两种方法：选或者不选。如果我们选了这个数，那么我们调用dfs(pos+1,rest−candidates[pos])进行递归，注意这里必须满足rest≥candidates[pos]。如果我们不选这个数，那么我们调用dfs(pos+1,rest)进行递归；


- 在某次递归开始前，如果rest的值为0，说明我们找到了一个和为target的组合，将其放入答案中。每次调用递归函数前，如果我们选了那个数，就需要将其放入列表的末尾，该列表中存储了我们选的所有数。在回溯时，如果我们选了那个数，就要将其从列表的末尾删除。


上述算法就是一个标准的递归 + 回溯算法，但是它并不适用于本题。这是因为题目描述中规定了解集不能包含重复的组合，而上述的算法中并没有去除重复的组合。

例如当candidates=[2,2]，target=2 时，上述算法会将列表`[2]`放入答案两次。

因此，我们需要改进上述算法，在求出组合的过程中就进行去重的操作。我们可以考虑将相同的数放在一起进行处理，也就是说，如果数x出现了y次，那么在递归时一次性地处理它们，即分别调用选择0,1,⋯,y 次x的递归函数。这样我们就不会得到重复的组合。具体地：

- 我们使用一个哈希映射（HashMap）统计数组candidates中每个数出现的次数。在统计完成之后，我们将结果放入一个列表freq中，方便后续的递归使用。
  - 列表freq的长度即为数组candidates中不同数的个数。其中的每一项对应着哈希映射中的一个键值对，即某个数以及它出现的次数。

- 在递归时，对于当前的第pos个数，它的值为`freq[pos][0]`，出现的次数为`freq[pos][1]`，那么我们可以调用`dfs(pos+1,rest−i×freq[pos][0])`

  即我们选择了这个数i次。这里i不能大于这个数出现的次数，并且`i×freq[pos][0]`也不能大于rest。同时，我们需要将i个`freq[pos][0]`放入列表中。

这样一来，我们就可以不重复地枚举所有的组合了。

我们还可以进行什么优化（剪枝）呢？一种比较常用的优化方法是，我们将freq根据数从小到大排序，这样我们在递归时会先选择小的数，再选择大的数。这样做的好处是，当我们递归到dfs(pos,rest) 时，如果`freq[pos][0]`已经大于rest，那么后面还没有递归到的数也都大于rest，这就说明不可能再选择若干个和为rest的数放入列表了。此时，我们就可以直接回溯。

### Code

```java
class Solution {
    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    	Arrays.sort(candidates);
    	for (int num : candidates) {
        	int size = freq.size();
        	if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
            	freq.add(new int[]{num, 1});
        	} else {
            	++freq.get(size - 1)[1];
        	}
    	}
    	dfs(0, target);
    	return ans;
	}

	public void dfs(int pos, int rest) {
    	if (rest == 0) {
        	ans.add(new ArrayList<Integer>(sequence));
        	return;
    	}
    	if (pos == freq.size() || rest < freq.get(pos)[0]) {
        	return;
    	}

		dfs(pos + 1, rest);

		int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
		for (int i = 1; i <= most; ++i) {
			sequence.add(freq.get(pos)[0]);
			dfs(pos + 1, rest - i * freq.get(pos)[0]);
		}
		for (int i = 1; i <= most; ++i) {
			sequence.remove(sequence.size() - 1);
		}
	}
}
```

### Complexity

时间复杂度：$O(n×2^n)$

空间复杂度：$O(n)$

## 216 组合总和Ⅲ

### Problem

找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

- 所有数字都是正整数。
- 解集不能包含重复的组合。

### Samples 

示例 1:

```
输入: k = 3, n = 7
输出: [[1,2,4]]
```


示例 2:

```
输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]
```

### Tag

枚举

### Solution

「组合中只允许含有1−9的正整数，并且每种组合中不存在重复的数字」意味着这个组合中最多包含9个数字。我们可以把原问题转化成集合S={1,2,3,4,5,6,7,8,9}，我们要找出S的当中满足如下条件的子集：

- 大小为k;
- 集合中元素的和为n

因此我们可以用子集枚举的方法来做这道题。即原序列中有9个数，每个数都有两种状态，「被选择到子集中」和「不被选择到子集中」，所以状态的总数为$2^9$。我们用一个9位二进制数mask来记录当前所有位置的状态，从第到高第i位为0表示i不被选择到子集中，为1表示i被选择到子集中。当我们按顺序枚举$[0, 2^9 - 1][0,2^9 −1]$ 中的所有整数的时候，就可以不重不漏地把每个状态枚举到，对于一个状态mask，我们可以用位运算的方法得到对应的子集序列，然后再判断是否满足上面的两个条件，如果满足，就记录答案。

### Code

```java
class Solution {
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        for (int mask = 0; mask < (1 << 9); ++mask) {
            if (check(mask, k, n)) {
                ans.add(new ArrayList<Integer>(temp));
            }
        }
        return ans;
    }

    public boolean check(int mask, int k, int n) {
        temp.clear();
        for (int i = 0; i < 9; ++i) {
            if (((1 << i) & mask) != 0) {
                temp.add(i + 1);
            }
        }
        if (temp.size() != k) {
            return false;
        }
        int sum = 0;
        for (int num : temp) {
            sum += num;
        }
        return sum == n;
    }
}
```

### Complexity

时间复杂度：$O(n×2^n)$

空间复杂度：$O(n)$

## 377 组合总和Ⅳ

### Problem

给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。

题目数据保证答案符合 32 位整数范围。

进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？

### Samples

示例 1：

```
输入：nums = [1,2,3], target = 4
输出：7
解释：
所有可能的组合为：
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
请注意，顺序不同的序列被视作不同的组合。
```


示例 2：

```
输入：nums = [9], target = 3
输出：0
```


提示：

- 1 <= nums.length <= 200
- 1 <= nums[i] <= 1000
- nums 中的所有元素 互不相同
- 1 <= target <= 1000

### Tag

动态规划

### Solution

计算可能的方案数。用dp[x]表示选取的元素之和等于x的方案数，目标是求dp[target]。

动态规划的边界是dp[0]=1。只有当不选取任何元素时，元素之和才为0，因此只有1种方案。

当1≤i≤target时，如果存在一种排列，其中的元素之和等于i，则该排列的最后一个元素一定是数组nums中的一个元素。假设该排列的最后一个元素是num，则一定有num≤i，对于元素之和等于i−num的每一种排列，在最后添加num之后即可得到一个元素之和等于i的排列，因此在计算dp[i]时，应该计算所有的dp[i−num]之和。

由此可以得到动态规划的做法：

- 初始化dp[0]=1；


- 遍历i从1到target，对于每个i，进行如下操作：
  - 遍历数组nums 中的每个元素num，当num≤i时，将dp[i−num]的值加到dp[i]。

- 最终得到dp[target] 的值即为答案。

### Code

```java
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
```

### Complexity

时间复杂度：O(mn)

空间复杂度：O(n)

## Conclusion

一类题，不过题目不大相同，解法也不尽相同，熟能生巧吧~