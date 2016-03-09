import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Takes an array of ints and sorts it with Merge sort.
 */
public class MergeSortParallelThresh extends RecursiveAction {

    private float[] list;
    private int start;
    private int end;
    private int threshold;

    public MergeSortParallelThresh(float[] list, int start, int end, int threshold) {
        this.list = list;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    public MergeSortParallelThresh(float[] list, int threshold) {
        this.list = list;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (end - start <= threshold) { // small enough task, do it
            Arrays.sort(list, start, end);
        }
        else { // task too large, make subtasks
            int mid = start + (end - start) / 2;
            invokeAll(new MergeSortParallelThresh(list, start, mid, threshold), new MergeSortParallelThresh(list, mid, end, threshold));
            merge(mid);
        }
    }

    private void merge(int mid) {
        int indexA = start;
        int indexB = mid;
        int indexTemp = 0;

        float[] temp = new float[end - start];

        while (indexA < mid && indexB < end) {
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

        while (indexB < end) {
            temp[indexTemp++] = list[indexB];
            indexB++;
        }

        System.arraycopy(temp, 0, list, start, temp.length);
    }
}
