import java.util.*;

public class lisset {

    public static void display(int[] dp ){
        for(int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for(int [] d : dp) {
            display(d);
        }
    }

    //====================================================================================
    //LIS lc 300
    //lis left to right
    public static int lengthOfLIS(int[] nums, int[] dp) {
        int omax = -(int)1e5;
        
        //int[] dp = new int[nums.length];
        
        dp[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            int max = -(int)1e5;
            for(int j = i - 1; j >= 0; j--) {
                if(nums[j] < nums[i]) {
                    max = Math.max(dp[j], max);   
                }
            }
            if(max != -(int)1e5) {
                dp[i] = max + 1;  
                if(dp[i] > omax) {
                omax = dp[i];
                }
            } else dp[i] = 1;
        }
        
        if(omax == -(int)1e5){
            return 1;
        }
        
        return omax ;
    }

    //=============================================================================


    public static int LIS_rec(int[] nums, int ei, int[] dp) {

        if(dp[ei] != -1) {
            return dp[ei];
        }

        int count = 1;
        for(int i = ei - 1; i >= 0; i--) {
            if(nums[i] < nums[ei])
            count = Math.max(count, LIS_rec(nums, i, dp));
        }

        return dp[ei] = count + 1;
    }

    public static void LISRec() {
        int maxLen = 1;
        int[] nums = {1, 15, 33, 51, 45, 100, 18, 9};
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            maxLen = Math.max(maxLen, LIS_rec(nums, i, dp));
        }

        System.out.println(maxLen);
    }

    //obtain the string containing the LIS
    //reverse engineering

