import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Takes an array of ints and sorts it with Merge sort.
 */
public class MergeSortParallel extends RecursiveAction {

    private float[] list;

    public MergeSortParallel(float[] list) {
        this.list = list;
    }

    @Override
    protected void compute() {
        if (list.length < 75) { // small enough task, do it
            sort(list);
        } else { // task too large, make subtasks

            float[] left = Arrays.copyOfRange(list, 0, list.length / 2);
            float[] right = Arrays.copyOfRange(list, (list.length / 2), list.length);

            invokeAll(new MergeSortParallel(left), new MergeSortParallel(right));

            merge(left, right, list);
        }
    }

    public void sort(float[] a) {
        if (a.length > 1) {
            float[] b = java.util.Arrays.copyOfRange(a, 0, a.length / 2);
            float[] c = java.util.Arrays.copyOfRange(a, a.length / 2, a.length);

            sort(b);
            sort(c);

            merge(b, c, a);
        }
    }

    private void merge(float[] a, float[] b, float[] c) {
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
