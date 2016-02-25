import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Takes an array of ints and sorts it with Merge sort.
 */
public class MergeSortParallel extends RecursiveTask<Void> {

    private float[] list;

    public MergeSortParallel(float[] list) {
        this.list = list;
    }

    @Override
    protected Void compute() {
        if (list.length < 14) { // small enough task, do it (14 not finalized)
            sort(list);
        }
        else { // task too large, make subtasks
            float[] b = Arrays.copyOfRange(list, 0, list.length / 2);
            float[] c = Arrays.copyOfRange(list, list.length / 2, list.length);

            MergeSortParallel leftTask = new MergeSortParallel(b);
            MergeSortParallel rightTask = new MergeSortParallel(c);

            invokeAll(leftTask, rightTask);
            merge(b, c, list);
        }

        return null;
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