    public static void LISString() {
        int[] nums = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15,14};
        int[] dp = new int[nums.length];
        dp = LIS(nums, dp);
        int dpMax = 0;
        int maxIdx = 0;
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            if(dp[i] > dpMax) {
                dpMax = dp[i];
                maxIdx = i;
            }
        }

        String ans = LIS_obtain(nums, dp, dpMax, maxIdx);
        System.out.println(ans);
    }

    public static String LIS_obtain(int[] nums, int[] dp, int dpMax, int maxIdx) {
        int n = nums.length;
        String s = dpMax + " ";
        for(int i = maxIdx; i >= 0; i--) {
            if(nums[i] < nums[maxIdx] && dp[i] == dpMax - 1) {
                s += nums[i] + " ";
                dpMax = dp[i];
                maxIdx = i;
            }
        }
        return s;
    }



    //======================================================================================

    public static void lis() {
        int[] nums = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15,14};
        int[] dp = new int[nums.length];
        System.out.println(lengthOfLIS(nums, dp));
        display(dp);
    }
    
    //=======================================================================================
    //https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
    public static void buildingBridges() {
        int[] N = {8, 1, 4, 3, 5, 2, 6, 7};
        int[] S = {1, 2, 3, 4, 5, 6, 7, 8};
        int[][] points = new int[N.length][2]; 

        for(int i = 0; i < N.length; i++) {
            points[i][0] = N[i];
            points[i][1] = S[i];
        }

        System.out.println(buildBridges(points));
    }

    public static int buildBridges(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            return a[0] - b[0];
        });

        //display2D(points);

        int[] dp = new int[points.length];

        int omax = 0;

        for(int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(points[i][1] > points[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if(omax < dp[i]) {
                omax = dp[i];
            }
        }

        //display(dp);
        return omax;
    }

    //=========================================================================================================================
    //minimum no of deletions to make array sorted
    public static int minDeletions(int[] nums) {
        int[]dp = new int[nums.length];
        int omax = 0;
        for(int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(nums[i] >= nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            omax = Math.max(omax, dp[i]);
        }

        return nums.length - omax;
    }

    //==========================================================================================================================
    // LDS -> left to Right
    public static int[] LDS_LR(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return dp;
    }

    // LDS -> right to left === LIS
    public static int[] LDS_RL(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return dp;
    }


    //===========================================================================================================================
    //max sum increasing subsequence
    //https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1#
    public static int maxSumIS(int arr[], int n)  
	{  
	    int msum = 0;
	    
	    int[] dp = new int[n];
	    
	    for(int i = 0; i < n; i++) {
	        dp[i] = arr[i];
	        for(int j = i - 1; j >= 0; j--) {
	            if(arr[i] > arr[j]) {
	                dp[i] = Math.max(dp[i], dp[j] + arr[i]);   
	            }
	        }
	        msum = Math.max(msum, dp[i]);
	    }
	    return msum;
	}  

    //===========================================================================================
    //maximum sum bitonic subsequence
        public static int[] LIS(int[] nums, int[] dp) {
        //int[]dp = new int[nums.length];
        // int max = 0;
        for(int i = 0; i < nums.length; i++) {
            //dp[i] = nums[i];
            for(int j = i - 1; j >= 0; j--) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }
            //max = Math.max(max, dp[i]);
        }
        
        return dp;
    }
    
    public static int[] LIS1(int[] nums, int[] dp) {
        //int[]dp = new int[nums.length];
        // int max = 0;
        for(int i = nums.length - 1; i >= 0; i--) {
            //dp[i] = nums[i];
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }
            //max = Math.min(max, dp[i]);
        }
        
        return dp;
    }
    
    public static int maxSumBS(int arr[], int n)
    {
        int[] d1 = new int[n];
        int[] d2 = new int[n];
        
        for(int i = 0; i < n; i++){
            d1[i] = arr[i];
            d2[i] = arr[i];
        }
        
        
        d1 = LIS(arr, d1);
        d2 = LIS1(arr, d2);
        
        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, d1[i] + d2[i] - arr[i]);
        }
        return max;
    }

    //===========================================================================================
    //longest decreasing subsequence
    // LDS -> right to left
    public static int[] LDS(int[] nums) {
        int[] dp = new int[nums.length];
        int omax = 0;
        for(int i = dp.length - 1; i >= 0; i--) {
            dp[i] = 1;
            for(int j = i + 1; j < dp.length; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            omax = Math.max(dp[i], omax);
        }

        return dp;
    }

    public static void lds() {
        int[] nums = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15,14};
        int[] ans = LDS(nums);
        display(ans);
    }

    //==============================================================================================
    //https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1#
    //longest bitonic subsequence
    public int[] LIS(int[] nums) {
        int[]dp = new int[nums.length];
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //max = Math.max(max, dp[i]);
        }
        
        return dp;
    }
    
    public int[] LIS1(int[] nums) {
        int[]dp = new int[nums.length];
        int max = (int)1e9;
        for(int i = nums.length - 1; i >= 0; i--) {
            dp[i] = 1;
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //max = Math.min(max, dp[i]);
        }
        
        return dp;
    }
    
    public int LongestBitonicSequence(int[] nums)
    {
        int[] d1 = LIS(nums);
        int[] d2 = LIS1(nums);
        
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            max = Math.max(max, d1[i] + d2[i] - 1);
        }
        return max;
        // Code here
    }


    //================================================================================================
    //Russian Dolls Envelopes
    public static int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) ->{
            if(a[0] != b[0])
            return a[0] - b[0]; 
            else return b[1] - a[1];
        });
        
        int[]dp = new int[envelopes.length];
        
        int max = 0;
        for(int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }

    //===================================================================================================
    //lc 673 No of LIS 
    public int findNumberOfLIS(int[] nums) {
        int[] count = new int[nums.length];
        int[] dp = new int[nums.length];
        
        int maxLen = 0, maxCount = 0;
        
        for(int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            count[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(nums[i] > nums[j]) {
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if(dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }   
            }
            if(maxLen < dp[i]) {
                maxLen = dp[i];
                maxCount = count[i];
            } else if(maxLen == dp[i]) {
                maxCount += count[i];
            }
        }
        return maxCount;
    }

    //==============================================================================================
    public static void main(String[] args) {
        //lis();
        //buildingBridges();
        //lds();
        LISString();
    }
}