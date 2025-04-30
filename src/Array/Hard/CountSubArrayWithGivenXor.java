package Array.Hard;

import java.util.HashMap;

public class CountSubArrayWithGivenXor {

    public long subarrayXor(int arr[], int k) {
        // code here
        int N = arr.length;

        // WE can't just pick an array outta mid and say its XOR is k.
        // Gotta reverse engineer. Say, upto the current idx, the XOR is XR..
        // then to find the subarrays having XOR 'k', we oughta find subarrays
        // starting from idx 0 w XOR of XR ^ k
        // x ^ k = XR
        // x ^ k ^ K = XR ^ k
        // x = XR ^ k

        int xr = 0;
        int count = 0;
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            xr ^= arr[i];

            if (xr == k) {
                count += 1;
            }

            int preXor = xr ^ k;
            if (freqMap.containsKey(preXor)) {
                count += freqMap.get(preXor);
            }

            // Anyway put the xor into hashMap w updated freq
            freqMap.put(xr, freqMap.getOrDefault(xr, 0) + 1);
        }
        return count;
    }

}
