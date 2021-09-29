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
        String str = "peeeeeeeeeeeeep";
        String[][]dp = new String[str.length()][str.length()];

        // for(int[] d : dp) {
        //     Arrays.fill(d, 0);
        // }

        //System.out.println(lpss(str, 0, str.length()-1, dp));
        //System.out.println(lpss_tab(str, 0, str.length() - 1, dp));
        System.out.println(lpss_dp(str, 0, str.length() - 1, dp));
        //display2D(dp);

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

    public static int lpss_tab(String str, int I, int J, int[][] dp) {
        int n = str.length();
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; i < n && j < n; i++, j++) {
                if(i >= j) {
                    dp[i][j] = (i == j) ? 1 : 0; 
                    continue;
                }
        
                if(str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[I][J];
    }

    //find the string obtained from longest palindrome subsequence
    public static String lpss_dp(String str, int I, int J, String[][] dp) {
        int n = str.length();
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; i < n && j < n; j++, i++) {
                if(i >= j) {
                    dp[i][j] = (i == j) ? str.charAt(i) + "": ""; 
                    continue;
                }
        
                if(str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = str.charAt(i) + dp[i + 1][j - 1] + str.charAt(j);
                } else {
                    dp[i][j] = dp[i + 1][j].length() > dp[i][j -1].length() ? dp[i + 1][j] : dp[i][j - 1];// Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[I][J];
    }

    public static void main(String[] args) {
        longestPalindromicSubsequnce();
    }


}