/*
public class Dec27_2025 {
}
*//*
2402. Meeting Rooms III
        Solved
        Hard
        Topics
        premium lock icon
        Companies
        Hint
        You are given an integer n. There are n rooms numbered from 0 to n - 1.

        You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be held during the half-closed time interval [starti, endi). All the values of starti are unique.

        Meetings are allocated to rooms in the following manner:

        Each meeting will take place in the unused room with the lowest number.
        If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the same duration as the original meeting.
        When a room becomes unused, meetings that have an earlier original start time should be given the room.
        Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.

        A half-closed interval [a, b) is the interval between a and b including a and not including b.



        Example 1:

        Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
        Output: 0
        Explanation:
        - At time 0, both rooms are not being used. The first meeting starts in room 0.
        - At time 1, only room 1 is not being used. The second meeting starts in room 1.
        - At time 2, both rooms are being used. The third meeting is delayed.
        - At time 3, both rooms are being used. The fourth meeting is delayed.
        - At time 5, the meeting in room 1 finishes. The third meeting starts in room 1 for the time period [5,10).
        - At time 10, the meetings in both rooms finish. The fourth meeting starts in room 0 for the time period [10,11).
        Both rooms 0 and 1 held 2 meetings, so we return 0.
        Example 2:

        Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
        Output: 1
        Explanation:
        - At time 1, all three rooms are not being used. The first meeting starts in room 0.
        - At time 2, rooms 1 and 2 are not being used. The second meeting starts in room 1.
        - At time 3, only room 2 is not being used. The third meeting starts in room 2.
        - At time 4, all three rooms are being used. The fourth meeting is delayed.
        - At time 5, the meeting in room 2 finishes. The fourth meeting starts in room 2 for the time period [5,10).
        - At time 6, all three rooms are being used. The fifth meeting is delayed.
        - At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts in room 1 for the time period [10,12).
        Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return 1.*/


import java.util.*;

class Dec27_2025 {
    public int mostBooked(int n, int[][] meetings) {

        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // Free rooms (smallest number first)
        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();

        // Busy rooms: [endTime, roomNumber]
        PriorityQueue<long[]> busyRooms =
                new PriorityQueue<>((a, b) ->
                        a[0] == b[0] ? (int)(a[1] - b[1]) : (int)(a[0] - b[0]));

        // Count meetings per room
        int[] count = new int[n];

        // Initially all rooms are free
        for (int i = 0; i < n; i++) {
            freeRooms.add(i);
        }

        // Process each meeting
        for (int[] meet : meetings) {

            long start = meet[0];
            long end = meet[1];
            long duration = end - start;

            // Free rooms that have finished
            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= start) {
                freeRooms.add((int) busyRooms.poll()[1]);
            }

            // If a free room exists
            if (!freeRooms.isEmpty()) {
                int room = freeRooms.poll();
                busyRooms.add(new long[]{end, room});
                count[room]++;
            }
            // Otherwise delay the meeting
            else {
                long[] earliest = busyRooms.poll();
                long newEnd = earliest[0] + duration;
                busyRooms.add(new long[]{newEnd, earliest[1]});
                count[(int) earliest[1]]++;
            }
        }

        // Find room with maximum meetings
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[ans]) {
                ans = i;
            }
        }

        return ans;
    }
}
