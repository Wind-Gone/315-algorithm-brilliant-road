# LeetCode 98 验证二叉搜索树

## Problem

给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。

## Samples

示例 1:

```
输入:
    2
   / \
  1   3
输出: true
```


示例 2:

```
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```

## Tag

二叉搜索树，递归

## Solution

我觉得本题的思路还是比较明朗的，写法也能比较清楚。首先题目要求小于当前节点的树，我们只要找到左子树的最小节点，然后递归判断右子树的部分。

## Code

```java
package 验证二叉搜索树;
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
    long last = -Long.MAX_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        if (isValidBST(root.left)) {
            if (last < root.val) {
                last = root.val;
                return isValidBST(root.right);
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        TreeNode root = new TreeNode(5);
        TreeNode l1 = new TreeNode(1);
        TreeNode l2 = new TreeNode(4);
        TreeNode l3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(6);
        root.left = l1;
        root.right = l2;
        l2.left = l3;
        l2.right = l4;
        System.out.println(a.isValidBST(root));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(n)