# 剑指Offer 38 字符串的排序

## Problem

输入一个字符串，打印出该字符串中字符的所有排列。

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

## Samples

示例:

```
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
```


限制：

- 1 <= s 的长度 <= 8


## Tag

回溯

## Solution

采用回溯算法，每次都从字符串头开始查找，使用vis数组对访问过元素进行标记，跳过已经访问过的和单词相同的。当下标和字符串长度相等时，输出字符串。

## Code

```java
package 字符串的排序;

import java.util.*;

class Solution {
    List<String> rec;
    boolean[] vis;

    public String[] permutation(String s) {
        int n = s.length();
        rec = new ArrayList<String>();
        vis = new boolean[n];
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuffer perm = new StringBuffer();
        backtrack(arr, 0, n, perm);
        int size = rec.size();
        String[] recArr = new String[size];
        for (int i = 0; i < size; i++) {
            recArr[i] = rec.get(i);
        }
        return recArr;
    }

    public void backtrack(char[] arr, int i, int n, StringBuffer perm) {
        if (i == n) {
            rec.add(perm.toString());
            return;
        }
        for (int j = 0; j < n; j++) {
            if (vis[j] || (j > 0 && !vis[j - 1] && arr[j - 1] == arr[j])) { // 字母相同时，等效，剪枝
                continue;
            }
            vis[j] = true;
            perm.append(arr[j]);
            backtrack(arr, i + 1, n, perm); // 字符串遍历回溯，用vis标记避免重复
            perm.deleteCharAt(perm.length() - 1);
            vis[j] = false;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s = "abc";
        System.out.println(Arrays.deepToString(a.permutation(s)));
    }
}
```

## Complexity

时间复杂度：O(n × n!)

空间复杂度：O(n)
