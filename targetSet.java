import java.util.*;
public class targetSet {

    public static void display(int[] dp) {
        for(int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int [][] dp) {
        for(int[] d : dp) {
            display(d);
        }
        System.out.println();
    }

    //===================================================================================
    
    public static int permutation_memo(int[] arr, int tar, int[] dp) {
        if(tar == 0) {
            return dp[tar] = 1;
        }

        if(dp[tar] != -1) {
            return dp[tar];
        }

        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(tar - arr[i] >= 0) {
                count += permutation_memo(arr, tar - arr[i], dp);
            }
        }

        return dp[tar] = count;
    }

    //==============================================================================================

    public static int permutation_tab(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for(int tar = 0; tar <= Tar; tar++) {
            int count = 0;
            for(int i = 0; i < arr.length; i++) {
                if(tar - arr[i] >= 0) {
                    count += dp[tar - arr[i]]; //permutation_memo(arr, tar - arr[i], dp);
                }
            }
    
            dp[tar] = count;
        }

        return dp[Tar];
    }

    //=================================================================================================================

    public static int combination_memo(int[] arr, int n, int tar, int[][] dp) {
        if(tar == 0) {
            return dp[n][tar] = 1;
        }

        if(dp[n][tar] != -1) {
            return dp[n][tar];
        }

        int count = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(tar - arr[i] >= 0)
            count += combination_memo(arr, i + 1, tar - arr[i], dp);
        }

