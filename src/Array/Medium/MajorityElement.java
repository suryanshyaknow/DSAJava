package Array.Medium;

import java.util.HashMap;

public class MajorityElement {

    public static int majorityElementViaMooresVotingAlgorithm(int[] arr) {
        // The key idea is pairwise cancellation. If an element occurs more than half the time,
        // it means it outnumbers all the other elements combined. Even if we cancel it with other
        // elements, it will still be left standing by the end.
        int N = arr.length;
        int majEle = arr[0];
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (arr[i] == majEle) {
                count += 1;
            } else if (arr[i] != majEle)
                count -= 1;

            if (count == 0) {
                majEle = arr[i];
                count += 1;
            }
        }
        // Verify if majEle at the end of the loop is indeed the majority element
        int majEleCount = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] == majEle)
                majEleCount++;
        }
        if (majEleCount > N / 2)
            return majEle;
        else
            return -1;
    }

    public static int majorityElement(int[] arr) {
        int N = arr.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i], 0) + 1);
        }

        System.out.println(hashMap);
        System.out.println(N / 2);
        int majorityEle = -1;
        for (int ele : hashMap.keySet()) {
            if (hashMap.get(ele) > N / 2) {
                majorityEle = ele;
                break;
            }
        }
        return majorityEle;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{2, 0, 1};
        int[] arr = new int[]{3, 2, 3};
        majorityElement(arr);
    }

}
