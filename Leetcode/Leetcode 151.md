# LeetCode 151 翻转字符串里的单词

## Problem

给定一个字符串，逐个翻转字符串中的每个单词。

说明：

无空格字符构成一个单词 。
输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

## Samples

示例 1：

```
输入："the sky is blue"
输出："blue is sky the"
```


示例 2：

```
输入："  hello world!  "
输出："world! hello"
解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
```


示例 3：

```
输入："a good   example"
输出："example good a"
解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
```


示例 4：

```
输入：s = "  Bob    Loves  Alice   "
输出："Alice Loves Bob"
```


示例 5：

```
输入：s = "Alice does not even like bob"
输出："bob like even not does Alice"
```

## Tag

字符串

## Solution

题型是常见的题型， 就是多加了些约束。首先要会单词的翻转。首先把整个句子看作是一整个字符串翻转，然后对空格特殊处理。首先反转字符串是将左右两部分依次比较交换顺序。主要是处理空格的问题，使用一个指针，首先找到第一个不为空格的单词，然后移动指针到下一个存在空格的位置，并将指针值+1，如此反复到字符串末尾。

## Code

```java
package 翻转字符串里的单词;

class Solution {
    public String reverseWords(String s) {
        char[] array = s.toCharArray();
        int len = array.length;
        //将整个字符串翻转
        reserve(array, 0, len - 1);

        int left = 0;
        int right = 0;

        int index = 0;
        while (index < len) {
            while (index < len && array[index] == ' ') {
                index++;
            }
            //第一个不为空格的字符
            left = index;

            while (index < len && array[index] != ' ') {
                index++;
            }
            //右端点
            right = index - 1;
            reserve(array, left, right);
        }

        return filterBlank(array, len);
    }
    public void reserve(char[] arr, int left, int right) {
        char temp;
        while (left < right) {
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
    public String filterBlank(char[] arr, int len) {
        int index = 0;
        int point = 0;
        int count = 0;

        while (point < len) {
            while (point < len && arr[point] == ' ') {
                //找到第一个不为空格的字符
                point++;
            }
            if (count > 0 && point < len) {
                arr[index++] = ' ';
            }

            while (point < len && arr[point] != ' ') {
                //处理完一个单词
                arr[index++] = arr[point++];
            }
            count++;
        }
        return new String(arr).substring(0, index);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s = " the sky is blue ";
        System.out.println(a.reverseWords(s));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(n)