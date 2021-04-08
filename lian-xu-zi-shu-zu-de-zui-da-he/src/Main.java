public class Main {
    static public int maxSubArray(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        dp[0]=nums[0];
        int max=dp[0];
        for(int i=1;i<n;i++){
            if(dp[i-1]>=0){
                dp[i]=dp[i-1]+nums[i];
            }else{
                dp[i]=nums[i];
            }
            max=dp[i]>max?dp[i]:max;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}
