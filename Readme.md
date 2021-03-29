## 3 无重复字符的最长子串
> 3/28
* 滑动窗口

* $ $ $ $ $ $ $ $ $ $

* L       R

* ```java
  new hashset;
  while(R < str.length)
  	if(R not in hashset)
  		hashset.add(R);
  		R右移；
  	else
  		更新maxsize；
  		hashset.remove(L);
  		L右移;
  更新maxsize  //防止最大长度子串的R在最后一个字符
  return maxsize
  ```






## 5 最长回文子串
> 3/28
* 动态规划

* $dp[i][j] = dp[i+1][j-1] \  \wedge \ (s[i]==s[j])$

* 一列列进行动态规划 （$j-i==1$时额外判断）

  * |      | 0    | 1     | 2     | 3     |
    | ---- | ---- | ----- | ----- | ----- |
    | 0    | T    | ==1== | ==2== | ==4== |
    | 1    | ×    | T     | ==3== | ==5== |
    | 2    | ×    | ×     | T     | ==6== |
    | 3    | ×    | ×     | ×     | T     |

