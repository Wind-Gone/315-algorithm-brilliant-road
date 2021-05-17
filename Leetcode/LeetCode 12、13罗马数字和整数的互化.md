# LeetCode 罗马数字和整数的互化

## Problem

### IntToRoman

罗马数字包含以下七种字符： `I`， `V`， `X`， `L`，`C`，`D` 和 `M`。

```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

例如， 罗马数字 2 写做 `II` ，即为两个并列的 1。12 写做 `XII` ，即为 `X` + `II` 。 27 写做  `XXVII`, 即为 `XX` + `V` + `II` 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 `IIII`，而是 `IV`。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 `IX`。这个特殊的规则只适用于以下六种情况：

- `I` 可以放在 `V` (5) 和 `X` (10) 的左边，来表示 4 和 9。
- `X` 可以放在 `L` (50) 和 `C` (100) 的左边，来表示 40 和 90。 
- `C` 可以放在 `D` (500) 和 `M` (1000) 的左边，来表示 400 和 900。

给你一个整数，将其转为罗马数字。

## Samples

示例 1:

```
输入: num = 3
输出: "III"
```

示例 2:

```
输入: num = 4
输出: "IV"
```

示例 3:

```
输入: num = 9
输出: "IX"
```

示例 4:

```
输入: num = 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.
```

示例 5:

```
输入: num = 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```

## Tag

哈希表、数组

## Solution

用哈希表存储罗马数字的所有字符及特殊字符，然后对于列出的字符，针对给定的num从左至右注意判断，返回罗马数字。建立一个数值-符号对的列表valueSymbols，按数值从大到小排列。遍历valueSymbols中的每个数值-符号对，若当前数值value不超过num，则从num中不断减去value，直至num小于value，然后遍历下一个数值-符号对。若遍历中num为0则跳出循环。

## Code

```java
package 整数转罗马数字;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String intToRoman(int num) {
        int[] str = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        Map<Integer, String> map = new HashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 13 && num > 0; i++) {
            while (str[i] <= num) {
                buf.append(map.get(str[i]));
                num -= str[i];
            }
        }
        return buf.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        System.out.println(a.intToRoman(9));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(1)

采用数组存储复杂度为O(1)

## Problem

### RomanToInt

罗马数字包含以下七种字符: `I`， `V`， `X`， `L`，`C`，`D` 和 `M`。

```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

例如， 罗马数字 2 写做 `II` ，即为两个并列的 1。12 写做 `XII` ，即为 `X` + `II` 。 27 写做  `XXVII`, 即为 `XX` + `V` + `II` 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 `IIII`，而是 `IV`。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 `IX`。这个特殊的规则只适用于以下六种情况：

- `I` 可以放在 `V` (5) 和 `X` (10) 的左边，来表示 4 和 9。
- `X` 可以放在 `L` (50) 和 `C` (100) 的左边，来表示 40 和 90。 
- `C` 可以放在 `D` (500) 和 `M` (1000) 的左边，来表示 400 和 900。

给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

## Samples

示例 1:

```
输入: "III"
输出: 3
```

示例 2:

```
输入: "IV"
输出: 4
```

示例 3:

```
输入: "IX"
输出: 9
```

示例 4:

```
输入: "LVIII"
输出: 58
解释: L = 50, V= 5, III = 3.
```

示例 5:

```
输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```

## Tag

哈希表、数组

## Solution

思路和方法和前面类似，若存在小的数字在大的数字的左边的情况，根据规则需要减去小的数字。对于这种情况，我们也可以将每个字符视作一个单独的值，若一个数字右侧的数字比它大，则将该数字的符号取反。

## Code

```java
package 罗马数字转整数;

import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int value = symbolValues.get(s.charAt(i));
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        System.out.println(a.romanToInt("LVIII"));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(1)