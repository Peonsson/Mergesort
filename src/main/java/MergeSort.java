/**
 * Created by Peonsson on 24/02/16.
 */

/**
 * Takes an array of ints and sorts it with Merge sort.
 */
public class MergeSort {

    private static int[] arr;

    public static void sort(int[] myList) {

        arr = myList;
        doSort(0, arr.length - 1);
    }

    private static void doSort(int low, int high) {

        if (low < high) {

            int mid = low + (high - low) / 2;

            doSort(low, mid);
            doSort(mid + 1, high);
            merge(low, mid, high);
        }
    }

    private static void merge(int low, int mid, int high) {

        int[] tempArr = new int[arr.length];

        for (int i = low; i <= high; i++) {
            tempArr[i] = arr[i];
        }
    }
}
