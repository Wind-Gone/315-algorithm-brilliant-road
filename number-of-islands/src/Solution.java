
import java.util.Stack;

public class Solution {

    public class Node {
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public void dfs(char[][] grid, int i, int j){
        int maxM= grid.length;
        int maxN=grid[0].length;
        boolean[][] visited=new boolean[maxM][maxN];
        for(int a=0;a<maxM;a++)
            for(int b=0;b<maxN;b++)
                visited[a][b]=false;


        Stack<Node> stk=new Stack<>();
        stk.push(new Node(i,j));
        visited[i][j]=true;
        while(!stk.empty()){
            Node cur= stk.pop();
            int m=cur.i;
            int n=cur.j;
            if( (m-1)>=0 && (m-1)<maxM && grid[m-1][n]=='1' && visited[m-1][n]==false ){
                stk.push(new Node(m-1,n));
                visited[m-1][n]=true;
            }
            if( (m+1)>=0 && (m+1)<maxM && grid[m+1][n]=='1' && visited[m+1][n]==false ){
                stk.push(new Node(m+1,n));
                visited[m+1][n]=true;
            }
            if( (n-1)>=0 && (n-1)<maxN && grid[m][n-1]=='1' && visited[m][n-1]==false ){
                stk.push(new Node(m,n-1));
                visited[m][n-1]=true;
            }
            if( (n+1)>=0 && (n+1)<maxN && grid[m][n+1]=='1' && visited[m][n+1]==false ){
                stk.push(new Node(m,n+1));
                visited[m][n+1]=true;
            }
            grid[m][n]='0';
        }
    }

    public int numIslands(char[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int res=0;

        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    res++;
                }
            }

        return res;

    }
}
