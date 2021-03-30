# LeetCode 074 搜索二维矩阵

## Problem

编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

- 每行中的整数从左到右按升序排列。
- 每行的第一个整数大于前一行的最后一个整数。

## Samples

示例 1：

<img src="https://assets.leetcode.com/uploads/2020/10/05/mat.jpg" alt="img" style="zoom:50%;"/>	

```
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
输出：true
```

示例 2：

<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/mat2.jpg" alt="img" style="zoom:50%;" />	

```
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
输出：false
```

## Tag

二分查找

## Solution

对于我这种水平很差的人来说，暴力解法是最好想的，没有任何障碍。直接遍历二维数组，在循环中每次比较一次。但复杂度高，不过本题能通过力扣的所有样例，居然时间上还超过100%的Java，怪了。

暴力解法：

```java
package 搜索二维矩阵;

/*
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
	每行中的整数从左到右按升序排列。
	每行的第一个整数大于前一行的最后一个整数。

 */
public class Main {
    static boolean searchMatrix(int[][] matrix, int target) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(target == matrix[i][j])
                    return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(searchMatrix(matrix,13));
    }
}
```

当然看了题解之后，觉得二分查找的方法好。

思路是这样的。由于要判断的是每行的第一个元素大于前一行的最后一个元素，且每行元素是升序的，所以每行的第一个元素大于前一行的第一个元素，这样一来，也就是说矩阵第一列的元素是升序的。

于是可以对矩阵的第一列的元素二分查找，找到最后一个不大于目标值的元素，然后在该元素所在行中二分查找目标值是否存在。

## Code

二分解法：

```java
package 搜索二维矩阵;

/*
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
   每行中的整数从左到右按升序排列。
   每行的第一个整数大于前一行的最后一个整数。
 */
public class Main {
    static boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = binarySearchFirstColumn(matrix, target);
        if (rowIndex < 0) {
            return false;
        }
        return binarySearchRow(matrix[rowIndex], target);
    }

    static int binarySearchFirstColumn(int[][] matrix, int target) { // 对第一列二分查找
        int low = -1, high = matrix.length - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (matrix[mid][0] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    static boolean binarySearchRow(int[] row, int target) {	// 对行二分查找
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(searchMatrix(matrix,3));	// false
    }
}
```

## Complexity

时间复杂度O(mn)，m表示矩阵行数，n表示矩阵列数

空间复杂度O(1)
