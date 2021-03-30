# LeetCode 300 最长递增子序列

## Problem

给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

## Samples

示例 1：

```
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
```


示例 2：

```
输入：nums = [0,1,0,3,2,3]
输出：4
```


示例 3：

```
输入：nums = [7,7,7,7,7,7,7]
输出：1
```

## Tag

dp，数组

## Solution

容易由下面的思路。对数组[0, nums.length)的所有元素，对[0, i)的元素去做遍历。对于nums[i] <= nums[j]的元素，因为它们不满足题目要求我们只要跳过进入下一次比较；而对于nums[i] > nums[j]的元素，那么最长递增子序列的长度就是1 + ans[j]。所以在遍历j时，让ans[i] = Math.max(ans[i], ans[j] + 1)。注意要将初始状态置为1。

## Code

```java
package 最长递增子序列;
/*
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

 */
public class Main {
    static int lengthOfLIS(int[] nums) {
        if(nums.length == 1)
            return 1;
        int count = 1;
        int[] ans = new int[nums.length];
        for(int i = 0; i < nums.length - 1; i++) {
            ans[i] = 1; // 假设初始值设为1
            for(int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    ans[i] = Math.max(ans[i], ans[j] + 1); // 比较nums数组的第i个和第j个+1的大小，将大的数赋值给新设的数组ans
                }
                count = Math.max(count, ans[i]); // 最大长度即为ans[i]和count中最大值，在两个for循环下多次比较得到最终结果
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }
}
```

## Complexity

时间复杂度O(n²)（遍历ans，计算ans[i]）

空间复杂度O(n)
