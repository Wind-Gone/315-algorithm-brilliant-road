import java.util.Arrays;

public class Main {
    public static int findKthLargest(int[] nums, int k) {
        k=nums.length-k;
        int res=findK(nums,k,0,nums.length-1);


        return res;
    }

    public static int findK(int[] nums, int k , int left , int right){
        int index=constructFirst(nums,left,right);
        int res=0;
        if(index<k){
            res=findK(nums,k,index+1,right);
        }else if(index > k){
            res=findK(nums,k,left,index-1);
        }else if(index==k){
            res= nums[index];
        }
        return res;
    }

    public static int constructFirst(int[] nums , int left, int right){
        int pilot = nums[left];
        int j=left+1;
        for(int i=left+1;i<=right;i++){
            if(nums[i]<pilot){
                int temp=nums[j];
                nums[j]=nums[i];
                nums[i]=temp;
                j++;
            }
        }
        int index= j-1;
        nums[left]=nums[index];
        nums[index]=pilot;
        return index;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k=4;
        int res= findKthLargest(nums,k);
    }


}
