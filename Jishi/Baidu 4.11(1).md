# 4.11 百度笔试编程题

因为只AC第一题，其它的也不会

# 树上最短路

## 题目描述

牛牛刚刚学了二叉树和最短路。他现在很好奇，对于一棵完全二叉树（对于有孩子的节点i，其左孩子为2 × i，右孩子为2 × i + 1），给定的两个点之间的最短路的长度是多少。

我们认为相邻两点之间的距离为1.

## 输入描述

第一行输入一个整数T，表示测试数据共有T组

对于每组测试数据，输入两个整数表示所询问的节点的标号

## 输出描述

对于每组测试数据，输出两点之间的最短路的长度

## 思路

本题本质是求完全二叉树任意两点的最近公共祖先，可参考[LeetCode 236.二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)

完全二叉树每层的节点编号i满足2^p<=i<2^(p+1)，因此求出编号较小的节点层数，将大的节点跳转到该层，最后两个节点一起跳转。

## 参考代码

```java
package 百度笔试1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] p1 = new int[500];
        int[] p2 = new int[500];
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int d1 = 0;
            int d2 = 0;
            while (m > 1) {
                p1[d1] = m;
                m = m / 2;
                d1++;
            }
            while (n > 1) {
                p2[d2] = n;
                n = n / 2;
                d2++;
            }
            int flag = 0;
            int pos = -1;
            for (int i = 0; i < d1; i++) {
                for (int j = 0; j < d2; j++) {
                    if (p1[i] == p2[j]) {
                        pos = p1[i];
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1)
                    break;
            }
            int d3 = 0;
            while (pos > 1) {
                pos = pos / 2;
                d3++;
            }
            System.out.println(d1 + d2 - 2 * d3);
        }
    }
}
```

