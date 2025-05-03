package GreedyAlgorithm.Easy;

import java.util.ArrayList;
import java.util.List;

public class MinimumNumberOfCoins {

    static List<Integer> minPartition(int N) {
        // code here
        int[] deno = {1, 2, 5, 10, 20, 50, 100, 200, 500, 2000};
        int D = deno.length;
        List<Integer> res = new ArrayList<>();
        int remN = N;

        // The idea is to always pick the largest possible denomination
        // as long as it can be subtracted from the amount.

        for (int i = D - 1; i >= 0; i--) {
            while (deno[i] <= remN) { // While cuz we have to repeatedly use the same note till its val allows
                remN -= deno[i];
                res.add(deno[i]);
            }
        }

        return res;
    }

}
