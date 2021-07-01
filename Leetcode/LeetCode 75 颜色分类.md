# LeetCode 75 颜色分类

## Problem

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

## Samples

示例 1：

```
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
```

示例 2：

```
输入：nums = [2,0,1]
输出：[0,1,2]
```

示例 3：

```
输入：nums = [0]
输出：[0]
```

示例 4：

```
输入：nums = [1]
输出：[1]
```


提示：

- n == nums.length
- 1 <= n <= 300
- nums[i] 为 0、1 或 2


进阶：

- 你可以不使用代码库中的排序函数来解决这道题吗？
- 你能想出一个仅使用常数空间的一趟扫描算法吗？

## Tag

指针遍历

## Solution

从题目描述来看，左边是0，中间1，右边是2。因此我们要做的就是遇到0放左边，要2放右边，1就很自然的在中间了。

首先我们需要定义两个指针，分别指向左边不是0的位置（且左边只能是0）和右边不是2的位置（且右边只能是2），用left和right表示
接下来就只需要在left和right直接进行遍历即可。

## Code

```java
package 颜色分类;

import java.util.Arrays;

class Solution {
    public void sortColors(int[] nums) {
        //zero表示数字0的右侧边界，one、two分别表示1 和 2 的右侧边界
        int zero = 0;
        int one = 0;
        int two = 0;
        int length = nums.length;
        for(int i = 0; i < length; i++) {
            //记录下待处理的值
            int temp = nums[i];

            //不管怎样，我先给你填个2
            nums[two] = 2;
            two ++;

            // <=1的话 1的右侧边界one要向右挪一格
            if(temp <= 1)
            {
                nums[one] = 1;
                one ++;
            }
            // 为0的话 0的右侧边界zero要向右挪一格
            if(temp == 0)
            {
                nums[zero] = 0;
                zero ++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] nums = {2,0,2,1,1,0};
        a.sortColors(nums);
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(1)