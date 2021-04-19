---
阿里巴巴题库 19. 删除链表的倒数第 N 个结点
---

## Problem

> 给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。
>
> **进阶：**你能尝试使用一趟扫描实现吗？



> 示例 1：
>
> ![img](LeetcodeAli_19.assets/remove_ex1.jpg)
>
>
> 输入：head = [1,2,3,4,5], n = 2
> 输出：[1,2,3,5]
> 示例 2：
>
> 输入：head = [1], n = 1
> 输出：[]
> 示例 3：
>
> 输入：head = [1,2], n = 1
> 输出：[1]
>
>
> 提示：
>
> 链表中结点的数目为 sz
> 1 <= sz <= 30
> 0 <= Node.val <= 100
> 1 <= n <= sz

## Tag

- 链表处理
- 快慢指针



## Solution

> **快慢指针**
>
> - 用一个与慢指针间隔n的快指针进行一趟链表遍历，如果快指针到了NULL，则慢指针的下一个节点就是需要删除的节点（即倒数第n个值）
> - 注意用一个哑节点指向头节点，这样可以避免对头指针的额外判断

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
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ListNode* dummyNode = new ListNode(0);
        dummyNode->next = head;
        ListNode *fastPoint = dummyNode, *slowPoint = dummyNode;
        for(int i = 0 ; i <= n; i++){
            fastPoint = fastPoint->next;
        }
        while(fastPoint){
            fastPoint = fastPoint ->next;
            slowPoint = slowPoint -> next;
        }
        ListNode *deleteNode = slowPoint->next;
        slowPoint -> next = deleteNode->next;
        ListNode* retNode = dummyNode->next;
        delete dummyNode;
        return retNode;
    }
};
```

### Complexity Analysis

- 时间复杂度：
- 空间复杂度：

