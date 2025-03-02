package String;

public class ZigZag {

    // Convert a given string into a zigzag pattern with a given number of rows and then read it row by row.

    public static String convertZigZag(String s, int numRows) {
        if (numRows == 1) return s; // No zigzag if only one row

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder(); // Initialize each row
        }

        int i = 0, step = 1; // i tracks row index, step is direction (1: down, -1: up)
        for (char c : s.toCharArray()) {
            rows[i].append(c);
            if (i == 0) step = 1; // Change the dir to down
            if (i == numRows - 1) step = -1; // Change the dir to up

            i += step;
        }
        StringBuilder newRes = new StringBuilder();
        for (StringBuilder row : rows) {
            newRes.append(row);
        }
        return newRes.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convertZigZag(s, numRows));
    }


}
