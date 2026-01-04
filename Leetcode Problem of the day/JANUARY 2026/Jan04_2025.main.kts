#!/usr/bin/env kotlin

internal class Solution {
    fun sumFourDivisors(nums: IntArray): Int {
        // Stores final sum of all valid numbers
        var total = 0

        // Loop through each number in the array
        for (x in nums) {
            var cnt = 0 // number of divisors
            var sum = 0 // sum of divisors

            // Try all numbers from 1 to x
            for (d in 1..x) {
                // If d divides x, it's a divisor
                if (x % d == 0) {
                    cnt++ // increment divisor count
                    sum += d // add divisor
                }
            }

            // Add sum only if exactly 4 divisors exist
            if (cnt == 4) total += sum
        }

        // Return the final result
        return total
    }
}