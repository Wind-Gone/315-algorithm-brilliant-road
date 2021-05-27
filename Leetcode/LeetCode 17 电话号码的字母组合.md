# LeetCode 17 电话号码的字母组合

## Problem

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/original_images/17_telephone_keypad.png" alt="img" style="zoom:50%;" /> 

## Samples

示例 1：

```
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
```

示例 2：

```
输入：digits = ""
输出：[]
```

示例 3：

```
输入：digits = "2"
输出：["a","b","c"]
```


提示：

- 0 <= digits.length <= 4
- digits[i] 是范围 ['2', '9'] 的一个数字。

## Tag

回溯

## Solution

用哈希表存入电话号码的字母内容，每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的字母，并将其中的一个字母插入到已有的字母排列后面，然后继续处理电话号码的后一位数字，直到处理完电话号码中的所有数字，即得到一个完整的字母排列。然后进行回退操作，遍历其余的字母排列。

## Code

```java
package 电话号码的字母组合;

import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return list;
        Map<Character, String> map = new HashMap<Character, String>() {{    // 哈希表存数据
            put('1', "!@#");
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(list, map, digits, 0, new StringBuffer());
        return list;
    }
    public void backtrack(List<String> list, Map<Character, String> map, String digits, int index, StringBuffer stringBuffer) {
        if (index == digits.length()) { // 用index记录每次遍历到字符串的位置
            list.add(stringBuffer.toString());
        }
        else {
            char digit = digits.charAt(index);
            String letters = map.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {    // 调用下一层递归,StringBuffer提高效率
                stringBuffer.append(letters.charAt(i));
                backtrack(list, map, digits, index + 1, stringBuffer);
                stringBuffer.deleteCharAt(index);
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        System.out.println(a.letterCombinations("23"));
    }
}
```

## Complexity

时间复杂度：$O(3^m×4^n)$

空间复杂度：$O(m+n)$

