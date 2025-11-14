package DynamicProgramming.PartitionDP;

public class BurstBalloons {

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        int N = arr.length;
        arr[0] = 1;
        arr[N - 1] = 1;
        for (int i = 1; i <= N - 2; i++) {
            arr[i] = nums[i - 1];
        }

        // Since there's neighbors dependency, the sub problems ain't independent.
        // So we gotta think more logically. Instead of picking the first balloon
        // to burst, we're gonna pick the last one.

        return maxCoinsHelper(1, N - 2, arr);
    }

    private static int maxCoinsHelper(int i, int j, int[] arr) {
        if (i > j) return 0;

        int maxCoins = 0;
        // The recursion doesn’t burst k immediately.
        // It tries every possible k (from i to j) as a candidate for being the last — and recursively computes the result if that were true.
        for (int k = i; k <= j; k++) {
            int coins = arr[i - 1] * arr[k] * arr[j + 1] + maxCoinsHelper(i, k - 1, arr) + maxCoinsHelper(k + 1, j, arr);
            maxCoins = Math.max(coins, maxCoins);
        }
        return maxCoins;
    }
}
