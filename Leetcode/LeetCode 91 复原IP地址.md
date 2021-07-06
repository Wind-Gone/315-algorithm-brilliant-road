# LeetCode 91 复原IP地址

## Problem

给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。

有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。

## Samples

示例 1：

```
输入：s = "25525511135"
输出：["255.255.11.135","255.255.111.35"]
```

示例 2：

```
输入：s = "0000"
输出：["0.0.0.0"]
```

示例 3：

```
输入：s = "1111"
输出：["1.1.1.1"]
```

示例 4：

```
输入：s = "010010"
输出：["0.10.0.10","0.100.1.0"]
```

示例 5：

```
输入：s = "101023"
输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
```


提示：

- 0 <= s.length <= 3000
- s 仅由数字组成

## Tag

回溯、剪枝

## Solution

可以采用暴力算法，也是采用回溯。这里我们进行适当优化，首先把问题转换为在字符串中加.号,保证.号间字符串表示的数小于等于255。回溯条件就是当前加了几个.号和上一个.号的位置。

## Code

```java
package 复原IP地址;

import java.util.*;

class Solution {
    static final String SPLIT_DOT = ".";
    static final int SECTION_COUNT = 4;
    String[] parsedStack = new String[4];
    List<String> ipList = new ArrayList<String>();

    public List<String> restoreIpAddresses(String s) {
        int length = s.length();
        if (length < SECTION_COUNT || length > 12) {
            return ipList;
        }
        paseIp(0, 0, s);
        return ipList;
    }

    private void paseIp(int index, int step, String s) {
        if ((s.length() - index) > (SECTION_COUNT - step) * 3
                || (s.length() - index) < (SECTION_COUNT - step)) {
            return;
        }

        if (step > 3) {
            StringBuilder ipAddr = new StringBuilder();
            for (int it = 0; it < SECTION_COUNT; it++) {
                ipAddr.append(parsedStack[it]);
                if (it != SECTION_COUNT - 1) {
                    ipAddr.append(SPLIT_DOT);
                }
            }
            ipList.add(ipAddr.toString());
            return;
        }

        char aChar = s.charAt(index);
        for (int i = 1; i <= 3; i++) {
            if (i > 1 && aChar == '0' || index + i > s.length()) {
                break;
            }
            String section = s.substring(index, index + i);
            Integer secInt = Integer.parseInt(section);
            if (secInt > 255 || secInt < 0) {
                break;
            }
            parsedStack[step] = section;
            paseIp(index + i, step + 1, s);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution a = new Solution();
        String s = "25525511135";
        System.out.println(a.restoreIpAddresses(s));
    }
}
```

## Complexity

时间复杂度：O(n)

空间复杂度：O(1)