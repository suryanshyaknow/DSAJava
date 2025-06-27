package BinarySearch;

public class NthRoot {

    public Integer getNthRoot(int n, int m) {
        int low = 1;
        int high = m;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long pow = power(mid, n);

            if (pow == m)
                return mid;
            else if (pow > m) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    private long power(int base, int exp) {
        long res = 1;
        for (int i = 0; i < exp; i++) {
            res *= base;
        }
        return res;
    }
}
