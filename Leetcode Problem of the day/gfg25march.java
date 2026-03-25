import java.util.*;

class Solution {
    public ArrayList<Integer> minHeightRoot(int V, int[][] edges) {
        ArrayList<Integer> result = new ArrayList<>();

        // Edge Case: Sirf ek node hai
        if (V == 1) {
            result.add(0);
            return result;
        }

        // Step 1: Adjacency list + degree array banao
        List<Set<Integer>> adj = new ArrayList<>();
        int[] degree = new int[V];

        for (int i = 0; i < V; i++)
            adj.add(new HashSet<>());

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }

        // Step 2: Pehli baar saari leaves queue mein daalo
        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (degree[i] == 1) {  // leaf node
                leaves.offer(i);
            }
        }

        // Step 3: Leaves trim karte jao jab tak <= 2 nodes bachen
        int remainingNodes = V;

        while (remainingNodes > 2) {
            int leafCount = leaves.size();
            remainingNodes -= leafCount;  // itne nodes remove ho rahe hain

            for (int i = 0; i < leafCount; i++) {
                int leaf = leaves.poll();

                // Is leaf ke neighbor ka degree kam karo
                for (int neighbor : adj.get(leaf)) {
                    degree[neighbor]--;
                    // Agar neighbor ab leaf ban gaya
                    if (degree[neighbor] == 1) {
                        leaves.offer(neighbor);
                    }
                }
            }
        }

        // Step 4: Jo bache hain — wahi answer hai (1 ya 2 nodes)
        result.addAll(leaves);
        return result;
    }
}