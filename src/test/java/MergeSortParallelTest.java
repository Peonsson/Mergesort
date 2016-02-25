import org.junit.Test;

/**
 * Created by Peonsson on 25/02/16.
 */
public class MergeSortParallelTest {


    @Test
    public void testMergeSortSort() throws Exception {

        float[] inputArr = {45.5f, 23.0f, 11.2f, 89.9f, 77.3f, 98.1f, 4.4f, 28.01f, 65.2f, 43.8f};

        MergeSortParallel.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testMergeSortSortOneElement() throws Exception {

        float[] inputArr = {45.5f};

        MergeSortParallel.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testMergeSortSortEmptyArray() throws Exception {

        float[] inputArr = {};

        MergeSortParallel.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testMergeSortSortMixedPositiveNegative() throws Exception {

        float[] inputArr = {-45.5f, 23.0f, -11.2f, 89.9f, -77.3f, 98.1f, -4.4f, 28.01f, -65.2f, 43.8f};

        MergeSortParallel.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testMergeSortSortOnlyNegative() throws Exception {

        float[] inputArr = {-45.5f, -23.0f, -11.2f, -89.9f, -77.3f, -98.1f, -4.4f, -28.01f, -65.2f, -43.8f};

        MergeSortParallel.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }
}
