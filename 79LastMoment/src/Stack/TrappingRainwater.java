package Stack;

import java.util.Stack;

public class TrappingRainwater {

    public int trapOptimal(int[] height) {
        int N = height.length;

        // We're gonna process the two pointers
        // and keep track of left and right maxes.
        // Also, note that, we're gonna move the
        // smaller ptr first cuz we need shorter
        // wall's height to compute the logged water.

        int leftPtr = 0;
        int leftMax = 0;
        int rightMax = 0;
        int rightPtr = N-1;
        int res = 0;

        while (leftPtr < rightPtr) {
            if (height[leftPtr] <= height[rightPtr]) {
                // We could only process if left max wall is greater than current
                if (height[leftPtr] < leftMax)
                    res += leftMax - height[leftPtr];
                else
                    leftMax = height[leftPtr];
                leftPtr++;
            } else {
                if (height[rightPtr] < rightMax)
                    res += rightMax - height[rightPtr];
                else
                    rightMax = height[rightPtr];
                rightPtr--;
            }
        }
        return res;
    }

    public int trap(int[] height) {
        int N = height.length;

        // Simply, for a give building to compute the
        // logged, we gotta figure out the min of its
        // left and right height, and compute the eff.
        // height. That's all!
        // right max height ain't nothing but NGE // WRONG CLAIM!
        // left max height ain't nothing but PGE // WRONG CLAIM!

        int[] sufMax = getSufMax(height); // O(N)
        int[] prefMax = getPrefMax(height); // O(N)

        int res = 0;
        // Iterate over each building and compute the water logged
        for (int i = 0; i < N; i++) {
            int h = height[i];
            if (h < prefMax[i] && h < sufMax[i])
                res += Math.min(prefMax[i], sufMax[i]) - h;
        }

        return res;

        // Note: Rainwater depends on the maximum wall available on each side,
        // not the nearest wall. Thats's why pge and nge won't work. We'll be
        // undercounting the water.

        // Time complexity: O(3N)
        // Space complexity: O(2N)
    }

    private static int[] getPrefMax(int[] arr) {
        int N = arr.length;

        int[] res = new int[N];
        res[0] = arr[0]; // prefMax of left extereme would be it itself
        for (int i = 1; i < N; i++) {
            res[i] = Math.max(res[i - 1], arr[i]);
        }
        return res;
    }

    private static int[] getSufMax(int[] arr) {
        int N = arr.length;

        int[] res = new int[N];
        res[N-1] = arr[N-1]; // prefMax of left extereme would be it itself
        for (int i = N-2; i >= 0; i--) {
            res[i] = Math.max(res[i + 1], arr[i]);
        }
        return res;
    }

    private static int[] getPGE(int[] arr) {
        int N = arr.length;

        int[] pge = new int[N];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < N; i++) {
            int ele = arr[i];
            while (!st.isEmpty() && st.peek() <= ele)
                st.pop();
            pge[i] = !st.isEmpty() ? st.peek() : -1;
            st.add(ele);
        }

        return pge;

        // Time complexity: O(2N)
    }

    private static int[] getNGE(int[] arr) {
        int N = arr.length;

        int[] nge = new int[N];
        Stack<Integer> st = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            int ele = arr[i];
            while (!st.isEmpty() && st.peek() <= ele)
                st.pop();
            nge[i] = !st.isEmpty() ? st.peek() : -1;
            st.add(ele);
        }

        return nge;
    }
}
