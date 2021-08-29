import java.util.*;

public class dp2 {
    
    //faith: mai ek kadam aage ya ek kadam neeche badunga jab mera rem distance 0 ho jaye toh return krdunga 1 path
    // my ans is dependent on ans obtained in the prev cell
    public static void main(String[] args) {
        dpSolutions();
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

    public static void dpSolutions() {
        int[][] board = new int[3][3];
        int[][]dp = new int[3][3];
        int sr = 0, sc = 0, er = 2, ec = 2;

        int[][] dir = {{0, 1}, {1, 0}, {1, 1}};
        System.out.println(mazePathWithMultipleJumps(sr, sc, er, ec, dp, dir));
        display2D(dp);
    }

    //we need to store the count in the cells we covered to calc the paths to reach to that cell so we need 2D dp
    public static int mazePathsWithOneJump(int[][] board, int sr, int sc, int[][] dp) {
        int n = board.length, m = board[0].length;
        if(sr == n - 1 && sc == m - 1) {
            return dp[sr][sc] = 1;
        }

        if(dp[sr][sc] != 0) {
            return dp[sr][sc];
        }

        int count = 0;
        if(sr + 1 <= m - 1) {
            count += mazePathsWithOneJump(board, sr + 1, sc, dp);
        }

        if(sc + 1 <= n - 1) {
            count += mazePathsWithOneJump(board, sr, sc + 1, dp);
        }

        if(sr + 1 <= n - 1 && sc + 1 <= m - 1) {
            count += mazePathsWithOneJump(board, sr + 1, sc + 1, dp);
        }

        return dp[sr][sc] = count;
    }

    

    public static int mazePathsWithOneJump_tab(int SR, int SC, int ER, int EC, int[][] dp, int[][] dir) {
        for(int sr = ER; sr >= SR; sr--) {
            for(int sc = EC; sc >= SC; sc--) {
                if(sr == ER && sc == EC) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        count += dp[r][c];
                    }
    
                }
    
                dp[sr][sc] = count;
            }

        }

        return dp[SR][SC];
    }

    public static int mazePathWithMultipleJumps(int SR, int SC, int ER, int EC, int[][] dp, int[][] dir) {
        for(int sr = ER; sr >= SR; sr--) {
            for(int sc = EC; sc >= SC; sc--) {
                if(sr == ER && sc == EC) {
                    dp[sr][sc] = 1;
                    continue;
                }


                int count = 0;

                for(int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    while(r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                        count += dp[r][c];
                        r += d[0];
                        c += d[1];
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    
}
