package Arrays;

import java.util.HashMap;

public class CountSubArraysWithXOR {

    public long subarrayXor(int arr[], int k) {
        // code here
        int N = arr.length;
        long cnt = 0;

        // x ^ k = xr
        // x ^ k ^ k = xr ^ k
        // x = xr ^ k

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int xr = 0;
        for (int i=0; i < N; i++) {
            xr ^= arr[i];

            if (xr == k) cnt += 1;

            // Check if x already exists in hashMap
            // that'd mean a subarray w xor k exists as well
            int x = xr ^ k;
            cnt += hashMap.getOrDefault(x, 0);

            hashMap.put(xr, hashMap.getOrDefault(xr, 0) + 1);
        }
        return cnt;
    }

    public long subarrayXorBruteForce(int arr[], int k) {
        // code here
        int N = arr.length;
        long cnt = 0;

        // Generate all subarrays and compute their k
        for (int i = 0; i < N; i++) {
            int xor = 0;
            for (int j = i; j < N; j++) {
                xor ^= arr[j];

                if (xor == k) cnt += 1;
            }
        }
        return cnt;
    }
}
