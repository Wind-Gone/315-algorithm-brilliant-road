# LeetCode 179 最大数

## Problem

给定一组非负整数 `nums`，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

**注意：**输出结果可能非常大，所以你需要返回一个字符串而不是整数。

## Samples

示例 1：

```
输入：nums = [10,2]
输出："210"
```


示例 2：

```
输入：nums = [3,30,34,5,9]
输出："9534330"
```


示例 3：

```
输入：nums = [1]
输出："1"
```


示例 4：

```
输入：nums = [10]
输出："10"
```


提示：

- 1 <= nums.length <= 100
- 0 <= nums[i] <= 109

## Tag

字符串、排序

## Solution

在比较两个数字大小的时候，若长度不相等，就把两个数字合并起来。如果数字特别大，强行合并起来是导致溢出。

所以可以把数字转为 String ，把字符串合并起来，然后对字符串进行比较。

在比较函数中要分别判断数字长度相等和不相等的情况，不过长度相等的情况也是可以合并到长度不相等的情况中去的。

## Code

```java
package 最大数;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String largestNumber(int[] nums) {
        Integer[] n = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            n[i] = nums[i];
        }
        Arrays.sort(n, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                String s1 = n1 + "" + n2;
                String s2 = n2 + "" + n1;
                //compareTo 方法
                //如果参数是一个按字典顺序排列等于该字符串的字符串，则返回值为0;
                //如果参数是按字典顺序大于此字符串的字符串，则返回值小于0;
                //如果参数是按字典顺序小于此字符串的字符串，则返回值大于0。
                return s2.compareTo(s1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(n[i]);
        }
        String res = sb.toString();
        return res.charAt(0) == '0' ? "0" : res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] nums = {3,30,34,5,9};
        System.out.println(a.largestNumber(nums));
    }
}
```

## Complexity

时间复杂度：O(nlogn)

空间复杂度：O(logn)	（可能复杂度是错的~~）