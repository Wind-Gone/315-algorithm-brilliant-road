import java.util.HashSet;

public class Solution {

    /**
     * hashSet
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> hashSet=new HashSet<>();
        for( int num : nums ){
            if(hashSet.add(num)!=true){
                return num;
            }
        }
        return -1;
    }

    public void swap(int a , int b, int[] nums ){
        int t=nums[a];
        nums[a]=nums[b];
        nums[b]=t;
    }

    public int findRepeatNumber2(int[] nums){
        for( int i=0;i<nums.length;i++ ){
            if(nums[i]==i){
                continue;
            }
            while(nums[i]!=i){
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                swap(i,nums[i],nums);
            }
        }
        return -1;
    }
}
