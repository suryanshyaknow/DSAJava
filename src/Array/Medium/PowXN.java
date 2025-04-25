package Array.Medium;

public class PowXN {

    public double myPow(double x, int n) {
        double ans = 1;
        long N = n;
        if (N < 0)
            N = -N;

        while (N > 0) {
            if (N % 2 == 0) {
                x = x * x;
                N = N / 2;
            } else {
                ans *= x;
                N = N - 1;
            }
        }
        if (n < 0) { // to begin with
            ans = 1 / ans;
        }

        return ans;
    }

}
