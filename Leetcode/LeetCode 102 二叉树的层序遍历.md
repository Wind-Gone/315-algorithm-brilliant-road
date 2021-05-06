# LeetCode 102 二叉树的层序遍历

## Problem

给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

## Samples

示例：

```
二叉树：[3,9,20,null,null,15,7],

	3
   / \
  9  20
    /  \
   15   7
返回其层序遍历结果：

[
  [3],
  [9,20],
  [15,7]
]
```

## Tag

BFS

## Solution

BFS，使用队列缓存下一层节点，递归遍历。

## Code

```java
package 二叉树的层序遍历;

import java.util.*;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            while(count > 0){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
                count--;
            }
            res.add(list);
        }
        return res;
    }
}
```

## Complexity

时间复杂度：O(n)（进出队各一次）

空间复杂度：O(n)