# LeetCode 43 字符串相乘

## Problem

给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

## Samples

示例 1:

```
输入: num1 = "2", num2 = "3"
输出: "6"
```

示例 2:

```
输入: num1 = "123", num2 = "456"
输出: "56088"
```

说明：

- num1 和 num2 的长度小于110。
- num1 和 num2 只包含数字 0-9。
- num1 和 num2 均不以零开头，除非是数字 0 本身。
- **不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。**

## Tag

字符串

## Solution

我们发现，num1的第i位(高位从0开始)和num2的第j位相乘的结果在乘积中的位置是`[i+j, i+j+1]`。例如123*45,123的第1位2和45的第0位4的乘积08存放在结果的第`[1,2]`位中。这样我们就可以单独都对每一位进行相乘计算把结果存入相应的index中。

## Code

```java
package 字符串相乘;
class Solution {
    public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if (len1 == 1 && num1.charAt(0) - '0' == 0
                || len2 == 1 && num2.charAt(0) - '0' == 0) {
            return "0";
        }
        int[] sums = new int[len1 + len2];
        int[] nums1 = new int[len1];
        for (int i = 0; i < len1; i++) {
            nums1[i] = num1.charAt(i) - '0';
        }
        for (int i = 0; i < len2; i++) {
            int multiply1 = num2.charAt(len2 - i - 1) - '0';
            for (int j = 0; j < len1; j++) {
                sums[i + j] += (multiply1 * nums1[len1 - j - 1]);   // 两个数位的乘积
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] >= 10) {    // 判断进位
                sums[i + 1] += sums[i] / 10;
                sums[i] = sums[i] % 10;
            }
            // 去除前导0
            if (i == sums.length - 1 && sums[i] == 0) {
                continue;
            }
            stringBuilder.append(sums[i]);
        }
        return stringBuilder.reverse().toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String num1 = "123", num2 = "456";
        System.out.println(a.multiply(num1, num2));
    }
}
```

## Complexity

时间复杂度：O(mn)

空间复杂度：O(m+n)