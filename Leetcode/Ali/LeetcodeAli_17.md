---
阿里巴巴题库 17. 电话号码的字母组合
---

## Problem

> 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
>
> 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
>
> ![img](LeetcodeAli_17.assets/17_telephone_keypad.png)
>
> 



> 示例 1：
>
> 输入：digits = "23"
> 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
> 示例 2：
>
> 输入：digits = ""
> 输出：[]
> 示例 3：
>
> 输入：digits = "2"
> 输出：["a","b","c"]
>
>
> 提示：
>
> 0 <= digits.length <= 4
> digits[i] 是范围 ['2', '9'] 的一个数字。

## Tag

- 队列
- 回溯



## Solution

> - 方法一是很容易想到的，回溯法
>   - 我只需要回溯过程中维护一个index变量指代已经遍历的字符串长度即可，如果已经长度满足了就插入到结果res中
> - 方法二倒是借鉴了别人的想法，用队列来进行遍历
>   - 我们可以使用队列，先将输入的 digits 中第一个数字对应的每一个字母入队，然后将出队的元素与第二个数字对应的每一个字母组合后入队...直到遍历到 digits 的结尾。最后队列中的元素就是所求结果。
>

### Code—1

```c++
class Solution {
public:
    string tmp;
    vector<string> res;
    vector<string> phoneNumber={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    void dfs(int index, string digits){
        if (index == digits.size()){
            res.push_back(tmp);
            return;
        }
        int cur = digits[index] - '0';
        string str = phoneNumber[cur];
        for(int i = 0; i < str.size(); i++){
            tmp += str[i];
            dfs(index+1,digits);
            tmp.pop_back();
        } 
    }

    vector<string> letterCombinations(string digits) {
        if (digits.empty())
            return res;
        dfs(0,digits);
        return res;
    }
};
```

### Complexity Analysis

- 时间复杂度：$O(3^M×4^N)$ M 是对应三个字母的数字个数，N 是对应四个字母的数字个数。
- 空间复杂度：$O(3^M×4^N)$



```c++
class Solution {
public:
    string tmp;
    vector<string> res;
    vector<string> phoneNumber={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    
    vector<string> letterCombinations(string digits) {
        if (digits.empty())
            return res;
        queue<string> store;
        int index = digits[0] - '0';
        string str = phoneNumber[index];
        for(int i = 0; i < str.size(); i++){
            string tmp = "";
            tmp.push_back(str[i]);
            store.push(tmp);
        } 
        
        for(int i = 1; i < digits.size();i++){
            int len = store.size();
            while(len--){
                index = digits[i] - '0';
                for(int j = 0; j < phoneNumber[index].size(); j++){
                    str = store.front();
                    str += phoneNumber[index][j];
                    store.push(str);
                } 
                store.pop();
            }
        }
        
        while (!store.empty())
		{
			res.push_back(store.front());//队头元素存储至res
			store.pop();//队头出队
		}
        return res;
    }
};
```

### Complexity Analysis

- 时间复杂度：$O(3^M×4^N)$ M 是对应三个字母的数字个数，N 是对应四个字母的数字个数。
- 空间复杂度：$O(3^M×4^N)$



