# LeetCode 33 搜索旋转排列数组

## Problem

整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的索引，否则返回 -1 。

## Samples

示例 1：

```
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
```


示例 2：

```
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
```

示例 3：

```
输入：nums = [1], target = 0
输出：-1
```

## Tag

二分查找

## Solution

考虑二分查找是因为本题的旋转方法能够保证旋转后的数组按照左右两边分，一定有一边的数组是有序的。基于这一点mid，我们做二分查找。如果 [left, mid - 1] 是有序数组，且 target 的大小满足nums[left]≤target＜nums[mid])，则我们应该将搜索范围缩小至 [left, mid - 1]，否则在 [mid + 1, right] 中寻找。类似地，如果 [mid, right] 是有序数组，且 target 的大小满足nums[mid+1]≤target＜nums[right]]，则我们应该将搜索范围缩小至 [mid + 1, right]，否则在 [left, mid - 1] 中寻找。

## Code

```java
package 搜索旋转排列数组;

public class Main {
    static int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0)
            return -1;
        if (n == 1)
            return nums[0] == target ? 0 : -1;
        int left = 0, right = n - 1;
        while (left <= right) { // 二分查找
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums, target));
    }
}
```

## Complexity

时间复杂度：O(logn)

空间复杂度：O(1)