package StringLeetCode;

public class RepeatedStringMatch {

    public int repeatedStringMatchRabinKarp(String a, String b) {
        int base = 29;
        int mod = 1_000_000_007;
        int N = a.length();
        int M = b.length();

        // Repeat 'a' until its length >= b.length()
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < M) {
            sb.append(a);
            count++;
        }

        if (containsRabinKarp(sb.toString(), b, base, mod))
            return count;

        // Do one more repetition to see if b would fold
        sb.append(sb);
        count += 1;
        if (containsRabinKarp(sb.toString(), b, base, mod))
            return count;

        return -1;
    }

    private boolean containsRabinKarpOptimal(String a, String b, int base, int mod) {
        // First off, convert String b into hash
        int N = a.length();
        int M = b.length();

        int hashB = 0;
        int hashWindow = 0;
        int basePower = 1; // Needed to remove the leftmost char from the rolling hash
        // Compute hash for String b and initial window of a
        for (int i = 0; i < M; i++) {
            int bVal = b.charAt(i) - 'a';
            int aVal = a.charAt(i) - 'a';

            hashB = (hashB * base + bVal) % mod;
            hashWindow = (hashWindow * base + aVal) % mod;

            if (i != M - 1) basePower = (basePower * base) % mod; // 'M - 1' because power starts off w zero: 0, 1, 2, and so on.
        }

        // Sliding the window: Remove outgoing char from front & Add incoming char at the back
        for (int i = 0; i < N - M + 1; i++) {
            if (hashWindow == hashB && a.substring(i, i + M).equals(b))
                return true;

            if (i < N - M) { // Because if i == N-M, there's no next window to slide to. You're done.
                int frontVal = a.charAt(i) - 'a';
                int backVal = a.charAt(i + M) - 'a';

//                hashWindow = (hashWindow - (frontVal * basePower) % mod + mod) % mod; // Remove front
                hashWindow = (hashWindow - (frontVal * basePower) % mod) % mod; // Remove front
                hashWindow = (hashWindow * base + backVal) % mod;
            }

        }
        return false;
    }

    private boolean containsRabinKarp(String a, String b, int base, int mod) {
        // First off, convert String b into hash
        int N = a.length();
        int M = b.length();

        int hashB = 0;
        // Compute hash for String b
        for (int i = 0; i < M; i++) {
            int val = b.charAt(i) - 'a';
            hashB = (hashB * base + val) % mod;
        }

        // Slide window and compare hash
        for (int i = 0; i < N - M + 1; i++) {
            int hashWindow = 0;
            for (int j = i; j < i + M; j++) {
                int val = a.charAt(j) - 'a';
                hashWindow = (hashWindow * base + val) % mod;
            }

            if (hashWindow == hashB && a.substring(i, i + M).equals(b))
                return true;
        }
        return false;
    }

    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        // Keep repeating until we at least reach b’s length
        while (sb.length() < b.length()) {
            sb.append(a);
            count += 1;
        } // O(M)

        // First check
        if (sb.toString().contains(b)) return count; // O((N + M) * M)

        // Try one more count
        sb.append(a);
        count += 1;
        if (sb.toString().contains(b)) return count; // O((N + M) * M)

        return -1;
        // Space complexity: O(M + N)
    }

}
