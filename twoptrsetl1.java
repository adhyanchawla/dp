import java.util.*;

public class twoptrsetl1 {
    
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

    //===========================================================================

    //fibonacci set
    //memoization

    public static int fibo_memo(int n, int[] dp) {
        if(n <= 1) {
            return dp[n] = n;
        }

        if(dp[n] != -1) {
            return dp[n];
        }

        int fibn = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);

        return dp[n] = fibn;
    }


    //fibonacci tabulation
    public static int fibo_tab(int N, int[] dp) {

        for(int n = 0; n <= N; n++) {
            if(n <= 1) {
                dp[n] = n;
                continue;
            }
    
            int fibn = dp[n - 1] + dp[n - 2];
            dp[n] = fibn;
        }
        return dp[N];
    }

    //fibonacci optimized
    public static int fib_opti(int n) {
        int a = 0, b = 1;
        for(int i = 2; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void fun() {
        int n = 6;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println(fib_opti(n));
        //display(dp);
    }

    //===================================================================================

    //min cost climbing stairs lc 746
    //memoization
    public static int minCost(int[] cost, int[] dp, int idx) {
        if(idx <= 1) {
            return dp[idx] = cost[idx];
        }

        if(dp[idx] != -1) {
            return dp[idx];
        }

        int costWithOneJump = minCost(cost, dp, idx - 1);
        int costWithTwoJump = minCost(cost, dp, idx - 2);

        int min = Math.min(costWithOneJump, costWithTwoJump) + (idx != cost.length ? cost[idx] : 0);

        return dp[idx] = min;
    }

    public static int minCost_tab(int[] dp, int[] cost) {

        for(int n = 0; n <= cost.length; n++) {
            if(n <= 1) {
                dp[n] = cost[n];
                continue;
            }
    
    
            int costWithOneJump = dp[n - 1];
            int costWithTwoJump = dp[n - 2];
    
            int min = Math.min(costWithOneJump, costWithTwoJump) + (n != cost.length ? cost[n] : 0);
            dp[n] = min;
        }

        return dp[cost.length];
    }

    public static int minCost_opti(int[] cost) {
        int a = cost[0], b = cost[1];

        for(int i = 2; i < cost.length; i++) {
            int minVal = Math.min(a, b) + cost[i];
            a = b;
            b = minVal;
        }

        return Math.min(a, b);

    }

    public static void fun1() {
        int[] cost = {1, 100, 1, 1, 100, 1, 1, 100};
        int[] dp = new int[cost.length + 1];
        Arrays.fill(dp, -1);
        //System.out.println(minCost_tab(dp, cost));
        System.out.println(minCost_opti(cost));
        //display(dp);
    }

    //====================================================================================
    
    //board path using dice
    public static int boardPath_mem(int si, int ei, int[] dp) {
        if(si == ei) {
            return dp[si] = 1;
        }

        if(dp[si] != -1) {
            return dp[si];
        }

        int count = 0;

        for(int i = 1; i <= 6 && si + i <= ei; i++) {
            count += boardPath_mem(si + i, ei, dp);
        }
        
        return dp[si] = count;
    }

    public static int boardPath_tab(int si, int ei, int[] dp) {
        for(int i = ei; i >= si; i--) {
            if(i == ei) {
                dp[ei] = 1;
                continue;
            }
    
            int count = 0;
    
            for(int j = 1; j <= 6 && i + j <= ei; j++) {
                count += dp[i + j];
            }

            dp[i] = count;
            
        }
        return dp[si];
    }

    public static int boardPath_opti(int si, int ei) {
        LinkedList<Integer> ll = new LinkedList<>();

        ll.addLast(1);
        ll.addLast(1);

        for(int i = 2; i <= ei; i++) {
            if(ll.size() <= 6) {
                ll.addLast(ll.getLast() * 2);
            } else {
                ll.addLast(ll.getLast() * 2 - ll.removeFirst());
            }
        }
        return ll.getLast();
    }

    public static void fun2() {
        int[]dp = new int[11];
        Arrays.fill(dp, -1);
        //System.out.println(boardPath_mem(0, 10, dp));
        //System.out.println(boardPath_tab(0, 10, dp));
        System.out.println(boardPath_opti(0, 10));
        //display(dp);
    }

    //====================================================================================

    //maze path with one jump
    public static void fun3() {
        int[][] dir = {{0, 1}, {1, 0}, {1, 1}};
        int[][]dp = new int[3][3];
        //System.out.println(mazePathOneJump_memo(dir, dp, 0, 0, 2, 2));
        System.out.println(mazePathWithOneJump_tab(dir, dp, 0, 0, 2, 2));
        //display2D(dp);
    }

    public static int mazePathOneJump_memo(int[][] dir, int[][] dp, int sr, int sc, int er, int ec) {
        if(sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if(dp[sr][sc] != 0) {
            return dp[sr][sc];
        }

        int count = 0;

        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePathOneJump_memo(dir, dp, r, c, er, ec);
            }
        }

        return dp[sr][sc] = count;
    }

    public static int mazePathWithOneJump_tab(int[][] dir, int [][] dp, int sr, int sc, int er, int ec) {

        for(int i = er; i >= sr; i--) {
            for(int j = ec; j >= sc; j--) {
                if(i == er && j == ec) {
                    dp[i][j] = 1;
                    continue;
                }

                int count = 0;

                for(int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if(r >= 0 && c >= 0 && r <= er && c <= ec) {
                        count += dp[r][c];
                    }
                }
        
                dp[i][j] = count;
            }
            
        }

        return dp[0][0];
    }

    //===============================================================================================

    //maze path with infinite jump
    public static void fun4() {
        int[][] dir = {{0, 1}, {1, 0}, {1, 1}};
        int[][]dp = new int[3][3];
        System.out.println(mazePathInfiniteJump_memo(dir, dp, 0, 0, 2, 2));
        //System.out.println(mazePathWithInfiniteJump_tab(dir, dp, 0, 0, 2, 2));
        display2D(dp);
    }

    public static int mazePathInfiniteJump_memo(int[][] dir, int[][] dp, int sr, int sc, int er, int ec) {
        if(sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if(dp[sr][sc] != 0) {
            return dp[sr][sc];
        }

        int count = 0;

        for(int d = 0; d < dir.length; d++) {
            //rad <= Math.max(m, n); use this here instead of er, ec
            for(int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad*dir[d][0];
                int c = sc + rad*dir[d][1];

                if(r >= 0 && c >= 0 && r <= er && c <= ec) {
                    count += mazePathInfiniteJump_memo(dir, dp, r, c, er, ec);
                } else break;
            }
        }

        return dp[sr][sc] = count;
    }

    //=========================================================================================

    //lc 64 min path sum in a grid using memoization and tabulation
    public static void minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dir = {{0, 1}, {1, 0}};
        int[][] dp = new int[m][n];
        System.out.println(minPath_tab(grid, dir, dp, 0, 0, m - 1, n - 1));
        //display2D(dp);
    }
    
    public static int minPath(int[][] grid, int sr, int sc, int er, int ec, int[][] dp, int[][] dir) {
        int m = grid.length, n = grid[0].length;
        if(sr == er && sc == ec) {
            return dp[sr][sc] = grid[er][ec];
        }
        
        if(dp[sr][sc] != 0) {
            return dp[sr][sc];
        }
        
        int min = (int)1e9;
        
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0], c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r < m && c < n) {
                min = Math.min(min, minPath(grid, r, c, er, ec, dp, dir) + grid[sr][sc]);
            }
        }
        
        return dp[sr][sc] = min;
    }

    public static int minPath_tab(int[][] grid, int[][] dir, int[][] dp, int SR, int SC, int ER, int EC) {
        int m = grid.length, n = grid[0].length;
        for(int sr = ER; sr >= SR; sr--) {
            for(int sc = EC; sc >= SC; sc--) {
                if(sr == ER && sc == EC) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }
                
                int min = (int)1e9;
                
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0], c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < m && c < n) {
                        min = Math.min(min, minPath(grid, r, c, ER, EC, dp, dir) + grid[sr][sc]);
                    }
                }
                
                dp[sr][sc] = min;
            }
        }

        return dp[0][0];
    }

    public static void fun5() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        minPathSum(grid);
    }

    //======================================================================================
    //friends pair
    public static int friendsPair(int n, int[] dp) {
        return friendPair_opti(n);
    }

    public static int friendPair(int n, int[] dp) {
        if(n <= 1) {
            return dp[n] = 1;
        }

        if(dp[n] != 0) {
            return dp[n];
        }

        int count = friendPair(n - 1, dp) + friendPair(n - 2, dp) * (n - 1);

        return dp[n] = count;
    }

    public static int friendPair_tab(int n, int[] dp) {

        for(int i = 0; i <= n; i++) {
            if(i <= 1) {
                dp[i] = 1;
                continue;
            }

            int count = dp[i - 1] + dp[i - 2] * (i - 1);
            dp[i] = count;
        }

        return dp[n];
    }

    public static int friendPair_opti(int n) {
        int a = 1, b = 1;
        for(int i = 2; i <= n; i++) {
            int count = b + a *(i - 1);
            a = b;
            b = count;
        }

        return b;
    }

    public static void fun6() {
        int n = 4;
        int[] dp = new int[n + 1];
        System.out.println(friendsPair(n, dp));
        //display(dp);
    }

    //===============================================================================================
    
    public static int maxGold(int n, int m, int M[][])
    {
        int[][] dir = {{0, 1}, {-1, 1}, {1, 1}};
        int[][] dp = new int[n][m];

        for(int[] d : dp) Arrays.fill(d, -1);
        
        // int max = 0;
        // for(int i = 0; i < n; i++) {
        //     max = Math.max(max, maximum(M, i, 0, n - 1, m - 1, dp, dir));
        // }
        System.out.println(maximum_tab(M, dp, dir));        
        display2D(dp);
        return maximum_tab(M, dp, dir);
        // code here
    }
    
    
    public static int maximum(int[][] board, int sr, int sc, int er, int ec, int[][] dp, int[][] dir) {
        int m = board.length, n = board[0].length;
        if(sc == ec) {
            return dp[sr][sc] = board[sr][sc];
        }
        
        if(dp[sr][sc] != 0) {
            return dp[sr][sc];
        }
        
        int max = 0;
        
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0], c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r <= m - 1 && c <= n - 1) {
                max = Math.max(max, maximum(board, r, c, er, ec, dp, dir) + board[sr][sc]);
            }
        }
        
        return dp[sr][sc] = max;
    }

    public static int maximum_tab(int[][] board, int[][]dp, int[][] dir) {
        int m = board.length, n = board[0].length;
        for(int sc = board[0].length - 1; sc >= 0; sc--) {
            for(int sr = 0; sr < board.length; sr++) {
                if(sc == board[0].length - 1) {
                    dp[sr][sc] = board[sr][sc];
                    continue;
                }

                int max = 0;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0], c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r <= m - 1 && c <= n - 1) {
                        max = Math.max(max, dp[r][c] + board[sr][sc]);
                    }
                }

                dp[sr][sc] = max;
            }
        } 
        
        int fmax = 0;
        for(int i = 0; i < board.length; i++) {
            fmax = Math.max(fmax, dp[i][0]);
        }

        return fmax;
    }

    public static void fun7() {
        int[][] M = {{1, 3, 3}, {2, 1, 4}, {0, 6, 4}};
        System.out.println(maxGold(M.length, M[0].length, M));
    }


    //===============================================================================================

    public static void numDecodings(String s) {
        int[]dp = new int[s.length() + 1];
        //Arrays.fill(dp , -1);
        System.out.println(decodeWays_tab(s, dp, s.length()));
        display(dp);
    }
    
    
    public static int decodeWays(String s, int idx, int[] dp) {
        if(idx == 0) {
            return dp[idx] = 1;
        }
        
        if(dp[idx] != -1) {
            return dp[idx];
        }

        if(s.charAt(idx - 1) == '0') {
            return dp[idx] = 0;
        }
        
        int count = 0;
        
        char ch = s.charAt(idx - 1);
        if(ch - '0' != 0) {
            count += decodeWays(s, idx - 1, dp);
        }
        
        if(idx > 1) {
            int num = (ch - '0') + (s.charAt(idx - 2) - '0') * 10;
            if(num >= 10 && num <= 26) {
                count += decodeWays(s, idx - 2, dp);
            }
        }
        
        return dp[idx] = count;
    }


    public static int decodeWays_tab(String s, int[] dp, int N) {
        for(int n = 0; n <= N; n++) {
            if(n == 0) {
                dp[n] = 1;
                continue;
            }

    
            if(s.charAt(n - 1) == '0') {
                dp[n] = 0;
                continue;
            }
            
            int count = 0;
            
            char ch = s.charAt(n - 1);
            if(ch - '0' != 0) {
                count += dp[n - 1];
            }
            
            if(n > 1) {
                int num = (ch - '0') + (s.charAt(n - 2) - '0') * 10;
                if(num >= 10 && num <= 26) {
                    count += dp[n - 2];
                }
            }
            
            dp[n] = count;

        }
        
        return dp[N];
    }

    //===============================================================================================
    
    
    public static void main(String[] args) {
        //fun(); // fibonacci
        //fun1(); //min cost climbing stairs
        //fun2(); // board path using dice;
        //fun3(); //maze path with one jump
        //fun4(); //maze path with infinite jumps
        //fun5(); //maze path with min sum
        //fun6(); //friendsPair
        //fun7(); //goldmine gfg
        numDecodings("226");
    }
}
