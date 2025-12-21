package BinarySearch;

public class AllocateBooks {

    public int findPages(int[] arr, int k) {
        // code here

        // So we could think of the solution by trying out each
        // maximum (max number of pages allowed to each student)
        // and see if we're able to allocate all the books to
        // each student.

        // Now, about the range
        // For each student to be allowed atleast one book we should
        // start the max from the book having the maximum number of pages.
        // And the highest max could be the summation of all the books
        //  given the number of students is 1.
        // We're just tryna define the range here.

        // First off, if number of students exceed the number of books,
        // return -1 since allocation ain't possible.
        int N = arr.length;
        if (N < k) return -1;

        // Find max
        int maxPagesBook = Integer.MIN_VALUE;
        int arrSum = 0;
        for (int i = 0; i < N; i++) {
            maxPagesBook = Math.max(maxPagesBook, arr[i]);
            arrSum += arr[i];
        }

        int low = maxPagesBook;
        int high = arrSum;
        int res = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Potential minimized max number of pages allowed
            int studentCnt = computeNumberOfStudents(arr, mid);

            if (studentCnt <= k) {
                // Try with an even lower maximum
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    private int computeNumberOfStudents(int[] arr, int maxPagesAllowed) {
        int N = arr.length;
        int studentCnt = 1;
        int pagesAllocated = 0;

        for (int i=0; i < N; i++) {
            if (pagesAllocated + arr[i] <= maxPagesAllowed)
                pagesAllocated += arr[i];
            else {
                // Assign to next student
                studentCnt += 1;
                pagesAllocated = arr[i];
            }
        }
        return studentCnt;
    }

    private boolean canWeAllocate(int[] arr, int k, int maxPagesAllowed) {
        int N = arr.length;
        int studentCnt = 1;
        int pagesAllocated = 0;

        for (int i = 0; i < N; i++) {
            if (pagesAllocated + arr[i] <= maxPagesAllowed) {
                pagesAllocated += arr[i];
            } else {
                studentCnt += 1;
                pagesAllocated = arr[i];
            }
        }

        // If an allocation is possible with fewer than k students,
        // then it is always possible with exactly k students
        return studentCnt <= k;
    }

}
