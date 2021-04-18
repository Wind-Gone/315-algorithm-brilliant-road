import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static int[] times=new int[2001];
    static int T,N,M,K;
    static int[][] zajiao=new int[2001][2001];

    public static int  dfs(HashSet<Integer> seeds , int time ){
        if(seeds.contains(T)){
            return time;
        }
        for(int i:seeds){
            for(int j=i+1;j<=N;j++){
                int added=zajiao[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        HashSet<Integer> seeds=new HashSet<>();
        N=scanner.nextInt();
        M=scanner.nextInt();
        K=scanner.nextInt();
        T=scanner.nextInt();
        for(int i=1;i<=N;i++){
            times[i]=scanner.nextInt();
        }
        for(int i=1;i<=M;i++){
            seeds.add(scanner.nextInt());
        }
        for(int i=1;i<=K;i++){
            int a=scanner.nextInt();
            int b=scanner.nextInt();
            int c=scanner.nextInt();
            if(a<b)
            zajiao[a][b]=c;
            else
                zajiao[b][a]=c;
        }
        int ans;
        ans=dfs(seeds,0);

    }
}
