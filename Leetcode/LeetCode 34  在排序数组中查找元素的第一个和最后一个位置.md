# LeetCode 34  在排序数组中查找元素的第一个和最后一个位置

## Problem

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？

## Samples


示例 1：

```
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
```

示例 2：

```
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
```

示例 3：

```
输入：nums = [], target = 0
输出：[-1,-1]
```


提示：

- 0 <= nums.length <= 10^5
- -10^9 <= nums[i] <= 10^9
- nums 是一个非递减数组
- -10^9 <= target <= 10^9

## Tag

二分查找

## Solution

本题限制了时间复杂度为O(logn)，因此采用二分查找。分别对第一个和最后一个元素采用二分查找。

## Code

```java
package 在排序数组中查找元素的第一个和最后一个位置;

import java.util.Arrays;

class Solution {
    // 简单思路，做两次二分查找
    public int searchFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            else if (nums[mid] > target) {
                right = mid - 1;
            }
            else {
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                }
                else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
    public int searchLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            else if (nums[mid] > target) {
                right = mid - 1;
            }
            else {
                // 如果当前元素已经是数组的最后一个元素了，那么无需再向后看了，直接返回
                // 如果不是最后一个元素，则需要看看后面是否还有元素满足条件
                if(mid == nums.length - 1 || nums[mid + 1] != target) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
    public int[] searchRange(int[] nums, int target) {
        int first = searchFirst(nums, target);
        int last = searchLast(nums, target);
        return new int[]{first, last};
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] nums = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(a.searchRange(nums, 8)));
    }
}
```

## Complexity

时间复杂度：O(logn)

空间复杂度：O(1)