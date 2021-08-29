import java.util.*;

//two pointer approach
//fibonacci
public class dp1 {

    //fibonacci

    //faith mera ans (n-1) and (n-2)  pe depend krta hai
    public static void main(String[] args) {
        dpSolutions();
    }

    public static void dpSolutions() {
        int n = 10;
        int[] dp = new int[n + 1];

        //System.out.println(fibo_mem(n, dp));
        //System.out.println(fibo_tab(n, dp));
        System.out.println(fib_optimised(n));
        //display(dp);
    }

    public static void display(int[] dp) {
        for(int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for(int[] d : dp) {
            display(d);
        }

        System.out.println();
    }

    public static int fibo_mem(int n, int[] dp) {
        if(n <= 1) {
            return dp[n] = n;
        }

        if(dp[n] != 0) {
            return dp[n];
        }

        int ans = fibo_mem(n - 1, dp) + fibo_mem(n - 2, dp);

        return dp[n] = ans;
    }

    //tabulation
    public static int fibo_tab(int N, int[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n <= 1) {
                dp[n] = n;
                continue; // in memoization what we return in the base case is continued in tabulation
            }

            int ans = dp[n-1] + dp[n-2]; //fibo_mem(n - 1, dp) + fibo_mem(n - 2, dp);
            dp[n] = ans;
        }

        return dp[N];
    }

    //optimisation
    public static int fib_optimised(int N) {
        int a = 0, b = 1;
        for(int n = 2; n <= N; n++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return b;
    }

}