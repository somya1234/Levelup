import java.util.Arrays;

public class lcsOfThree {
    public static void main(String[] args) {
        solve();
    }

    /*********************************************************************************************** */

    public static void print(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2d(int[][] arr) {
        for (int[] ar : arr) {
            print(ar);
        }
        System.out.println();
    }

    public static void print3d(int[][][] arr) {
        for (int[][] ar : arr) {
            print2d(ar);
        }
        System.out.println();
    }

    /***************************************************************************/

    public static void solve() {
        String s1 = "geeks";
        String s2 = "geeksfor";
        String s3 = "geeksforgeeks";

        // System.out.println(lcs_rec(s1, s2, s3, 0, 0, 0));

        int x = s1.length();
        int y = s2.length();
        int z = s3.length();
        int[][][] dp = new int[x + 1][y + 1][z + 1];
        for (int[][] d1 : dp) {
            for (int[] d : d1) {
                Arrays.fill(d, -1);
            }
        }
        // System.out.println(lcs_mem(s1, s2, s3, x, y, z, dp));
        // print3d(dp);

        System.out.println(lcs_tab(s1, s2, s3, x, y, z, dp));
        print3d(dp);
    }

    public static int lcs_rec(String s1, String s2, String s3, int i, int j, int k) {
        int x = s1.length();
        int y = s2.length();
        int z = s3.length();

        if (i == x || j == y || k == z)
            return 0;

        char ch1 = s1.charAt(i);
        char ch2 = s2.charAt(j);
        char ch3 = s3.charAt(k);

        if (ch1 == ch2 && ch1 == ch3) {
            return lcs_rec(s1, s2, s3, i + 1, j + 1, k + 1) + 1;
        } else {
            int ans1 = lcs_rec(s1, s2, s3, i + 1, j, k);
            int ans2 = lcs_rec(s1, s2, s3, i, j + 1, k);
            int ans3 = lcs_rec(s1, s2, s3, i, j, k + 1);
            return Math.max(ans1, Math.max(ans2, ans3));
        }
    }

    public static int lcs_mem(String s1, String s2, String s3, int x, int y, int z, int[][][] dp) {
        if (x == 0 || y == 0 || z == 0)
            return dp[x][y][z] = 0;

        if (dp[x][y][z] != -1)
            return dp[x][y][z];

        char ch1 = s1.charAt(x - 1);
        char ch2 = s2.charAt(y - 1);
        char ch3 = s3.charAt(z - 1);

        if (ch1 == ch2 && ch1 == ch3) {
            return dp[x][y][z] = lcs_mem(s1, s2, s3, x - 1, y - 1, z - 1, dp) + 1;
        } else {
            int ans1 = lcs_mem(s1, s2, s3, x - 1, y, z, dp);
            int ans2 = lcs_mem(s1, s2, s3, x, y - 1, z, dp);
            int ans3 = lcs_mem(s1, s2, s3, x, y, z - 1, dp);
            return dp[x][y][z] = Math.max(ans1, Math.max(ans2, ans3));
        }
    }

    public static int lcs_tab(String s1, String s2, String s3, int x, int y, int z, int[][][] dp) {
        int X = x, Y = y, Z = z;
        for (x = 0; x <= X; x++) {
            for (y =0; y <= Y; y++) {
                for (z = 0; z <= Z; z++) {
                    if (x == 0 || y == 0 || z == 0){
                         dp[x][y][z] = 0;
                         continue;
                    }

                    char ch1 = s1.charAt(x - 1);
                    char ch2 = s2.charAt(y - 1);
                    char ch3 = s3.charAt(z - 1);

                    if (ch1 == ch2 && ch1 == ch3) {
                         dp[x][y][z] = dp[x-1][y-1][z-1] + 1;
                    } else {
                        int ans1 = dp[x-1][y][z]; 
                        int ans2 = dp[x][y-1][z]; 
                        int ans3 = dp[x][y][z-1]; 
                        dp[x][y][z] = Math.max(ans1, Math.max(ans2, ans3));
                    }
                }
            }
        }
        return dp[X][Y][Z];
    }

}
