# LeetCode 买卖股票问题

## Summary

买卖股票问题是LeetCode的经典问题集。主要思想是**动态规划**。我觉得将这一类题目做个整理，一来对自己而言挺有必要，二来对算法有进一步的认识。

当然，网络上也有对该类问题的总结，我也有所参考。

选取六道买卖股票的问题。按照由特殊到一般的顺序，再到有附加条件的问题，在代码中由应该挺详细的注释，至少自己能理解啦！希望有所帮助。

## 121 买卖股票的最佳时机

### Problem

给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。[LeetCode121](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

### Samples

示例 1：

```
输入：[7,1,5,3,6,4]
输出：5
解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
```


示例 2：

```
输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
```

### Solution

求数组最大差值，暴力可解，动态规划考虑第i-1天和第i天是否持有股票的状态关系建立方程。也可以遍历数组分别找到最大最小值作差。

### Code

```java
package 买卖股票的最佳时机_全;
/*
给定一个数组prices，它的第i个元素 prices[i]表示一支给定股票第i天的价格。
你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票。
设计一个算法来计算你所能获取的最大利润。
返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回0。
 */
class Solution1 {
    // 题目的意思转化成算法，其实就是求数组中两个数的最大差值，那么只要遍历出最小值和最大值就行了
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
}

public class 买卖股票的最佳时机 {
    public static void main(String[] args) {
        Solution1 a = new Solution1();
        int[] arr=new int[]{7,1,5,3,6,4};
        System.out.println(a.maxProfit(arr));
    }
}
```

### Complexity

时间复杂度：O(n)

空间复杂度：O(1)

### Code_Ref

```java
public class Solution {
	public int maxProfit(int[] prices) {
    	int len = prices.length;
    	// 特殊判断
    	if (len < 2) {
        	return 0;
    	}
    	int[][] dp = new int[len][2];

		// dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
		// dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数

		// 初始化：不持股显然为 0，持股就需要减去第 1 天（下标为 0）的股价
		dp[0][0] = 0;
		dp[0][1] = -prices[0];

		// 从第 2 天开始遍历
		for (int i = 1; i < len; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
			dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
		}
		return dp[len - 1][0];
	}
}
// 时间复杂度为O(N)，空间复杂度为O(N)
```

## 122 买卖股票的最佳时机Ⅱ

### Problem

给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。[LeetCode122](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

### Samples

示例 1:

```
输入: prices = [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
```


示例 2:

```
输入: prices = [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```


示例 3:

```
输入: prices = [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

### Solution

动态规划，因为不能同时参与多笔交易，所以每天交易结束只有持有一只股票和没有股票两种状态。分别讨论即可。

### Code

```java
package 买卖股票的最佳时机_全;
/*
给定一个数组prices，其中prices[i]是一支给定股票第i天的价格。
设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
class Solution2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        // dp[i][0]是dp[i-1][0]和dp[i-1][1]+prices[i]的最大的那个
        // 因为可能在第i-1天没有股票，也就是dp[i-1][0]，或者第i-1天有一支股票，所以在第i天有利润prices[i]
        // 同理，dp[i][1]是dp[i-1][1]和dp[i-1][0]-prices[i]的最大值，
        // 因为可能第i-1天有一支股票，那么在第i天仍然有，或者第i-1天没有股票，所以第i天有股票，买入会减少收益


        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // plain case
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0]; // 返回最大利润
    }
}
public class 买卖股票的最佳时机Ⅱ {
    public static void main(String[] args) {
        Solution2 a = new Solution2();
        int[] prices = {1,2,3,4,5};
        System.out.println(a.maxProfit(prices));
    }
}
```

### Complexity

时间复杂度：O(n)

空间复杂度：O(n)

## 123 买卖股票的最佳时机Ⅲ

### Problem

给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。[LeetCode123](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/)

### Samples

示例 1:

```
输入：prices = [3,3,5,0,0,3,1,4]
输出：6
解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
```


示例 2：

```
输入：prices = [1,2,3,4,5]
输出：4
解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```


示例 3：

```
输入：prices = [7,6,4,3,1] 
输出：0 
解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
```


示例 4：

```
输入：prices = [1]
输出：0
```

### Solution

对于任意一天考虑四个变量：

- buy1：在该天第一次买入股票可获得的最大收益 
- sell1：在该天第一次卖出股票可获得的最大收益
- buy2：在该天第二次买入股票可获得的最大收益
- sell2：在该天第二次卖出股票可获得的最大收益

分别对四个变量进行相应的更新, 最后sell2就是最大。

### Code

```java
package 买卖股票的最佳时机_全;
/*
给定一个数组，它的第i个元素是一支给定的股票在第i天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
class Solution3 {
    /* 理解题目，最多两笔交易，所以我们会有下面几种情况：
    ①没有交易；
    ②一场买交易；
    ③完成一场交易，即买和卖×1；
    ④买和卖×1，以及一场买交易；
    ⑤完成两场交易，即买和卖×2.
    注意到买卖的前提是有买才能卖，所以不会出现单独卖的情况。
     */
    public int maxProfit(int[] prices) {
        // 首先①的利润为0，不用讨论

        // 分别将②~⑤交易情况下的利润记作buy1,sell1,buy2,sell2
        // 初始化
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;

        for (int i = 1; i < prices.length; i++) {
            // buy1：第i天不做操作，或买入prices[i]的股票
            // 用buy‘1表示第i-1天的利润，那么第i天的最大利润显然就是上面两种情况的最大值，买入为负
            buy1 = Math.max(buy1, -prices[i]);
            // sell1：容易想明白，同buy1可以不做操作（因为没有买也没有卖），或者卖出prices[i]的股票
            // 用sell’1表示第i-1天的利润，那么第i天的最大利润是卖出的利润，和前一天的利润加上当天卖出的利润
            sell1 = Math.max(sell1, buy1 + prices[i]);
            // buy2：可以不做操作，或者买入prices[i]的股票
            buy2 = Math.max(buy2, sell1 - prices[i]);
            // sell2：可以不做操作，或者卖出prices[i]的股票
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        // 要求的最大利润其实就是sell2
        return sell2;
    }
}
public class 买卖股票的最佳时机Ⅲ {
    public static void main(String[] args) {
        Solution3 a = new Solution3();
        int[] prices = {7,6,4,3,1};
        System.out.println(a.maxProfit(prices));
    }
}
```

### Complexity

时间复杂度：O(n)

空间复杂度：O(1)

## 188 买卖股票的最佳时机Ⅳ

### Problem

给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。[LeetCode188](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)

### Samples

示例 1：

```
输入：k = 2, prices = [2,4,1]
输出：2
解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
```


示例 2：

```
输入：k = 2, prices = [3,2,6,5,0,3]
输出：7
解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
```

### Solution

上三题的总结题。通过两个二维数组表示k次交易的状态变化。

### Code

```java
package 买卖股票的最佳时机_全;

import java.util.Arrays;

/*
给定一个整数数组prices ，它的第i个元素prices[i]是一支给定的股票在第i天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成k笔交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
class Solution4 {
    /*
    本题比Ⅲ少在没有交易次数的限制。
    很显然，由于交易次数的未知性，我们需要维护两个二维数组buy[i][j]和sell[i][j]
    对于buy[i][j]：如果在第i天买入，那么有sell[i-1][j]，设为在第i-1天卖出，没有股票，利润为-prices[i]；
                  如果不是在第i天买入，那么有buy[i-1][j]，设为第i-1天买入
    于是，有状态转移方程
            buy[i][j]=max{buy[i-1][j],sell[i-1][j]-price[i]}
    同理，对于sell[i][j]：如果在第i天卖出，那么在第i−1天时，我们手上持有股票，buy[i−1][j−1]，并且需要增加prices[i]的卖出收益；
    如果不是第i天卖出的，那么在第i-1i天时，我们手上不持有股票，对应状态sell[i−1][j]。
    可以得到状态转移方程：
            sell[i][j]=max{sell[i−1][j],buy[i−1][j−1]+price[i]}
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        k = Math.min(k, prices.length / 2);
        int[][] buy = new int[prices.length][k + 1];
        int[][] sell = new int[prices.length][k + 1];

        // 初始化
        buy[0][0] = -prices[0];
        sell[0][0] = 0;

        for (int i = 1; i <= k; i++) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < prices.length; i++) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell[prices.length - 1]).max().getAsInt();
    }
}
public class 买卖股票的最佳时机Ⅳ {
    public static void main(String[] args) {
        Solution4 a = new Solution4();
        int[] prices = {3,2,6,5,0,3};
        System.out.println(a.maxProfit(2, prices));
    }
}
```

### Complexity

时间复杂度：O(n min(n, k))

空间复杂度：O(n min(n, k))

## 309 最佳买卖股票时机含冷冻期

### Problem

给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。[LeetCode309](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)

### Samples

示例:

```
输入: [1,2,3,0,2]
输出: 3 
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
```

### Solution

根据题意：卖出股票后的第2天为冷冻期。即：

- 卖出股票的当天：不持股；
- 卖出股票的第2天：冷冻期（不能买入股票，当然也不能卖出股票）；
- 卖出股票的第3天：可以买入股票，也可以什么都不操作。

### Code

```java
package 买卖股票的最佳时机_全;
/*
给定一个整数数组，其中第i个元素代表了第i天的股票价格.
设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）
你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 */
class Solution5 {
    /*
    用 f[i]表示第i天结束之后的「累计最大收益」.有三种不同的状态：
    持有一支股票，对应的「累计最大收益」记为 f[i][0]；
    不持有任何股票，并且处于冷冻期中，对应的「累计最大收益」记为 f[i][1]；
    不持有任何股票，并且不处于冷冻期中，对应的「累计最大收益」记为 f[i][2].
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            /*
            对于f[i][0]，我们目前持有的这一支股票可以是在第i−1天就已经持有的，对应的状态为f[i−1][0]；
            或者是第i天买入的，那么第i−1天就不能持有股票并且不处于冷冻期中，对应的状态为f[i−1][2]加上买入股票的负收益prices[i]。
            因此状态转移方程为：f[i][0]=max(f[i−1][0],f[i−1][2]−prices[i])
             */
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);

            /*
            对于f[i][1]，我们在第i天结束之后处于冷冻期的原因是在当天卖出了股票，那么说明在第i−1天时我们必须持有一支股票，
            对应的状态为f[i−1][0]加上卖出股票的正收益prices[i]。
            因此状态转移方程为：f[i][1]=f[i−1][0]+prices[i]
             */
            f[i][1] = f[i - 1][0] + prices[i];

            /*
            对于f[i][2]，我们在第i天结束之后不持有任何股票并且不处于冷冻期，说明当天没有进行任何操作，即第i−1天时不持有任何股票：
            如果处于冷冻期，对应的状态为f[i−1][1]；如果不处于冷冻期，对应的状态为f[i−1][2]。
            因此状态转移方程为：[i][2]=max(f[i−1][1],f[i−1][2])
             */
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        // 如果一共有n天，那么最终的答案即为：max(f[n−1][1],f[n−1][2])
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }
}
public class 最佳买卖股票时机含冷冻期 {
    public static void main(String[] args) {
        Solution5 a = new Solution5();
        int[] prices = {1,2,3,0,2};
        System.out.println(a.maxProfit(prices));
    }
}
```

### Complexity

时间复杂度：O(n)

空间复杂度：O(n)

## 714 买卖股票的最佳时机含手续费

### Problem

给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

返回获得利润的最大值。

注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。[LeetCode714](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

### Samples

示例 1:

```
输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
输出: 8
解释: 能够达到的最大利润:  
在此处买入 prices[0] = 1
在此处卖出 prices[3] = 8
在此处买入 prices[4] = 4
在此处卖出 prices[5] = 9
总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
```

### Solution

类似于122，增加手续费只需要在利润上进行增减操作。

### Code

```java
package 买卖股票的最佳时机_全;
/*
给定一个整数数组prices，其中第i个元素代表了第i天的股票价格；非负整数fee代表了交易股票的手续费用。
你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
返回获得利润的最大值。
注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 */
class Solution6 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // base case
        // 据状态定义我们可以知道第0天交易结束的时候有dp[0][0]=0以及dp[0][1]=−prices[0]。
        // 因此，我们只要从前往后依次计算状态即可。
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        /*
        考虑 dp[i][0]的转移方程，如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，
        即dp[i−1][0]，或者前一天结束的时候手里持有一支股票，即dp[i−1][1]，
        这时候我们要将其卖出，并获得prices[i]的收益，但需要支付fee的手续费。因此为了收益最大化，我们列出如下的转移方程：
            dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]−fee}
        再来按照同样的方式考虑dp[i][1].
        按状态转移，那么可能的转移状态为前一天已经持有一支股票，即dp[i−1][1]，
        或者前一天结束时还没有股票，即dp[i−1][0]，这时候我们要将其买入，并减少prices[i]的收益。可以列出如下的转移方程：
            dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
        */
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // dp[n−1][0]的收益必然大于dp[n−1][1]，最后的答案即为dp[n−1][0]。
        return dp[n - 1][0];
    }
}
public class 买卖股票的最佳时机含手续费 {
    public static void main(String[] args) {
        Solution6 a = new Solution6();
        int[] prices = {1,3,2,8,4,9};
        System.out.println(a.maxProfit(prices, 2));
    }
}
```

### Complexity

时间复杂度：O(n)

空间复杂度：O(1)

## Conclusion

动态规划，真的难
