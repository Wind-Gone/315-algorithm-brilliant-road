# LeetCode 50 Pow(x, n)

实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，$x^n$）。

## Samples

示例 1：

```
输入：x = 2.00000, n = 10
输出：1024.00000
```

示例 2：

```
输入：x = 2.10000, n = 3
输出：9.26100
```

示例 3：

```
输入：x = 2.00000, n = -2
输出：0.25000
解释：2-2 = 1/22 = 1/4 = 0.25
```


提示：

- $-100.0 < x < 100.0$
- $-2^{31} <= n <= 2^{31}-1$
- $-10^4 <= x^n <= 10^4$

## Tag

快速幂、递归、迭代

## Solution

使用折半计算，每次把n缩小一半，这样n最终会缩小到0，任何数的0次方都为1，这时候我们再往回乘，如果此时n是偶数，直接把上次递归得到的值算个平方返回即可；如果是奇数，则还需要乘上个x的值。当n是负数时，先用其绝对值计算出一个结果再取其倒数即可。

## Code

```java
package Pow_x_n_;
class Solution {
    public double myPow(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x;
            }
            x *= x;
        }
        return  n < 0 ? 1 / res : res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        System.out.println(a.myPow(2.00000, -2));
    }
}
```

## Complexity

时间复杂度：O(logn)

空间复杂度：O(1)