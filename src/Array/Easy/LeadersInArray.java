package Array.Easy;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Collections.swap;

public class LeadersInArray {

    private static ArrayList<Integer> leadersOptimal(int arr[]) {
        int N = arr.length;
        ArrayList<Integer> leaders = new ArrayList<>();
        int maxFromRight = Integer.MIN_VALUE;

        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] >= maxFromRight) {
                leaders.add(arr[i]);
                maxFromRight = arr[i];
            }
        }
        reverseArr(leaders);
        return leaders;
    }

    private static void reverseArr(ArrayList<Integer> leaders) {
        int leftPtr = 0;
        int rightPtr = leaders.size() - 1;

        while (leftPtr < rightPtr) {
            swap(leaders, leftPtr, rightPtr);
            leftPtr++;
            rightPtr--;
        }
    }

    private static void swapEle(ArrayList<Integer> arr, int i, int j) {
        int temp  = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    private static ArrayList<Integer> leadersUsingBruteForce(int arr[]) {
        // code here
        int N = arr.length;
        ArrayList<Integer> leaders = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            boolean isLeader = true;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] > arr[i]) {
                    isLeader = false;
                    break; // It ain't a leader
                }
            }
            if (isLeader)
                leaders.add(arr[i]);
        }

        return leaders;
    }

}
