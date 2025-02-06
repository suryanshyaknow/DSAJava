package TwoPointers;

public class CompareStringContainingBackspaces {

    public static boolean compare(String str1, String str2) {
        // TODO: Write your code here
        int ptr1 = str1.length() - 1;
        int ptr2 = str2.length() - 1;
        while (ptr1 >= 0 || ptr2 >= 0) {
            ptr1 = getNextValidCharIndex(str1, ptr1);
            ptr2 = getNextValidCharIndex(str2, ptr2);
            if (ptr1 < 0 && ptr2 < 0) // Reached at the end of both strings and false hasn't been returned already
                return true;

            if (ptr1 < 0 || ptr2 < 0) // Reached at the end of one string, lengths mismatch after getting rid of '#' from one of the strings
                return false;

            if (str1.charAt(ptr1) != str2.charAt(ptr2))
                return false;

            ptr1--;
            ptr2--;
        }
        return true;
    }

    private static int getNextValidCharIndex(String str, int ptr) {
        int backSpaceCount = 0;
        int i = ptr;
        while (i >= 0) {
            if (str.charAt(i) == '#')
                backSpaceCount += 1; // Incrementing the backspace count
            else if (backSpaceCount > 0 && str.charAt(i) != '#')
                backSpaceCount -= 1; // the char is to be skipped
            else {
                // I.e. backspaceCount == 0 && the char ain't '#'
                break;
            }
            i--;
        }
        return i;
    }

    public static void main(String[] args) {
        String str1 = "xyz#", str2 = "xp#";
        System.out.println(compare(str1, str2));

    }

}
