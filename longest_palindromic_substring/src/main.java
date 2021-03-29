public class main {

    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2)
            return s;
        boolean dp[][] = new boolean[n][n];
        String res = "";
        res+=s.charAt(0);
        for (int i = 0; i < n; i++)
            dp[i][i] = true;
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                boolean same = s.charAt(i) == s.charAt(j);
                if (i == j - 1) {
                    dp[i][j] = same;
                }else{
                    dp[i][j] = dp[i+1][j-1] && same;
                }
                if(dp[i][j] == true) {
                    res= s.substring(i,j+1).length()>res.length()?s.substring(i,j+1):res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s="cbbd";
        System.out.println(longestPalindrome(s));
    }
}
