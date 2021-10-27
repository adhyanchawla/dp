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

    public static void cutSet01() {
        int[] arr = {4, 2, 3, 2, 3, 5, 4};
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int []d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(mcm_memo(arr, 0, arr.length - 1, dp));
        //System.out.println(mcm_tabu(arr, 0, n - 1, dp));
        display2D(dp);
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

    //==============================================================================================
    // https://www.geeksforgeeks.org/minimum-maximum-values-expression/
    public static class minMaxPair {
        int minVal = (int)1e9;
        int maxVal = -(int)1e9;

        minMaxPair(int minVal, int maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }

        minMaxPair(){

        }
    }

    public static minMaxPair evaluation(minMaxPair lp, minMaxPair rp, char operator) {
        if(operator == '+') {
            return new minMaxPair(lp.minVal + rp.minVal, lp.maxVal + rp.maxVal);
        } else {
            return new minMaxPair(lp.minVal * rp.minVal, lp.maxVal * rp.maxVal);
        }
    }

    public static minMaxPair minMaxEvaluation(String str, int si, int ei, minMaxPair[][] dp) {
        if(si == ei) {
            int val = str.charAt(si) - '0';
            return new minMaxPair(val, val);
        }

        if(dp[si][ei] != null) {
            return dp[si][ei];
        }

        minMaxPair myRes = new minMaxPair();
        for(int cut = si + 1; cut < ei; cut+=2) {
            minMaxPair lp = minMaxEvaluation(str, si, cut - 1, dp);
            minMaxPair rp = minMaxEvaluation(str, cut + 1, ei, dp);

            minMaxPair eval = evaluation(lp, rp, str.charAt(cut));

            myRes.minVal = Math.min(myRes.minVal, eval.minVal);
            myRes.maxVal = Math.max(myRes.maxVal, eval.maxVal);
        }

        return dp[si][ei] = myRes;
    }

    //if operators are + - * /
    public static minMaxPair evaluation01(minMaxPair lp, minMaxPair rp, char operator) {
        if(operator == '+') {
            return new minMaxPair(lp.minVal + rp.minVal, lp.maxVal + rp.maxVal);
        } else if(operator == '-') {
            return new minMaxPair(lp.minVal - rp.minVal, lp.maxVal - rp.maxVal);
        } else if(operator == '*') {
            return new minMaxPair(lp.minVal * rp.minVal, lp.maxVal * rp.maxVal);
        } else {
            return new minMaxPair(lp.minVal / rp.minVal, lp.maxVal / rp.maxVal);
        }
    }

    public static void minMaxEval() {
        String str = "1+2*3+4*5";
        int n = str.length();
        minMaxPair[][] dp = new minMaxPair[n][n];

        minMaxPair res = minMaxEvaluation(str, 0, str.length() - 1, dp);

        System.out.println("MinVal : " + res.minVal);
        System.out.println("MaxVal : " + res.maxVal);
    }

    //==================================================================================================
    //boolean parenthesis
    // https://practice.geeksforgeeks.org/problems/boolean-parenthesization5610/1

    public static class bpair{
        int tCount = 0;
        int fCount = 0;

        bpair(int tCount, int fCount) {
            this.tCount = tCount;
            this.fCount = fCount;
        }

        bpair() {

        }
    }

    public static void Evaluation(bpair lp, bpair rp, char operator, bpair res) {
        int totalTF = (lp.tCount + lp.fCount) * (rp.fCount + rp.tCount);
        if(operator == '|') {
            int totalF = (lp.fCount * rp.fCount);
            res.fCount += totalF;
            res.tCount += totalTF - totalF;
        } else if(operator == '&') {
            int totalT = (lp.tCount * rp.tCount);
            res.tCount += totalT;
            res.fCount += totalTF - totalT;
        } else {
            int totalT = (lp.tCount * rp.fCount) + (lp.fCount * rp.tCount);
            res.tCount += totalT;
            res.fCount += totalTF - totalT;
        }
    }

    public static bpair boolParenthesis(String str, int si, int ei, bpair[][] dp) {
        if(si == ei) {
            int t = str.charAt(si) == 'T' ? 1 : 0;
            int f = str.charAt(si) == 'F' ? 1 : 0;

            bpair base = new bpair(t, f);
            return dp[si][ei] = base;
        }

        if(dp[si][ei] != null) {
            return dp[si][ei];
        }

        bpair ans = new bpair(0, 0);

        for(int cut = si + 1; cut < ei; cut += 2) {
            bpair left = boolParenthesis(str, si, cut - 1, dp);
            bpair right = boolParenthesis(str, cut + 1, ei, dp);

            char operator = str.charAt(cut);
            Evaluation(left, right, operator, ans);
        }

        return dp[si][ei] = ans;
    }

    //================================================================================================================
    //lc 312 burst balloons
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for(int[] d : dp) {
            Arrays.fill(d, -1);
        }
        
        return maxCoins(nums, 0, nums.length - 1, dp);
    }
    
    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if(dp[si][ei] != -1) {
            return dp[si][ei];
        }
        
        int lval = (si == 0) ? 1 : nums[si - 1];
        int rval = (ei == nums.length - 1) ? 1 : nums[ei + 1];
        
        int maxResult = 0;
        for(int cut = si; cut <= ei; cut++) {
            int left = (si == cut) ? 0 : maxCoins(nums, si, cut - 1, dp);
            int right = (ei == cut) ? 0 : maxCoins(nums, cut + 1, ei, dp);
            
            int myResult = left + right + lval * nums[cut] * rval;
            maxResult = Math.max(maxResult, myResult);
        }
        
        return dp[si][ei] = maxResult;
    }

    //=============================================================================================================
    // https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/
    //OBST
    public static int sumOfFreq(int si, int ei, int[] freq) {
        int sum = 0;
        for(int i = si; i <= ei; i++) {
            sum += freq[i];
        }

        return sum;
    }

    public static int OBST_memo(int[] nodes, int[] freq, int si, int ei, int[][] dp) {
        if(dp[si][ei] != -1) {
            return dp[si][ei];
        }

        int minRes = (int)1e9;
        int sum = 0;
        for(int cut = si; cut <= ei; cut++) {
            int leftTree = (cut == si) ? 0 : OBST_memo(nodes, freq, si, cut - 1, dp); //base case handled
            int rightTree = (cut == ei) ? 0 : OBST_memo(nodes, freq, cut + 1, ei, dp); //base case handled

            sum += freq[cut];
            int ans = leftTree + rightTree;
            minRes = Math.min(minRes, ans);
        }

        return dp[si][ei] = minRes + sum;
    }

    public static void obst() {
        int[] nodes = {10, 12, 20};
        int[] freq = {34, 8, 50};
        int n = nodes.length;
        int[][] dp = new int[n][n];

        for(int[] d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(OBST_memo(nodes, freq, 0, n - 1, dp));
    }

    //============================================================================================
    //lc 95
    public static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode() {}
             
             TreeNode(int val) 
             { this.val = val; }
             
             TreeNode(int val, TreeNode left, TreeNode right) {
                 this.val = val;
                 this.left = left;
                 this.right = right;
             }
        }

    public static void generateAllTrees(int num, List<TreeNode> left, List<TreeNode> right, List<TreeNode> ans) {
        if(left.size() != 0 && right.size() != 0) {
            for(int i = 0; i < left.size(); i++) {
                for(int j = 0; j < right.size(); j++) {
                    TreeNode root = new TreeNode(num);
                    root.left = left.get(i);
                    root.right = right.get(j);
                    ans.add(root);
                }
            }
        } else if(left.size() != 0) {
            for(int i = 0; i < left.size(); i++) {
                TreeNode root = new TreeNode(num);
                root.left = left.get(i);
                ans.add(root);
            }
        } else if(right.size() != 0) {
            for(int j = 0; j < right.size(); j++) {
                TreeNode root = new TreeNode(num);
                root.right = right.get(j);
                ans.add(root);
            }
        } else {
            ans.add(new TreeNode(num));
        }
    }
    
    public static List<TreeNode> generateTrees(int si, int ei) {
        List<TreeNode> ans = new ArrayList<>();
        for(int cut = si; cut <= ei; cut++) {
            List<TreeNode> left = generateTrees(si, cut - 1);
            List<TreeNode> right = generateTrees(cut + 1, ei);
            
            generateAllTrees(cut, left, right, ans);
        }
        
        return ans;
    }
    
    public static List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    //======================================================================================================
    public static void main(String[] args) {
        //cutSet01();
        //minMaxEval();
        obst();
    }
}