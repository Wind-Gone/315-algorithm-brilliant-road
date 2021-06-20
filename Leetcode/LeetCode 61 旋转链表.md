# LeetCode 61 旋转链表

## Problem

给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。

## Samples

示例 1：

<img src="https://assets.leetcode.com/uploads/2020/11/13/rotate1.jpg" alt="img" style="zoom: 33%;" /> 

```
输入：head = [1,2,3,4,5], k = 2
输出：[4,5,1,2,3]
```

示例 2：

<img src="https://assets.leetcode.com/uploads/2020/11/13/roate2.jpg" alt="img" style="zoom:33%;" /> 

```
输入：head = [0,1,2], k = 4
输出：[2,0,1]
```


提示：

- 链表中节点的数目在范围 [0, 500] 内
- -100 <= Node.val <= 100
- 0 <= k <= 2 * 10^9

## Tag

链表、循环链表

## Solution

先遍历求得链表总长度n，同时将链表首尾相连；再找到原链表的倒数第k+1个节点，该节点的next就是新链表的头结点。

## Code

```java
package 旋转链表;

import java.util.Arrays;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {  // 不需要旋转
            return head;
        }
        int n = 1; // 用来统计链表总结点数
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        // 先将首尾相连
        temp.next = head;
        // 然后只需要找到倒数第k+1个节点
        for (int i = 0; i < n - k; i++) {
            temp = temp.next;
        }
        ListNode ret = temp.next;
        temp.next = null;
        return ret;
    }
    public void outputList(ListNode head) {
        ListNode tmp = head;
        while (true) {
            System.out.print(tmp.val + " ");
            if (tmp.next == null)
                break;
            else
                tmp = tmp.next;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(5);
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        ListNode res = a.rotateRight(head, 2);
        a.outputList(res);
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(1)