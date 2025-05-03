package GreedyAlgorithm.Easy;

public class LemonadeChange {

    public static boolean lemonadeChange(int[] bills) {
        int N = bills.length;

        int available = 0;
        for (int i = 0; i < N; i++) {
            if (bills[i] == 5) {
                // No change to be returned
                available += bills[i];
            } else {
                // $5, $10, $20
                int change = bills[i] - 5; // To be returned
                if (available >= change) {
                    available -= change;
                } else {
                    // Can't do change
                    return false;
                }
                available += bills[i];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] bills = {5, 5, 10, 10, 20};
        System.out.println(lemonadeChange(bills));
    }

}
