/*You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.

Return this maximum sum.

Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.

 

Example 1:


Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
Example 2:

Example 1 Diagram
Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.
Example 3:


Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.

*/


class Dec23_2025Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs[0].length();
        // dp[i] stores the max length of an increasing subsequence ending at index i
        int[] dp = new int[m];
        int maxKept = 0;

        for (int i = 0; i < m; i++) {
            dp[i] = 1; // Initialize with 1 (the column itself)
            for (int j = 0; j < i; j++) {
                // Check if column j can precede column i
                if (isSorted(strs, j, i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxKept = Math.max(maxKept, dp[i]);
        }

        return m - maxKept;
    }

    private boolean isSorted(String[] strs, int j, int i) {
        for (String s : strs) {
            // Compare characters at index j and i for every string
            if (s.charAt(j) > s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}