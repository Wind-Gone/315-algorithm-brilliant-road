# LeetCode 143 重排链表

## Problem

给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

## Samples

示例 1:

```
给定链表 1->2->3->4, 重新排列为 1->4->2->3.
```


示例 2:

```
给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
```

## Tag

链表

## Solution

本题重排链表的方法是要先找到链表的中间节点，将右半部分反转，然后再合并。

思路很好想，反转链表可以见[LeetCode206](https://leetcode-cn.com/problems/reverse-linked-list/)

## Code

```java
class Solution {
    public void reorderList(ListNode head) {
        if (head == null)
            return;
        ListNode mid = findMid(head);
        ListNode node1 = head;
        ListNode node2 = mid.next;
        mid.next = null;

        node2 = reverseList(node2);

        merge(node1, node2);
    }
    // 返回链表的中间节点
    public ListNode findMid(ListNode head) {
        if (head == null)
            return null;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    // 反转链表
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
    // 合并两个链表
    public void merge(ListNode head1, ListNode head2) {
        ListNode node1 = null;
        ListNode node2 = null;
        while (head1 != null && head2 != null) {
            node1 = head1.next;
            node2 = head2.next;

            head1.next = head2;
            head2.next = node1;

            head1 = node1;
            head2 = node2;
        }
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(1)