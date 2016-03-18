/**
 * Created by Peonsson on 17/03/16.
 */
public class TestSortCompare {

    public static void main(String[] args) {

        // Merge sort

        Framework framework = new Framework(new MergeSortParallel());
        framework.doTest();

        // Quicksort

        framework.setStrategy(new QuicksortParallel());
        framework.doTest();

    }
}
