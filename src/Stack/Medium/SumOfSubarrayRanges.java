package Stack.Medium;

import java.util.Stack;

public class SumOfSubarrayRanges {

    public long subArrayRanges(int[] nums) {
        int N = nums.length;
        // The idea is to count the contribution of each min/max and accordingly
        // compute the whole range.

        long minSum = getSumSubArrayMins(nums);
        long maxSum = getSumSubArrayMax(nums);

        return maxSum - minSum;
    }

    private long getSumSubArrayMins(int[] nums) {
        int N = nums.length;

        int[] psee = getPSEEArr(nums);
        int[] nse = getNSEArr(nums);

        long sum = 0;
        for (int i = 0; i < N; i++) {
            int left = i - psee[i];
            int right = nse[i] - i;
            sum += (long) left * right * nums[i];
        }
        return sum;
    }

    private int[] getNSEArr(int[] nums) {
        int N = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) st.pop();
            res[i] = st.isEmpty() ? N : st.peek();
            st.push(i);
        }
        return res;
    }

    private int[] getPSEEArr(int[] nums) {
        int N = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[N];

        for (int i = 0; i < N; i++) {
            while (!st.isEmpty() && nums[st.peek()] > nums[i]) st.pop();
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return res;
    }


    private long getSumSubArrayMax(int[] nums) {
        int N = nums.length;

        int[] pgee = getPGEEArr(nums);
        int[] nge = getNGEArr(nums);

        long sum = 0;
        for (int i = 0; i < N; i++) {
            int left = i - pgee[i];
            int right = nge[i] - i;
            sum += (long) left * right * nums[i];
        }
        return sum;
    }

    private int[] getNGEArr(int[] nums) {
        int N = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] <= nums[i]) st.pop();
            res[i] = st.isEmpty() ? N : st.peek();
            st.push(i);
        }
        return res;
    }

    private int[] getPGEEArr(int[] nums) {
        int N = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[N];

        for (int i = 0; i < N; i++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop();
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return res;
    }
}
