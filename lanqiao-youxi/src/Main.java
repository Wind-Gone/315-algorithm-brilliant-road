import java.util.Scanner;

public class Main {
    static int[] prime = new int[1001];
    static int primeNum;
    static int[][] dp = new int[1001][1001];

    static void getPrime(int n) {
        boolean[] notprime = new boolean[1001];
        for (int i = 2; i <= n; i++) {
            if (!notprime[i]) {
                prime[++primeNum] = i;
            }
            for (int j = i * i; j <= n; j += i) {
                notprime[j] = true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        getPrime(n);

        dp[0][0] = 1;
        for (int i = 1; i <= primeNum; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = prime[i]; k <= n; k *= prime[i]) {
                    if (k > j) break;
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        long ans=0;
        for (int i = 0; i <= n; i++)
        {
            ans += dp[primeNum][i];
        }
        System.out.print(ans);


    }
}
