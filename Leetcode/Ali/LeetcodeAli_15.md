---
阿里巴巴题库 15. 三数之和
---

## Problem

> 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
>
> **注意：答案中不可以包含重复的三元组。**



> 示例 1：
>
> 输入：nums = [-1,0,1,2,-1,-4]
> 输出：[[-1,-1,2],[-1,0,1]]
> 示例 2：
> 
> 输入：nums = []
>输出：[]
> 示例 3：
>
> 输入：nums = [0]
> 输出：[]
> 
> **提示：**
> 
>0 <= nums.length <= 3000
> -105 <= nums[i] <= 105

## Tag

- 常规数组处理
- 双指针



## Solution

> - 比较浅显的排序+双指针移动
>
>   #### 算法流程：
>
>   1. 特判，对于数组长度 nn，如果数组为 nullnull 或者数组长度小于 33，返回 [][]。
>   2. 对数组进行排序。
>   3. 遍历排序后数组：
>      - 若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
>      - 对于重复元素：跳过，避免出现重复解
>      - 令左指针 L=i+1，右指针 R=n-1，当 L<R 时，执行循环：
>        - 当 nums[i]+nums[L]+nums[R] == 0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R 移到下一位置，寻找新的解
>        - 若和大于 0，说明 nums[R]太大，R 左移
>        - 若和小于 0，说明 nums[L]太小，L 右移
>
>   

### Code—1

```c++
class Solution
{
public:
    vector<vector<int>> threeSum(vector<int> &nums)
    {
        if (nums.empty() || nums.size() < 3) // 数组为空或者长度小于3
            return {};
        vector<vector<int>> ans;
        sort(nums.begin(), nums.end()); // 先排序再遍历
        for (size_t i = 0; i < nums.size(); i++)
        {
            if (nums[i] > 0) // 如果首个元素已经为正，则后序不可能求和为0了
                return ans;
            if (i >= 1 && nums[i] == nums[i - 1]) // 如果当前元素与左边的一样就跳过
                continue;
            int left = i + 1; // 左右指针
            int right = nums.size() - 1;
            while (left < right)
            {
                if (nums[left] + nums[right] + nums[i] < 0) // 求和小于0，需要增大nums[left]的值
                    left++;
                else if (nums[left] + nums[right] + nums[i] > 0) // 求和大于0，需要减小nums[right]的值
                    right--;
                else //得到所求解
                {
                    ans.push_back(vector<int>{nums[i], nums[left], nums[right]});
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) //去重
                        left++;
                    while (left < right && nums[right] == nums[right + 1])
                        right--;
                }
            }
        }
        return ans;
    }
};
```

### Complexity Analysis

- 时间复杂度：`O(NlogN)`
- 空间复杂度：`O(N)`
