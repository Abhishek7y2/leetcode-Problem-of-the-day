import java.util.*;

class Solution {
    public boolean canFinish(int n, int[][] prerequisites) {

        // Step 1: Build adjacency list and indegree array
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course); // prereq → course
            indegree[course]++;           // course needs one more prereq
        }

        // Step 2: Add all courses with 0 prerequisites to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: BFS - Kahn's Algorithm
        int count = 0; // tracks how many courses we finish

        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++; // finished this course!

            // Reduce indegree of dependent courses
            for (int next : adj.get(course)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next); // now this course is ready!
                }
            }
        }

        // Step 4: If we finished all n courses → no cycle
        return count == n;
    }
}