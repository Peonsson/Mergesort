/**
 * Created by Peonsson on 17/03/16.
 */
public class TestSortCompare {

    public static void main(String[] args) {

        // Merge sort

        SortCompare sortCompare = new SortCompare(new DoMergeSort());
        sortCompare.sort();

        // Quicksort

        sortCompare.setStrategy(new DoQuicksort());
        sortCompare.sort();
    }
}
