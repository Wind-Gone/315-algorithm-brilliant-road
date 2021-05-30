# LeetCode 31 下一个排列

## Problem

实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须 原地 修改，只允许使用额外常数空间。

## Samples

示例 1：

```
输入：nums = [1,2,3]
输出：[1,3,2]
```

示例 2：

```
输入：nums = [3,2,1]
输出：[1,2,3]
```

示例 3：

```
输入：nums = [1,1,5]
输出：[1,5,1]
```

示例 4：

```
输入：nums = [1]
输出：[1]
```


提示：

- 1 <= nums.length <= 100
- 0 <= nums[i] <= 100

## Tag

数组

## Solution

扫描数组，将数组中的大数和小数交换，反转交换及后面的部分。

对于长度为n的排列a：

首先从后向前查找第一个顺序对(i,i+1)，满足a[i]<a[i+1]。这样「较小数」即为a[i]。此时[i+1,n)必然是下降序列。

如果找到了顺序对，那么在区间[i+1,n)中从后向前查找第一个元素j满足a[i]<a[j]。这样「较大数」即为a[j]。

交换a[i]与a[j]，此时可以证明区间[i+1,n) 必为降序。我们可以直接使用双指针反转区间[i+1,n)使其变为升序，而无需对该区间进行排序。

## Code

```java
package 下一个排列;

import java.util.Arrays;

class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {   // 将i后面的元素排序
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);   // 翻转后面的逆序区域
        System.out.println(Arrays.toString(nums));
    }
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public void reverse(int[] nums, int i) {
        int left = i, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] nums = {1,2,3};
        a.nextPermutation(nums);
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(1)