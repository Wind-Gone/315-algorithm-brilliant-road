import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str=scanner.next();
        int[] nums= new int[26];
        for(int i=0;i<str.length();i++){
            char ch= str.charAt(i);
            nums[ch-'a']++;
        }
        int ans=0;
        char res='a';
        for(int i=0;i<26;i++){
            res= nums[i]>ans? (char) ('a' + i) :res;
            ans=Math.max(ans,nums[i]);
        }
        System.out.println(res);
        System.out.println(ans);
    }
}
