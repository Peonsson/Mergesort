
/**
 * Takes an array of floats and sorts it with serial quicksort.
 *
 * Created by Peonsson & roppe546 on 2016-02-25.
 */
public class Quicksort {

    static public void sort(float[] a) {
        sort(a, 0, a.length - 1);
    }

    static private void sort(float[] a, int first, int last) {
        if (first < last) {
            int pivIndex = partition(a, first, last);

            sort(a, first, pivIndex - 1);
            sort(a, pivIndex + 1, last);
        }
    }

    static private int partition(float[] a, int first, int last) {
        float pivVal = a[first];
        int up = first;
        int down = last;

        do {
            // Increment up (starting at first) until an element
            // that is larger than pivVal is found.
            while (a[up] <= pivVal && up < last) {
                up++;
            }

            // Increment down (starting at last) until an element
            // smaller or equal to pivVal is found
            while (a[down] > pivVal && down >= first) {
                down--;
            }

            if (up < down) {
                // Swap place of up and down (if up is to the left of
                // down)
                float temp = a[up];
                a[up] = a[down];
                a[down] = temp;
            }
        } while (up < down);

        // Swap place of pivot (first element) and
        // down
        float temp = a[first];
        a[first] = a[down];
        a[down] = temp;

        return down;
    }
}
