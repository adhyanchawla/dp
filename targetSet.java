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
        for(int tar = 0; tar <= Tar; tar++) {
            if(tar == 0) {
                dp[tar] = 1;
                continue;
            }
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

    public static void coinSet() {
        int[] coins = {2, 3, 5, 7};
        int tar = 10;
        int[] dp = new int[tar + 1];
        //Arrays.fill(dp, -1);
        //System.out.println(permutation_memo(coins, tar, dp));
        System.out.println(permutation_tab(coins, tar, dp));
        display(dp);
    }

    public static void main(String[] args) {
        coinSet();
    }
}
