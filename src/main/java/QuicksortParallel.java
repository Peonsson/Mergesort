import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Peonsson & roppe546 on 25/02/16.
 */
public class QuicksortParallel extends RecursiveAction {

    private float[] list;
    private int first;
    private int last;
    private int threshold = 100000;

    public QuicksortParallel(float[] list, int first, int last) {
        this.list = list;
        this.first = first;
        this.last = last;
    }

    @Override
    protected void compute() {
        if (last - first < threshold) { // small enough task, do it
            Arrays.sort(list, first, last + 1);
        }
        else { // task too large, make subtasks
            int pivot = partition();
            invokeAll(new QuicksortParallel(list, first, pivot - 1), new QuicksortParallel(list, pivot + 1, last));
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
