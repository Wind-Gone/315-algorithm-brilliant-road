# LeetCode 297 二叉树的序列化和反序列化

## Problem

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 [LeetCode 序列化二叉树的格式](https://support.leetcode-cn.com/hc/kb/category/1018267/)。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

## Samples

示例 1：

```
输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]
```


示例 2：

```
输入：root = []
输出：[]
```


示例 3：

```
输入：root = [1]
输出：[1]
```


示例 4：

```
输入：root = [1,2]
输出：[1,2]
```


提示：

- 树中结点数在范围 [0, 104] 内
- -1000 <= Node.val <= 1000

## Tag

dfs

## Solution

从根节点 1 开始，序列化字符串是 1,。然后我们跳到根节点 2 的左子树，序列化字符串变成 1,2,。现在从节点 2 开始，我们访问它的左节点 3（1,2,3,None,None,）和右节点 4 (1,2,3,None,None,4,None,None)。None,None, 是用来标记缺少左、右子节点，这就是我们在序列化期间保存树结构的方式。最后，我们回到根节点 1 并访问它的右子树，它恰好是叶节点 5。最后，序列化字符串是 1,2,3,None,None,4,None,None,5,None,None,。

即我们可以先序遍历这颗二叉树，遇到空子树的时候序列化成 None，否则继续递归序列化。那么我们如何反序列化呢？首先我们需要根据 , 把原先的序列分割开来得到先序遍历的元素列表，然后从左向右遍历这个序列：

- 如果当前的元素为 None，则当前为空树
- 否则先解析这棵树的左子树，再解析它的右子树

## Code

```java
package 二叉树的序列化和反序列化;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
// bfs——根-》叶-》根-》叶
//以先序遍历的方法编码
class Codec {

    // Encodes a tree to a single string.
    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,"; //节点的子树为空，输出None
        }
        else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    // Decodes your encoded data to tree.
    public TreeNode rdeserialize(List<String> l) {
        if (l.get(0).equals("None")) {
            l.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);
        return root;
    }

    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }
}

```

## Complexity

时间复杂度：O(n)

空间复杂度：O(n)