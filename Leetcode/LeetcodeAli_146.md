---
阿里巴巴题库 146. LRU 缓存机制
---

## Problem

运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

**进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作**

> 输入
>["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
> [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
> 输出
> [null, null, null, 1, null, -1, null, -1, 3, 4]
> 
> 解释
>LRUCache lRUCache = new LRUCache(2);
> lRUCache.put(1, 1); // 缓存是 {1=1}
>lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
> lRUCache.get(1);    // 返回 1
> lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
> lRUCache.get(2);    // 返回 -1 (未找到)
> lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
> lRUCache.get(1);    // 返回 -1 (未找到)
>lRUCache.get(3);    // 返回 3
> lRUCache.get(4);    // 返回 4
>
> 提示：
> 
> 1 <= capacity <= 3000
> 0 <= key <= 3000
>0 <= value <= 104
> 最多调用 3 * 104 次 get 和 put



## Tag

- Map
- 双向链表
- 设计



## Solution

> 想法其实就是哈希表+双向队列，为什么要用这两个数据结构呢？理由如下：
>
> - 分析上面的操作过程，要让 put 和 get 方法的时间复杂度为 O(1)，我们可以总结出 cache 这个数据结构必要的条件：查找快，插入快，删除快，有顺序之分。
>
> - 因为显然 cache 必须有顺序之分，以区分最近使用的和久未使用的数据；而且我们要在 cache 中查找键是否已存在；如果容量满了要删除最后一个数据；每次访问还要把数据插入到队头。
>
> - 那么，什么数据结构同时符合上述条件呢？哈希表查找快，但是数据无固定顺序；链表有顺序之分，插入删除快，但是查找慢。所以结合一下，形成一种新的数据结构：哈希链表。
>
> - LRU 缓存算法的核心数据结构就是哈希链表，双向链表和哈希表的结合体。这个数据结构长这样：
>
>   ![HashLinkedList](LeetcodeAli_146.assets/b84cf65debb43b28bd212787ca63d34c9962696ed427f638763be71a3cb8f89d.jpg)
>
>   - **哈希表存储了某个key双向链表的地址，这样当我们进行搜索的时候就不必进行O（N）级别的遍历，而只在哈希表中进行查询是否存在该key即可，同时双向链表的原因在于如果达到了链表的容量，那么就需要删除最后一个node，单向链表删除最后一个node时间复杂度是O(N)，所以使用双向链表，可以首尾操作**

### Code—1

```c++
/*
此处的迭代器值得深入学习的知识还很多，总结如下
https://blog.csdn.net/weixin_38736371/article/details/80873865
 指针能指向函数而迭代器不行，迭代器只能指向容器；
 指针是迭代器的一种。指针只能用于某些特定的容器；
 迭代器是指针的抽象和泛化。所以，指针满足迭代器的一切要求。
 总之，指针和迭代器是有很大差别的，虽然他们表现的行为相似，但是本质是不一样的！一个是类模板，一个是存放一个对象地址的指针变量。
*/

class LRUCache {
public:
    int capacity;
    unordered_map<int, list<pair<int, int>> :: iterator> myHashMap;
    list<pair<int,int>> cache;
    LRUCache(int capacity) {
        this->capacity = capacity;
    }
    
    int get(int key) {
        if (myHashMap.find(key) == myHashMap.end())
            return -1;
        auto targetVal = *myHashMap[key];
        cache.erase(myHashMap[key]);
        cache.push_front(targetVal);
        myHashMap[key] = cache.begin();
        return targetVal.second;
    }
    
    void put(int key, int value) {
        if (myHashMap.find(key) != myHashMap.end()){
            cache.erase(myHashMap[key]);
        }
        else {
            if (cache.size() == capacity){
                myHashMap.erase(cache.back().first);
                cache.pop_back();
            }
        }
        cache.push_front({key,value});
        myHashMap[key] = cache.begin();
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
```

### Complexity Analysis

- 时间复杂度：`O(1)`
- 空间复杂度：`O(Capacity)`
