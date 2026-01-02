package Stack.Medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Stack;

public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        int N = asteroids.length;
        int[] nums = asteroids;

        // Now, couple things:
        // - process the collisions. (pos > neg)
        // - process the explosions. (pos =  neg)
        // - only enter neg elements to the stack if st is empty
        // or it already contains neg eles.

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < N; i++) {
            // Add till asteroids are moving towards right only
            if (nums[i] >= 0)
                st.push(nums[i]);
            else {
                // Process the collisions
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(nums[i]))
                    st.pop();

                if (!st.isEmpty() && st.peek() == Math.abs(nums[i]))
                    st.pop();
                else if (st.isEmpty() || st.peek() < 0)
                    st.push(nums[i]);
            }
        }

        // Now reverse the stack into array
        int S = st.size();
        int[] res = new int[S];
        for (int i = S-1; i >=0; i--) {
            res[i] = st.pop();
        }
        return res;

        // Time complexity: O(2N)
        // Space complexity: O(N)
    }

}
