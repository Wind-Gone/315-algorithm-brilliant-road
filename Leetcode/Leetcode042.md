# LeetCode 042 接雨水

## Problem

给定 *n* 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

## Samples

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png) 

```
输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
```


示例 2：

```
输入：height = [4,2,0,3,2,5]
输出：9
```

## Tag

Stack 、……

## Code

```java
package 接雨水;

import java.util.Deque;
import java.util.LinkedList;

/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 */
public class Main {
    /*
    我们在遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈，
    意思是当前的条形块被栈中的前一个条形块界定。如果我们发现一个条形块长于栈顶，
    我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，
    因此我们可以弹出栈顶元素并且累加答案到 ans。
    算法
    使用栈来存储条形块的索引下标。
    遍历数组：
        当栈非空且height[current]>height[st.top()]
            意味着栈中元素可以被弹出。弹出栈顶元素top。
            计算当前元素和栈顶元素的距离，准备进行填充操作
                distance=current−st.top()−1
            找出界定高度
                bounded_height=min(height[current],height[st.top()])−height[top]
            往答案中累加积水量ans+=distance×bounded_height
        将当前索引下标入栈
        将current移动到下个位置
     */
    static int trap(int[] height) {
        int ans = 0, current = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty())
                    break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }
}
```

## Complexity

时间复杂度O(n)

空间复杂度O(n)
