---
阿里巴巴题库 23. 合并K个升序链表
---

## Problem

> 给你一个链表数组，每个链表都已经按升序排列。
>
> 请你将所有链表合并到一个升序链表中，返回合并后的链表。



> 示例 1：
>
> 输入：lists = [[1,4,5],[1,3,4],[2,6]]
> 输出：[1,1,2,3,4,4,5,6]
> 解释：链表数组如下：
> [
>   1->4->5,
>   1->3->4,
>   2->6
> ]
> 将它们合并到一个有序链表中得到。
> 1->1->2->3->4->4->5->6
> 示例 2：
>
> 输入：lists = []
> 输出：[]
> 示例 3：
>
> 输入：lists = [[]]
> 输出：[]
>
>
> 提示：
>
> k == lists.length
> 0 <= k <= 10^4
> 0 <= lists[i].length <= 500
> -10^4 <= lists[i][j] <= 10^4
> lists[i] 按 升序 排列
> lists[i].length 的总和不超过 10^4

## Tag

- 链表合并
- 分治
- 优先队列



## Solution-1

> **优先队列**
>
> - 对于优先队列有这几种做法：
>
>   - **方法1：** 建立优先队列（最大堆或者最小堆均可），全部元素接连入队；最后再不断弹出，构建链表。这也是一种想法，不过这样子效率就有些低下了。
>
>   空间复杂度O(n)，时间复杂度O(2*log(n!)) ；n是所有节点个数
>
>   这里我不知道计算是否正确，如果不对还望指出~~
>
>   - **方法2：** 依然建立优先队列，不过不需要全部元素一次性入队；只需要让链表头元素入队即可，弹出该元素后，该链表往后移。

### Code—1

```c++
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
	struct cmp{
		bool operator()(ListNode *a, ListNode *b){
			return a->val > b ->val;
		}
	};
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        priority_queue<ListNode*,vector<ListNode*>, cmp> myQueue;
        for(ListNode* element : lists){
        	if (element)
        		myQueue.push(element);
		}
		ListNode dummy (-1);
		ListNode* p = &dummy;
        // 开始出队
        while(!myQueue.empty()){
            ListNode* top = myQueue.top(); 
			myQueue.pop();
            p ->next = top; 
			p = top;
            if(top->next) 
				myQueue.push(top->next);
        }
        return dummy.next; 
    }
};
```

### Complexity Analysis

- 时间复杂度：$O(k)$
- 空间复杂度：$O(nlog(k)$，n是所有节点个数，k是链表数



## Solution-2

> -  **两两合并**
>
>   简单的说，合并两个有序链表可以使用迭代或者递归来完成，思路是一样的；这里就不多做介绍了。
>
>   对于合并K个链表，不难想到我们可以从头开始两两合并。
>

### Code—2

```c++
class Solution {
public:
    // 合并两个有序链表
    ListNode* merge(ListNode* p1, ListNode* p2){
        if(!p1) return p2;
        if(!p2) return p1;
        if(p1->val <= p2->val){
            p1->next = merge(p1->next, p2);
            return p1;
        }else{
            p2->next = merge(p1, p2->next);
            return p2;
        }
    }

     ListNode* mergeKLists(vector<ListNode*>& lists) {
        if(lists.size() == 0) return nullptr;
        ListNode* head = lists[0];
        for(int i = 1; i<lists.size(); ++i){
            if(lists[i]) head = merge(head, lists[i]);
        }
        return head;  
    }
};


```

### Complexity Analysis

- 时间复杂度：$O(kn)$
- 空间复杂度：$O(1)$，n是所有节点个数，k是链表数

## Solution-3

> **分治合并**
> 还是采用上面两两合并的法子，不过这里添加分治的思想，请看下图，这就是分治合并的过程
>
> - k个链表两两配对，进行第一轮合并，结束后k个链表被合并成k/2个链表
>
> - k/2个链表依然两两配对，进行第二轮合并，结束后k/2个链表被合并成k/4个链表
>
> - 重复上述过程，进行log(k)次合并，完成总体合并工作
>
>   ![分治合并](LeetcodeAli_23.assets/72ec698ca75a416fd5d8abb76a43549e26e78fc7acf5c24889ebe7ac7de6b7a8)
>
> 

### Code—3

```c++
class Solution {
public:
    // 合并两个有序链表
    ListNode* merge(ListNode* p1, ListNode* p2){
        if(!p1) return p2;
        if(!p2) return p1;
        if(p1->val <= p2->val){
            p1->next = merge(p1->next, p2);
            return p1;
        }else{
            p2->next = merge(p1, p2->next);
            return p2;
        }
    }

    ListNode* merge(vector<ListNode*>& lists, int start, int end){
        if(start == end) return lists[start];
        int mid = (start + end) / 2;
        ListNode* l1 = merge(lists, start, mid);
        ListNode* l2 = merge(lists, mid+1, end);
        return merge(l1, l2);
    }

    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if(lists.size() == 0) return nullptr;
        return merge(lists, 0, lists.size()-1);
    }
};
```

### Complexity Analysis

- 时间复杂度：$O(logk * n)$
- 空间复杂度：$O(1)$，n是所有节点个数，k是链表数

