import org.junit.Test;

/**
 * Created by Peonsson on 24/02/16.
 */

/**
 * Tries to sort an array of ints.
 */
public class MergeSortTest {

    @Test
    public void testMergeSortSort() throws Exception {

        int[] inputArr = {45, 23, 11, 89, 77, 98, 4, 28, 65, 43};

        MergeSort.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }
        assert (isSorted);
    }
}