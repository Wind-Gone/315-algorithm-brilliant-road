import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        int n=scanner.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=scanner.nextInt();
        }
        int[] pre=new int[n];
        int[] post=new int[n];
        pre[0]=1;
        post[n-1]=1;
        for(int i=1;i<n;i++){
            if(arr[i]>arr[i-1]){
                pre[i]=pre[i-1]+1;
            }else{
                pre[i]=1;
            }
        }
        for(int i=n-2;i>=0;i--){
            if(arr[i]<arr[i+1]){
                post[i]=post[i+1]+1;
            }else{
                post[i]=1;
            }
        }
        int res=1;
        for(int i=1;i<n-1;i++){
            if(arr[i-1]<arr[i+1]){
                res = (pre[i-1]+1+post[i+1])>res?(pre[i-1]+1+post[i+1]):res;
            }
        }
        System.out.print(res);




    }
}
