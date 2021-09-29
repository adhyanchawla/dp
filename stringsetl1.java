import java.util.*;

public class stringsetl1 {

    public static void display(int[] dp) {
        for(int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for(int[]d : dp) {
            display(d);
        }
    }

    //==================================================================================

    public static void longestPalindromicSubsequnce() {
        String str = "lpecdcfpc";
        int[][]dp = new int[str.length()][str.length()];

        for(int[] d : dp) {
            Arrays.fill(d, 0);
        }

        System.out.println(lpss(str, 0, str.length()-1, dp));
        display2D(dp);

    }

    //longest palindrome subsequence
    public static int lpss(String str, int i, int j, int[][] dp) {
        if(i >= j) {
            return dp[i][j] = (i == j) ? 1 : 0; 
        }

        if(dp[i][j] != 0) {
            return dp[i][j];
        }

        if(str.charAt(i) == str.charAt(j)) {
            return dp[i][j] = (lpss(str, i + 1, j - 1, dp) + 2);
        } else {
            return dp[i][j] = Math.max(lpss(str, i + 1, j, dp), lpss(str, i, j - 1, dp));
        }

    }

    public static void main(String[] args) {
        longestPalindromicSubsequnce();
    }


}