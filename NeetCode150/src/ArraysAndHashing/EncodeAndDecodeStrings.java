package ArraysAndHashing;

import java.util.*;

public class EncodeAndDecodeStrings {

    public String encode(String arr[]) {
        // write your logic to encode the strings

        StringBuilder sb = new StringBuilder();
        // Iterate over each string and process
        for (String str : arr) {
            int len = str.length();
            sb.append(len).append("/:").append(str);
        }

        return sb.toString();
    }

    public ArrayList<String> decode(String s) {
        // write your logic to decode the string
        ArrayList<String> res = new ArrayList<>();

        // See, at the time of encoding we were made to
        // store the length of each string, so it should
        // mean something.
        int i = 0;
        int N = s.length();

        while (i < N) {

            // Read the len till you encounter '/'
            int len = 0;
            while (s.charAt(i) != '/') {
                len = len * 10 + s.charAt(i) - '0';
                i++;
            }

            // Skip the delimiters
            i += 2;

            // Now, store the string of len into res from the curr idx
            res.add(s.substring(i, i + len));

            i = i + len;
        }

        return res;
    }

//    public ArrayList<String> decode(String s) {
//        // write your logic to decode the string
//        ArrayList<String> res = new ArrayList<>();
//
//        // See, at the time of encoding we were made to
//        // store the length of each string, so it should
//        // mean something.
//        int i = 0;
//        int N = s.length();
//
//        while (i < N) {
//            char ch = s.charAt(i);
//
//            // NOw if the char is digit and the following two chars are
//            // ':' & '/'
//            if (Character.isDigit(ch)
//                    && (i + 1 < N) && s.charAt(i + 1) == ':'
//                    && (i + 2 < N) && s.charAt(i + 2) == '/') {
//                int len = s.charAt(i) - '0';
//                StringBuilder sb = new StringBuilder(N);
//                for (int j = 0; j < len; j++) {
//                    sb.append(s.charAt(i + j));
//                }
//                res.add(sb.toString());
//                i = i + len;
//            } else {
//                i++;
//            }
//        }
//
//        return res;
//    }
}
