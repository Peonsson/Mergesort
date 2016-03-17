/**
 * Created by Peonsson on 17/03/16.
 */
public class TestStrategy {

    public static void main(String[] args) {

        // Merge sort

        Context context = new Context(new doMergeSort());
        context.sort();

        // Quicksort

        context.setStrategy(new doQuicksort());
        context.sort();
    }
}
