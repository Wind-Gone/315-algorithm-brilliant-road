---
阿里巴巴题库 4. 寻找两个正序数组的中位数
---

## Problem

#### [ 寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/)

给定两个大小分别为 `m` 和 `n` 的正序（从小到大）数组 `nums1` 和 `nums2`。请你找出并返回这两个正序数组的 **中位数** 。

> **示例 1：**
>
> ```
> 输入：nums1 = [1,3], nums2 = [2]
> 输出：2.00000
> 解释：合并数组 = [1,2,3] ，中位数 2
> ```
>
> **示例 2：**
>
> ```
> 输入：nums1 = [1,2], nums2 = [3,4]
> 输出：2.50000
> 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
> ```
>
> **示例 3：**
>
> ```
> 输入：nums1 = [0,0], nums2 = [0,0]
> 输出：0.00000
> ```
>
> **示例 4：**
>
> ```
> 输入：nums1 = [], nums2 = [1]
> 输出：1.00000
> ```
>
> **示例 5：**
>
> ```
> 输入：nums1 = [2], nums2 = []
> 输出：2.00000
> ```
>
>  
>
> **提示：**
>
> - `nums1.length == m`
> - `nums2.length == n`
> - `0 <= m <= 1000`
> - `0 <= n <= 1000`
> - `1 <= m + n <= 2000`
> - `-106 <= nums1[i], nums2[i] <= 106`
>
>  时间复杂度为 `O(log (m+n))`



## Tag

- 二分查找
- 数组处理



## Solution

> - 如果不考虑时间复杂度，那么思路其实很简单，归并+排序



### Code—1

```c++
class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        vector<int> ans;
        if (nums1.empty())
            ans = nums2;
        else if (nums2.empty())
            ans = nums1;
        else {
            ans.insert(ans.end(),nums1.begin(),nums1.end());
            ans.insert(ans.end(),nums2.begin(),nums2.end());
            sort(ans.begin(),ans.end());
        }
        if((m+n)%2)
                return ans[(m+n)>>1];
        else
                return (ans[(m+n)/2]+ans[(m+n)/2-1]) / 2.0;
    }
};
```

### Complexity Analysis

- 时间复杂度：`O(m+n)`
- 空间复杂度：`O(m+n)`



## Solution

> ***看到时间复杂度为对数级别，二分查找可能是必须能想到的方法，但是如何运用二分搜索是个比较困难的地方***
>
> 借助官方题解大概理解了思路：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
>
> 首先回顾一下中位数的定义：
>
> - 如果某个有序数组长度是奇数，那么其中位数就是最中间那个
>
> - 如果是偶数，那么就是最中间两个数字的平均值。
>
>   这里对于两个有序数组也是一样的，假设两个有序数组的长度分别为m和n，由于两个数组长度之和 m+n 的奇偶不确定，因此需要分情况来讨论，对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值。
>
> 其实理解的核心就在于如何把思路转向反求第K大元素这个新问题，每次更新K的值和数组的索引起始位置，通过比较指定位数值的大小缩减数组范围，最终将问题浓缩为在两个子数组里找到第1大的值（即首元素）**（一般情况）**



### Code—2

```c++
class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        int total = m + n;
        return (total % 2) ? getKthElement(nums1,nums2,(total+1)/2) : (getKthElement(nums1,nums2,total/2) + getKthElement(nums1,nums2,total / 2 + 1)) / 2.0;
    }
    int getKthElement(const vector<int>& nums1, const vector<int>& nums2, int  k) {
        int m = nums1.size(), n = nums2.size();
        int start1 = 0, start2 = 0;
        while(1){ 
            // 如果一个数组为空，说明该数组中的所有元素都被排除，我们可以直接返回另一个数组中第 k 小的元素。
            if (start1 == m)
                return nums2[start2 + k -1];
            if (start2 == n)
                return nums1[start1 + k -1];
            if (k == 1) // 如果 k=1 我们只要返回两个数组首元素的最小值即可
                return min(nums1[start1], nums2[start2]);
            int end1 = min (m-1, start1 + (k >> 1) - 1); //如果 A[k/2−1] 或者 B[k/2−1] 越界，那么我们可以选取对应数组中的最后一个元素。在这种情况下，我们必须根据排除数的个数减少 k 的值，而不能直接将 k 减去 k/2
            int end2 = min (n-1, start2 + (k >> 1) - 1);
            int pivot1 = nums1[end1];
            int pivot2 = nums2[end2];
            if (pivot1 <= pivot2) {
                k -= end1 - start1 + 1;
                start1 = end1 + 1;
            }
            else if (pivot2 < pivot1) {
                k -= end2 - start2 + 1;
                start2 = end2 + 1;
            }
        }        
    }
};
```

### Complexity Analysis

- 时间复杂度： `O(log (m+n))`
- 空间复杂度：`O(1)`