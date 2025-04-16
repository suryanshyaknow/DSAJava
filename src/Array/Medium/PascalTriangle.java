package Array.Medium;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    // Shortcut from (N - 1) C (r - 1) -> N going to
    public List<List<Integer>> generateOptimal(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            res.add(genRow(i));
        }
        return res;
    }

    private static List<Integer> genRow(int rowNum) {
        List<Integer> row = new ArrayList<Integer>();
        int rowEle = 1;
        row.add(rowEle);
        for (int i = 1; i < rowNum; i++) {
            rowEle *= rowNum - i;
            rowEle /= i;
            row.add(rowEle);
        }
        return row;
    }

    public List<List<Integer>> generateBetter(int numRows) {
        // Simply, apply the NCR formula to generate each element
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                row.add(generateRowEle(i, j));
            }
            res.add(row);
        }
        return res;
    }

    private Integer generateRowEle(int rowNum, int colNum) {
        int N = rowNum - 1;
//        int R = colNum - 1;
        int rowEle = 1;
        for (int i = 0; i < colNum; i++) {
            rowEle *= N - i;
            rowEle /= i + 1;
        } // O(R) where R is colIdx
        return rowEle;
    }
}
