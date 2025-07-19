package StringLeetCode;

public class VersionString {

    public int compareVersion(String version1, String version2) {
        String v1[] = version1.split("\\.");
        String v2[] = version2.split("\\.");

        int N1 = version1.length();
        int N2 = version2.length();
        int maxLen = Integer.max(N1, N2);

        for (int i = 0; i < maxLen; i++) {
            int n1 = (i >= N1) ? 0 : version1.charAt(i) - '0';
            int n2 = (i >= N2) ? 0: version2.charAt(i) - '0';

            if (n1 < n2) return -1;
            else if (n1 > n2) return 1;
        }
        return 0;
    }
}
