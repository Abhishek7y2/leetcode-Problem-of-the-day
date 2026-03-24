class Leet1594 {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1_000_000_007;
        
        long[][] maxDP = new long[m][n];
        long[][] minDP = new long[m][n];
        
        // Base case - starting point
        maxDP[0][0] = minDP[0][0] = grid[0][0];
        
        // Pehli row fill karo
        for (int j = 1; j < n; j++) {
            maxDP[0][j] = maxDP[0][j-1] * grid[0][j];
            minDP[0][j] = minDP[0][j-1] * grid[0][j];
        }
        
        // Pehla column fill karo
        for (int i = 1; i < m; i++) {
            maxDP[i][0] = maxDP[i-1][0] * grid[i][0];
            minDP[i][0] = minDP[i-1][0] * grid[i][0];
        }
        
        // Baaki cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                
                // Charo candidates
                long a = maxDP[i-1][j] * val; // upar se max
                long b = minDP[i-1][j] * val; // upar se min
                long c = maxDP[i][j-1] * val; // left se max
                long d = minDP[i][j-1] * val; // left se min
                
                maxDP[i][j] = Math.max(Math.max(a,b), Math.max(c,d));
                minDP[i][j] = Math.min(Math.min(a,b), Math.min(c,d));
            }
        }
        
        // Final answer
        if (maxDP[m-1][n-1] < 0) return -1;
        return (int)(maxDP[m-1][n-1] % MOD);
    }
}