        return dp[n][tar] = count;
    }



    public static int combination_tab(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for(int ele : arr) {
            for(int tar = 0; tar <= Tar; tar++) {

                if(tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];
    }

    //================================================================================================================
    //lc 322
    public static int coinChange(int[] coins, int tar, int[] dp) {
        if(tar == 0) {
            return dp[tar] = 0;
        }
        
        if(dp[tar] != (int)1e9) {
            return dp[tar];
        }
        
        int minCount = (int)1e8;
        for(int ele : coins) {
            if(tar - ele >= 0) {
                minCount = Math.min(minCount, coinChange(coins, tar - ele, dp) + 1);
            }
        }
        
        return dp[tar] = minCount >= (int)1e8 ? (int)1e8 : minCount;
    }

    public static int coinChange_tab(int[] coins, int Tar, int[] dp) {
        //dp[0] = 0;
        for(int tar = 0; tar <= Tar; tar++) {
            
            if(tar == 0) {
                dp[tar] = 0;
                continue;
            }
            int minCount = (int)1e8;
            for(int ele : coins) {
                if(tar - ele >= 0) {
                    minCount = Math.min(minCount, dp[tar - ele] + 1);
                }
            }
            dp[tar] = minCount >= (int)1e8 ? (int)1e8 : minCount;
        }
        
        return dp[Tar];
    }

    //===========================================================================================

    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    //same as coin change where RHS is the target and the coefficients is the coins array 
    //find different combinations such that LHS == RHS

    //===========================================================================================

    // https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
    public static int ss(int[] coins, int n, int tar, int[][] dp) {
        if(tar == 0 || n == 0) {
            if(tar == 0)
                return dp[n][tar] = 1;
            return dp[n][tar] = 0;    
        }

        if(dp[n][tar] != -1) {
            return dp[n][tar];
        }

        int count = 0;
        if(tar - coins[n - 1] >= 0) {
            count += ss(coins, n - 1, tar - coins[n - 1], dp);
        }

        count += ss(coins, n - 1, tar, dp);

        return dp[n][tar] = count;

    }

    public static int ss_tab(int[] coins, int N, int Tar, int[][] dp) {
        for(int n = 0; n <= N; n++) {
            for(int tar = 0; tar <= Tar; tar++) {
                if(n == 0 || tar == 0) {
                    if(tar == 0) {
                        dp[n][tar] = 1;
                        continue;
                    }
                    dp[n][tar] = 0;
                    continue;
                }

                int count = 0;
                if(tar - coins[n - 1] >= 0) {
                    count += dp[n - 1][tar - coins[n - 1]];
                }

                count += dp[n - 1][tar];

                dp[n][tar] = count;
            }
        }

        return dp[N][Tar];
    }

    //================================================================================================
    // https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1#
    public static int knapSack(int W, int wt[], int val[], int n) 
    { 
         // your code here 
         int[][] dp = new int[n + 1][W + 1];
         for(int[] d : dp) {
             Arrays.fill(d, -1);
         }
         int ans = knapSack(wt, val, n, W, dp);
         return ans;
    } 
    
    public static int knapSack(int[] wt, int[] val, int n, int W, int[][] dp) {
        if(W == 0 || n == 0) {
            return dp[n][W] = 0;
        }
        
        if(dp[n][W] != -1) {
            return dp[n][W];
        }
        
        int maxVal = 0;
        if(W - wt[n - 1] >= 0) {
            maxVal = Math.max(maxVal, knapSack(wt, val, n - 1, W - wt[n - 1], dp) + val[n - 1]);
        }
        
        maxVal = Math.max(maxVal, knapSack(wt, val, n - 1, W, dp));
        
        return dp[n][W] = maxVal;
    }

    //============================================================================================================
    // https://practice.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1#
    //unbounded knapsack
    public static int knapSack(int N, int W, int val[], int wt[])
    {
        int[][] dp = new int[N + 1][W + 1];
        for(int[] d : dp) {
            Arrays.fill(d, -1);
        }
        
        int ans = uknapSack(val, wt, N, W, dp);
        return ans;
    }

    public static int uknapsack_tab(int[] val, int[] wt, int N, int WI, int[] dp) {
        for(int W = 0; W <= WI; W++) {
            for(int i = 0; i < N; i++) {
                if(W - wt[i] >= 0) {
                    dp[W] = Math.max(dp[W - wt[i]] + val[i], dp[W]);
                }
            }
        }

        return dp[WI];
    }
    
    public static int uknapSack(int[] val, int[] wt, int n, int W, int[][] dp) {
        if(n == 0 || W == 0) {
            return dp[n][W] = 0;
        }
        
        if(dp[n][W] != -1) {
            return dp[n][W];
        }
        
        int maxVal = 0;
        
        if(W - wt[n - 1] >= 0) {
            maxVal = Math.max(maxVal, uknapSack(val, wt, n, W - wt[n - 1], dp) + val[n - 1]);
        }
        
        maxVal = Math.max(maxVal, uknapSack(val, wt, n - 1, W, dp));
        
        return dp[n][W] = maxVal;
    }

    //============================================================================================================
    // lc 494: Partition into equal subsets
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for(int ele : nums) sum += ele;
        
        int n = nums.length;
        if(n ==  0 || sum % 2 != 0) return false;
        int[][] dp = new int[n + 1][sum/2 + 1];
        
        for(int[] d: dp) {
            Arrays.fill(d, -1);
        }
        
        
        return canPartition(nums, n, sum/2, dp) == 1;
    }
    
    public static int canPartition(int[] nums, int n, int tar, int [][] dp) {
        if(n == 0 || tar == 0) {
            return dp[n][tar] = (tar == 0) ? 1 : 0;
        }
        
        if(dp[n][tar] != -1) {
            return dp[n][tar];
        }
        
        boolean res = false;
        if(tar - nums[n - 1] >= 0) {
            res = res || canPartition(nums, n - 1, tar - nums[n - 1], dp) == 1;
        }
        
        res = res || canPartition(nums, n - 1, tar, dp) == 1;
        
        return dp[n][tar] = (res) ? 1 : 0;
    }

    //===================================================================================================================

    public static void subsetSumSet() {
        int[] coins = {2, 3, 5, 7};
        int tar = 10;
        int[][] dp = new int[coins.length + 1][tar + 1];
        // for(int[] d : dp) {
        //     Arrays.fill(d, -1);
        // }
        
        System.out.println(ss_tab(coins, coins.length, tar, dp));

        //System.out.println(ss(coins, coins.length, tar, dp));
        display2D(dp);
    }

    public static void coinSet() {
        int[] coins = {2, 3, 5, 7};
        int tar = 10;

        // int[][] dp = new int[coins.length + 1][tar + 1];
        // for(int[] d : dp) Arrays.fill(d, - 1);
        // System.out.println(combination_memo(coins, coins.length, tar, dp));
        // display2D(dp);
        int[] dp = new int[tar + 1];
        System.out.println(combination_tab(coins, tar, dp));
        display(dp);
        //Arrays.fill(dp, -1);
        //System.out.println(permutation_memo(coins, tar, dp));
        //System.out.println(permutation_tab(coins, tar, dp));
        //display(dp);
    }


    public static void main(String[] args) {
        //coinSet();
        subsetSumSet();
    }
}
