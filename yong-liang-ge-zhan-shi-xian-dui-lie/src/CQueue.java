import java.util.Stack;

public class CQueue {

    private Stack<Integer> stk1 ;
    private Stack<Integer> stk2 ;

    public CQueue() {
        stk1=new Stack<>();
        stk2=new Stack<>();
    }

    public void appendTail(int value) {
        stk1.push(value);
    }

    public int deleteHead() {
        if(stk1.empty()&&stk2.empty()){
            return -1;
        }
        if(!stk2.empty()){
            return stk2.pop();
        }else{
            while(!stk1.empty()){
                stk2.push(stk1.pop());
            }
            return stk2.pop();
        }
    }
}
