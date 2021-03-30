# LeetCode 199 二叉树的右视图

## Problem

给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

## Sample

```
输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
```

## Tag

DFS，二叉树

## Solution

首先这么想，题目想要求的时二叉树的右视图，需要保证每层访问的都是最右边的节点。这恰好与前序遍历的顺序相反，前序遍历的顺序是根节点 --> 左子树 --> 右子树。那么由DFS，先遍历完右子树，再遍历左子树，就可以保证每层的右子树会被最先访问到。

## Code

#### Solution

```java
package 二叉树的右视图;

import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
// 采用dfs
public class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null) return;
        if (level == res.size()) {
            res.add(node.val);
        }
        if (node.right != null)
            dfs(node.right, level + 1); //dfs右子树
        if (node.left != null)
            dfs(node.left, level + 1); //dfs左子树
    }
}
```

#### Main

```java
package 二叉树的右视图;

public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        TreeNode l0 = new TreeNode(1);
        TreeNode l1 = new TreeNode(2);
        TreeNode l2 = new TreeNode(3);
        TreeNode l3 = new TreeNode(5);
        TreeNode l4 = new TreeNode(4);
        l0.left = l1;
        l0.right = l2;
        l1.right = l3;
        l2.right = l4;
        System.out.println(a.rightSideView(l0));
    }
}
```

## Complexity

时间复杂度：$O(n)$

空间复杂度：$O(n)$
