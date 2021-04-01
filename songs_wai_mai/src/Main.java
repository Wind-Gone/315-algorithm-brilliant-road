import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static boolean limit=false;
    static int n;

    public static void dfs(char cha , int index, String res,int[] visited, int[] a, int[] b,boolean InfinityFlag){
        if(limit==true)
            return;



        int[] newvisited=visited.clone();
        newvisited[index]=1;
        
        int oldindex=index;
        index=index + (cha=='a'?a[index]:b[index]);
        res+=cha;

        if(!(index>=0&&index<=n-1))
            return;
        if(visited[index]==1){
            if(cha=='a') {
                dfs('b', oldindex, res, newvisited, a, b,true);
            }
            return;
        }else if(index==n-1){
            if(InfinityFlag==false)
                System.out.print(res);
            else
                System.out.println("Infinity!");
            limit=true;
            return;
        }else if(index>=0&&index<=n-2){
            dfs('a',index,res,newvisited,a,b,false);
            dfs('b',index,res,newvisited,a,b,false);
        }
    }


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        n =scanner.nextInt();
        int[] a=new int[n];
        int[] b=new int[n];
        for(int i=0;i<n;i++){
            a[i]=scanner.nextInt();
        }
        for(int i=0;i<n;i++){
            b[i]=scanner.nextInt();
        }
        LinkedList<Character> path = new LinkedList<>();
        int[] visited = new int[n];
        dfs('a',0,"",visited,a,b,false);
        dfs('b',0,"",visited,a,b,false);

        if(limit==false) {
            System.out.print("No solution!");
        }

    }
}
