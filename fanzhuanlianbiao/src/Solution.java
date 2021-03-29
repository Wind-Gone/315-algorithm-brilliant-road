public class Solution {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode initList() {
        ListNode head=new ListNode(1);
        ListNode cur=head;
        for(int i=2;i<=5;i++){
            cur.next=new ListNode(i);
            cur=cur.next;
        }
        return head;
    }

    public void print(ListNode head){
        ListNode cur=head;
        while(cur!=null){
            System.out.println(cur.val);
            cur=cur.next;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode temp= cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }


}
