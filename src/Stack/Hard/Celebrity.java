package Stack.Hard;

public class Celebrity {

    public int celebrityOptimal(int mat[][]) {
        int N = mat.length;
        // Idea: Determine celebrity via elimination

        int top = 0;
        int bottom = N - 1;

        while (top < bottom) {
            if (mat[top][bottom] == 1) top += 1; // cuz top knows bottom and thereby can't be a celeb
            else if (mat[bottom][top] == 1) bottom -= 1;
            else {
                top += 1;
                bottom -= 1;
            }
        }

        if (top > bottom) return -1;

        // Confirm if top is indeed the celeb
        for (int i = 0; i < N; i++) {
            if (i == top) continue;
            if (mat[top][i] == 0 && mat[i][top] == 1) {
                continue;
            } else {
                return -1;
            }
        }
        return top;
    }

    public int celebrity(int mat[][]) {
        // code here
        int N = mat.length;

        int[] knowMeArr = new int[N];
        int[] iKnowArr = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue; // Makes no sense
                if (mat[i][j] == 1) {
                    iKnowArr[i] += 1; // 'i' knows someone
                    knowMeArr[j] += 1; // 'j' is known by someone
                }
            }
        }

        // The Celebrity should know no one
        for (int i = 0; i < N; i++) {
            if (iKnowArr[i] == 0 && knowMeArr[i] == N - 1)
                return i;
        }

        return -1;

        // Time complexity: O(N^2) + O(N)
        // Space complexity: O(2N)
    }


}
