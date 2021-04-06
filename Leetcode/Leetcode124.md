# LeetCode 124 二叉树中的最大路径和

## Problem

路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。

## Samples

示例 1：

```
输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
```

示例 2：

```
输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
```

## Tag

递归，二叉树

## Solution

根据题解的思路是这样的。要求二叉树的最大路径和，首先保证每个节点的值是正数，将一些均为正数的子树进行比较其中最大的是满足题目的和，也就是说最多是由3个节点加和组成的值。首先按照题解的思路，明确一个节点最大贡献值的概念，最大贡献值等于节点值与其子节点中的最大贡献值之和，简单说，叶子节点的最大贡献值等于其本身，非叶子节点等于其本身加其左右子节点的和。那么很容易想到递归的思路，就是分别对左子树和右子树去递归出最大的贡献值，那么要求的最大路径和就是左右两个最大贡献值加上该节点值。详见下面的代码。

## Code

```java
package 二叉树中的最大路径和;
/*
路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
路径和 是路径中各节点值的总和。
给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */

/**
 * @Author Tiantian
 * @Date 2021/4/6 19:58
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
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值,最大贡献值等于节点值与其子节点中的最大贡献值之和
        // （对于叶节点而言，最大贡献值等于节点值）
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        TreeNode root = new TreeNode(-10);
        TreeNode l1 = new TreeNode(9);
        TreeNode l2 = new TreeNode(20);
        TreeNode l3 = new TreeNode(15);
        TreeNode l4 = new TreeNode(7);
        root.left = l1;
        root.right = l2;
        l2.left = l3;
        l3.right = l4;
        System.out.println(a.maxPathSum(root));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(n)