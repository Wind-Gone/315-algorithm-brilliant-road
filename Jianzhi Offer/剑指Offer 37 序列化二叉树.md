# 剑指Offer 37 序列化二叉树

请实现两个函数，分别用来序列化和反序列化二叉树。

你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

**提示：**输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 [LeetCode 序列化二叉树的格式](https://leetcode-cn.com/faq/#binary-tree)。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

## Samples

示例：

<img src="https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg" alt="img" style="zoom:50%;" /> 

```
输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]
```

## Tag

BFS

## Solution

因为题目中示例给的树的level，因此选择了层序序列化二叉树，使用广度优先遍历BFS。当然前中后序都可以序列化二叉树，只要加入适当的字符表示null，以及加入符号进行分割就可以正确的序列化。

## Code

```java
package 序列化二叉树;

import java.util.LinkedList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    public void serialize(TreeNode root, StringBuilder sb){
        if (root == null) {
            sb.append("null").append(",");
            return;
        }
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0)  
            return null;
        String[] s = data.split(",");
        LinkedList<String> nodes = new LinkedList<>();
        for (String str : s) {
            nodes.addLast(str);
        }
        return deserialize(nodes);
    }
    public TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) 
            return null;
        String first = nodes.removeFirst();
        if (first.equals("null")) 
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(n)