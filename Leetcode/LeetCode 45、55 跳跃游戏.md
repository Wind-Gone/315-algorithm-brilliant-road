# LeetCode 45、55 跳跃游戏

## Problem 55 跳跃游戏

给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个下标。

### Samples

示例 1：

```
输入：nums = [2,3,1,1,4]
输出：true
解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
```

示例 2：

```
输入：nums = [3,2,1,0,4]
输出：false
解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
```


提示：

- 1 <= nums.length <= 3 * 10^4
- 0 <= nums[i] <= 10^5

### Tag

贪心

### Solution

在每个位置都计算自己能达到的最远距离，同时每个位置要判断自己是否可达，也就是本位置需要在当前最远能到达的距离中。最终计算出来能到达的最远距离，与数组长度比较即可。

### Code

```java
package 跳跃游戏;
class Solution {
    public boolean canJump(int[] nums) {
        int size = nums.length;
        int reach = 1;
        for (int i = 2; i <= size; i++) {
            if(nums[size-i] < reach) {    // 如果这个节点不能达到
                reach++;
            }
            else {  //这个节点可以达到
                reach = 1;
            }
        }
        return reach == 1;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] nums = {2,3,1,1,4};
        System.out.println(a.canJump(nums));
    }
}
```

### Complexity

时间复杂度：O(n)

空间复杂度：O(1)

## Problem 45 跳跃游戏Ⅱ

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

假设你总是可以到达数组的最后一个位置。

### Samples

示例 1:

```
输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
```

示例 2:

```
输入: [2,3,0,1,4]
输出: 2
```


提示:

- 1 <= nums.length <= 1000
- 0 <= nums[i] <= 10^5

## Tag

贪心、动态规划

### Solution

进行正向查找，每次找到可到达的最远位置，就可以得到最少的跳跃次数。进行一次遍历，第一次找能到的区间[a,b],第二次找到[a,b]区间能到的最大值c，再在[b+1,c]找最大值，依次类推。

### Code

```java
package 跳跃游戏Ⅱ;

class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int res = 0;
        while(right < n - 1) {
            for(int i = left; i <= right; i++) {
                left = Math.max(left, i + nums[i]);
            }
            int tmp = left;
            left = right + 1;
            right = tmp;
            res++;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] nums = {2,3,1,1,4};
        System.out.println(a.jump(nums));
    }
}
```

### Complexity

时间复杂度：O(n)

空间复杂度：O(1)