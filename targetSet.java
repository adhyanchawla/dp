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
        coinSet();
    }
}
