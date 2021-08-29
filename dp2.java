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
        //int[][]dp = new int[4][4];
        int sr = 0, sc = 0, er = 3, ec = 3;

        int[][] dir = {{0, 1}, {1, 0}, {1, 1}};
        int[] dp = new int[11];
        //System.out.println(mazePathWithMulipleJumps_mem(sr, sc, er, ec, dp, dir));
        System.out.println(boardPath_tab(0, 10, dp));
        display(dp);
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

    public static int mazePathWithMulipleJumps_mem(int sr, int sc, int er, int ec, int[][] dp, int[][] dir ) {
        if(sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        int count = 0;

        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0], c = sc + dir[d][1];

            while(r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                count += mazePathWithMulipleJumps_mem(r, c, er, ec, dp, dir);
                r += dir[d][0];
                c += dir[d][1];
            }
        }

        return dp[sr][sc] = count;
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

    //===========================================================================

    //boardpath

    //using dice
    public static int boardPath(int sr, int er, int[] dp) {
        if(sr == er) {
            return dp[sr] = 1;
        }

        if(dp[sr] != 0) return dp[sr];

        int count = 0;

        for(int dice = 1; dice <= 6 && dice + sr <= er; dice++) {
            count += boardPath(sr + dice, er, dp);
        }

        return dp[sr] = count;
    }

    public static int boardPath_tab(int SR, int ER, int[] dp) {

        for(int sr = ER; sr >= SR; sr--) {
            if(sr == ER) {
                dp[sr] = 1;
                continue;
            }

            int count = 0;
            for(int dice = 1; dice <= 6 && dice + sr <= ER; dice++) {
                count += dp[sr + dice];
            }

            dp[sr] = count;
        }

        return dp[SR];
    }    
}
