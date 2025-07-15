package Stack.Hard;

public class TrappingRainwater {

    public int trapOptimal(int[] height) {
        int N = height.length;
        // The idea is not to have extra space complexity for prefix and suffix maxes.
        // Rather we're gonna track 'em in real time via two pointers.
        if (N == 0) return 0;

        int leftPtr = 0;
        int rightPtr = N - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        // We're only gonna process the end which has minimum max
        // Because the water you can trap depends on the shorter wall on either side.
        while (leftPtr < rightPtr) {
            if (height[leftPtr] <= height[rightPtr]) {
                if (leftMax > height[leftPtr])
                    water += leftMax - height[leftPtr];
                else
                    leftMax = height[leftPtr];
                leftPtr++;
            } else {
                if (rightMax > height[rightPtr])
                    water += rightMax - height[rightPtr];
                else
                    rightMax = height[rightPtr];
                rightPtr--;
            }
        }
        return water;
    }

    public int trap(int[] height) {
        // The idea is at a given idx find the immediate left max and immediate right max.
        // Choose min of them, and just compute the water stored units.
        // Area(i) = min(leftMax, rightMax) - height[i]

        int N = height.length;

        // Now for left max and right max, we'll have to formulate prefixMax and suffixMax arrays beforehand.
        int[] prefixMax = getPrefixMax(height); // O(N)
        int[] suffixMax = getSuffixMax(height); // O(N)

        int water = 0;

        for (int i = 0; i < N; i++) {
            if (height[i] < prefixMax[i] && height[i] < suffixMax[i]) // 👉 prefixMax[i] and suffixMax[i] are not guaranteed to be taller than height[i]
                water += Integer.min(prefixMax[i], suffixMax[i]) - height[i];
        }
        return water;
    }

    private int[] getSuffixMax(int[] arr) {
        int[] prefixMax = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (i == 0)
                prefixMax[i] = arr[i];
            else
                prefixMax[i] = Integer.max(prefixMax[i - 1], arr[i]);
        }
        return prefixMax;
    }

    private int[] getPrefixMax(int[] arr) {
        int[] suffixMax = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            if (i == arr.length - 1)
                suffixMax[i] = arr[i];
            else
                suffixMax[i] = Integer.max(suffixMax[i + 1], arr[i]);
        }
        return suffixMax;
    }

}
