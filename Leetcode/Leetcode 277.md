---
Leetcode 277. 搜寻名人
---

## 题目地址

https://leetcode-cn.com/problems/find-the-celebrity/





## 题目描述

```text
假设你是一个专业的狗仔，参加了一个 n 人派对，其中每个人被从 0 到 n - 1 标号。在这个派对人群当中可能存在一位 “名人”。所谓 “名人” 的定义是：其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人。

现在你想要确认这个 “名人” 是谁，或者确定这里没有 “名人”。而你唯一能做的就是问诸如 “A 你好呀，请问你认不认识 B呀？” 的问题，以确定 A 是否认识 B。你需要在（渐近意义上）尽可能少的问题内来确定这位 “名人” 是谁（或者确定这里没有 “名人”）。

在本题中，你可以使用辅助函数 bool knows(a, b) 获取到 A 是否认识 B。请你来实现一个函数 int findCelebrity(n)。

派对最多只会有一个 “名人” 参加。若 “名人” 存在，请返回他/她的编号；若 “名人” 不存在，请返回 -1。

 

示例 1:



输入: graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
输出: 1
解析: 有编号分别为 0、1 和 2 的三个人。graph[i][j] = 1 代表编号为 i 的人认识编号为 j 的人，而 graph[i][j] = 0 则代表编号为 i 的人不认识编号为 j 的人。“名人” 是编号 1 的人，因为 0 和 2 均认识他/她，但 1 不认识任何人。
示例 2:



输入: graph = [
  [1,0,1],
  [1,1,0],
  [0,1,1]
]
输出: -1
解析: 没有 “名人”
```



## 前置知识

数组



## 公司



- 谷歌 Google|5
- VMware|3
- 阿里巴巴|2
- 优步 Uber|2]
- 新浪
- 彭博 Bloomberg
- 雅虎 Yahoo
- 爱彼迎 Airbnb
- 百度
- 思科 Cisco



## 标签

- 数组



## 方法一：



### 思路

- 我的初始想法其实很简单，当时在做的时候还没注意题目提供的API函数，后来发现后处理就更为简单
- 简单来说就是一个二重循环，如果第i个人不认识第j个人且第j个人认识第i个人，就用一个计数器count++，如果最后count == n-1，就找到这个名人，否则就返回-1

### 代码

```java
/*
 * @Author: Wind-Gone Hu 
 * @Date: 2020-09-21 21:32:01 
 * @Last Modified by:   Wind-Gone Hu 
 * @Last Modified time: 2020-09-21 21:32:01 
 */
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
        public int findCelebrity(int n) {
            int count = 0;
            for(int i = 0;i < n; i++){
                for(int j =0; j < n ;j++){
                    if(!knows(i,j)&&knows(j,i)&&i!=j){
                        count++;
                    }     
                }
                if(count == n-1)
                    return i;
                count = 0;
            }
            return -1;
        }
    }
```

### **复杂度分析**

- 时间复杂度：O(N^2) 
- 空间复杂度：O(1)

