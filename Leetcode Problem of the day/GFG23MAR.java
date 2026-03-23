
import java.util.Arrays;

class GFG23MAR {
    public int longestCycle(int V, int[][] edges) {
        
        // Step 1: Adjacency array banao
        // Har node ka sirf ek outgoing edge hai
        int[] next = new int[V];
        Arrays.fill(next, -1); // -1 = koi edge nahi
        
        for (int[] e : edges) {
            next[e[0]] = e[1];
        }
        
        // Step 2: Visit time array
        // -1 = abhi tak visit nahi hua
        int[] visitTime = new int[V];
        Arrays.fill(visitTime, -1);
        
        int ans = -1;
        int timer = 0;
        
        // Step 3: Har unvisited node se DFS
        for (int i = 0; i < V; i++) {
            
            if (visitTime[i] != -1) continue; // already visited
            
            int startTime = timer; // is DFS ka start time
            int cur = i;
            
            // Step 4: Path follow karo
            while (cur != -1) {
                
                // Pehle se visit hua koi node
                if (visitTime[cur] != -1) {
                    
                    // Check: Is current DFS mein tha?
                    if (visitTime[cur] >= startTime) {
                        // ✅ Cycle mili!
                        ans = Math.max(ans, timer - visitTime[cur]);
                    }
                    break;
                }
                
                // Pehli baar visit kar rahe hain
                visitTime[cur] = timer++;
                cur = next[cur]; // agle node pe jao
            }
        }
        
        return ans;
    }
}