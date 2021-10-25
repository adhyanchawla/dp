import java.util.*;

public class cutSet {
    
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

    //===========================================================================

    public static int mcm_memo(int[] arr, int si, int ei, int[][] dp) {
        if(si + 1 == ei) {
            return dp[si][ei] = 0;
        }

        if(dp[si][ei] != -1) {
            return dp[si][ei];
        }

        int minRes = (int)1e8;
        for(int cut = si + 1; cut < ei; cut++) {
            int leftRes = mcm_memo(arr, si, cut, dp);
            int rightRes = mcm_memo(arr, cut, ei, dp);

            int res = leftRes + arr[cut] * arr[si] * arr[ei] + rightRes;

            if(res < minRes) {
                minRes = res;
            }
        }

        return dp[si][ei] = minRes;
    }

    public static int mcm_tabu(int[] arr, int SI, int EI, int[][] dp) {
        int n = arr.length;
        for(int gap = 1; gap < n; gap++) {
            for(int si = 0, ei = gap; si < n && ei < n; si++, ei++) {
                if(si + 1 == ei) {
                    dp[si][ei] = 0;
                    continue;
                }
        
                int minRes = (int)1e8;
                for(int cut = si + 1; cut < ei; cut++) {
                    int leftRes = dp[si][cut];//mcm_memo(arr, si, cut, dp);
                    int rightRes = dp[cut][ei];//mcm_memo(arr, cut, ei, dp);
        
                    int res = leftRes + arr[cut] * arr[si] * arr[ei] + rightRes;
        
                    if(res < minRes) {
                        minRes = res;
                    }
                }
        
                dp[si][ei] = minRes;
            }
        }

        return dp[SI][EI];
    }

    //=================================================================================================
    // https://practice.geeksforgeeks.org/problems/brackets-in-matrix-chain-multiplication1024/1#
    //matrix chain order
    public static String matrixChainOrder(int p[], int n){
        // code here
        String[][] sdp = new String[n][n];
        int[][] dp = new int[n][n];
        
        for(String[] s : sdp) {
            Arrays.fill(s, "");
        }
        
        int SI = 0;
        int EI = n - 1;
        
        for(int gap = 1; gap < n; gap++) {
            for(int si = 0, ei = gap; si < n && ei < n; si++, ei++) {
                if(si + 1 == ei) {
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char)(si + 'A') + "";
                    continue;
                }
                
                int minRes = (int)1e9;
                String minStr = "";
                for(int cut = si + 1; cut < ei; cut++) {
                    int leftRes = dp[si][cut];
                    int rightRes = dp[cut][ei];
                    
                    int res = leftRes + p[si] * p[ei] * p[cut] + rightRes;
                    
                    if(res < minRes) {
                        minRes = res;
                        minStr = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                    }
                }
                
                dp[si][ei] = minRes;
                sdp[si][ei] = minStr;
            }
        }
        
        return sdp[SI][EI];
    }

    public static void cutSet01() {
        int[] arr = {4, 2, 3, 2, 3, 5, 4};
        int n = arr.length;
        int[][] dp = new int[n][n];
        // for(int []d : dp) {
        //     Arrays.fill(d, -1);
        // }

        //System.out.println(mcm_memo(arr, 0, arr.length - 1, dp));
        System.out.println(mcm_tabu(arr, 0, n - 1, dp));
        display2D(dp);
    }
    public static void main(String[] args) {
        cutSet01();
    }
}