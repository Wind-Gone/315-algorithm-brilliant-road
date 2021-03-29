public class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        int n=0;
        ListNode cur=head;
        while(cur!=null){
            cur=cur.next;
            n++;
        }
        cur=head;
        for(int i=0;i<n-k+1;i++){
            cur=cur.next;
        }
        return cur;
    }
}

