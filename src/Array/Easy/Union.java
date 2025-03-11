package Array.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Union {

    public static ArrayList<Integer> findUnionUsingTwoPointers(int a[], int b[]) {
        // Iterate both arrays simultaneously and add elements in sorted order accordingly
        int ptr1 = 0;
        int ptr2 = 0;

        // Loop runs till one of the array gets exhausted
        ArrayList<Integer> union = new ArrayList<>();
        while (ptr1 <= a.length - 1 && ptr2 <= b.length - 1) {
            if (a[ptr1] <= b[ptr2]) {
                if (union.isEmpty() || union.get(union.size() - 1) != a[ptr1])
                    union.add(a[ptr1]);
                ptr1++;
            } else {
                if (union.isEmpty() || union.get(union.size() - 1) != b[ptr2])
                    union.add(b[ptr2]);
                ptr2++;
            }
        }
        while (ptr1 <= a.length - 1) {
            if (union.isEmpty() || union.get(union.size() - 1) != a[ptr1])
                union.add(a[ptr1]);
            ptr1++;
        }
        while (ptr2 <= b.length - 1) {
            if (union.isEmpty() || union.get(union.size() - 1) != b[ptr2])
                union.add(b[ptr2]);
            ptr2++;
        }
        return union;
    }

    public static ArrayList<Integer> findUnion(int a[], int b[]) {
        // add your code here
        // The idea is to add all unique elements into a storage and create an array out of it

        Set<Integer> unionSet = new TreeSet<>();

        // Insertion in a TreeSet is O(log N) per element because TreeSet is implemented using a self-balancing BST (Red-Black Tree).
        // Iterate through array 1 and populate the set
        for (int i = 0; i < a.length; i++) {
            unionSet.add(a[i]);
        } // O(n log n)

        // Iterate through array 2 and populate the set
        for (int i = 0; i < b.length; i++) {
            unionSet.add(b[i]);
        } // O(m log m)

        // Construct an array out of the set
        return new ArrayList<>(unionSet); // O(n + m)
    }

}
