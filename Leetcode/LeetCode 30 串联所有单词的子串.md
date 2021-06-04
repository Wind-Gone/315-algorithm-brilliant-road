# LeetCode 30 串联所有单词的子串

## Problem

给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。

注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。

## Samples

示例 1：

```
输入：s = "barfoothefoobarman", words = ["foo","bar"]
输出：[0,9]
解释：
从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。
```

示例 2：

```
输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
输出：[]
```

示例 3：

```
输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
输出：[6,9,12]
```


提示：

- 1 <= s.length <= 10^4
- s 由小写英文字母组成
- 1 <= words.length <= 5000
- 1 <= words[i].length <= 30
- words[i] 由小写英文字母组成

## Tag

哈希表、滑动窗口

## Solution

由于单词长度是固定的，因此可以使用哈希表计算出截取字符串的单词个数是否和words里相等。一个哈希表是words，一个哈希表是截取的字符串，然后比较两个哈希表是否相等。

## Code

```java
package 串联所有单词的子串;

import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0)
            return res;
        HashMap<String, Integer> map = new HashMap<>();
        // 记录words中的单词数
        int one_word = words[0].length();
        int word_num = words.length;
        int all_len = one_word * word_num;
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // 记录窗口的单词数
        for (int i = 0; i < s.length() - all_len + 1; i++) {
            String tmp = s.substring(i, i + all_len);
            HashMap<String, Integer> tmp_map = new HashMap<>();
            for (int j = 0; j < all_len; j += one_word) {
                String w = tmp.substring(j, j + one_word);
                tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
            }
            if (map.equals(tmp_map))
                res.add(i);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        System.out.println(a.findSubstring(s, words));
    }
}
```

## Complexity

时间复杂度：O(n²)

空间复杂度：O(n)