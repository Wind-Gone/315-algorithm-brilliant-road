# LeetCode 092 反转链表Ⅱ

## Problem

给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。

## Samples

示例 1：

<img src="https://assets.leetcode.com/uploads/2021/02/19/rev2ex2.jpg" alt="img" style="zoom:50%;" /> 

```
输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
```


示例 2：

```
输入：head = [5], left = 1, right = 1
输出：[5]
```

## Tag

链表

## Solution

我的思路是这样的，首先把从left到right的链表部分反转。然后将left.pre指向right，以及right.next指向left。

## Code

```java
package 反转链表Ⅱ;
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Main {
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;
        pre.next = null;
        rightNode.next = null;

        reverseLinkedList(leftNode);

        // 接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    private static void reverseLinkedList(ListNode head) {  //递归方式反转链表 即T206
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    public static void outputList(ListNode head) {
        ListNode tmp = head;
        while (true) {
            System.out.print(tmp.val + " ");
            if (tmp.next == null)
                break;
            else
                tmp = tmp.next;
        }
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(5);
        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        int left, right;
        ListNode n = reverseBetween(head, 2, 4);
        outputList(n);  //1 4 3 2 5
        System.out.println("");
    }
}
```

## Complexity

时间复杂度O(n)

空间复杂度O(1)

