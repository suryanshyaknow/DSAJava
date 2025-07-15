package Stack.Medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Stack;

public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        int N = asteroids.length;

        // Upon encountering a -ve element, we continuously have to check
        // for the prev element till the negative ele gets destroyed or the
        // array runs out of elements. So it gives us an intuition of using a
        // stack.

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (asteroids[i] > 0)
                st.push(asteroids[i]);
            else {
                // Process the collisions
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(asteroids[i])) st.pop();

                if (st.peek() == Math.abs(asteroids[i])) st.pop();
                else if (st.isEmpty() || st.peek() < 0) // Only case where -ve elements are going into stack
                    st.push(asteroids[i]);
            }
        }

        int res[] = new int[st.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = st.pop();
        }
        return res;
    }

}
