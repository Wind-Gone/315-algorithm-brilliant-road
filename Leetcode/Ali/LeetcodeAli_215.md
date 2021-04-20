---
阿里巴巴题库 215. 数组中的第K个最大元素
---

## Problem

在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

> 示例 1:
>
> 输入: [3,2,1,5,6,4] 和 k = 2
> 输出: 5
> 示例 2:
>
> 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
> 输出: 4
> 说明:
>
> 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
>



## Tag

- 大根堆
- 快排



### Solution-1

> 方法一：暴力解法
> 题目要求我们找到“数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素” ，
>
> 语义是从右边往左边数第 k 个元素（从 1 开始），那么从左向右数是第几个呢，我们列出几个找找规律就好了。
>
> 一共 6 个元素，找第 2 大，索引是 4；
> 一共 6 个元素，找第 4 大，索引是 2。
> 因此，升序排序以后，目标元素的索引是 len - k。这是最简单的思路，如果只答这个方法，面试官可能并不会满意，但是在我们平时的开发工作中，还是不能忽视这种思路简单的方法，理由如下：
>
> 最简单同时也一定是最容易编码的，编码成功的几率最高，可以用这个最简单思路编码的结果和其它思路编码的结果进行比对，验证高级算法的正确性；
>
> 在数据规模小、对时间复杂度、空间复杂度要求不高的时候，简单问题简单做；
>
> 思路简单的算法考虑清楚了，有些时候能为实现高级算法铺路，这道题也是如此
>

### Code—1

```c++
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
		int size  = nums.size();
		sort(nums.begin(), nums.end());
		return nums[size-k];
    }
};
```

### Complexity Analysis

- 时间复杂度：O($NlogN$)，这里 N是数组的长度，算法的性能消耗主要在排序
- 空间复杂度：$O(1)$

### Solution-2

> 堆排：https://blog.csdn.net/u010452388/article/details/81283998
>
> **方法二：基于堆排序的选择方法**
> 思路和算法
>
> 我们也可以使用堆排序来解决这个问题——建立一个大根堆，做 k - 1 次删除操作后堆顶元素就是我们要找的答案。在很多语言中，都有优先队列或者堆的的容器可以直接使用，但是在面试中，面试官更倾向于让更面试者自己实现一个堆。所以建议读者掌握这里大根堆的实现方法，在这道题中尤其要搞懂「建堆」、「调整」和「删除」的过程。
>

### Code—2

```c++
class Solution {
public:
	void heapInsert(vector<int>& nums){
		for (int i = 0 ; i < nums.size(); i ++){
			int curIndex = i;
			int fatherIndex = (curIndex - 1) / 2;
			while(nums[curIndex] > nums[fatherIndex]){
				swap(nums[fatherIndex],nums[curIndex]);
				curIndex = fatherIndex;
				fatherIndex = (curIndex - 1) / 2;
			}
		}
	}
	
	
	void maxHeapify(vector<int>& nums ,int index, int size){
		int left = index * 2 +1;
		int right = index * 2 + 2;
		int largestVal = index;
		if (left < size && nums[left] > nums[largestVal])
			largestVal = left;
		if (right < size && nums[right] > nums[largestVal])
			largestVal = right;
		if (largestVal != index){
			swap(nums[largestVal],nums[index]);
			maxHeapify(nums,largestVal,size);
		}
	}
	
    int findKthLargest(vector<int>& nums, int k) {
		int size  = nums.size();
		heapInsert(nums);
		for(int i = nums.size() - 1 ; i >= nums.size() - (k-1); i--){
			swap(nums[0],nums[i]);
			size -- ;
			maxHeapify(nums,0,size);
		}
		return nums[0];
    }
};
```

```c++
// 直接调库: 优先级队列
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
		int size  = nums.size();
		priority_queue<int,vector<int>,greater<int>> myQueue;
		for(int i = 0 ; i < size; i++){
			if (myQueue.size() < k)
				myQueue.push(nums[i]);
			else if (myQueue.top() < nums[i]){
				myQueue.pop();
				myQueue.push(nums[i]);
			}
		}
		return myQueue.top();
    }
};
```

### Complexity Analysis

- 时间复杂度：O($NlogN$)，这里 N是数组的长度，算法的性能消耗主要在排序
- 空间复杂度：$O(logN)$



