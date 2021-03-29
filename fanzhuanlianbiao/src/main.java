public class main {
    public static void main(String[] args) {
        Solution solution=new Solution();
        Solution.ListNode head= solution.initList();
        head=solution.reverseList(head);
        solution.print(head);
    }
}
