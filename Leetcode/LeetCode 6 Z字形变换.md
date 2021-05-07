# LeetCode 6 Z字形变换

## Problem

将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

```
P   A   H   N
A P L S I I G
Y   I   R
```

之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

请你实现这个将字符串进行指定行数变换的函数：

```
string convert(string s, int numRows);
```

## Samples


示例 1：

```
输入：s = "PAYPALISHIRING", numRows = 3
输出："PAHNAPLSIIGYIR"
```

示例 2：

```
输入：s = "PAYPALISHIRING", numRows = 4
输出："PINALSIGYAHRPI"
解释：
P     I    N
A   L S  I G
Y A   H R
P     I
```

示例 3：

```
输入：s = "A", numRows = 1
输出："A"
```


提示：

- 1 <= s.length <= 1000
- s 由英文字母（小写和大写）、',' 和 '.' 组成
- 1 <= numRows <= 1000

## Tag

模拟、找规律

## Solution

从左向右迭代字符串，将字符串的每个字符放入正确的行，注意方向的改变在移动到最上方和最下方的时候。

也可以找规律模拟，规律如下：

第1行： `0, 2*(n-1),4*(n-1),4*(n - 1),...,m*2*(n-1)`

第2行： `1, 2*(n-1)-1,2*(n-1)+1,4*(n-1)-1,4*(n-1)+1,...,m*2*(n-1)+1`

第3行： `2, 2*(n-1)-2,2*(n-1)+2,4*(n-1)-2,4*(n-1)+2,...,m*2*(n-1)+2`

. . .

第i+1行：`i, 2*(n-1)-i,2*(n-1)+i,4*(n-1)-i,4*(n-1)+i,...,m*2*(n-1)+i`

. . .

第n行：`n-1, 3*(n-1),5*(n-1),7*(n - 1),...,(m*2+1)*(n-1)`

## Code1

```java
package Z字形变换;

import java.util.*;

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean flag = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1)
                flag = !flag;
            curRow += flag ? 1 : -1;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows)
            ret.append(row);
        return ret.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s = "PAYPALISHIRING";
        System.out.println(a.convert(s, 4));
    }
}
```

## Code2

```java
package Z字形变换;

class Solution {
    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        char[] array = new char[s.length()];
        int index = 0;
        int n = (numRows - 1) * 2;
        for (int i = 0; i < numRows; i++) {
            int x = i;
            while (x < s.length()) {
                array[index] = s.charAt(x);
                index++;
                if (i == 0) {
                    x += n;
                    continue;
                }
                if (i == numRows - 1) {
                    x += n;
                    continue;
                }
                if ((x + i) % n == 0) {
                    x = x + 2 * i;
                    continue;
                }
                x = x + n - 2 * i;
            }
        }
        return String.valueOf(array);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s = "PAYPALISHIRING";
        System.out.println(a.convert(s, 4));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(n)