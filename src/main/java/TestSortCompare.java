/**
 * Created by Peonsson and roppe546 on 17/03/16.
 */
public class TestSortCompare {

    public static void main(String[] args) {

        // Merge sort

        Framework framework = new Framework(new MergeSortParallel());

        framework.setDoGC(true);
        framework.setDoSleep(true);
        framework.setNumberOfTests(15);
        framework.setSleepTime(150);

        framework.doTest();

        // Quicksort

        framework.setStrategy(new QuicksortParallel());

        framework.setDoGC(false);
        framework.setDoSleep(false);
        framework.setNumberOfTests(20);

        framework.doTest();

    }
}
