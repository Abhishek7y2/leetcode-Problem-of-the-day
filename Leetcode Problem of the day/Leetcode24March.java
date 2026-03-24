class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int mod = 12345;

        int size = n * m;
        int[] arr = new int[size];

        // Step 1: flatten
        int index = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                arr[index++] = grid[i][j] % mod;
            }
        }

        // Step 2: prefix
        int[] prefix = new int[size];
        prefix[0] = 1;
        for(int i = 1; i < size; i++){
            prefix[i] = (prefix[i - 1] * arr[i - 1]) % mod;
        }

        // Step 3: suffix
        int[] suffix = new int[size];
        suffix[size - 1] = 1;
        for(int i = size - 2; i >= 0; i--){
            suffix[i] = (suffix[i + 1] * arr[i + 1]) % mod;
        }

        // Step 4: result
        int[] result = new int[size];
        for(int i = 0; i < size; i++){
            result[i] = (prefix[i] * suffix[i]) % mod;
        }

        // Step 5: reshape to matrix
        int[][] ans = new int[n][m];
        index = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                ans[i][j] = result[index++];
            }
        }

        return ans;
    }
}