class leetcode25march {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;    // rows
        int n = grid[0].length; // columns

        // Step 1: Total sum nikalo
        long totalSum = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                totalSum += grid[i][j];

        // Step 2: Agar totalSum odd hai → impossible
        // (but positive integers hain to still check prefix)

        // Step 3: Horizontal Cut Check
        // Row by row prefix sum check karo
        long prefixSum = 0;
        for (int i = 0; i < m - 1; i++) { // last row tak nahi (non-empty!)
            for (int j = 0; j < n; j++) {
                prefixSum += grid[i][j];
            }
            // Kya is row ke baad cut lagane se equal parts milenge?
            if (prefixSum * 2 == totalSum) {
                return true; // ✅
            }
        }

        // Step 4: Vertical Cut Check
        // Column by column prefix sum check karo
        prefixSum = 0;
        for (int j = 0; j < n - 1; j++) { // last col tak nahi (non-empty!)
            for (int i = 0; i < m; i++) {
                prefixSum += grid[i][j];
            }
            // Kya is column ke baad cut lagane se equal parts milenge?
            if (prefixSum * 2 == totalSum) {
                return true; // ✅
            }
        }

        return false; // ❌ Koi valid cut nahi mila
    }
}
