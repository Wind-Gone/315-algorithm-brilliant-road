# LeetCode 11 盛最多水的容器

## Problem

给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器。

## Samples

示例 1：

<img src="https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg" alt="img" style="zoom:50%;" /> 

```
输入：[1,8,6,2,5,4,8,3,7]
输出：49 
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
```

示例 2：

```
输入：height = [1,1]
输出：1
```

示例 3：

```
输入：height = [4,3,2,1,4]
输出：16
```

示例 4：

```
输入：height = [1,2,1]
输出：2
```


提示：

- n = height.length
- 2 <= n <= $3 * 10^4$
- 0 <= height[i] <= $3 * 10^4$

## Tag

双指针

## Solution

本题可以采用双指针的方法，目的是要求一个类似矩形的最大面积。由于暴力不可取，可以将暴力进行优化，ans值表示ans和高度最小值中的最大值，两个指针从头和尾一次找寻，得到答案ans并返回。

## Code

```java
package 盛最多水的容器;
class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        while (left < right) {
            ans = Math.max(ans, (right - left) * Math.min(height[left], height[right]));
            if (height[left] > height[right])
                right--;
            else
                left++;
        }
        return ans;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(a.maxArea(height));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(1)