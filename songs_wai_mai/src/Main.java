import javax.naming.InitialContext;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static boolean limit = false;
    static int n;

    public static void dfs(char cha, int index, String res, int[] visited, int[] a, int[] b, boolean InfinityFlag) {
        if (limit == true)
            return;
        if (!(index >= 0 && index <= n - 1))
            return;
        int[] newvisited = visited;
        newvisited[index] ++;
        int nextAIndex = index + a[index];
        int nextBIndex = index + b[index];

        if(cha==' '){
            dfs('a', nextAIndex, res, newvisited, a, b, InfinityFlag);
            dfs('b', nextBIndex, res, newvisited, a, b, InfinityFlag);
        }

        res += Character.toString(cha);
        if (index == n - 1) {
            if (InfinityFlag == false) {
                System.out.print(res);
            }
            else
                System.out.println("Infinity!");
            limit = true;
            return;
        } else if (nextAIndex >= 0 && nextAIndex <= n - 1 && visited[nextAIndex] >= 1) {
            dfs('b', nextBIndex, res, newvisited, a, b, true);
            return;
        } else if (nextBIndex >= 0 && nextBIndex <= n - 1 && visited[nextBIndex] >= 1) {
            return;
        } else {
            dfs('a', nextAIndex, res, newvisited, a, b, InfinityFlag);
            dfs('b', nextBIndex, res, newvisited, a, b, InfinityFlag);
        }
        newvisited[index]--;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        LinkedList<Character> path = new LinkedList<>();
        int[] visited = new int[n];
        dfs(' ', 0, "", visited, a, b, false);

        if (limit == false) {
            System.out.print("No solution!");
        }

    }
}
