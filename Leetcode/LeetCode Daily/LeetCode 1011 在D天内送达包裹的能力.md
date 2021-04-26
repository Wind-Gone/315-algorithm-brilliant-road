# LeetCode 1011 在D天内送达包裹的能力

## Problem

传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。

传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。

## Samples

示例 1：

```
输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
输出：15
解释：
船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
第 1 天：1, 2, 3, 4, 5
第 2 天：6, 7
第 3 天：8
第 4 天：9
第 5 天：10

请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。 
```


示例 2：

```
输入：weights = [3,2,2,4,1,4], D = 3
输出：6
解释：
船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
第 1 天：3, 2
第 2 天：2, 4
第 3 天：1, 4
```


示例 3：

```
输入：weights = [1,2,3,1,1], D = 4
输出：3
解释：
第 1 天：1
第 2 天：2
第 3 天：3
第 4 天：1, 1
```


提示：

- 1 <= D <= weights.length <= 50000
- 1 <= weights[i] <= 500

## Tag

二分查找

## Solution

本题其实想到二分是有一定难度的，具体怎么想的见下文：

假设当船的运载能力为x时，我们可以在D天内运送完所有包裹，那么只要运载能力大于x，我们同样可以在D天内运送完所有包裹：我们只需要使用运载能力为x时的运送方法即可。

这样一来，我们就得到了一个非常重要的结论：

存在一个运载能力的下限$x_{ans}$，使得当 $x \geq x_{ans}$时，我们无法在D天内运送完所有包裹。

同时，$x_{ans}$即为我们需要求出的答案。因此，我们就可以使用二分查找的方法找出$x_{ans}$的值。

由于我们必须按照数组weights中包裹的顺序进行运送，因此我们从数组weights的首元素开始遍历，将连续的包裹都安排在同一天进行运送。当这批包裹的重量大于运载能力x时，我们就需要将最后一个包裹拿出来，安排在新的一天，并继续往下遍历。当我们遍历完整个数组后，就得到了最少需要运送的天数。

## Code

```java
package 在D天内送达包裹的能力;
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int left = 0;
        int n = weights.length;
        int right = 0;
        int res = 0;
        for (int i = 0; i < n; i++){
            left = Math.max(left, weights[i]);
            right += weights[i];
        }
        // 二分查找
        while (left <= right) {
            int mid = (left + right) / 2;
            if (checkPackage(weights, D, mid)){
                res = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return res;
    }
    //检查当前的承载重量能否在D天内全部运输完成
    public boolean checkPackage (int [] weights, int D, int cur) {
        int sum_day = 1, cur_load = 0;
        for (int i = 0; i < weights.length; i++) {
            if(cur_load + weights[i] <= cur) {
                cur_load += weights[i];
            }
            else {
                cur_load = weights[i];
                sum_day++;
            }
        }
        return sum_day <= D;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        int[] weight = {1,2,3,4,5,6,7,8,9,10};
        int D = 5;
        System.out.println(a.shipWithinDays(weight, D));
    }
}
```

## Complexity

时间复杂度：$O(nlog(Σw))$

空间复杂度：O(1)