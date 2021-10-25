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

    public static void cutSet01() {
        int[] arr = {4, 2, 3, 2, 3, 5, 4};
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int []d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(mcm_memo(arr, 0, arr.length - 1, dp));
        display2D(dp);
    }
    public static void main(String[] args) {
        cutSet01();
    }
}