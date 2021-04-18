import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[][] triple =new int[102][102];
        int[][] dp = new int[102][102];

        for(int i=0;i<102;i++)
            for(int j=0;j<102;j++)
                triple[i][j]=0;

        int n=scanner.nextInt();
        for(int i=1;i<=n;i++)
            for(int j=1;j<=i;j++) {
                triple[i][j] = scanner.nextInt();
            }


        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                dp[i][j]= Math.max(dp[i-1][j-1],dp[i-1][j])+triple[i][j];
            }
        }
        int ans;
        if(n%2==0){
            ans = Math.max(dp[n][n/2],dp[n][n/2+1]);
        }else{
            ans = dp[n][n/2+1];
        }

        System.out.println(ans);


    }



}
