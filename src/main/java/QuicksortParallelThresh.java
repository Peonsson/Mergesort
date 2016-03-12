import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Takes an array of floats and sorts it with parallel quicksort. Used when finding optimal threshold.
 *
 * Created by Peonsson & roppe546 on 2016-02-25.
 */
public class QuicksortParallelThresh extends RecursiveAction {

    private float[] list;
    private int first;
    private int last;
    private int threshold;

    public QuicksortParallelThresh(float[] list, int first, int last, int threshold) {
        this.list = list;
        this.first = first;
        this.last = last;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (last - first < threshold) { // small enough task, do it
            Arrays.sort(list, first, last + 1);
        }
        else { // task too large, make subtasks
            int pivot = partition();
            invokeAll(new QuicksortParallelThresh(list, first, pivot - 1, threshold), new QuicksortParallelThresh(list, pivot + 1, last, threshold));
        }
    }

    private int partition() {
        float pivVal = list[first];
        int up = first;
        int down = last;

        do {
            // Increment up (starting at first) until an element
            // that is larger than pivVal is found.
            while (list[up] <= pivVal && up < last) {
                up++;
            }

            // Increment down (starting at last) until an element
            // smaller or equal to pivVal is found
            while (list[down] > pivVal && down >= first) {
                down--;
            }

            if (up < down) {
                // Swap place of up and down (if up is to the left of
                // down)
                float temp = list[up];
                list[up] = list[down];
                list[down] = temp;
            }
        } while (up < down);

        // Swap place of pivot (first element) and
        // down
        float temp = list[first];
        list[first] = list[down];
        list[down] = temp;

        return down;
    }
}
