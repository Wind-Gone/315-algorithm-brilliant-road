# LeetCode 23 合并K个升序链表

## Problem

给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

## Samples

示例 1：

```
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
```


示例 2：

```te
输入：lists = []
输出：[]
```


示例 3：

```
输入：lists = [[]]
输出：[]
```

## Tag

链表、分治、优先队列、小根堆

## Solution

我的想法很简单。因为之前做过合并两个链表，用递归写的，本题可以直接套用，然后两两合并就能合并出K个链表。

## Code

```java
package 合并K个升序链表;

/*
给你一个链表数组，每个链表都已经按升序排列。
请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */

/**
 * @Author Tiantian
 * @Date 2021/4/3 13:25:30
 */
//输入：lists = [[1,4,5],[1,3,4],[2,6]]
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));
        ListNode[] listNode = new ListNode[3];
        listNode[0] = l1;
        listNode[1] = l2;
        listNode[2] = l3;
        ListNode res = a.mergeKLists(listNode);
        a.outputList(res); //1 1 2 3 4 4 5 6
    }
}
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
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null)
            return null;
        ListNode res = null;
        for (ListNode list : lists)
            res = mergeTwoLists(res, list);
        return res;
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
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
```

## Complexity

时间复杂度：O(kn²)

空间复杂度：O(1)

## Supplement

本题的方法过于耗时，应该是递归的复杂度太高加上还是k次递归。所以执行和内存是非常差。这里参考题解的代码进行改进。

题解的思路是使用分治法进行优化。将k个链表配对并将同一对中的链表合并；第一轮合并以后，k个链表被合并成了k/2个链表，平均长度为2*n/k，然后是k/4个链表，k/8个链表……重复这一过程，直到我们得到了最终的有序链表。

### Reference Code

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

	public ListNode merge(ListNode[] lists, int l, int r) {
    	if (l == r) {
        	return lists[l];
    	}
    	if (l > r) {
        	return null;
    	}
    	int mid = (l + r) >> 1;
    	return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
	}

	public ListNode mergeTwoLists(ListNode a, ListNode b) {
    	if (a == null || b == null) {
        	return a != null ? a : b;
    	}
    	ListNode head = new ListNode(0);
    	ListNode tail = head, aPtr = a, bPtr = b;
    	while (aPtr != null && bPtr != null) {
        	if (aPtr.val < bPtr.val) {
            	tail.next = aPtr;
            	aPtr = aPtr.next;
        	} else {
            	tail.next = bPtr;
            	bPtr = bPtr.next;
        	}
        	tail = tail.next;
    	}
    	tail.next = (aPtr != null ? aPtr : bPtr);
    	return head.next;
	}
}
```

### Complexity

时间复杂度：O(knlogk)

空间复杂度：O(logk)

优先队列的方法我不大会用，我贴了题解的思路和代码参考：维护当前每个链表没有被合并的元素的最前面一个，则k个链表就最多有k个满足这样条件的元素，每次在这些元素里面选取val属性最小的元素合并到答案中。在选取最小元素的时候，我们可以用优先队列来优化这个过程。

### Code

```java
class Solution {
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

		Status(int val, ListNode ptr) {
			this.val = val;
			this.ptr = ptr;
		}

		public int compareTo(Status status2) {
			return this.val - status2.val;
		}
	}

	PriorityQueue<Status> queue = new PriorityQueue<Status>();

	public ListNode mergeKLists(ListNode[] lists) {
    	for (ListNode node: lists) {
        	if (node != null) {
            	queue.offer(new Status(node.val, node));
        	}
    	}
    	ListNode head = new ListNode(0);
    	ListNode tail = head;
    	while (!queue.isEmpty()) {
        	Status f = queue.poll();
        	tail.next = f.ptr;
        	tail = tail.next;
        	if (f.ptr.next != null) {
            	queue.offer(new Status(f.ptr.next.val, f.ptr.next));
        	}
    	}
    	return head.next;
	}
}
```

### Complexity

时间复杂度：O(knlogk)

空间复杂度：O(k)

### 小根堆解法

维护一个小根堆，首先将所有链表的第一个节点加入小根堆，每次从小根堆中弹出一个节点node加入到最终结果的链表里，并将node的next加入到小根堆中。

### Code

```java
class Solution {
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists==null || lists.length==0) {
			return null;
		}
		//创建一个堆，并设置元素的排序方式
		PriorityQueue<ListNode> queue = new PriorityQueue(new Comparator<ListNode>() {
			public int compare(ListNode o1, ListNode o2) {
				return (o1.val - o2.val);
			}
		});
		//遍历链表数组，然后将每个链表的每个节点都放入堆中
		for(int i=0;i<lists.length;i++) {
			while(lists[i] != null) {
				queue.add(lists[i]);
				lists[i] = lists[i].next;
			}
		}
		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		//从堆中不断取出元素，并将取出的元素串联起来
		while( !queue.isEmpty() ) {
			dummy.next = queue.poll();
			dummy = dummy.next;
		}
		dummy.next = null;
		return head.next;
	}
}
```