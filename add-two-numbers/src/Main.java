public class Main {

      public class ListNode {
          int val;
         ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int flag=0;
        ListNode resHead= new ListNode();
        ListNode cur1=l1;
        ListNode cur2=l2;
        ListNode curRes = resHead ;
        while(cur1!=null&&cur2!=null){
            int add = flag + cur1.val + cur2.val ;
            if(add>=10){
                curRes.next=new ListNode(add-10);
                flag=1;
            }else if(add<10){
                curRes.next= new ListNode(add);
                flag=0;
            }
            cur1=cur1.next;
            cur2=cur2.next;
            curRes=curRes.next;
        }
        while(cur1!=null){
            int add =flag + cur1.val;
            if(add>=10){
                curRes.next=new ListNode(add-10);
                flag=1;
            }else if(add<10){
                curRes.next= new ListNode(add);
                flag=0;
            }
            cur1=cur1.next;
            curRes=curRes.next;
        }
        while(cur2!=null){
            int add =flag + cur2.val;
            if(add>=10){
                curRes.next=new ListNode(add-10);
                flag=1;
            }else if(add<10){
                curRes.next= new ListNode(add);
                flag=0;
            }
            cur2=cur2.next;
            curRes=curRes.next;
        }
        if(flag==1){
            curRes.next=new ListNode(1);
        }
        return resHead.next;
    }
}
