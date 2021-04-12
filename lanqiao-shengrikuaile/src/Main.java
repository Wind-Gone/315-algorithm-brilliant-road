import java.util.Scanner;

public class Main {



    static double dfs(double x ,double y ,int n){
        if(x<y){
            double t=x;
            x=y;
            y=t;
        }

        if(n==1){
            return x/y;
        }
        double res=100000000;
        for(int i=1;i<=n-1;i++){
            double ans1 = Math.max(dfs(x/n*i,y,i),dfs(x-x/n*i,y,n-i));
            double ans2 = Math.max(dfs(x,y/n*i,i),dfs(x,y-y/n*i,n-i));
            res=Math.min(Math.min(ans1,ans2),res);
        }
        return res;

    }



    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        double x=scanner.nextDouble();
        double y=scanner.nextDouble();
        int n=scanner.nextInt();

        double ans = dfs(x,y,n);
        System.out.printf("%.6f",ans);




    }
}
