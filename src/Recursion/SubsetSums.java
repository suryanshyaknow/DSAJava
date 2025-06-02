package Recursion;

import java.util.ArrayList;

public class SubsetSums {

    public ArrayList<Integer> subsetSums(int[] arr) {
        // code here
        ArrayList<Integer> sumSubset = new ArrayList<>();
        func(0, 0, arr, sumSubset);
        return sumSubset;

        // Time Complexity: 2^N cuz at every recursive call there are two choices.
        // And if we oughta sort the array then it'll be: 2^N + 2^N Log 2^N
        // Space Complexity: 2^N
    }

    void func(int idx, int sum, int[] arr, ArrayList<Integer> sumSubset) {
        int N = arr.length;

        if (idx >= N) {
            sumSubset.add(sum);
            return;
        }

        // Pick the fking ele
        func(idx+1, sum + arr[idx], arr, sumSubset);

        // Don't pick the fking ele
        func(idx+1, sum, arr, sumSubset);
    }

}
