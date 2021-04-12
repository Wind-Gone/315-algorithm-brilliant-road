---
[19国赛填空题] 递增序列
---

## Problem

> - ### 题目描述
>
>   **本题为填空题，只需要算出结果后，在代码中使用输出语句将所填结果输出即可。**
>
>   对于一个字母矩阵，我们称矩阵中的一个递增序列是指在矩阵中找到两个字母，它们在同一行，同一列，或者在同一 4545 度的斜线上，这两个字母从左向右看、或者从上向下看是递增的。
>
>   例如，如下矩阵中
>
>   ```
>   LANN
>   QIAO
>   ```
>
>   有LN、LN、AN、AN、IO、AO、LQ、AI、NO、NO、AQ、IN、AN 等 13 个 递增序列。注意当两个字母是从左下到右上排列时，从左向右看和从上向下看 是不同的顺序。
>
>   对于下面的 3030 行 5050 列的矩阵，请问总共有多少个递增序列？
>
>   ```
>   VLPWJVVNNZSWFGHSFRBCOIJTPYNEURPIGKQGPSXUGNELGRVZAG
>   SDLLOVGRTWEYZKKXNKIRWGZWXWRHKXFASATDWZAPZRNHTNNGQF
>   ZGUGXVQDQAEAHOQEADMWWXFBXECKAVIGPTKTTQFWSWPKRPSMGA
>   BDGMGYHAOPPRRHKYZCMFZEDELCALTBSWNTAODXYVHQNDASUFRL
>   YVYWQZUTEPFSFXLTZBMBQETXGXFUEBHGMJKBPNIHMYOELYZIKH
>   ZYZHSLTCGNANNXTUJGBYKUOJMGOGRDPKEUGVHNZJZHDUNRERBU
>   XFPTZKTPVQPJEMBHNTUBSMIYEGXNWQSBZMHMDRZZMJPZQTCWLR
>   ZNXOKBITTPSHEXWHZXFLWEMPZTBVNKNYSHCIQRIKQHFRAYWOPG
>   MHJKFYYBQSDPOVJICWWGGCOZSBGLSOXOFDAADZYEOBKDDTMQPA
>   VIDPIGELBYMEVQLASLQRUKMXSEWGHRSFVXOMHSJWWXHIBCGVIF
>   GWRFRFLHAMYWYZOIQODBIHHRIIMWJWJGYPFAHZZWJKRGOISUJC
>   EKQKKPNEYCBWOQHTYFHHQZRLFNDOVXTWASSQWXKBIVTKTUIASK
>   PEKNJFIVBKOZUEPPHIWLUBFUDWPIDRJKAZVJKPBRHCRMGNMFWW
>   CGZAXHXPDELTACGUWBXWNNZNDQYYCIQRJCULIEBQBLLMJEUSZP
>   RWHHQMBIJWTQPUFNAESPZHAQARNIDUCRYQAZMNVRVZUJOZUDGS
>   PFGAYBDEECHUXFUZIKAXYDFWJNSAOPJYWUIEJSCORRBVQHCHMR
>   JNVIPVEMQSHCCAXMWEFSYIGFPIXNIDXOTXTNBCHSHUZGKXFECL
>   YZBAIIOTWLREPZISBGJLQDALKZUKEQMKLDIPXJEPENEIPWFDLP
>   HBQKWJFLSEXVILKYPNSWUZLDCRTAYUUPEITQJEITZRQMMAQNLN
>   DQDJGOWMBFKAIGWEAJOISPFPLULIWVVALLIIHBGEZLGRHRCKGF
>   LXYPCVPNUKSWCCGXEYTEBAWRLWDWNHHNNNWQNIIBUCGUJYMRYW
>   CZDKISKUSBPFHVGSAVJBDMNPSDKFRXVVPLVAQUGVUJEXSZFGFQ
>   IYIJGISUANRAXTGQLAVFMQTICKQAHLEBGHAVOVVPEXIMLFWIYI
>   ZIIFSOPCMAWCBPKWZBUQPQLGSNIBFADUUJJHPAIUVVNWNWKDZB
>   HGTEEIISFGIUEUOWXVTPJDVACYQYFQUCXOXOSSMXLZDQESHXKP
>   FEBZHJAGIFGXSMRDKGONGELOALLSYDVILRWAPXXBPOOSWZNEAS
>   VJGMAOFLGYIFLJTEKDNIWHJAABCASFMAKIENSYIZZSLRSUIPCJ
>   BMQGMPDRCPGWKTPLOTAINXZAAJWCPUJHPOUYWNWHZAKCDMZDSR
>   RRARTVHZYYCEDXJQNQAINQVDJCZCZLCQWQQIKUYMYMOVMNCBVY
>   ABTCRRUXVGYLZILFLOFYVWFFBZNFWDZOADRDCLIRFKBFBHMAXX
>   ```
>
>   ### 运行限制
>
>   - 最大运行时间：1s
>   - 最大运行内存: 128M

## Tag

> - 动归
> - 数论

## Solution

> 直接进行遍历输出

### Code-1

```c++
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int main()
{
    int num = 0;
    char a[30][50];
    for (int i = 0; i < 30; i++)
        for (int j = 0; j < 50; j++)
            cin >> a[i][j];
    for (int i = 0; i < 30; i++)
    {
        for (int j = 0; j < 50; j++)
        {
            //行
            for (int x = j + 1; x < 50; x++)
                if (a[i][x] - a[i][j] > 0)
                {
                    num++;
                }

            //列
            for (int y = i + 1; y < 30; y++)
                if (a[y][j] - a[i][j] > 0)
                {
                    num++;
                }
            //左上右下
            for (int o = i + 1, p = j + 1; o < 30 && p < 50; o++, p++)
                if (a[o][p] - a[i][j] > 0)
                {
                    num++;
                }
            //左下右上
            for (int b = i + 1, c = j - 1; b < 30 && c >= 0; b++, c--)
            {
                if (a[i][j] - a[b][c] > 0)
                {
                    num++;
                }

                if (a[b][c] - a[i][j] > 0)
                {
                    num++;
                }
            }
        }
    }
    cout << num << endl;
    return 0;
}
```

### Code-2

```java
import java.io.*;


public class Test_B {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("src/inc.txt")));
        char[][] cs = new char[30][];
        int ci = 0;
        String s;
        while ((s = br.readLine()) != null) {
            cs[ci++] = s.toCharArray();
        }
        //定义五个可行方向
        int[][] dir = {{1,0},{0,1},{1,1},{1,-1},{-1,1}};
        int cut = 0;
        //遍历矩阵中每一个位置，然后向五个方向查找
        for (int i = 0; i < cs.length; i++) {
            for (int j = 0; j < cs[i].length; j++) {
                char c = cs[i][j];
                for (int k = 0; k < 5; k++) {
                    int x = i;
                    int y = j;
                    while (true) {
                        x += dir[k][0];
                        y += dir[k][1];
                        if (x < 0 || y < 0 ||x >= cs.length || y >= cs[x].length) {
                            break;
                        }
                        if (cs[x][y] > c) {
                            cut ++;
                        }
                    }
                }
            }
        }
        System.out.println(cut);
    }
}
```

