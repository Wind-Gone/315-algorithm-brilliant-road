# LeetCode 232 用栈实现队列

## Problem

请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：

实现 MyQueue 类：


```
void push(int x) 将元素 x 推到队列的末尾
int pop() 从队列的开头移除并返回元素
int peek() 返回队列开头的元素
boolean empty() 如果队列为空，返回 true ；否则，返回 false
```


说明：

- 你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
- 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。

## Tag

Stack、Queue

## Solution

双栈法。将一个栈当作输入栈，push传入的数据；另一个栈当作输出栈，pop和peek操作。每次pop或peek时，若栈为空则弹栈并压栈。

## Code

```java
class MyQueue {
    Deque<Integer> inStack;
    Deque<Integer> outStack;

	public MyQueue() {
    	inStack = new LinkedList<Integer>();
    	outStack = new LinkedList<Integer>();
	}

	public void push(int x) {
    	inStack.push(x);
	}

	public int pop() {
    	if (outStack.isEmpty()) {
        	in2out();
    	}
    	return outStack.pop();
	}

	public int peek() {
    	if (outStack.isEmpty()) {
        	in2out();
    	}
    	return outStack.peek();
	}

	public boolean empty() {
    	return inStack.isEmpty() && outStack.isEmpty();
	}

	private void in2out() {
    	while (!inStack.isEmpty()) {
        	outStack.push(inStack.pop());
    	}
	}
}
```

## Complexity

时间复杂度O(1)

空间复杂度O(n)

## Reference

参考下面的链接，感觉对自己比较好理解~~~

https://www.sohu.com/a/270671146_478315