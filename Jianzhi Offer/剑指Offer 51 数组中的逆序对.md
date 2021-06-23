# 剑指Offer 51 数组中的逆序对

## Problem

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

## Samples

示例 1:

```
输入: [7,5,6,4]
输出: 5
```


限制：

- 0 <= 数组长度 <= 50000


## Tag

归并排序

## Solution

在每一次归并的过程中，发现左半部分有大于右半部分的一个元素时，左半部分大于这个元素的个数为mid-i+1个，因此在每次发现后求出某段的逆序对即可。

## Code

```java
package 数组中的逆序对;
class Solution {
    int N = 50005;
    int[] tmp = new int[N];
    int res = 0;
    public int reversePairs(int[] nums) {
        return mergesort(nums, 0, nums.length - 1);
    }

    // 归并排序
    public int mergesort(int[] nums, int left, int right) {
        if(left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        mergesort(nums, left, mid);
        mergesort(nums, mid + 1, right);

        int k = 0, i = left, j = mid + 1;
        while (i <= mid && j <= right){
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
                res += mid - i + 1; // res计数
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        // 赋值给原数组
        for (i = left, j = 0; i <= right; i++, j++){
            nums[i] = tmp[j];
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] nums = {7,5,6,4};
        System.out.println(a.reversePairs(nums));
    }
}
```

## Complexity

时间复杂度：O(nlogn)

空间复杂度：O(n)

