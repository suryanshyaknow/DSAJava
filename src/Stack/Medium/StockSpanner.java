package Stack.Medium;

import java.util.Stack;

public class StockSpanner {

    private static class Pair {
        private int idx;
        private int val;

        public Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    Stack<Pair> st;
    int idx;

    public StockSpanner() {
        st = new Stack<>();
        idx = -1;
    }

    public int next(int price) {
        idx += 1;

        while (!st.isEmpty() && st.peek().val <= price) st.pop();

        int res = idx - (st.isEmpty() ? -1 : st.peek().idx);
        st.push(new Pair(idx, price));
        return res;
    }
}
