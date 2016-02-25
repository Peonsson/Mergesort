import java.util.Arrays;

/**
 * Takes an array of ints and sorts it with Merge sort.
 */
public class MergeSort {
    static public void sort(float[] a) {
        if (a.length > 1) {
            float[] b = java.util.Arrays.copyOfRange(a, 0, a.length / 2);
            float[] c = java.util.Arrays.copyOfRange(a, a.length / 2, a.length);

            sort(b);
            sort(c);

            merge(b, c, a);
        }
    }

    static private void merge(float[] a, float[] b, float[] c) {
        int indexA = 0;
        int indexB = 0;
        int indexC = 0;

        while (indexA < a.length && indexB < b.length) {
            if (a[indexA] < b[indexB]) {
                c[indexC++] = a[indexA];
                indexA++;
            } else {
                c[indexC++] = b[indexB];
                indexB++;
            }
        }

        while (indexA < a.length) {
            c[indexC++] = a[indexA];
            indexA++;
        }

        while (indexB < b.length) {
            c[indexC++] = b[indexB];
            indexB++;
        }
    }
}
