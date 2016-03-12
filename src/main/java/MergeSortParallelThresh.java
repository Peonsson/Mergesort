import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Merge sort parallel.
 *
 * Created by Peonsson & roppe546 on 2016-03-16.
 */
public class MergeSortParallelThresh extends RecursiveAction {

    private float[] list;
    private int first;
    private int last;
    private int threshold;

    public MergeSortParallelThresh(float[] list, int first, int last, int threshold) {
        this.list = list;
        this.first = first;
        this.last = last;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (last - first < threshold) { // small enough task, do it
            Arrays.sort(list, first, last);
        }
        else { // task too large, make subtasks
            int mid = first + (last - first) / 2;
            invokeAll(new MergeSortParallelThresh(list, first, mid, threshold), new MergeSortParallelThresh(list, mid, last, threshold));
            merge(mid);
        }
    }

    private void merge(int mid) {
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