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

    //====================================================================================================================
    //longest palindrome substring
    public static void longestPalindromicSubstring() {
        String str = "lgeekfkeeg";
        int n = str.length();
        int[][] dp = new int[n][n];

        System.out.println(lps(str, dp));
        display2D(dp);
    }

    public static String lps(String str, int[][] dp) {
        int n = str.length();
        int mi = 0;
        int mj = 0;
        int count = 0;
        int max = 0;
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; i < n && j < n; i++, j++) {
                if(gap == 0) {
                    dp[i][j] = 1;
                } else if(gap == 1 && str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = 2;
                } else if(str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] > 0) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }

                if(dp[i][j] != 0) {
                    count++; //count of all the palindromic substrings
                }

                if(max < dp[i][j]) {
                    max = dp[i][j]; //max length of the substring which is palindrome
                    mi = i;
                    mj = j;
                }
            }
        }
        return str.substring(mi, mj + 1); //returns the longest palindrome substring
    }
    //==========================================================================================================
    //lcss
    public static void longestCommonSubsequence() {
        String s1 = "abcde";
        String s2 = "ace";
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        // for(int[]d : dp) {
        //     Arrays.fill(d, -1);
        // }
        System.out.println(lcss_dp(s1, s2, n, m, dp));
        display2D(dp);

    }

    public static int lcss(String s1, String s2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1) {
            return dp[n][m];
        }

        if(s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = lcss(s1, s2, n - 1, m - 1, dp) + 1;
        } else {
            return dp[n][m] = Math.max(lcss(s1, s2, n - 1, m, dp), lcss(s1, s2, n, m - 1, dp));
        }
    }

    public static int lcss_dp(String s1, String s2, int N, int M, int[][] dp) {
        for(int n = 0; n <= N; n++) {
            for(int m = 0; m <= M; m++) {
                if(n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }
        
                if(s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                } else {
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
                }
            }
        }
        return dp[N][M];
    }

    //==============================================================================================
    //lc 1035
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for(int[]d : dp) {
            Arrays.fill(d, -1);
        }
        return maxLines(nums1, nums2, nums1.length, nums2.length, dp);
    }
    
    public static int maxLines(int[] arr1, int[] arr2, int i, int j, int[][] dp) {
        if(i == 0 || j == 0) {
            return dp[i][j] = 0;
        }
        
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        
        if(arr1[i - 1] == arr2[j - 1]) {
            return dp[i][j] = 1 + maxLines(arr1, arr2, i - 1, j - 1, dp);
        } else {
            return dp[i][j] = Math.max(maxLines(arr1, arr2, i, j - 1, dp), maxLines(arr1, arr2, i - 1, j, dp));
        }
    }

    public static int maxLines_dp(int[] arr1, int[] arr2, int N, int M, int[][] dp) {
        for(int n = 0; n <= N; n++) {
            for(int m = 0; m <= M; m++) {
                    if(n == 0 || m == 0) {
                        dp[n][m] = 0;
                        continue;
                    }

            if(arr1[n - 1] == arr2[m - 1]) {
                dp[n][m] = 1 + dp[n - 1][m -1];
            } else {
                dp[n][m] = Math.max(dp[n][m -1], dp[n - 1][m]);
                }
            }
        }
    
        return dp[N][M];
    }

    public static void main(String[] args) {
        //longestPalindromicSubsequnce();
        //longestPalindromicSubstring();
        longestCommonSubsequence();
    }
}