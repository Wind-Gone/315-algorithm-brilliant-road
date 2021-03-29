import java.util.HashSet;

public class main {
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> hashSet=new HashSet<>();
        int l=0;
        int r=0;
        int max=0;
        while(r<s.length()){
            char rightChar=s.charAt(r);
            if(!hashSet.contains(rightChar)){
                hashSet.add(rightChar);
                r++;
            }else{
                max=(hashSet.size()>max)?hashSet.size():max;
                hashSet.remove(s.charAt(l));
                l++;
            }
        }
        max=(hashSet.size()>max)?hashSet.size():max;
        return max;
    }

    public static void main(String[] args) {
        String s="p";
        System.out.println(lengthOfLongestSubstring(s));
    }

}
