package DynamicProgramming.DPOnSubsequences;

public class RodCutting {

    public int cutRod(int[] price) {
        // code here
        // The problem statement basically boils down to either
        // picking a length once (or more than once) or skipping it
        // out it entirely.
        // price[i] denotes the price of i+1th piece
        // price[0] denotes the price of 1 piece
        // We have a constraint of length N cuz obviously cut pieces couldn't
        // make up length greater than the total length of the rod.
        int N = price.length;

        return cutRodHelper(N - 1, price, N);
    }

    private int cutRodHelper(int idx, int[] price, int target) {
        if (target == 0) return 0;

        int rodLength = idx + 1;
        if (idx == 0) {
            return target / 1 * price[idx];
        }

        // If we don't consider the current piece
        int notTake = cutRodHelper(idx - 1, price, target);
        int take = rodLength <= target ? price[idx] + cutRodHelper(idx, price, target - rodLength) : 0;
        return Integer.max(notTake, take);
    }


}
