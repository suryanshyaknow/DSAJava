package GreedyAlgorithm.Easy;

import java.util.Arrays;

public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        int G = g.length;
        int S = s.length;

        int leftPtr = 0; // for cookies
        int rightPtr = 0; // for children

        // Sort the arrays
        Arrays.sort(g);
        Arrays.sort(s);

        while (leftPtr < S && rightPtr < G) {
            // We can only assign a cookie if its size is greater than equal to a child's greed
            if (s[leftPtr] >= g[rightPtr]) {
                rightPtr++;
            }
            leftPtr++;
        }
        return rightPtr; // Because rightPtr would either be standing at the latest unsatisfied kid or outta the array
    }

}
