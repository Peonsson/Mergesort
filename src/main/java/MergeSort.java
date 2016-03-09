import java.util.Arrays;

/**
 * Takes an array of ints and sorts it with Merge sort.
 */
public class MergeSort {
    static public void sort(float[] a, int first, int last) {
        if (last - first > 1) {
            int mid = first + (last - first) / 2;
            sort(a, first, mid);
            sort(a, mid, last);
            merge(a, first, mid, last);
        }
    }

    static private void merge(float[] list, int first, int mid, int last) {
        int indexA = first;
        int indexB = mid;
        int indexTemp = 0;

        float[] temp = new float[last - first];

        while (indexA < mid && indexB < last) {
            if (list[indexA] < list[indexB]) {
                temp[indexTemp++] = list[indexA];
                indexA++;
            } else {
                temp[indexTemp++] = list[indexB];
                indexB++;
            }
        }

        while (indexA < mid) {
            temp[indexTemp++] = list[indexA];
            indexA++;
        }

        while (indexB < last) {
            temp[indexTemp++] = list[indexB];
            indexB++;
        }

        System.arraycopy(temp, 0, list, first, temp.length);
    }
}
