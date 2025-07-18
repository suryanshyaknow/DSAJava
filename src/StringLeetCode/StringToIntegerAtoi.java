package StringLeetCode;

public class StringToIntegerAtoi {

    public int myAtoi(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int i = 0, N = s.length();
        int sign = 1;
        long res = 0;

        // Step 1: Ignore leading whites spaces
        while (i < N && s.charAt(i) == ' ')
            i++;

        // Step 2: Read and store the sign
        if (i < N && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Step 3: Conversion
        while (i < N && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            res = res * 10 + digit;

            // Step 4: Check for overflow before adding
            if (res > Integer.MAX_VALUE)
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            i++;
        }
        return (int) (sign * res);
    }
}
