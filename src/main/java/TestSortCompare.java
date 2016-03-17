/**
 * Created by Peonsson on 17/03/16.
 */
public class TestSortCompare {

    public static void main(String[] args) {

        // Merge sort

        SortCompare sortCompare = new SortCompare(new doMergeSort());
        sortCompare.sort();

        // Quicksort

        sortCompare.setStrategy(new doQuicksort());
        sortCompare.sort();
    }
}
