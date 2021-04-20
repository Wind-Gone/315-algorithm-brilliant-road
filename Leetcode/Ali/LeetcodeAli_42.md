---
阿里巴巴题库 42. 接雨水
---

## Problem

给定 *n* 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

> 示例 1：
>
> ![img](LeetcodeAli_42.assets/rainwatertrap.png)
>
> 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
> 输出：6
> 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
> 示例 2：
>
> 输入：height = [4,2,0,3,2,5]
> 输出：9
>
> > **提示：**
> >
> > - n == height.length
> > - 0 <= n <= 3 * 104
> > - 0 <= height[i] <= 105

## Tag

- 动态规划
- 栈
- 数学分析



### Solution-1

> **方法 1：暴力**
>
> ###### 直观想法
>
> 直接按问题描述进行。对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值。
>
> ###### 算法
>
> - 初始化 ans=0ans=0
> - 从左向右扫描数组：
>   - 初始化 $\text{max_left}=0$ 和$ \text{max_right}=0$从
>   - 当前元素向左扫描并更新：
>     - $\text{max_left}=\max(\text{max_left},\text{height}[j])$从
>   - 当前元素向右扫描并更新：
>     - $\text{max_right}=\max(\text{max_right},\text{height}[j])$将
>   - $\min(\text{max_left},\text{max_right}) - \text{height}$累加到$ \text{ans}$

### Code—1

```c++
class Solution {
public:
    int trap(vector<int>& height) {
		int ans = 0;
		int size = height.size();
		for(int i = 1; i < size - 1; i++){
			int max_left = 0, max_right = 0;
			for(int j = i ; j < size; j++){
				max_right = max(max_right,height[j]);
			}
			for(int j = i; j >= 0; j--)
				max_left = max(max_left,height[j]);
			ans += min(max_left,max_right) - height[i];
		}
		return ans; 
    }
};
```

### Complexity Analysis

- 时间复杂度：O($N^2$)
- 空间复杂度：$O(1)$

### Solution-2

> **方法 2：动态编程**
>
> ###### 直观想法
>
> 在暴力方法中，我们仅仅为了找到最大值每次都要向左和向右扫描一次。但是我们可以提前存储这个值。因此，可以通过动态编程解决。
>
> 这个概念可以见下图解释：
>
> ![trapping_rain_water.png](LeetcodeAli_42.assets/53ab7a66023039ed4dce42b709b4997d2ba0089077912d39a0b31d3572a55d0b-trapping_rain_water.png)
>
> ###### 算法
>
> 找到数组中从下标 i 到最左端最高的条形块高度$ \text{left_max}$
> 找到数组中从下标 i 到最右端最高的条形块高度 $\text{right_max}$
> 扫描数组$ height $并更新答案：
> 累加 $\min(\text{max_left}[i],\text{max_right}[i]) - \text{height}[i]$到 ans 上

### Code—2

```c++
class Solution {
public:
    int trap(vector<int>& height) {
        if (height.empty())
            return 0;
		int ans = 0;
		int size = height.size();
		vector<int> left_max(size), right_max(size);
		left_max[0] = height[0];
		right_max[size-1] = height[size-1];
		for (int i =1 ; i < size;i++){
			left_max[i] = max(height[i],left_max[i-1]);
		}
		for (int i = size - 2; i>=0 ; i --){
			right_max[i] = max(height[i],right_max[i+1]);
		}
		for(int i = 1 ; i < size - 1 ; i ++) {
			ans += min(left_max[i],right_max[i]) - height[i];
		}
		return ans;
    }
};
```

### Complexity Analysis

- 时间复杂度：O($N$)
- 空间复杂度：$O(N)$



### Solution-3

> **方法 3：栈的应用**
>
> ###### 直观想法
>
> 我们可以不用像方法 2 那样存储最大高度，而是用栈来跟踪可能储水的最长的条形块。使用栈就可以在一次遍历内完成计算。
>
> 我们在遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块界定。如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，因此我们可以弹出栈顶元素并且累加答案到 \text{ans}ans 。
>
> ###### 算法
>
> - 使用栈来存储条形块的索引下标。
>
> - 遍历数组：
>
>   - 当栈非空且 $\text{height}[current]>\text{height}[st.top()$
>
>     - 意味着栈中元素可以被弹出。弹出栈顶元素 $\text{top}$
>
>     - 计算当前元素和栈顶元素的距离，准备进行填充操作
>       $\text{distance} = \text{current} - \text{st.top}() - 1$
>
>     - 找出界定高度
>       $\text{bounded\_height} = \min(\text{height[current]}, \text{height[st.top()]}) - \text{height[top]}$
>
>     - 往答案中累加积水量
>
>       $\text{ans} \mathrel{+}= \text{distance} \times \text{bounded\_height}$
>
>   - 将当前索引下标入栈
>
>   - 将 $\text{current}$ 移动到下个位置

```c++
int trap(vector<int>& height)
{
    int ans = 0, current = 0;
    stack<int> st;
    while (current < height.size()) {
        while (!st.empty() && height[current] > height[st.top()]) {
            int top = st.top();
            st.pop();
            if (st.empty())
                break;
            int distance = current - st.top() - 1;
            int bounded_height = min(height[current], height[st.top()]) - height[top];
            ans += distance * bounded_height;
        }
        st.push(current++);
    }
    return ans;
}
```

### Complexity Analysis

- 时间复杂度：O($N$)
- 空间复杂度：$O(N)$