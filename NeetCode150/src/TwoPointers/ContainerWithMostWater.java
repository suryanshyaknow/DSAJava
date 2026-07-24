package TwoPointers;

public class ContainerWithMostWater {

    class Solution {
        public int maxArea(int[] height) {
            int N = height.length;

            // Simply, start computing water from extreme left
            // and extreme right and dynamically updatet the max
            // water a container could hold.
            // Formula = (rightPtr - leftPtr) * min(hieght[leftPtr], height[rightPtr])

            // Now about which ptr to move? Since the width is always gonna
            // be decreasingif we move either pointer, so we're gonna make
            // sure that atleast hieght is increasing.

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
