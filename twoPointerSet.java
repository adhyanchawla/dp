public class twoPointerSet {
    

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

    //Fibonacci series============================================================================================================================

    public static int fibo_mem(int n, int[] dp) {
        if(n <= 1) {
            return dp[n] = n;
        }

        if(dp[n] != 0) return dp[n];

        int ans = fibo_mem(n - 1, dp) + fibo_mem(n - 2, dp);

        return dp[n] = ans;
    }

    public static int fibo_tab(int N, int[] dp) {

        for(int n = 0; n <= N; n++) {
            if(n <= 1) {
                dp[n] = n;
                continue;
            }

            int ans = dp[n - 1] + dp[n - 2];
            dp[n] = ans;
        }

        return dp[N];
    }

    public static int fibo_optimised(int n) {
        int a = 0, b = 1;

        for(int i = 2; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return b;
    }

    public static void fibo() {
        int n = 10;
        int[] dp = new int[n + 1];
        //System.out.println(fibo_mem(n, dp));
        //System.out.println(fibo_tab(n, dp));
        System.out.println(fibo_optimised(n));
        //display(dp);
    }

    //minPath Climbing Stairs=================================================================================================================

        //dp size n + 1 to store the result in its last index as we need to use n as the last index of cost array
        public static int minCostClimbingStairs(int[] cost) {
            int[] dp = new int[cost.length + 1];
            return minCost_tab(cost, 0, cost.length, dp);
        }
        //faith: (n - 1) aur (n - 2) se minimum path le aaoga aur apna cost daal dunga
        public static int minCost_mem(int[] cost, int[] dp, int idx) {
        
            // 0 ya 1 se start kr skta hai
        if(idx <= 1) return dp[idx] = cost[idx];
        
        if(dp[idx] != 0) return dp[idx];
        
        
        int oneStep = minCost_mem(cost, dp, idx - 1);
        int twoStep = minCost_mem(cost, dp, idx - 2);
        // 0 kyuki jab dp ka last idx pe phonchega toh cost ka idx exist ni krega
        int minPath = Math.min(oneStep, twoStep) + ((idx != cost.length) ? cost[idx] : 0);
        
        return dp[idx] = minPath;
    }
    
    public static int minCost_tab(int[] cost, int SR, int ER, int[] dp) {
        for(int er = SR; er <= ER; er++) {
            if(er <= 1) {
                dp[er] = cost[er];
                continue;
            }
            
            int minPath1 = dp[er - 1];
            int minPath2 = dp[er - 2];
            
            int minPath = Math.min(minPath1, minPath2) + (er != cost.length ? cost[er] : 0);
            
            dp[er] = minPath;
        }
        
        return dp[ER];
    }


    public static int minCost_optimised(int[] cost) {
        int a = cost[0], b = cost[1];

        for(int i = 2; i <= cost.length; i++) {
            int min = Math.min(a, b) + (i != cost.length ? cost[i] : 0);
            a = b;
            b = min;
        }
        
        return b;
    }

    public static void main(String[] args) {
        fibo();
    }
}
