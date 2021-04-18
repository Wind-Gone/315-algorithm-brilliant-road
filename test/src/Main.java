import java.util.Scanner;

public class Main {
    static boolean[] notp = new boolean[2001];
    static int numsp;
    public static void main(String[] args) {
        int n=20;
        for(int i=2;i<=n;i++){
            if (!notp[i]){
                numsp++;
                System.out.println(i);
            }
            for(int j=i*i;j<=n;j+=i){
                notp[j]=true;
            }
        }

        System.out.println(numsp);
    }
}
