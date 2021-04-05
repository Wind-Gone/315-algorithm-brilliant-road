# LeetCode 113 路径总和Ⅱ

## Problem

给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。

## Samples

示例 1：

<img src="https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg" alt="img" style="zoom:50%;" /> 

```
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]
```

示例 2：

<img src="https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg" alt="img" style="zoom:50%;" /> 

```
输入：root = [1,2,3], targetSum = 5
输出：[]
```


示例 3：

```
输入：root = [1,2], targetSum = 0
输出：[]
```

## Tag

DFS

## Solution

采用深度优先搜索的方式，枚举每一条从根节点到叶子节点的路径。当我们遍历到叶子节点，且此时路径和恰为目标和时，我们就找到了一条满足条件的路径。

## Code

```java
package 路径总和Ⅱ;

/*
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
叶子节点 是指没有子节点的节点。
 */

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Tiantian
 * @Date 2021/4/3 16:44:34
 */
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
class Solution {
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Deque<Integer> path = new LinkedList<Integer>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum);
        return ret;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            ret.add(new LinkedList<Integer>(path));
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
        path.pollLast();
    }
}

public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        TreeNode l0 = new TreeNode(5);
        TreeNode l1 = new TreeNode(4);
        TreeNode l2 = new TreeNode(8);
        TreeNode l3 = new TreeNode(11);
        TreeNode l4 = new TreeNode(13);
        TreeNode l5 = new TreeNode(4);
        TreeNode l6 = new TreeNode(7);
        TreeNode l7 = new TreeNode(2);
        TreeNode l8 = new TreeNode(5);
        TreeNode l9 = new TreeNode(1);
        l0.left = l1;
        l0.right = l2;
        l1.left = l3;
        l3.left = l6;
        l3.right = l7;
        l2.left = l4;
        l2.right = l5;
        l5.left = l8;
        l5.right = l9;
        List<List<Integer>> b = a.pathSum(l0, 22);
        for (List<Integer> b1 : b) {
            System.out.println(b1);
        }
    }

}
```

## Complexity

时间复杂度：O(n²)

空间复杂度：O(n)