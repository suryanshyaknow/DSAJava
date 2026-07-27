package TwoPointers;

public class ContainerWithMostWater {

    public int trapTwoPointers(int[] height) {
        int N = height.length;

        // Now the idea is to reduce the space to used for
        // precomputing the max left and right heights for each
        // idx, we have to do it also one go to achieve the same.

        // Both could be computed on the go when you implment two pointers.
        // Now about what pointer to move? MOve the pointer pointing to least
        // height because the area at that pointer is determinable since we're
        // to pick the min of left and right max heights

        int leftPtr = 1;
        int leftMaxSeen = height[0];

        int rightPtr = N - 2;
        int rightMaxSeen = height[N - 1];

        int res = 0;
        while (leftPtr <= rightPtr) {
            int bottleneck = Math.min(leftMaxSeen, rightMaxSeen);

            int water = 0;
            if (leftMaxSeen <= rightMaxSeen) {
                // compute for the leftPtr
                water = Math.max(bottleneck - height[leftPtr], 0);

                leftMaxSeen = Math.max(leftMaxSeen, height[leftPtr]);
                leftPtr++;
            } else {
                // compute for the right
                water = Math.max(bottleneck - height[rightPtr], 0);

                rightMaxSeen = Math.max(rightMaxSeen, height[rightPtr]);
                rightPtr--;
            }

            res += water;
        }

        return res;
    }

    public int trap(int[] height) {
        int N = height.length;

        // Now the idea is to reduce the space to used for
        // precomputing the max left and right heights for each
        // idx, we have to do it also one go to achieve the same.

        // Both could be computed on the go when you implment two pointers.
        // Now about what pointer to move? MOve the pointer pointing to least
        // height because the area at that pointer is determinable since we're
        // to pick the min of left and right max heights

        int leftPtr = 1;
        int leftMaxSeen = height[0];

        int rightPtr = N - 2;
        int rightMaxSeen = height[N - 1];

        int res = 0;
        while (leftPtr <= rightPtr) {
            int bottleneck = Math.min(leftMaxSeen, rightMaxSeen);

            int water = 0;
            if (leftMaxSeen <= rightMaxSeen) {
                // compute for the leftPtr
                water = Math.max(bottleneck - height[leftPtr], 0);

                leftMaxSeen = Math.max(leftMaxSeen, height[leftPtr]);
                leftPtr++;
            } else {
                // compute for the right
                water = Math.max(bottleneck - height[rightPtr], 0);

                rightMaxSeen = Math.max(rightMaxSeen, height[rightPtr]);
                rightPtr--;
            }

            res += water;
        }

        return res;
    }

    class Solution {
        public int maxArea(int[] height) {
            int N = height.length;

            // Simply, start computing water from extreme left
            // and extreme right and dynamically update the max
            // water a container could hold.
            // Formula = (rightPtr - leftPtr) * min(hieght[leftPtr], height[rightPtr])

            // Now about which ptr to move? Since the width is always gonna
            // be decreasing, now to get most likely chances to increase the area
            // we should move the pointer which is pointing to shorter height.

            int maxArea = 0;

            int leftPtr = 0;
            int rightPtr = N - 1;

            while (leftPtr < rightPtr) {
                int area = (rightPtr - leftPtr) * Math.min(height[leftPtr], height[rightPtr]);

                maxArea = Math.max(area, maxArea);

                if (height[leftPtr] < height[rightPtr])
                    leftPtr++; // alteast we may get greater height
                else
                    rightPtr--;
            }
            return maxArea;
        }
    }

}
